package com.edj.trade.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.ObjectUtil;
import com.edj.api.api.trade.vo.TradingVO;
import com.edj.cache.helper.LockHelper;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.CommonException;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.trade.constant.Constants;
import com.edj.trade.domain.entity.EdjRefundRecord;
import com.edj.trade.domain.entity.EdjTrading;
import com.edj.trade.enums.EdjRefundStatus;
import com.edj.trade.enums.EdjTradingState;
import com.edj.trade.enums.TradingEnum;
import com.edj.trade.handler.BasicPayHandler;
import com.edj.trade.handler.BeforePayHandler;
import com.edj.trade.handler.HandlerFactory;
import com.edj.trade.service.BasicPayService;
import com.edj.trade.service.EdjRefundRecordService;
import com.edj.trade.service.EdjTradingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.edj.cache.helper.LockHelper.COMPLEX_OPERATION_WAIT_TIME;
import static com.edj.common.constants.CommonRedisConstants.Lock.*;

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

    private final EdjRefundRecordService refundRecordService;

    private final Snowflake snowflake;

    @Override
    public TradingVO getTradingResult(Long tradingOrderNo) {

        // 通过单号查询交易数据
        EdjTrading trading = tradingService.findTradByTradingOrderNo(tradingOrderNo);
        if (ObjectUtils.isNull(trading)) {
            return null;
        }

        // 如果已付款或已取消
        if (EnumUtils.eqAny(trading.getTradingState(), EdjTradingState.SETTLED, EdjTradingState.ORDER_CANCELLED)) {
            return BeanUtils.toBean(trading, TradingVO.class);
        }

        // 查询前置检查
        beforePayHandler.checkQueryTrading(trading);

        // 查询加锁
        lockHelper.syncLock(
                String.format(TRADE_QUERY, tradingOrderNo),
                COMPLEX_OPERATION_WAIT_TIME,
                () -> {
                    BasicPayHandler handler = HandlerFactory.get(trading.getTradingChannel(), BasicPayHandler.class);
                    Boolean result = handler.queryTrading(trading);
                    if (result) {
                        // 如果交易已完成，删除二维码数据，节省数据库空间
                        if (EnumUtils.eqAny(trading.getTradingState(), EdjTradingState.SETTLED, EdjTradingState.ORDER_CANCELLED)) {
                            trading.setQrCode("");
                        }
                        // 更新数据
                        tradingService.updateById(trading);
                    }
                }
        );
        return BeanUtils.toBean(trading, TradingVO.class);
    }

    @Override
    public EdjRefundRecord refundTrading(Long tradingOrderNo, BigDecimal refundAmount) {
        // 获取交易数据
        EdjTrading trading = tradingService.findTradByTradingOrderNo(tradingOrderNo);

        // 检查交易数据
        beforePayHandler.checkRefundTrading(trading, refundAmount);

        // 检查是否已经退款成功
        // 同步退款状态
        syncRefundResult(tradingOrderNo);

        // 查询退款记录
        List<EdjRefundRecord> refundRecordList = refundRecordService.findByOrderNo(trading.getTradingOrderNo());
        // 当没有退款成功和退款中的记录时方可继续退款
        boolean exist = refundRecordList
                .stream()
                .anyMatch(r -> EnumUtils.eqAny(
                        r.getRefundStatus(),
                        EdjRefundStatus.REFUNDING,
                        EdjRefundStatus.SUCCESS
                ));

        if (!exist) {
            // 加锁退款
            return lockHelper.syncLockResult(
                    String.format(TRADE_REFUND, tradingOrderNo),
                    COMPLEX_OPERATION_WAIT_TIME,
                    () -> {
                        // 设置退款金额
                        trading.setRefund(refundAmount);

                        // 构造退款记录
                        EdjRefundRecord refundRecord = EdjRefundRecord
                                .builder()
                                .refundNo(snowflake.nextId())
                                .tradingOrderNo(trading.getTradingOrderNo())
                                .productOrderNo(trading.getProductOrderNo())
                                .productAppId(trading.getProductAppId())
                                .refundAmount(refundAmount)
                                .enterpriseId(trading.getEnterpriseId())
                                .tradingChannel(trading.getTradingChannel())
                                .total(trading.getTradingAmount())
                                // 初始化为退款中
                                .refundStatus(EnumUtils.value(EdjRefundStatus.REFUNDING, Integer.class))
                                .build();
                        refundRecordService.save(refundRecord);

                        // 交易单状态设置为退款
                        trading.setIsRefund(Constants.YES);
                        tradingService.updateById(trading);

                        // 请求第三方退款
                        // 选取不同的支付渠道实现
                        BasicPayHandler handler = HandlerFactory.get(refundRecord.getTradingChannel(), BasicPayHandler.class);
                        Boolean result = handler.refundTrading(refundRecord);
                        if (result) {
                            // 更新退款记录数据
                            refundRecordService.saveOrUpdate(refundRecord);
                        }
                        return refundRecord;
                    }
            );
        } else {
            // 取出第一条记录返回
            EdjRefundRecord first = refundRecordList.getFirst();
            if (ObjectUtil.isNotNull(first)) {
                return first;
            }
        }
        throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.NATIVE_QUERY_FAIL.getValue());
    }

    @Override
    public void syncRefundResult(Long tradingOrderNo) {
        List<EdjRefundRecord> refundRecordList = refundRecordService.findRefundingByOrderNo(tradingOrderNo);
        if (CollUtils.isNotEmpty(refundRecordList)) {
            // 查询退款中记录是否退款成功
            for (EdjRefundRecord edjRefundRecord : refundRecordList) {
                queryRefundTrading(edjRefundRecord.getRefundNo());
            }
        }
    }

    @Override
    public EdjRefundRecord queryRefundTrading(Long refundNo) {

        //通过单号查询交易单数据
        EdjRefundRecord refundRecord = refundRecordService.findByRefundNo(refundNo);

        //查询前置处理
        beforePayHandler.checkQueryRefundTrading(refundRecord);

        // 查询退款结果加锁
        lockHelper.syncLock(
                String.format(TRADE_REFUND_QUERY, refundNo),
                COMPLEX_OPERATION_WAIT_TIME,
                () -> {
                    // 选取不同的支付渠道实现
                    BasicPayHandler handler = HandlerFactory.get(refundRecord.getTradingChannel(), BasicPayHandler.class);
                    Boolean result = handler.queryRefundTrading(refundRecord);
                    if (result) {
                        // 更新数据
                        refundRecordService.saveOrUpdate(refundRecord);
                    }
                }
        );
        return refundRecord;
    }


    @Override
    public Boolean closeTrading(Long tradingOrderNo) {
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
                COMPLEX_OPERATION_WAIT_TIME,
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
