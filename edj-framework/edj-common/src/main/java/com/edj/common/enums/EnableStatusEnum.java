package com.edj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnableStatusEnum {
    UNKNOWN(-1, "未知"),
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private final int status;
    private final String description;

    public boolean equals(Integer status) {
        return this.status == status;
    }

    public boolean equals(EnableStatusEnum enableStatusEnum) {
        return enableStatusEnum != null && enableStatusEnum.status == this.getStatus();
    }
}
