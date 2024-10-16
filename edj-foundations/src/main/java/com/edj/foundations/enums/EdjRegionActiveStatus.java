package com.edj.foundations.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 区域状态枚举
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Getter
@AllArgsConstructor
public enum EdjRegionActiveStatus {

    DRAFTS(0, "草稿"),

    DISABLED(1, "禁用"),

    ENABLED(2, "启用");

    @EnumValue
    private final Integer value;
    private final String describe;
}
