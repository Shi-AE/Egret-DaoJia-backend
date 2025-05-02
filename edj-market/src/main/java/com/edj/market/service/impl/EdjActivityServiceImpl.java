package com.edj.market.service.impl;

import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.*;
import com.edj.market.domain.dto.ActivityPageDTO;
import com.edj.market.domain.dto.ActivitySaveDTO;
import com.edj.market.domain.entity.EdjActivity;
import com.edj.market.domain.entity.EdjCoupon;
import com.edj.market.domain.entity.EdjCouponWriteOff;
import com.edj.market.domain.vo.ActivityInfoVO;
import com.edj.market.domain.vo.ActivityPageVO;
import com.edj.market.enums.EdjActivityStatus;
import com.edj.market.enums.EdjCouponStatus;
import com.edj.market.mapper.EdjActivityMapper;
import com.edj.market.mapper.EdjCouponMapper;
import com.edj.market.mapper.EdjCouponWriteOffMapper;
import com.edj.market.service.EdjActivityService;
import com.edj.mysql.utils.PageUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import static com.edj.cache.constants.CacheConstants.CacheName.*;
import static com.edj.market.constants.RedisConstants.QUEUE_NUM;

/**
 * 针对表【edj_activity(优惠券活动表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EdjActivityServiceImpl extends MPJBaseServiceImpl<EdjActivityMapper, EdjActivity> implements EdjActivityService {

    private final EdjCouponMapper couponMapper;

    private final EdjCouponWriteOffMapper couponWriteOffMapper;

    private final RedisTemplate<String, String> stringRedisTemplate;

    private final RedisTemplate<String, Long> longRedisTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(ActivitySaveDTO activitySaveDTO) {
        // 参数校验
        activitySaveDTO.check();

        // 区分新增与修改
        Long id = activitySaveDTO.getId();

        // 新增
        if (id == null) {
            // 数据组装
            EdjActivity activity = BeanUtils.toBean(activitySaveDTO, EdjActivity.class);
            baseMapper.insert(activity);
            return;
        }

        // 修改
        LambdaQueryWrapper<EdjActivity> wrapper = new LambdaQueryWrapper<EdjActivity>()
                .select(EdjActivity::getId, EdjActivity::getStatus)
                .eq(EdjActivity::getId, id);
        EdjActivity activity = baseMapper.selectOne(wrapper);

        // 校验
        if (activity == null || EnumUtils.ne(EdjActivityStatus.PENDING, activity.getStatus())) {
            throw new BadRequestException("活动不存在或活动已生效无法修改");
        }

        baseMapper.updateById(BeanUtils.toBean(activitySaveDTO, EdjActivity.class));
    }

    @Override
    public PageResult<ActivityPageVO> page(ActivityPageDTO activityPageDTO) {

        Page<EdjActivity> page = PageUtils.parsePageQuery(activityPageDTO);

        Long id = activityPageDTO.getId();
        String name = activityPageDTO.getName();
        Integer type = activityPageDTO.getType();
        Integer status = activityPageDTO.getStatus();
        LambdaQueryWrapper<EdjActivity> wrapper = new LambdaQueryWrapper<EdjActivity>()
                .eq(ObjectUtils.isNotNull(id), EdjActivity::getId, id)
                .like(StringUtils.isNotBlank(name), EdjActivity::getName, name)
                .eq(ObjectUtils.isNotNull(type), EdjActivity::getType, type)
                .eq(ObjectUtils.isNotNull(status), EdjActivity::getStatus, status);

        Page<EdjActivity> activityPage = baseMapper.selectPage(page, wrapper);

        return PageUtils.toPage(activityPage, ActivityPageVO.class);
    }

    @Override
    public ActivityPageVO detail(Long id) {

        EdjActivity activity = baseMapper.selectById(id);

        if (ObjectUtils.isNull(activity)) {
            return new ActivityPageVO();
        }

        ActivityPageVO activityPageVO = BeanUtils.toBean(activity, ActivityPageVO.class);

        // 查询领取数量
        LambdaQueryWrapper<EdjCoupon> couponCountWrapper = new LambdaQueryWrapper<EdjCoupon>()
                .eq(EdjCoupon::getEdjActivityId, id);
        Long couponCount = couponMapper.selectCount(couponCountWrapper);

        // 查询核销数量
        LambdaQueryWrapper<EdjCouponWriteOff> writeOffCountWrapper = new LambdaQueryWrapper<EdjCouponWriteOff>()
                .eq(EdjCouponWriteOff::getEdjActivityId, id);
        Long writeOffCountCount = couponWriteOffMapper.selectCount(writeOffCountWrapper);

        activityPageVO.setReceiveNum(Math.toIntExact(couponCount));
        activityPageVO.setWriteOffNum(Math.toIntExact(writeOffCountCount));

        return activityPageVO;
    }

    @Override
    @Transactional
    public void revoke(Long id) {
        LambdaUpdateWrapper<EdjActivity> activityWrapper = new LambdaUpdateWrapper<EdjActivity>()
                .eq(EdjActivity::getId, id)
                // 待发放和进行中可作废
                .in(EdjActivity::getStatus, List.of(EdjActivityStatus.PENDING, EdjActivityStatus.ONGOING))
                .set(EdjActivity::getStatus, EdjActivityStatus.CANCELLED);
        int update = baseMapper.update(new EdjActivity(), activityWrapper);
        if (update != 1) {
            return;
        }

        // 成功作废活动，需要作废未使用的优惠券
        LambdaUpdateWrapper<EdjCoupon> couponWrapper = new LambdaUpdateWrapper<EdjCoupon>()
                .eq(EdjCoupon::getEdjActivityId, id)
                .eq(EdjCoupon::getStatus, EdjCouponStatus.UNUSED)
                .set(EdjCoupon::getStatus, EdjCouponStatus.CANCELLED);
        couponMapper.update(new EdjCoupon(), couponWrapper);
    }

    @Override
    public void updateStatus() {
        LocalDateTime now = LocalDateTime.now();
        // 1. 到达发放开始时间状态改为“进行中”
        LambdaUpdateWrapper<EdjActivity> wrapper1 = new LambdaUpdateWrapper<EdjActivity>()
                .eq(EdjActivity::getStatus, EdjActivityStatus.PENDING) // 待生效的活动
                .le(EdjActivity::getDistributeStartTime, now) // 活动开始时间小于等于当前时间
                .gt(EdjActivity::getDistributeEndTime, now) // 活动结束时间大于当前时间
                .set(EdjActivity::getStatus, EdjActivityStatus.ONGOING);
        baseMapper.update(new EdjActivity(), wrapper1);

        // 2. 到达发放结束时间状态改为“已失效”
        LambdaUpdateWrapper<EdjActivity> wrapper2 = new LambdaUpdateWrapper<EdjActivity>()
                .in(EdjActivity::getStatus, List.of(EdjActivityStatus.PENDING, EdjActivityStatus.ONGOING)) // 待生效及进行中的活动
                .le(EdjActivity::getDistributeEndTime, now) // 活动结束时间小于等于当前时间
                .set(EdjActivity::getStatus, EdjActivityStatus.EXPIRED);
        baseMapper.update(new EdjActivity(), wrapper2);
    }

    @Override
    public void perHeat() {
        LambdaQueryWrapper<EdjActivity> wrapper = new LambdaQueryWrapper<EdjActivity>()
                .in(EdjActivity::getStatus, List.of(EdjActivityStatus.ONGOING, EdjActivityStatus.PENDING))
                .le(EdjActivity::getDistributeStartTime, LocalDateTime.now().plusDays(30))
                .orderByDesc(EdjActivity::getDistributeStartTime);
        List<EdjActivity> activityList = baseMapper.selectList(wrapper);

        if (CollUtils.isEmpty(activityList)) {
            activityList = new ArrayList<>();
        }

        // 转类型
        List<ActivityInfoVO> activityInfoVOList = activityList.stream()
                .map(activity -> {
                    ActivityInfoVO activityInfoVO = BeanUtils.toBean(activity, ActivityInfoVO.class);

                    // 从redis获取库存
                    Long activityId = activity.getId();
                    String stockKey = String.format(ACTIVITY_STOCK_CACHE, activityId % QUEUE_NUM);
                    Integer stock = (Integer) longRedisTemplate.opsForHash().get(stockKey, activityId);
                    activityInfoVO.setRemainNum(NumberUtils.null2Default(stock, 0));

                    return activityInfoVO;
                })
                .toList();

        // 写入缓存
        String jsonStr = JsonUtils.toJsonStr(activityInfoVOList);
        stringRedisTemplate.opsForValue().set(ACTIVITY_CACHE, jsonStr);
    }

    @Override
    public List<ActivityInfoVO> listFromCache(Integer tabType) {

        // 从 redis 查询活动信息
        String jsonStr = stringRedisTemplate.opsForValue().get(ACTIVITY_CACHE);
        if (ObjectUtils.isNull(jsonStr)) {
            return new ArrayList<>();
        }

        // json 转实体
        List<ActivityInfoVO> activityInfoVOList = JsonUtils.toList(jsonStr, ActivityInfoVO.class);

        Integer queryStatus = EnumUtils.value(tabType == 1 ? EdjActivityStatus.ONGOING : EdjActivityStatus.PENDING, Integer.class);

        // 过滤活动，实时状态
        return activityInfoVOList.stream()
                // 获取实际状态
                .peek(item -> {
                    // 修改实际状态
                    Integer status = item.getStatus();
                    LocalDateTime distributeStartTime = item.getDistributeStartTime();
                    LocalDateTime distributeEndTime = item.getDistributeEndTime();
                    LocalDateTime now = LocalDateTime.now();

                    // 活动待生效
                    if (EnumUtils.eq(EdjActivityStatus.PENDING, status)) {
                        // 活动已结束
                        if (distributeEndTime.isBefore(now)) {
                            item.setStatus(EnumUtils.value(EdjActivityStatus.EXPIRED, Integer.class));
                            return;
                        }
                        // 活动开始
                        if (distributeStartTime.isEqual(now) || distributeStartTime.isBefore(now)) {
                            item.setStatus(EnumUtils.value(EdjActivityStatus.ONGOING, Integer.class));
                            return;
                        }
                    }

                    // 活动进行中
                    if (EnumUtils.eq(EdjActivityStatus.ONGOING, status)) {
                        // 活动已结束
                        if (distributeEndTime.isBefore(now)) {
                            item.setStatus(EnumUtils.value(EdjActivityStatus.EXPIRED, Integer.class));
                        }
                    }
                })
                .filter(item -> ObjectUtils.equals(item.getStatus(), queryStatus))
                .toList();
    }

    @Override
    public void stockPerHeat() {
        LambdaQueryWrapper<EdjActivity> wrapper = new LambdaQueryWrapper<EdjActivity>()
                .select(EdjActivity::getId, EdjActivity::getStatus, EdjActivity::getTotalNum)
                .in(EdjActivity::getStatus, List.of(EdjActivityStatus.ONGOING, EdjActivityStatus.PENDING))
                .le(EdjActivity::getDistributeStartTime, LocalDateTime.now().plusDays(30));
        List<EdjActivity> activityList = baseMapper.selectList(wrapper);

        for (EdjActivity activity : activityList) {
            Integer status = activity.getStatus();
            Integer totalNum = activity.getTotalNum();
            Long id = activity.getId();

            // 构造key
            String key = String.format(ACTIVITY_STOCK_CACHE, id % QUEUE_NUM);

            // 活动待生效，直接保存总量到 redis
            if (EnumUtils.eq(EdjActivityStatus.PENDING, status)) {
                // 无限制
                if (totalNum == 0) {
                    longRedisTemplate.opsForHash().put(key, id, Integer.MAX_VALUE);
                    continue;
                }

                // 有限制
                longRedisTemplate.opsForHash().put(key, id, totalNum);
                continue;
            }

            // 活动进行中
            if (EnumUtils.eq(EdjActivityStatus.ONGOING, status)) {
                // 无限制
                if (totalNum == 0) {
                    longRedisTemplate.opsForHash().put(key, id, Integer.MAX_VALUE);
                    continue;
                }

                // 有限制
                // 检查是否存在key
                Boolean hasKey = longRedisTemplate.opsForHash().hasKey(key, id);
                // 库存不存在则同步
                if (!hasKey) {

                    // 查询已领取数量
                    LambdaQueryWrapper<EdjCoupon> couponCountWrapper = new LambdaQueryWrapper<EdjCoupon>()
                            .eq(EdjCoupon::getEdjActivityId, id);
                    long couponCount = couponMapper.selectCount(couponCountWrapper);

                    // 计算库存
                    long stock = NumberUtils.max(totalNum - couponCount, 0);

                    // 同步库存
                    longRedisTemplate.opsForHash().put(key, id, Math.toIntExact(stock));
                }
            }
        }
    }

    @Override
    public void cleanCache() {
        // 清理库存缓存
        // 获取所有库存key
        Set<String> stockKeys = redisTemplate.keys(ACTIVITY_STOCK_CACHE
                .substring(0, ACTIVITY_STOCK_CACHE.length() - 4)
                .concat("*")
        );

        if (CollUtils.isNotEmpty(stockKeys)) {
            stockKeys.forEach(stockKey -> {
                // 获取所有活动id
                Set<Object> activityIdSet = redisTemplate.opsForHash().keys(stockKey);
                // 校验活动是否结束
                LambdaQueryWrapper<EdjActivity> wrapper = new LambdaQueryWrapper<EdjActivity>()
                        .select(EdjActivity::getId)
                        .in(EdjActivity::getStatus, List.of(EdjActivityStatus.EXPIRED, EdjActivityStatus.CANCELLED))
                        .in(EdjActivity::getId, activityIdSet);
                List<EdjActivity> activityEndList = baseMapper.selectList(wrapper);
                // 清除缓存
                Object[] activityEndIdList = activityEndList
                        .stream()
                        .map(EdjActivity::getId)
                        .toArray();
                if (ArrayUtils.isNotEmpty(activityEndIdList)) {
                    log.info("清理库存缓存 activityEndIdList", activityEndIdList);
                    redisTemplate.opsForHash().delete(stockKey, activityEndIdList);
                }
            });
        }

        // 清理抢券成功队列缓存
        // 获取所有key
        String prefix = SUCCESS_LIST_CACHE.substring(0, SUCCESS_LIST_CACHE.length() - 7);
        Set<String> successKeys = redisTemplate.keys(prefix.concat("*"));

        if (CollUtils.isNotEmpty(successKeys)) {
            // 匹配所有活动id
            Pattern pattern = Pattern.compile("^" + prefix + "(\\d+)_\\{\\d+}$");
            List<String> activityIdList = successKeys
                    .stream()
                    .map(key -> ReUtil.get(pattern, key, 1))
                    .filter(Objects::nonNull)
                    .toList();
            // 校验活动是否结束
            LambdaQueryWrapper<EdjActivity> wrapper = new LambdaQueryWrapper<EdjActivity>()
                    .select(EdjActivity::getId)
                    .in(EdjActivity::getStatus, List.of(EdjActivityStatus.EXPIRED, EdjActivityStatus.CANCELLED))
                    .in(EdjActivity::getId, activityIdList);
            List<EdjActivity> activityEndList = baseMapper.selectList(wrapper);
            // 清除缓存
            List<String> activityEndKeys = activityEndList
                    .stream()
                    .map(EdjActivity::getId)
                    .map(id -> String.format(SUCCESS_LIST_CACHE, id, id % QUEUE_NUM))
                    .toList();
            if (CollUtils.isNotEmpty(activityEndKeys)) {
                log.info("清理抢券成功队列缓存 activityEndKeys: {}", activityEndKeys);
                redisTemplate.delete(activityEndKeys);
            }
        }
    }
}