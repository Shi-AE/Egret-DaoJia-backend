package com.edj.api.api.market.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 使用优惠券
 *
 * @author A.E.
 * @date 2025/3/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "使用优惠券")
public class CouponUseDTO {

    /**
     * 优惠券id
     */
    @NotNull
    @Positive
    @Schema(description = "优惠券id")
    private Long id;

    /**
     * 订单id
     */
    @NotNull
    @Positive
    @Schema(description = "订单id")
    private Long ordersId;

    /**
     * 用户id
     */
    @NotNull
    @Positive
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 总金额
     */
    @NotNull
    @Schema(description = "总金额")
    private BigDecimal totalAmount;
}
