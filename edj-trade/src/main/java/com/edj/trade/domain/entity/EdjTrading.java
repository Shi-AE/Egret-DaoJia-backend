package com.edj.trade.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 交易订单表
 *
 * @author A.E.
 * @date 2024/12/30
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjTrading extends EjdBaseEntity {
    /**
     * 交易订单id
     */
    private Long id;

    /**
     * 业务系统应用标识
     */
    private String productAppId;

    /**
     * 业务系统订单号
     */
    private Long productOrderNo;

    /**
     * 交易系统订单号（对于三方来说：商户订单）
     */
    private Long tradingOrderNo;

    /**
     * 第三方支付交易号
     */
    private String transactionId;

    /**
     * 支付渠道（支付宝、微信、现金、免单挂账）
     */
    private String tradingChannel;

    /**
     * 交易类型（付款、退款、免单、挂账）
     */
    private String tradingType;

    /**
     * 交易单状态（1待付款 2付款中 3付款失败 4已结算 5取消订单 6免单 7挂账）
     */
    private Integer tradingState;

    /**
     * 收款人姓名
     */
    private String payeeName;

    /**
     * 收款人账户id
     */
    private Long payeeId;

    /**
     * 付款人姓名
     */
    private String payerName;

    /**
     * 付款人id
     */
    private Long payerId;

    /**
     * 交易金额，单位：元
     */
    private BigDecimal tradingAmount;

    /**
     * 退款金额（付款后，单位：元）
     */
    private BigDecimal refund;

    /**
     * 是否有退款（YES，NO）
     */
    private String isRefund;

    /**
     * 第三方交易返回编码（最终确认交易结果）
     */
    private String resultCode;

    /**
     * 第三方交易返回提示消息（最终确认交易信息）
     */
    private String resultMsg;

    /**
     * 第三方交易返回信息json（分析交易最终信息）
     */
    private String resultJson;

    /**
     * 统一下单返回编码
     */
    private String placeOrderCode;

    /**
     * 统一下单返回信息
     */
    private String placeOrderMsg;

    /**
     * 统一下单返回信息json（用于生产二维码、Android ios唤醒支付等）
     */
    private String placeOrderJson;

    /**
     * 商户号
     */
    private Long enterpriseId;

    /**
     * 备注（订单门店，桌台信息）
     */
    private String memo;

    /**
     * 二维码base64数据
     */
    private String qrCode;

    /**
     * open_id标识
     */
    private String openId;

    /**
     * 是否有效
     */
    private String enableFlag;
}