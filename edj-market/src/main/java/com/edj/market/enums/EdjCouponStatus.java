package com.edj.market.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券状态（1未使用 2已使用 3已过期 4已作废）
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Getter
@AllArgsConstructor
public enum EdjCouponStatus {

    UNUSED(1, "未使用"),
    USED(2, "已使用"),
    EXPIRED(3, "已过期"),
    CANCELLED(4, "已作废");

    @EnumValue
    private final Integer value;
    private final String describe;
}
