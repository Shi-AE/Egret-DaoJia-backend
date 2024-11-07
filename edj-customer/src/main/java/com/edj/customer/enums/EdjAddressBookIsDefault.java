package com.edj.customer.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 地址簿地址是否默认枚举
 *
 * @author A.E.
 * @date 2024/11/7
 */
@Getter
@AllArgsConstructor
public enum EdjAddressBookIsDefault {

    NOT_DEFAULT(0, "非默认"),
    DEFAULT(1, "默认");

    @EnumValue
    private final Integer value;
    private final String describe;
}
