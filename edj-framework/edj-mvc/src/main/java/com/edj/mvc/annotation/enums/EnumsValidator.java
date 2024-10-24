package com.edj.mvc.annotation.enums;

import com.edj.common.utils.EnumUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;


/**
 * 属性枚举类元素约束实现
 *
 * @author A.E.
 * @date 2024/10/15
 */
@Slf4j
public class EnumsValidator implements ConstraintValidator<Enums, Integer> {

    private Class<? extends Enum<?>> value;

    @Override
    public void initialize(Enums constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer target, ConstraintValidatorContext constraintValidatorContext) {
        // 放行空数据
        if (target == null) {
            return true;
        }
        Enum<?>[] enumConstants = value.getEnumConstants();
        return Arrays.stream(enumConstants).anyMatch(x -> EnumUtils.eq(x, target));
    }
}