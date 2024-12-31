package com.edj.api.api.trade.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 本地支付响应
 *
 * @author A.E.
 * @date 2024/12/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "本地支付响应")
public class NativePayVO {

    /**
     * 二维码base64数据
     */
    @Schema(description = "二维码base64数据")
    private String qrCode;

    /**
     * 业务系统订单号
     */
    @Schema(description = "业务系统订单号")
    private Long productOrderNo;

    /**
     * 交易系统订单号（对于三方来说：商户订单）
     */
    @Schema(description = "交易系统订单号（对于三方来说：商户订单）")
    private Long tradingOrderNo;

    /**
     * 支付渠道（支付宝、微信、现金、免单挂账）
     */
    @Schema(description = "支付渠道（支付宝、微信、现金、免单挂账）")
    private String tradingChannel;

}
