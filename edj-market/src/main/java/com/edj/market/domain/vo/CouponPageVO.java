package com.edj.market.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券查询
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "优惠券查询")
public class CouponPageVO {
    /**
     * 优惠券id
     */
    @Schema(description = "优惠券id")
    private Long id;

    /**
     * 优惠券活动id
     */
    @Schema(description = "优惠券活动id")
    private Long activityId;

    /**
     * 订单id
     */
    @Schema(description = "订单id")
    private Long ordersId;

    /**
     * 优惠券名称
     */
    @Schema(description = "优惠券名称")
    private String name;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    private String username;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String nickname;

    /**
     * 用户手机号
     */
    @Schema(description = "用户手机号")
    private String userPhone;

    /**
     * 优惠券类型（1满减 2折扣）
     */
    @Schema(description = "优惠券类型（1满减 2折扣）")
    private Integer type;

    /**
     * 折扣率（单位%，折扣类型优惠券）
     */
    @Schema(description = "折扣率（单位%，折扣类型优惠券）")
    private Integer discountRate;

    /**
     * 优惠金额（单位元，满减类型优惠券）
     */
    @Schema(description = "优惠金额（单位元，满减类型优惠券）")
    private BigDecimal discountAmount;

    /**
     * 使用条件（最低消费金额）
     */
    @Schema(description = "使用条件（最低消费金额）")
    private BigDecimal amountCondition;

    /**
     * 有效期
     */
    @Schema(description = "有效期")
    private LocalDateTime validityTime;

    /**
     * 使用时间
     */
    @Schema(description = "使用时间")
    private LocalDateTime useTime;

    /**
     * 优惠券状态（1未使用 2已使用 3已过期 4已作废）
     */
    @Schema(description = "优惠券状态（1未使用 2已使用 3已过期 4已作废）")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
