package com.edj.orders.base.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 订单取消表
 *
 * @author A.E.
 * @date 2025/3/3
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjOrdersCanceled extends EjdBaseEntity {
    /**
     * 订单取消id
     */
    private Long id;

    /**
     * 取消人id
     */
    private Long cancellerId;

    /**
     * 取消人名称
     */
    private String cancellerName;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;
}