package com.edj.trade.service.impl;

import com.edj.cache.helper.LockHelper;
import com.edj.common.expcetions.CommonException;
import com.edj.common.utils.ObjectUtils;
import com.edj.trade.domain.entity.EdjTrading;
import com.edj.trade.handler.BasicPayHandler;
import com.edj.trade.handler.BeforePayHandler;
import com.edj.trade.handler.HandlerFactory;
import com.edj.trade.service.BasicPayService;
import com.edj.trade.service.EdjTradingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.edj.cache.helper.LockHelper.SIMPLE_OPERATION_WAIT_TIME;
import static com.edj.common.constants.CommonRedisConstants.Lock.TRADE_CLOSE;

/**
 * 支付的基础功能
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BasicPayServiceImpl implements BasicPayService {

    private final EdjTradingService tradingService;

    private final BeforePayHandler beforePayHandler;

    private final LockHelper lockHelper;

    @Override
    public Boolean closeTrading(Long tradingOrderNo) throws CommonException {
        // 通过单号查询交易单数据
        EdjTrading trading = tradingService.findTradByTradingOrderNo(tradingOrderNo);
        if (ObjectUtils.isEmpty(trading)) {
            return true;
        }

        // 入库前置检查
        beforePayHandler.checkCloseTrading(trading);

        // 加锁关闭订单
        lockHelper.syncLock(
                String.format(TRADE_CLOSE, trading.getTradingOrderNo()),
                SIMPLE_OPERATION_WAIT_TIME,
                () -> {
                    // 选取不同的支付渠道实现
                    BasicPayHandler handler = HandlerFactory.get(trading.getTradingChannel(), BasicPayHandler.class);
                    Boolean result = handler.closeTrading(trading);
                    if (result) {
                        trading.setQrCode("");
                        tradingService.saveOrUpdate(trading);
                    }
                }
        );

        return true;
    }
}
