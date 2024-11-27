package com.edj.user.service;

import com.edj.user.domain.dto.PhoneResetPasswordDTO;

/**
 * 重置密码服务
 *
 * @author A.E.
 * @date 2024/11/27
 */
public interface ResetPasswordService {

    /**
     * 手机验证码重置
     */
    void resetPasswordForPhone(PhoneResetPasswordDTO phoneResetPasswordDTO);
}
