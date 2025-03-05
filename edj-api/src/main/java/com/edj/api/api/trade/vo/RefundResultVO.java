package com.edj.api.api.trade.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 退款结果
 *
 * @author A.E.
 * @date 2025/3/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "退款结果")
public class RefundResultVO {

    /**
     * 支付服务退款单号
     */
    @Schema(description = "支付服务退款单号")
    private Long refundNo;

    /**
     * 第三方支付的退款单号
     */
    @Schema(description = "第三方支付的退款单号")
    private String refundId;

    /**
     * 退款状态（0无退款 1退款中 2退款成功 3退款失败）
     */
    @Schema(description = "退款状态（0无退款 1退款中 2退款成功 3退款失败）")
    private Integer refundStatus;
}
