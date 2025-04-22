package com.edj.user.controller.open;

import com.edj.common.domain.Result;
import com.edj.user.domain.dto.UserLoginDTO;
import com.edj.user.domain.entity.EdjUser;
import com.edj.user.domain.vo.UserTokenVO;
import com.edj.user.mapper.EdjUserMapper;
import com.edj.user.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 用户控制类
 *
 * @author A.E.
 * @date 2024/9/29
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "用户管理")
@RequestMapping("open")
public class OpenLoginController {

    private final PasswordEncoder passwordEncoder;

    private final EdjUserMapper userMapper;

    private final LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("login")
    @Operation(summary = "登录")
    public UserTokenVO login(@Validated @RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        return loginService.loginForUsername(userLoginDTO, request);
    }

    /**
     * 注册
     */
    @PostMapping("register")
    @Operation(summary = "注册")
    public Result<Void> register() {
        userMapper.insert(EdjUser
                .builder()
                .username(UUID.randomUUID().toString().substring(18))
                .password(passwordEncoder.encode("123456"))
                .build()
        );
        return Result.success();
    }
}
