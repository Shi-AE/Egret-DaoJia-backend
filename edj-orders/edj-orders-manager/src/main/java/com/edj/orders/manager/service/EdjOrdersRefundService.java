package com.edj.orders.manager.service;

import com.edj.orders.base.domain.entity.EdjOrdersRefund;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_orders_refund(订单退款表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2025/03/04
 */
public interface EdjOrdersRefundService extends MPJBaseService<EdjOrdersRefund> {

    /**
     * 获取指定数量退款记录
     */
    List<EdjOrdersRefund> selectRefundOrderListByCount(Integer refundOrderCount);
}
