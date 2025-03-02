package com.edj.orders.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单是否展示
 *
 * @author A.E.
 * @date 2025/3/1
 */
@Getter
@AllArgsConstructor
public enum EdjOrderDisplay {

    DISPLAY(1, "展示"),
    HIDDEN(0, "隐藏");

    @EnumValue
    private final Integer value;
    private final String describe;
}
