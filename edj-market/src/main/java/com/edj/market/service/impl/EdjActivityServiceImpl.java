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
import com.edj.market.domain.vo.ActivityPageVO;
import com.edj.market.enums.EdjActivityStatus;
import com.edj.market.enums.EdjCouponStatus;
import com.edj.market.mapper.EdjActivityMapper;
import com.edj.market.mapper.EdjCouponMapper;
import com.edj.market.service.EdjActivityService;
import com.edj.mysql.utils.PageUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .eq(ObjectUtils.isNotNull(status), EdjActivity::getStatus, status)
                .orderByDesc(EdjActivity::getId);

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

        // todo 领取数量、核销量

        return activityPageVO;
    }

    @Override
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
}