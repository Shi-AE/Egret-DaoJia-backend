package com.edj.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户账号状态
 *
 * @author A.E.
 * @date 2024/10/6
 */
@Getter
@AllArgsConstructor
public enum EdjUserStatus {

    NORMAL(0, "正常"),
    FROZEN(1, "冻结");

    @EnumValue
    private final Integer value;
    private final String describe;
}
