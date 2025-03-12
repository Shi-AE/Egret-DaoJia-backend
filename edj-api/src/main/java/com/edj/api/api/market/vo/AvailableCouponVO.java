package com.edj.api.api.market.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 可用优惠券
 *
 * @author A.E.
 * @date 2025/3/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "可用优惠券")
public class AvailableCouponVO {
    /**
     * 优惠券id
     */
    private Long id;

    /**
     * 优惠券活动id
     */
    private Long edjActivityId;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券类型（1满减 2折扣）
     */
    private Integer type;

    /**
     * 折扣率（单位%，折扣类型优惠券）
     */
    private Integer discountRate;

    /**
     * 优惠金额（单位元，满减类型优惠券）
     */
    private BigDecimal discountAmount;

    /**
     * 使用条件（最低消费金额）
     */
    private BigDecimal amountCondition;

    /**
     * 有效期
     */
    private LocalDateTime validityTime;
}
