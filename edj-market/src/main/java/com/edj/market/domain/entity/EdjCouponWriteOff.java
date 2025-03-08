package com.edj.market.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 优惠券核销表
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
public class EdjCouponWriteOff extends EjdBaseEntity {
    /**
     * 优惠券核销id
     */
    private Long id;

    /**
     * 优惠券id
     */
    private Long edjCouponId;

    /**
     * 用户id
     */
    private Long edjUserId;

    /**
     * 核销时使用的订单号
     */
    private Long edjOrdersId;

    /**
     * 优惠券活动id
     */
    private Long edjActivityId;

    /**
     * 核销时间
     */
    private LocalDateTime writeOffTime;

    /**
     * 核销人手机号
     */
    private String writeOffManPhone;

    /**
     * 核销人姓名
     */
    private String writeOffManName;
}