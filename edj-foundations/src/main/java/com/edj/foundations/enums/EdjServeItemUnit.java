package com.edj.foundations.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务项数量单位枚举
 *
 * @author A.E.
 * @date 2024/10/15
 */
@Getter
@AllArgsConstructor
public enum EdjServeItemUnit {

    HOUR(1, "小时"),
    DAY(2, "天"),
    TIME(3, "次"),
    DEVICE(4, "台"),
    PIECE(5, "个"),
    SQUARE_METER(6, "平方米"),
    METER(7, "米");

    @EnumValue
    private final Integer value;
    private final String describe;
}
