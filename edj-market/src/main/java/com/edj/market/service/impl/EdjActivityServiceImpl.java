package com.edj.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.common.utils.StringUtils;
import com.edj.market.domain.dto.ActivityPageDTO;
import com.edj.market.domain.dto.ActivitySaveDTO;
import com.edj.market.domain.entity.EdjActivity;
import com.edj.market.domain.entity.EdjCoupon;
import com.edj.market.domain.entity.EdjCouponWriteOff;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 针对表【edj_activity(优惠券活动表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Service
@RequiredArgsConstructor
public class EdjActivityServiceImpl extends MPJBaseServiceImpl<EdjActivityMapper, EdjActivity> implements EdjActivityService {

    private final EdjCouponMapper couponMapper;

    private final EdjCouponWriteOffMapper couponWriteOffMapper;

    @Override
    public void save(ActivitySaveDTO activitySaveDTO) {
        // 参数校验
        activitySaveDTO.check();

        // 数据组装
        EdjActivity activity = BeanUtils.toBean(activitySaveDTO, EdjActivity.class);

        // 保存数据
        saveOrUpdate(activity);
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

        LambdaQueryWrapper<EdjCoupon> couponCountWrapper = new LambdaQueryWrapper<EdjCoupon>()
                .eq(EdjCoupon::getEdjActivityId, id);
        Long couponCount = couponMapper.selectCount(couponCountWrapper);

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
}