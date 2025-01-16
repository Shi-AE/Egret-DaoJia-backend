package com.edj.orders.manager.domain.dto;

import com.edj.api.api.trade.enums.TradingChannel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付订单模型
 *
 * @author A.E.
 * @date 2024/12/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "支付订单模型")
public class OrdersPayDTO {
    /**
     * 订单id
     */
    @Schema(description = "订单id")
    @NotNull(message = "订单错误")
    @Positive(message = "订单错误")
    private Long id;

    /**
     * 支付渠道
     */
    @NotNull(message = "支付渠道不能为空")
    private TradingChannel tradingChannel;
}
