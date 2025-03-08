package com.edj.market.service.impl;

import com.edj.market.domain.entity.EdjCoupon;
import com.edj.market.mapper.EdjCouponMapper;
import com.edj.market.service.EdjCouponService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_coupon(用户优惠券表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Service
public class EdjCouponServiceImpl extends MPJBaseServiceImpl<EdjCouponMapper, EdjCoupon> implements EdjCouponService {
}