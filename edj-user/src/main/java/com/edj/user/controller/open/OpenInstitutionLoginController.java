package com.edj.user.controller.open;

import com.edj.user.domain.dto.InstitutionRegisterDTO;
import com.edj.user.domain.dto.PhoneLoginDTO;
import com.edj.user.domain.vo.UserTokenVO;
import com.edj.user.service.LoginService;
import com.edj.user.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final RegisterService registerInstitution;

    /**
     * 机构端用户登录
     */
    @PostMapping("login")
    @Operation(summary = "登录")
    @PreAuthorize("isAnonymous()")
    public UserTokenVO consumerLogin(@Validated @RequestBody PhoneLoginDTO phoneLoginDTO, HttpServletRequest request) {
        return loginService.loginForPhone(phoneLoginDTO, request);
    }

    /**
     * 机构端用户注册
     */
    @PostMapping("register")
    @Operation(summary = "机构端用户注册")
    public void register(@Validated @RequestBody InstitutionRegisterDTO institutionRegisterDTO) {
        registerInstitution.institutionRegister(institutionRegisterDTO);
    }
}
