package com.edj.orders.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EdjOrderRefundStatus {

    NO_REFUND(0, "无退款"),
    REFUNDING(1, "退款中"),
    REFUND_SUCCESS(2, "退款成功"),
    REFUND_FAILED(3, "退款失败");

    @EnumValue
    private final Integer value;
    private final String describe;
}
