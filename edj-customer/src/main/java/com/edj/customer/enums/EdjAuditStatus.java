package com.edj.customer.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 认证审核状态枚举
 *
 * @author A.E.
 * @date 2024/11/18
 */
@Getter
@AllArgsConstructor
public enum EdjAuditStatus {

    UNREVIEWED(0, "未审核"),
    REVIEWED(1, "已审核");

    @EnumValue
    private final Integer value;
    private final String describe;
}
