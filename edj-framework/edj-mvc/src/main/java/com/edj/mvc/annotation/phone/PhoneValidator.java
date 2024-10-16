package com.edj.mvc.annotation.phone;

import com.edj.common.utils.StringUtils;
import com.edj.common.utils.ValidUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 手机号检验实现
 *
 * @author A.E.
 * @date 2024/10/16
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(phone)) {
            return true;
        }
        return ValidUtils.isMobile(phone);
    }
}
