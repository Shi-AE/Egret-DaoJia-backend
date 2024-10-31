package com.edj.publics.service;

/**
 * 验证码服务
 *
 * @author A.E.
 * @date 2024/10/31
 */
public interface SmsCodeService {

    /**
     * 发送短信验证码
     */
    void smsCodeSend(String phoneNumber);

    /**
     * 校验短信验证码
     *
     * @param phone 验证手机号
     * @return 验证结果
     */
    boolean verify(String phone, String verifyCode);
}
