package com.edj.trade.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author A.E.
 * @date 2024/12/30
 */
@Getter
@AllArgsConstructor
public enum EdjTradingState {

    PENDING_PAYMENT(1, "待付款"),
    PAYMENT_IN_PROGRESS(2, "付款中"),
    PAYMENT_FAILED(3, "付款失败"),
    SETTLED(4, "已结算"),
    ORDER_CANCELLED(5, "取消订单"),
    FREE_ORDER(6, "免单"),
    ACCOUNT_PENDING(7, "挂账");

    @EnumValue
    private final Integer value;
    private final String describe;
}