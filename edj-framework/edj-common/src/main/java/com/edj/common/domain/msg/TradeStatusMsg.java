package com.edj.common.domain.msg;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 交易状态消息
 *
 * @author A.E.
 * @date 2024/9/20
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TradeStatusMsg {

    /**
     * 交易单号
     */
    private Long tradingOrderNo;

    /**
     * 业务系统标识
     */
    private String productAppId;
    /**
     * 订单号
     */
    private Long productOrderNo;

    /**
     * 第三方支付的交易号
     */
    private String transactionId;

    /**
     * 支付渠道
     */
    private String tradingChannel;

    /**
     * 退款单号
     */
    private Long refundNo;

    /**
     * 支付/退款 状态名称
     */
    private String statusName;

    /**
     * 支付/退款 状态编码
     */
    private Integer statusCode;

    /**
     * 扩展信息
     */
    private String info;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    public String toJson() {
        return JSONUtil.toJsonStr(this);
    }
}
