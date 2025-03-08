package com.edj.market.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券类型（1满减 2折扣）
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Getter
@AllArgsConstructor
public enum EdjCouponType {

    AMOUNT_DISCOUNT(1, "满减"),
    RATE_DISCOUNT(2, "打折");

    @EnumValue
    private final Integer value;
    private final String describe;
}
