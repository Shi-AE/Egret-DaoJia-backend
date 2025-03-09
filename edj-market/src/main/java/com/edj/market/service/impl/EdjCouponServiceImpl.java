package com.edj.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.market.domain.dto.CouponPageDTO;
import com.edj.market.domain.entity.EdjCoupon;
import com.edj.market.domain.vo.CouponPageVO;
import com.edj.market.mapper.EdjCouponMapper;
import com.edj.market.service.EdjCouponService;
import com.edj.mysql.utils.PageUtils;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 针对表【edj_coupon(用户优惠券表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Service
public class EdjCouponServiceImpl extends MPJBaseServiceImpl<EdjCouponMapper, EdjCoupon> implements EdjCouponService {
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
}