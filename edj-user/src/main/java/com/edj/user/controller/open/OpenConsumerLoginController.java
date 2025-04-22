package com.edj.user.controller.open;

import com.edj.user.domain.dto.WechatLoginDTO;
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
 * Consumer 用户登录
 *
 * @author A.E.
 * @date 2024/10/31
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Consumer 用户登录")
@RequestMapping("open/consumer")
public class OpenConsumerLoginController {

    private final LoginService loginService;

    /**
     * c端用户登录
     */
    @PostMapping("login")
    @Operation(summary = "登录")
    public UserTokenVO consumerLogin(@Validated @RequestBody WechatLoginDTO WechatLoginDTO, HttpServletRequest request) {
        return loginService.loginForWechat(WechatLoginDTO, request);
    }
}
