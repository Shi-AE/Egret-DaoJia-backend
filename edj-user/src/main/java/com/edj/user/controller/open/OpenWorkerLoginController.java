package com.edj.user.controller.open;

import com.edj.user.domain.dto.PhoneLoginDTO;
import com.edj.user.domain.vo.UserTokenVO;
import com.edj.user.service.LoginService;
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
 * Worker 服务端用户登录
 *
 * @author A.E.
 * @date 2024/10/31
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Worker 服务端用户登录")
@RequestMapping("open/worker")
public class OpenWorkerLoginController {

    private final LoginService loginService;

    /**
     * 服务端用户登录
     */
    @PostMapping("login")
    @Operation(summary = "登录")
    public UserTokenVO consumerLogin(@Validated @RequestBody PhoneLoginDTO phoneLoginDTO, HttpServletRequest request) {
        return loginService.loginForPhone(phoneLoginDTO, request);
    }
}
