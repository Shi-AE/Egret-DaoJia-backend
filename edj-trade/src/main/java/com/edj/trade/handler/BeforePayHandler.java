package com.edj.trade.handler;

import com.edj.trade.domain.entity.EdjTrading;

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
}
