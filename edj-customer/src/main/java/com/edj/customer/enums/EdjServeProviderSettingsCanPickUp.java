package com.edj.customer.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务者接单设置（-1未知 0关闭接单 1开启接单）
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Getter
@AllArgsConstructor
public enum EdjServeProviderSettingsCanPickUp {

    UNKNOWN(-1, "未知"),

    OFF(0, "关闭接单"),

    ON(1, "开启接单");

    @EnumValue
    private final Integer value;
    private final String describe;
}
