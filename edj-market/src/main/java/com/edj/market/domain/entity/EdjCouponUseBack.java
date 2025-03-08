package com.edj.market.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 优惠券退回表
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
public class EdjCouponUseBack extends EjdBaseEntity {
    /**
     * 优惠券退回
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
     * 回退时间
     */
    private LocalDateTime useBackTime;

    /**
     * 核销时间
     */
    private LocalDateTime writeOffTime;
}