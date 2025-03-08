package com.edj.market.mapper;

import com.edj.market.domain.entity.EdjCouponUseBack;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_coupon_use_back(优惠券退回表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Mapper
public interface EdjCouponUseBackMapper extends MPJBaseMapper<EdjCouponUseBack> {
}