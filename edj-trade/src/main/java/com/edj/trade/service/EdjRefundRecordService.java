package com.edj.trade.service;

import com.edj.trade.domain.entity.EdjRefundRecord;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_refund_record(退款记录表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/12/31
 */
public interface EdjRefundRecordService extends MPJBaseService<EdjRefundRecord> {

    /**
     * 查询退款中记录
     */
    List<EdjRefundRecord> findRefundingByOrderNo(Long tradingOrderNo);

    /**
     * 根据退款单号查询退款记录
     */
    EdjRefundRecord findByRefundNo(Long refundNo);

    /**
     * 查询所有退款记录
     */
    List<EdjRefundRecord> findByOrderNo(Long tradingOrderNo);
}
