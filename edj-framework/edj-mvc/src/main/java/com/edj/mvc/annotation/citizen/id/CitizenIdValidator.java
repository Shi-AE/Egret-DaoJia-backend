package com.edj.mvc.annotation.citizen.id;

import com.edj.common.utils.StringUtils;
import com.edj.common.utils.ValidUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 公民身份证校验实现
 *
 * @author A.E.
 * @date 2024/11/15
 */
public class CitizenIdValidator implements ConstraintValidator<CitizenId, String> {
    @Override
    public boolean isValid(String citizenId, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(citizenId)) {
            return true;
        }
        return ValidUtils.isCitizenId(citizenId);
    }
}
