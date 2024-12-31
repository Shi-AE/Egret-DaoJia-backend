package com.edj.api.api.trade.dto;

import com.edj.api.api.trade.enums.TradingChannel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Native 支付参数模型
 *
 * @author A.E.
 * @date 2024/12/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Native 支付参数模型")
public class NativePayDTO {

    /**
     * 商户号
     */
    @Schema(description = "商户号")
    private Long enterpriseId;

    /**
     * 业务系统标识
     */
    @Schema(description = "业务系统标识")
    private String productAppId;

    /**
     * 业务系统订单号
     */
    @Schema(description = "业务系统订单号")
    private Long productOrderNo;

    /**
     * 支付渠道
     */
    @Schema(description = "支付渠道")
    private TradingChannel tradingChannel;

    /**
     * 交易金额，单位：元
     */
    @Schema(description = "交易金额，单位：元")
    private BigDecimal tradingAmount;

    /**
     * 备注，如：运费
     */
    @Schema(description = "备注，如：运费")
    private String memo;

    /**
     * 是否切换支付渠道
     */
    @Schema(description = "是否切换支付渠道")
    private boolean changeChannel = false;
}
