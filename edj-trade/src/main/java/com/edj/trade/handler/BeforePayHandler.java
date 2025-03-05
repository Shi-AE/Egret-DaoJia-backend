package com.edj.trade.handler;

import com.edj.trade.domain.entity.EdjRefundRecord;
import com.edj.trade.domain.entity.EdjTrading;

import java.math.BigDecimal;

/**
 * 交易前置处理接口
 *
 * @author A.E.
 * @date 2024/12/30
 */
public interface BeforePayHandler {
    /***
     * 交易单参数校验
     */
    void checkCreateTrading(EdjTrading tradingEntity);

    /***
     * CloseTrading交易单参数校验
     * @param trading 交易订单
     */
    void checkCloseTrading(EdjTrading trading);

    /***
     * QueryTrading交易单参数校验
     * @param trading 交易订单
     */
    void checkQueryTrading(EdjTrading trading);

    /***
     * RefundTrading退款交易单参数校验
     * @param trading 交易订单
     * @param refundAmount 退款金额
     */
    void checkRefundTrading(EdjTrading trading, BigDecimal refundAmount);

    /**
     * QueryRefundTrading交易单参数校验
     * @param refundRecord 退款记录
     */
    void checkQueryRefundTrading(EdjRefundRecord refundRecord);
}
