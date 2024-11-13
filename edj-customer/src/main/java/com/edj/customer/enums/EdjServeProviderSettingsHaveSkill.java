package com.edj.customer.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否有技能（1有 0无）
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Getter
@AllArgsConstructor
public enum EdjServeProviderSettingsHaveSkill {

    SKILLED(1, "有技能"),

    UNSKILLED(0, "无技能");

    @EnumValue
    private final Integer value;
    private final String describe;
}
