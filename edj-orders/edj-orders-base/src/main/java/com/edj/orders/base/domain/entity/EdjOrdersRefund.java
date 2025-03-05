package com.edj.orders.base.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 订单退款表
 *
 * @author A.E.
 * @date 2025/3/4
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjOrdersRefund extends EjdBaseEntity {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 支付服务交易单号
     */
    private Long tradingOrderNo;

    /**
     * 实际支付金额
     */
    private BigDecimal realPayAmount;
}