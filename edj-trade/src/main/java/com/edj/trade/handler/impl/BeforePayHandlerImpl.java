package com.edj.trade.handler.impl;

import cn.hutool.core.lang.Snowflake;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.CommonException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.NumberUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.trade.domain.entity.EdjRefundRecord;
import com.edj.trade.domain.entity.EdjTrading;
import com.edj.trade.enums.EdjRefundStatus;
import com.edj.trade.enums.EdjTradingState;
import com.edj.trade.enums.TradingEnum;
import com.edj.trade.handler.BeforePayHandler;
import com.edj.trade.service.EdjTradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 交易前置处理实现
 *
 * @author A.E.
 * @date 2024/12/30
 */
@Component
@RequiredArgsConstructor
public class BeforePayHandlerImpl implements BeforePayHandler {

    private final EdjTradingService tradingService;

    private final Snowflake snowflake;

    @Override
    public void checkCreateTrading(EdjTrading tradingEntity) {
        // 校验不为为空，订单备注、订单号、企业号、交易金额、支付渠道
        boolean f1 = ObjectUtils.isAllNotEmpty(
                tradingEntity,
                tradingEntity.getMemo(),
                tradingEntity.getProductOrderNo(),
                tradingEntity.getEnterpriseId(),
                tradingEntity.getTradingAmount(),
                tradingEntity.getTradingChannel()
        );
        if (!f1) {
            throw new ServerErrorException("参数为空");
        }
        // 金额不能小于零
        boolean f2 = NumberUtils.isGreater(tradingEntity.getTradingAmount(), BigDecimal.ZERO);
        if (!f2) {
            throw new ServerErrorException("金额错误");
        }

        // 查找已付款的记录
        EdjTrading finishedTrading = tradingService.getFinishedTrading(tradingEntity.getProductAppId(), tradingEntity.getProductOrderNo());
        if (ObjectUtils.isNotEmpty(finishedTrading)) {
            // 存在已付款单子直接抛出重复支付异常
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.TRADING_STATE_SUCCEED.getValue());
        }
        // 找到该支付渠道支付中的单子
        EdjTrading trading = tradingService.getDuringTrading(tradingEntity.getProductAppId(), tradingEntity.getProductOrderNo(), tradingEntity.getTradingChannel());
        if (ObjectUtils.isNotEmpty(trading)) {
            // 存在相同支付渠道的付款中单子
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.TRADING_STATE_PAYING.getValue());
        }
        // 新交易单，生成交易号
        tradingEntity.setTradingOrderNo(snowflake.nextId());
    }

    @Override
    public void checkCloseTrading(EdjTrading trading) {
        if (ObjectUtils.notEqual(EnumUtils.value(EdjTradingState.PAYMENT_IN_PROGRESS), trading.getTradingState())) {
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.CLOSE_FAIL.getValue());
        }
    }

    @Override
    public void checkQueryTrading(EdjTrading trading) {
        if (ObjectUtils.isEmpty(trading)) {
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.NOT_FOUND.getValue());
        }
    }

    @Override
    public void checkRefundTrading(EdjTrading trading, BigDecimal refundAmount) {
        if (ObjectUtils.isEmpty(trading)) {
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.NOT_FOUND.getValue());
        }

        if (EnumUtils.ne(EdjTradingState.SETTLED, trading.getTradingState())) {
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.NATIVE_REFUND_FAIL.getValue());
        }

        //退款总金额不可超实付总金额
        if (NumberUtils.isGreater(refundAmount, trading.getTradingAmount())) {
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.BASIC_REFUND_OUT_FAIL.getValue());
        }
    }

    @Override
    public void checkQueryRefundTrading(EdjRefundRecord refundRecord) {
        if (ObjectUtils.isEmpty(refundRecord)) {
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.REFUND_NOT_FOUND.getValue());
        }

        if (EnumUtils.eq(EdjRefundStatus.SUCCESS, refundRecord.getRefundStatus())) {
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.REFUND_ALREADY_COMPLETED.getValue());
        }
    }
}
