package com.edj.trade.service;

import com.edj.trade.domain.entity.EdjTrading;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_trading(交易订单表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/12/30
 */
public interface EdjTradingService extends MPJBaseService<EdjTrading> {

    /**
     * 根据订单id和支付方式查询付款中的交易单
     */
    EdjTrading getDuringTrading(String productAppId, Long productOrderNo, String tradingChannel);

    /**
     * 查找已付款的记录
     */
    EdjTrading getFinishedTrading(String productAppId, Long productOrderNo);

    /**
     * 根据订单id查询交易单
     *
     * @param productAppId 业务系统标识
     * @param productOrderNo 订单号
     * @return 交易单
     */
    List<EdjTrading> getByProductOrder(String productAppId, Long productOrderNo);

    /***
     * 按交易单号查询交易单
     *
     * @param tradingOrderNo 交易单号
     * @return 交易单数据
     */
    EdjTrading findTradByTradingOrderNo(Long tradingOrderNo);
}
