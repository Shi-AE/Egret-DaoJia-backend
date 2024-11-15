package com.edj.mvc.annotation.credit.code;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 统一社会信用代码校验
 *
 * @author A.E.
 * @date 2024/11/15
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreditCodeValidator.class)
public @interface CreditCode {

    String message() default "统一社会信用代码证格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
