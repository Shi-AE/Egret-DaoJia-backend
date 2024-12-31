package com.edj.trade.service.impl;

import com.edj.api.api.trade.enums.TradingChannel;
import com.edj.cache.helper.LockHelper;
import com.edj.common.constants.CommonRedisConstants;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.CommonException;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.trade.constant.Constants;
import com.edj.trade.constant.TradingConstant;
import com.edj.trade.domain.entity.EdjTrading;
import com.edj.trade.enums.EdjTradingState;
import com.edj.trade.enums.TradingEnum;
import com.edj.trade.handler.BeforePayHandler;
import com.edj.trade.handler.HandlerFactory;
import com.edj.trade.handler.NativePayHandler;
import com.edj.trade.service.BasicPayService;
import com.edj.trade.service.EdjTradingService;
import com.edj.trade.service.NativePayService;
import com.edj.trade.service.QRCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.edj.cache.helper.LockHelper.SIMPLE_OPERATION_WAIT_TIME;

/**
 * 二维码本地支付实现
 *
 * @author A.E.
 * @date 2024/12/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NativePayServiceImpl implements NativePayService {

    private final EdjTradingService tradingService;

    private final BasicPayService basicPayService;

    private final BeforePayHandler beforePayHandler;

    private final LockHelper lockHelper;

    private final QRCodeService qrCodeService;

    @Override
    public EdjTrading createDownLineTrading(boolean changeChannel, EdjTrading tradingEntity) {
        // 获取付款中的记录
        EdjTrading trading = tradingService.getDuringTrading(tradingEntity.getProductAppId(), tradingEntity.getProductOrderNo(), tradingEntity.getTradingChannel());
        // 如果切换渠道，关闭原有订单
        if (changeChannel) {
            changeChannelAndCloseTrading(tradingEntity.getProductAppId(), tradingEntity.getProductOrderNo(), tradingEntity.getTradingChannel());
        }
        // 如果存在订单直接返回
        if (ObjectUtils.isNotNull(trading)) {
            return trading;
        }

        // 订单参数校验
        beforePayHandler.checkCreateTrading(tradingEntity);

        tradingEntity.setTradingType(TradingConstant.TRADING_TYPE_FK);
        tradingEntity.setEnableFlag(Constants.YES);

        // 对交易订单加锁
        Long productOrderNo = tradingEntity.getProductOrderNo();
        lockHelper.syncLock(
                String.format(CommonRedisConstants.Lock.TRADE_CREATE, productOrderNo),
                SIMPLE_OPERATION_WAIT_TIME,
                () -> {
                    NativePayHandler nativePayHandler = HandlerFactory.get(tradingEntity.getTradingChannel(), NativePayHandler.class);
                    nativePayHandler.createDownLineTrading(tradingEntity);

                    // 生成统一收款二维码
                    String placeOrderMsg = tradingEntity.getPlaceOrderMsg();
                    String qrCode = qrCodeService.generate(placeOrderMsg, TradingChannel.valueOf(tradingEntity.getTradingChannel()));
                    tradingEntity.setQrCode(qrCode);
                    // 指定交易状态为付款中
                    tradingEntity.setTradingState(EnumUtils.value(EdjTradingState.PAYMENT_IN_PROGRESS, Integer.class));
                    // 新增交易数据
                    boolean flag = tradingService.save(tradingEntity);
                    if (!flag) {
                        throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.SAVE_OR_UPDATE_FAIL.getValue());
                    }
                }
        );
        return tradingEntity;
    }

    /**
     * 切换支付渠道自动关单
     *
     * @param tradingChannel 要切换的目标支付渠道
     * @param productAppId   业务系统标识
     * @param productOrderNo 业务订单号
     */
    private void changeChannelAndCloseTrading(String productAppId, Long productOrderNo, String tradingChannel) {
        // 根据订单号查询交易单
        List<EdjTrading> yjsTradByProductOrderNo = tradingService.getByProductOrder(productAppId, productOrderNo);

        yjsTradByProductOrderNo.forEach(v -> {
            // 与目标支付渠道不同的渠道且支付中的进行关单
            if (ObjectUtils.notEqual(v.getTradingChannel(), tradingChannel) &&
                    v.getTradingState().equals(EnumUtils.value(EdjTradingState.PAYMENT_IN_PROGRESS))
            ) {
                // 关单
                Boolean result = basicPayService.closeTrading(v.getTradingOrderNo());
                log.info("业务系统:{},业务订单:{},切换交易订单:{}的支付渠道为:{},关闭其它支付渠道:{}", productAppId, productOrderNo, v.getTradingOrderNo(), tradingChannel, v.getTradingChannel());
            }
        });
    }
}
