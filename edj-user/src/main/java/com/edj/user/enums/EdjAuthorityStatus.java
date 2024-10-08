package com.edj.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * disabled or normal
 */
@Getter
@AllArgsConstructor
public enum EdjAuthorityStatus {

    NORMAL(0, "正常"),
    DISABLED(1, "停用");

    @EnumValue
    private final Integer value;
    private final String describe;
}
