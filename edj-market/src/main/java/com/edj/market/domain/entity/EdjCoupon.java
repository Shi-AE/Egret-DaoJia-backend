package com.edj.market.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户优惠券表
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjCoupon extends EjdBaseEntity {
    /**
     * 优惠券id
     */
    private Long id;

    /**
     * 优惠券拥有者
     */
    private Long edjUserId;

    /**
     * 优惠券活动id
     */
    private Long edjActivityId;

    /**
     * 订单id
     */
    private Long edjOrdersId;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户名称
     */
    private String nickname;

    /**
     * 用户手机号
     */
    private String userPhone;

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

    /**
     * 使用时间
     */
    private LocalDateTime useTime;

    /**
     * 优惠券状态（1未使用 2已使用 3已过期）
     */
    private Integer status;
}