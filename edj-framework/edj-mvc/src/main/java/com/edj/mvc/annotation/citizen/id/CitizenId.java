package com.edj.mvc.annotation.citizen.id;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 公民身份证校验
 *
 * @author A.E.
 * @date 2024/11/15
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CitizenIdValidator.class)
public @interface CitizenId {

    String message() default "公民身份证格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
