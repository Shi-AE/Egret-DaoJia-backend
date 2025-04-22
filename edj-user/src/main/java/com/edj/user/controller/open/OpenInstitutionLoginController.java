package com.edj.user.controller.open;

import com.edj.user.domain.dto.InstitutionRegisterDTO;
import com.edj.user.domain.dto.PhoneLoginDTO;
import com.edj.user.domain.dto.PhoneResetPasswordDTO;
import com.edj.user.domain.vo.UserTokenVO;
import com.edj.user.service.LoginService;
import com.edj.user.service.RegisterService;
import com.edj.user.service.ResetPasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Institution 机构端用户登录
 *
 * @author A.E.
 * @date 2024/10/31
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Institution 机构端用户登录")
@RequestMapping("open/institution")
public class OpenInstitutionLoginController {

    private final LoginService loginService;

    private final RegisterService registerService;

    private final ResetPasswordService resetPasswordService;

    /**
     * 机构端用户登录
     */
    @PostMapping("login")
    @Operation(summary = "登录")
    public UserTokenVO consumerLogin(@Validated @RequestBody PhoneLoginDTO phoneLoginDTO, HttpServletRequest request) {
        return loginService.loginForPhone(phoneLoginDTO, request);
    }

    /**
     * 机构端用户注册
     */
    @PostMapping("register")
    @Operation(summary = "机构端用户注册")
    public void register(@Validated @RequestBody InstitutionRegisterDTO institutionRegisterDTO) {
        registerService.institutionRegister(institutionRegisterDTO);
    }

    /**
     * 机构重置登录密码
     */
    @PostMapping("reset/password")
    @Operation(summary = "机构重置登录密码")
    public void resetPassword(@Validated @RequestBody PhoneResetPasswordDTO phoneResetPasswordDTO) {
        resetPasswordService.resetPasswordForPhone(phoneResetPasswordDTO);
    }
}
