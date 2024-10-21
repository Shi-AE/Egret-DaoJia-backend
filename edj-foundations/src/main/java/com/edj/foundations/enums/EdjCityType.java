package com.edj.foundations.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 城市类型枚举
 *
 * @author A.E.
 * @date 2024/10/21
 */
@Getter
@AllArgsConstructor
public enum EdjCityType {

    PROVINCE(1, "省"),
    CITY(2, "市");

    @EnumValue
    private final Integer value;
    private final String describe;
}
