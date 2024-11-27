package com.edj.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.api.api.publics.SmsCodeApi;
import com.edj.api.api.publics.dto.SmsCodeDTO;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.BooleanUtils;
import com.edj.user.domain.dto.PhoneResetPasswordDTO;
import com.edj.user.domain.entity.EdjUser;
import com.edj.user.mapper.EdjUserMapper;
import com.edj.user.service.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final SmsCodeApi smsCodeApi;

    private final PasswordEncoder passwordEncoder;

    private final EdjUserMapper userMapper;

    @Override
    public void resetPasswordForPhone(PhoneResetPasswordDTO phoneResetPasswordDTO) {

        String password = phoneResetPasswordDTO.getPassword();
        String phone = phoneResetPasswordDTO.getPhone();
        String verifyCode = phoneResetPasswordDTO.getVerifyCode();

        SmsCodeDTO verify = smsCodeApi.verify(phone, verifyCode);
        if (BooleanUtils.isFalse(verify.getIsSuccess())) {
            throw new BadRequestException("验证码错误");
        }

        LambdaUpdateWrapper<EdjUser> wrapper = new LambdaUpdateWrapper<EdjUser>()
                .set(EdjUser::getPassword, passwordEncoder.encode(password))
                .eq(EdjUser::getPhoneNumber, phone);

        userMapper.update(wrapper);
    }
}
