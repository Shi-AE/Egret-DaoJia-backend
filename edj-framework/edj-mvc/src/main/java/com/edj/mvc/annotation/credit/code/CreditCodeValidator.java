package com.edj.mvc.annotation.credit.code;

import com.edj.common.utils.StringUtils;
import com.edj.common.utils.ValidUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 统一社会信用代码实现
 *
 * @author A.E.
 * @date 2024/11/15
 */
public class CreditCodeValidator implements ConstraintValidator<CreditCode, String> {
    @Override
    public boolean isValid(String creditCode, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(creditCode)) {
            return true;
        }
        return ValidUtils.isCreditCode(creditCode);
    }
}
