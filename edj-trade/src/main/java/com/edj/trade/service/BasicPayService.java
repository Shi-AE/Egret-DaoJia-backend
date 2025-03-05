package com.edj.trade.service;


import com.edj.api.api.trade.vo.TradingVO;
import com.edj.trade.domain.entity.EdjRefundRecord;

import java.math.BigDecimal;

/**
 * 支付的基础功能
 *
 * @author A.E.
 * @date 2024/12/31
 */
public interface BasicPayService {

    /***
     * 统一收单线下交易查询
     * 该接口提供所有支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。
     * @param tradingOrderNo 交易单号
     * @return 交易数据对象
     */
    TradingVO getTradingResult(Long tradingOrderNo);

    /***
     * 统一收单交易退款接口
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
     * 将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     *
     *
     * @param tradingOrderNo 交易单号
     * @param refundAmount 退款金额，不能大于总支付的总金额
     * @return 是否成功
     */
    EdjRefundRecord refundTrading(Long tradingOrderNo, BigDecimal refundAmount);

    /***
     * 对于退款中的记录需要同步退款状态
     * 同步退款中记录
     * @param tradingOrderNo 交易单号
     */
    void syncRefundResult(Long tradingOrderNo);

    /***
     * 统一收单交易退款查询接口
     * 查询退款中记录是否退款成功
     * @param refundNo 退款单号
     * @return 退款记录数据
     */
    EdjRefundRecord queryRefundTrading(Long refundNo);

    /***
     * 关闭交易单
     * @param tradingOrderNo 交易单号
     * @return 是否成功
     */
    Boolean closeTrading(Long tradingOrderNo);
}
