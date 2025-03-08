package com.edj.market.mapper;

import com.edj.market.domain.entity.EdjCouponWriteOff;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_coupon_write_off(优惠券核销表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Mapper
public interface EdjCouponWriteOffMapper extends MPJBaseMapper<EdjCouponWriteOff> {
}