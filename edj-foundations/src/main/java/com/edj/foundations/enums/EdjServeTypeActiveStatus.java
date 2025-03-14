package com.edj.foundations.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务类型状态枚举
 *
 * @author A.E.
 * @date 2024/10/14
 */
@Getter
@AllArgsConstructor
public enum EdjServeTypeActiveStatus {

    DRAFTS(0, "草稿"),

    DISABLED(1, "禁用"),

    ENABLED(2, "启用");

    @EnumValue
    private final Integer value;
    private final String describe;
}
