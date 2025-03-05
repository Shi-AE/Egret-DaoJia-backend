package com.edj.orders.base.mapper;

import com.edj.orders.base.domain.entity.EdjOrdersRefund;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_orders_refund(订单退款表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2025/03/04
 */
@Mapper
public interface EdjOrdersRefundMapper extends MPJBaseMapper<EdjOrdersRefund> {
}