package com.edj.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.JsonUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.market.domain.dto.CouponPageDTO;
import com.edj.market.domain.dto.GrabCouponDTO;
import com.edj.market.domain.entity.EdjCoupon;
import com.edj.market.domain.vo.ActivityInfoVO;
import com.edj.market.domain.vo.CouponPageVO;
import com.edj.market.mapper.EdjCouponMapper;
import com.edj.market.service.EdjCouponService;
import com.edj.mysql.utils.PageUtils;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.edj.cache.constants.CacheConstants.CacheName.*;

/**
 * 针对表【edj_coupon(用户优惠券表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Service
@RequiredArgsConstructor
public class EdjCouponServiceImpl extends MPJBaseServiceImpl<EdjCouponMapper, EdjCoupon> implements EdjCouponService {

    private final RedisTemplate<String, String> stringRedisTemplate;

    private final RedisTemplate<String, Long> longRedisTemplate;

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
        // 队列总数
        int n = 10;
        // 序号
        long index = activityId % n;

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
        Long result = longRedisTemplate.execute(
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
}