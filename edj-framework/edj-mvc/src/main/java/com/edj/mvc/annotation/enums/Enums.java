package com.edj.mvc.annotation.enums;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>属性枚举类元素约束
 * <p>必须为枚举内元素
 * <p>字段为 Integer 类型
 * <p>枚举类型必须标注 @EnumValue 注解
 *
 * @author A.E.
 * @date 2024/10/15
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumsValidator.class)
public @interface Enums {

    Class<? extends Enum<?>> value();

    String message() default "属性枚举类元素校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
