package com.edj.foundations.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务是否热门枚举
 *
 * @author A.E.
 * @date 2024/10/24
 */
@Getter
@AllArgsConstructor
public enum EdjServeIsHot {

    HOT(1, "热门;"),
    NOT_HOT(0, "非热门");

    @EnumValue
    private final Integer value;
    private final String describe;
}
