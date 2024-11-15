package com.edj.customer.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 认证状态枚举
 *
 * @author A.E.
 * @date 2024/11/15
 */
@Getter
@AllArgsConstructor
public enum EdjCertificationStatus {

    INITIAL(0, "初始态"),
    IN_PROGRESS(1, "认证中"),
    SUCCESS(2, "认证成功"),
    FAILURE(3, "认证失败");

    @EnumValue
    private final Integer value;
    private final String describe;
}
