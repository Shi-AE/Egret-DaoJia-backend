package com.edj.api.api.market.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CouponUseVO {

    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;
}
