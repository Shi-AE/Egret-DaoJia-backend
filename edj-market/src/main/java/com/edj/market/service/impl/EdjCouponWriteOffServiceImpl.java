package com.edj.market.service.impl;

import com.edj.market.domain.entity.EdjCouponWriteOff;
import com.edj.market.mapper.EdjCouponWriteOffMapper;
import com.edj.market.service.EdjCouponWriteOffService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_coupon_write_off(优惠券核销表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Service
public class EdjCouponWriteOffServiceImpl extends MPJBaseServiceImpl<EdjCouponWriteOffMapper, EdjCouponWriteOff> implements EdjCouponWriteOffService {
}