package com.edj.orders.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单支付状态
 *
 * @author A.E.
 * @date 2024/12/26
 */
@Getter
@AllArgsConstructor
public enum EdjOrderPayStatus {

    PENDING(2, "待支付"),
    SUCCESS(4, "支付成功");

    @EnumValue
    private final Integer value;
    private final String describe;
}
