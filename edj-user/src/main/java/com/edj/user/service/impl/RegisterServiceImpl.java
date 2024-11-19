package com.edj.user.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.edj.api.api.customer.ProviderApi;
import com.edj.api.api.publics.SmsCodeApi;
import com.edj.api.api.publics.dto.SmsCodeDTO;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.AsyncUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.IdUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.user.domain.dto.InstitutionRegisterDTO;
import com.edj.user.domain.entity.EdjUser;
import com.edj.user.domain.entity.EdjUserRole;
import com.edj.user.enums.EdjSysRole;
import com.edj.user.mapper.EdjUserMapper;
import com.edj.user.mapper.EdjUserRoleMapper;
import com.edj.user.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

/**
 * 注册服务实现
 *
 * @author A.E.
 * @date 2024/11/1
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final SmsCodeApi smsCodeApi;

    private final ProviderApi providerApi;

    private final EdjUserMapper userMapper;

    private final EdjUserRoleMapper userRoleMapper;

    private final Snowflake snowflake;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void institutionRegister(InstitutionRegisterDTO institutionRegisterDTO) {
        // 检验验证码
        String phone = institutionRegisterDTO.getPhone();
        String verifyCode = institutionRegisterDTO.getVerifyCode();
        SmsCodeDTO verify = smsCodeApi.verify(phone, verifyCode);
        if (!verify.getIsSuccess()) {
            throw new BadRequestException("短信验证码校验失败");
        }

        // 生成用户标识
        String username = IdUtils.toWechatUserName(snowflake.nextId());
        String password = institutionRegisterDTO.getPassword();

        // 新增机构
        EdjUser user = EdjUser
                .builder()
                .username(username)
                .nickname(username)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phone)
                .build();
        userMapper.insert(user);

        // 添加机构端基础权限
        Long id = user.getId();
        if (ObjectUtils.isEmpty(id)) {
            log.error("服务端手机号用户注册失败: {}", user);
            throw new ServerErrorException("用户注册失败");
        }

        CompletableFuture<Void> future1 = AsyncUtils.runAsyncComplete(() -> userRoleMapper.insert(EdjUserRole
                .builder()
                .edjUserId(id)
                .edjRoleId((Long) EnumUtils.value(EdjSysRole.INSTITUTION))
                .build()
        ));

        // 创建额外信息
        CompletableFuture<Void> future2 = AsyncUtils.runAsyncComplete(() -> providerApi.add(id));

        CompletableFuture.allOf(future1, future2).join();
    }
}
