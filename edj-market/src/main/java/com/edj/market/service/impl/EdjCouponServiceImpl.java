package com.edj.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.api.api.market.dto.CouponUseDTO;
import com.edj.api.api.market.vo.AvailableCouponVO;
import com.edj.api.api.market.vo.CouponUseVO;
import com.edj.api.api.user.UserApi;
import com.edj.api.api.user.dto.UserDTO;
import com.edj.cache.helper.LockHelper;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.DBException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.*;
import com.edj.market.domain.dto.CouponPageDTO;
import com.edj.market.domain.dto.GrabCouponDTO;
import com.edj.market.domain.entity.EdjActivity;
import com.edj.market.domain.entity.EdjCoupon;
import com.edj.market.domain.entity.EdjCouponWriteOff;
import com.edj.market.domain.vo.ActivityInfoVO;
import com.edj.market.domain.vo.CouponPageVO;
import com.edj.market.enums.EdjCouponStatus;
import com.edj.market.mapper.EdjActivityMapper;
import com.edj.market.mapper.EdjCouponMapper;
import com.edj.market.mapper.EdjCouponWriteOffMapper;
import com.edj.market.service.EdjCouponService;
import com.edj.market.utils.CouponUtils;
import com.edj.mysql.utils.PageUtils;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static com.edj.cache.constants.CacheConstants.CacheName.*;
import static com.edj.cache.helper.LockHelper.COMPLEX_OPERATION_WAIT_TIME;
import static com.edj.common.constants.CommonRedisConstants.Lock.GRAB_COUPON_LOCK;
import static com.edj.market.constants.RedisConstants.LIMIT;
import static com.edj.market.constants.RedisConstants.QUEUE_NUM;

/**
 * 针对表【edj_coupon(用户优惠券表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EdjCouponServiceImpl extends MPJBaseServiceImpl<EdjCouponMapper, EdjCoupon> implements EdjCouponService {

    private final RedisTemplate<String, String> stringRedisTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    private final UserApi userApi;

    private final EdjActivityMapper activityMapper;

    private final LockHelper lockHelper;

    private final EdjCouponWriteOffMapper couponWriteOffMapper;

    @Resource(name = "grabCouponScript")
    private DefaultRedisScript<Long> grabCouponScript;

    @Override
    public PageResult<CouponPageVO> pageByActivity(CouponPageDTO couponPageDTO) {
        Page<EdjCoupon> page = PageUtils.parsePageQuery(couponPageDTO);
        LambdaQueryWrapper<EdjCoupon> wrapper = new LambdaQueryWrapper<EdjCoupon>()
                .eq(EdjCoupon::getEdjActivityId, couponPageDTO.getActivityId());
        Page<EdjCoupon> couponPage = baseMapper.selectPage(page, wrapper);
        return PageUtils.toPage(couponPage, CouponPageVO.class);
    }

    @Override
    public List<CouponPageVO> getMyCouponForPage(Long lastId, Integer status) {
        LambdaQueryWrapper<EdjCoupon> wrapper = new LambdaQueryWrapper<EdjCoupon>()
                .eq(EdjCoupon::getEdjUserId, SecurityUtils.getUserId())
                .eq(EdjCoupon::getStatus, status)
                .lt(ObjectUtils.isNotNull(lastId), EdjCoupon::getId, lastId)
                .orderByDesc(EdjCoupon::getId)
                .last("LIMIT 10");

        List<EdjCoupon> couponList = baseMapper.selectList(wrapper);

        return BeanUtils.copyToList(couponList, CouponPageVO.class);
    }

    @Override
    public void grabCoupon(GrabCouponDTO grabCouponDTO) {
        Long activityId = grabCouponDTO.getId();

        // 从redis查询活动信息
        String activityListJson = stringRedisTemplate.opsForValue().get(ACTIVITY_CACHE);
        if (ObjectUtils.isNull(activityListJson)) {
            throw new BadRequestException("活动已结束");
        }
        List<ActivityInfoVO> activityInfoVOList = JsonUtils.toList(activityListJson, ActivityInfoVO.class);
        if (CollUtils.isEmpty(activityInfoVOList)) {
            throw new BadRequestException("活动已结束");
        }
        // 过滤出活动
        ActivityInfoVO activityInfoVO = activityInfoVOList
                .stream()
                .filter(item -> item.getId().equals(activityId))
                .findFirst()
                .orElse(null);
        if (activityInfoVO == null) {
            throw new BadRequestException("活动已结束");
        }

        // 校验活动有效
        LocalDateTime now = LocalDateTime.now();
        if (activityInfoVO.getDistributeStartTime().isAfter(now)) {
            throw new BadRequestException("活动未开始");
        }
        if (activityInfoVO.getDistributeEndTime().isBefore(now)) {
            throw new BadRequestException("活动已结束");
        }

        //-- key: 库存(KEYS[1]), 抢券成功列表(KEYS[2]), 抢券同步队列(KEYS[3])
        //-- argv: 活动id(ARGV[1]), 用户id(ARGV[2]), EdjCoupon实体(ARGV[3])

        // 用户id
        Long userId = SecurityUtils.getUserId();
        // 序号
        long index = activityId % QUEUE_NUM;

        // 库存key
        String stockKey = String.format(ACTIVITY_STOCK_CACHE, index);
        // 抢券成功列表key
        String successListKey = String.format(SUCCESS_LIST_CACHE, activityId, index);
        // 抢券同步队列key
        String successSyncKey = String.format(SUCCESS_SYNC_CACHE, index);

        // 构建领取优惠券元组
        EdjCoupon coupon = EdjCoupon
                .builder()
                .edjUserId(userId)
                .edjActivityId(activityId)
                .build();
        // 转为字符串
        String couponStr = JsonUtils.toJsonStr(coupon);

        // 执行脚本
        Long result = redisTemplate.execute(
                grabCouponScript,
                List.of(stockKey, successListKey, successSyncKey),
                activityId, userId, couponStr
        );

        if (ObjectUtils.isNull(result)) {
            throw new ServerErrorException("抢券失败");
        }

        //noinspection IfCanBeSwitch
        if (result == -1) {
            throw new BadRequestException("限领一张");
        }
        if (result == -2 || result == -3) {
            throw new BadRequestException("已抢光");
        }
        if (result == -4) {
            throw new BadRequestException("抢券失败");
        }
    }

    @Override
    public void grabCouponSync() {
        // 分线程处理
        List<CompletableFuture<Void>> futureList = IntStream.range(0, QUEUE_NUM)
                .mapToObj(i -> AsyncUtils.runAsync(() -> {
                    // 获取key
                    String key = String.format(SUCCESS_SYNC_CACHE, i);
                    log.debug("线程: {} -> 获取key | key: {}", i, key);

                    // 加锁执行
                    LockHelper.Execution execution = () -> {
                        // 获取同步列表数据
                        List<Object> couponStrList = redisTemplate.opsForList().rightPop(key, LIMIT);
                        log.debug("线程: {} -> 获取同步列表数据 | couponStrList: {}", i, couponStrList);

                        // 判断空
                        if (CollUtils.isEmpty(couponStrList)) {
                            return;
                        }

                        for (Object couponStr : couponStrList) {
                            try {
                                // 解析为实体
                                EdjCoupon coupon = JsonUtils.toBean(
                                        StringUtils.strip((String) couponStr, "\""),
                                        EdjCoupon.class
                                );

                                // 获取redis数据
                                Long userId = coupon.getEdjUserId();
                                Long activityId = coupon.getEdjActivityId();
                                if (userId == null || activityId == null) {
                                    log.error("同步列表数据不完整 userId: {}, activityId: {}", userId, activityId);
                                    continue;
                                }

                                // 获取用户数据
                                UserDTO user = userApi.getUserById(userId);
                                if (ObjectUtils.isNull(user)) {
                                    log.error("用户不存在 userId: {}", userId);
                                    continue;
                                }

                                // 获取活动数据
                                EdjActivity activity = activityMapper.selectById(activityId);
                                if (ObjectUtils.isNull(activity)) {
                                    log.error("活动不存在 activityId: {}", activityId);
                                    continue;
                                }

                                // 组装数据
                                coupon.setName(activity.getName());
                                coupon.setUsername(user.getUsername());
                                coupon.setNickname(user.getNickname());
                                coupon.setUserPhone(user.getPhoneNumber());
                                coupon.setType(activity.getType());
                                coupon.setDiscountRate(activity.getDiscountRate());
                                coupon.setDiscountAmount(activity.getDiscountAmount());
                                coupon.setAmountCondition(activity.getAmountCondition());
                                coupon.setValidityTime(LocalDateTime.now().plusDays(activity.getValidityDays()));

                                int insert = baseMapper.insert(coupon);
                                if (insert != 1) {
                                    log.error("保存失败 coupon: {}", coupon);
                                    throw new ServerErrorException("coupon保存失败");
                                }
                            } catch (DuplicateKeyException duplicateKeyException) {
                                log.error("优惠券重复获得 coupon: {}", couponStr);
                            } catch (Exception e) {
                                // 保存失败恢复redis数据
                                redisTemplate.opsForList().leftPush(key, couponStr);
                            }
                        }
                    };

                    lockHelper.syncLock(
                            String.format(GRAB_COUPON_LOCK, key),
                            COMPLEX_OPERATION_WAIT_TIME,
                            execution
                    );
                }))
                .toList();
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
    }

    @Override
    public List<AvailableCouponVO> getAvailable(Long userId, BigDecimal totalAmount) {

        // 查询符合条件的优惠券
        LambdaQueryWrapper<EdjCoupon> wrapper = new LambdaQueryWrapper<EdjCoupon>()
                .eq(EdjCoupon::getEdjUserId, ObjectUtils.isNull(userId) ? SecurityUtils.getUserId() : userId)
                .eq(EdjCoupon::getStatus, EdjCouponStatus.UNUSED)
                .gt(EdjCoupon::getValidityTime, LocalDateTime.now())
                .le(EdjCoupon::getAmountCondition, totalAmount);
        List<EdjCoupon> couponList = baseMapper.selectList(wrapper);

        // 判空
        if (CollUtils.isEmpty(couponList)) {
            return List.of();
        }

        // 计算优惠金额
        return couponList.stream()
                .peek(coupon -> coupon.setDiscountAmount(CouponUtils.calDiscountAmount(coupon, totalAmount)))
                .sorted(Comparator.comparing(EdjCoupon::getDiscountAmount).reversed())
                .map(coupon -> BeanUtils.toBean(coupon, AvailableCouponVO.class))
                .toList();
    }

    @Override
    public CouponUseVO use(CouponUseDTO couponUseDTO) {
        // 查询优惠券
        Long id = couponUseDTO.getId();
        EdjCoupon coupon = baseMapper.selectById(id);

        // 校验优惠券
        if (coupon == null) {
            throw new BadRequestException("优惠券不存在");
        }
        // 校验用户
        Long userId = coupon.getEdjUserId();
        if (ObjectUtils.notEqual(userId, couponUseDTO.getUserId())) {
            throw new BadRequestException("优惠券不属于此用户");
        }
        // 校验优惠券状态
        Integer status = coupon.getStatus();
        if (EnumUtils.ne(EdjCouponStatus.UNUSED, status)) {
            throw new BadRequestException("优惠券不可用");
        }
        // 校验优惠券截止日期
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validityTime = coupon.getValidityTime();
        if (validityTime.isBefore(now)) {
            throw new BadRequestException("优惠券已过期");
        }
        // 校验使用条件
        BigDecimal amountCondition = coupon.getAmountCondition();
        BigDecimal totalAmount = couponUseDTO.getTotalAmount();
        if (amountCondition.compareTo(totalAmount) < 0) {
            throw new BadRequestException("使用条件不符合");
        }

        Long ordersId = couponUseDTO.getOrdersId();
        // 更新优惠券状态
        LambdaUpdateWrapper<EdjCoupon> couponLambdaUpdateWrapper = new LambdaUpdateWrapper<EdjCoupon>()
                .eq(EdjCoupon::getId, id)
                .set(EdjCoupon::getEdjOrdersId, ordersId)
                .set(EdjCoupon::getUseTime, now)
                .set(EdjCoupon::getStatus, EdjCouponStatus.USED);
        int update = baseMapper.update(new EdjCoupon(), couponLambdaUpdateWrapper);
        if (update != 1) {
            throw new DBException("优惠券核销失败");
        }

        Long activityId = coupon.getEdjActivityId();
        String userPhone = coupon.getUserPhone();
        String username = coupon.getUsername();
        // 添加核销记录
        EdjCouponWriteOff couponWriteOff = EdjCouponWriteOff
                .builder()
                .edjCouponId(id)
                .edjUserId(userId)
                .edjOrdersId(ordersId)
                .edjActivityId(activityId)
                .writeOffTime(now)
                .writeOffManPhone(userPhone)
                .writeOffManName(username)
                .build();
        int insert = couponWriteOffMapper.insert(couponWriteOff);
        if (insert != 1) {
            throw new DBException("优惠券核销失败");
        }

        return CouponUseVO
                .builder()
                .discountAmount(CouponUtils.calDiscountAmount(coupon, totalAmount))
                .build();
    }
}