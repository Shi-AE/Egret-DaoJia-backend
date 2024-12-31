package com.edj.trade.handler.impl;

import cn.hutool.core.lang.Snowflake;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.CommonException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.NumberUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.trade.domain.entity.EdjTrading;
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
        // 金额大于零
        boolean f2 = NumberUtils.isGreater(tradingEntity.getTradingAmount(), BigDecimal.ZERO);
        if (!f1 || !f2) {
            throw new ServerErrorException();
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
}
