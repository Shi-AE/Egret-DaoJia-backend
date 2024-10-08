package com.edj.user.controller;

import cn.hutool.http.useragent.UserAgent;
import com.edj.common.domain.Result;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.AsyncUtils;
import com.edj.common.utils.JWTUtils;
import com.edj.common.utils.ServletUtils;
import com.edj.common.utils.UserAgentUtils;
import com.edj.security.domain.dto.AuthorizationUserDTO;
import com.edj.user.domain.dto.UserLoginDTO;
import com.edj.user.domain.entity.EdjUser;
import com.edj.user.domain.vo.UserTokenVO;
import com.edj.user.mapper.EdjUserMapper;
import com.edj.user.service.EdjUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.edj.common.constants.AuthorizationConstants.RedisKey.ACCESS_TOKEN_KEY;
import static com.edj.common.constants.AuthorizationConstants.RedisKey.REFRESH_TOKEN_KEY;
import static com.edj.common.constants.AuthorizationConstants.Timeout.ACCESS_TIMEOUT;
import static com.edj.common.constants.AuthorizationConstants.Timeout.REFRESH_TIMEOUT;
import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * 用户控制类
 *
 * @author A.E.
 * @date 2024/9/29
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Tag(name = "用户管理")
public class UserLoginController {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final EdjUserMapper userMapper;

    private final RedisTemplate<String, AuthorizationUserDTO> redisTemplate;

    private final EdjUserService userService;

    /**
     * 登录
     */
    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<UserTokenVO> login(@Validated @RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        // 验证账号
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());

        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new BadRequestException(e.getMessage());
        }

        // 解析用户设备
        AuthorizationUserDTO principal = (AuthorizationUserDTO) authenticate.getPrincipal();

        String userAgentStr = request.getHeader(USER_AGENT);
        UserAgent parseUserAgent = UserAgentUtils.parse(userAgentStr);
        String os = parseUserAgent.getOs().toString();
        String browser = parseUserAgent.getBrowser().toString();
        String ip = ServletUtils.getClientIP(request);

        principal.setLoginIp(ip);
        principal.setOs(os);
        principal.setBrowser(browser);

        // 生成token
        Long id = principal.getId();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        String accessToken = JWTUtils.createToken(claims);
        String refreshToken = JWTUtils.createToken(claims);

        // 存入redis
        redisTemplate.opsForValue().set(String.format(
                ACCESS_TOKEN_KEY,
                os,
                browser,
                accessToken
        ), principal, ACCESS_TIMEOUT);

        redisTemplate.opsForValue().set(String.format(
                REFRESH_TOKEN_KEY,
                os,
                browser,
                refreshToken
        ), principal, REFRESH_TIMEOUT);

        // 异步记录登录信息
        AsyncUtils.runAsyncComplete(userService.RecordLoginInfo(id, ip));

        return Result.success(UserTokenVO
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build()
        );
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
