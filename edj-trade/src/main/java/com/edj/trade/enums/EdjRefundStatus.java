package com.edj.trade.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EdjRefundStatus {

    REFUNDING(1, "退款中"),
    SUCCESS(2, "成功"),
    FAILED(3, "失败");

    @EnumValue
    private final Integer value;
    private final String describe;
}