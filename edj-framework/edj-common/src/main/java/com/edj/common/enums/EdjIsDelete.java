package com.edj.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据逻辑删除
 *
 * @author A.E.
 * @date 2024/10/6
 */
@Getter
@AllArgsConstructor
public enum EdjIsDelete {

    EXISTS(0, "存在"),
    DELETED(1, "删除");

    @EnumValue
    private final Integer value;
    private final String describe;
}
