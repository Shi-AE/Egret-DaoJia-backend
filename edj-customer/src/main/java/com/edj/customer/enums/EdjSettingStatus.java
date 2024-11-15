package com.edj.customer.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 设置状态枚举
 */
@Getter
@AllArgsConstructor
public enum EdjSettingStatus {

    NOT_COMPLETED(0, "未完成"),
    COMPLETED(1, "已完成");

    @EnumValue
    private final Integer value;
    private final String describe;
}
