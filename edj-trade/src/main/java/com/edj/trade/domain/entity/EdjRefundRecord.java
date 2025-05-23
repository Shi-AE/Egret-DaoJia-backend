package com.edj.trade.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款记录表
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EdjRefundRecord {
    /**
     * 退款记录id
     */
    private Long id;

    /**
     * 交易系统订单号（对于三方来说：商户订单）
     */
    private Long tradingOrderNo;

    /**
     * 业务系统应用标识
     */
    private String productAppId;

    /**
     * 业务系统订单号
     */
    private Long productOrderNo;

    /**
     * 本次退款订单号
     */
    private Long refundNo;

    /**
     * 第三方支付的退款单号
     */
    private String refundId;

    /**
     * 商户号
     */
    private Long enterpriseId;

    /**
     * 退款渠道（支付宝、微信、现金）
     */
    private String tradingChannel;

    /**
     * 退款状态（1退款中 2成功 3失败）
     */
    private Integer refundStatus;

    /**
     * 返回编码
     */
    private String refundCode;

    /**
     * 返回信息
     */
    private String refundMsg;

    /**
     * 备注（订单门店，桌台信息）
     */
    private String memo;

    /**
     * 本次退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 原订单金额
     */
    private BigDecimal total;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}