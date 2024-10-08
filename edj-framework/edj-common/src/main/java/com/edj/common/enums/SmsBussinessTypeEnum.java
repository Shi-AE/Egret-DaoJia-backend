package com.edj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SmsBussinessTypeEnum {
    INSTITUTION_REGISTER(1),
    INSTITUTION_RESET_PASSWORD(2),
    SERVE_STAFF_LOGIN(3);

    private final int type;
}
