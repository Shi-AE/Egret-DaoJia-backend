package com.edj.user.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.http.useragent.UserAgent;
import com.edj.api.api.customer.WorkerApi;
import com.edj.api.api.publics.SmsCodeApi;
import com.edj.api.api.publics.WechatApi;
import com.edj.api.api.publics.dto.OpenIdDTO;
import com.edj.api.api.publics.dto.SmsCodeDTO;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.CommonException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.*;
import com.edj.security.domain.dto.AuthorizationUserDTO;
import com.edj.user.domain.dto.PhoneLoginDTO;
import com.edj.user.domain.dto.UserLoginDTO;
import com.edj.user.domain.dto.WechatLoginDTO;
import com.edj.user.domain.entity.EdjUser;
import com.edj.user.domain.entity.EdjUserRole;
import com.edj.user.domain.vo.UserTokenVO;
import com.edj.user.enums.EdjSysRole;
import com.edj.user.service.EdjUserRoleService;
import com.edj.user.service.EdjUserService;
import com.edj.user.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.edj.common.constants.AuthorizationConstants.RedisKey.ACCESS_TOKEN_KEY;
import static com.edj.common.constants.AuthorizationConstants.RedisKey.REFRESH_TOKEN_KEY;
import static com.edj.common.constants.AuthorizationConstants.Timeout.ACCESS_TIMEOUT;
import static com.edj.common.constants.AuthorizationConstants.Timeout.REFRESH_TIMEOUT;
import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * 登录服务实现
 *
 * @author A.E.
 * @date 2024/10/29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final ApplicationContext applicationContext;

    private final WechatApi wechatApi;

    private final SmsCodeApi smsCodeApi;

    private final WorkerApi workerApi;

    private final EdjUserService userService;

    private final Snowflake snowflake;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final RedisTemplate<String, AuthorizationUserDTO> redisTemplate;

    private final AuthenticationManager authenticationManager;

    private final EdjUserRoleService userRoleService;

    @Override
    public UserTokenVO createToken(AuthorizationUserDTO principal, HttpServletRequest request) {
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

        return UserTokenVO
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public UserTokenVO loginForUsername(UserLoginDTO userLoginDTO, HttpServletRequest request) {
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

        return createToken(principal, request);
    }

    @Override
    @Transactional
    public UserTokenVO loginForWechat(WechatLoginDTO wechatLoginDTO, HttpServletRequest request) {
        // 根据code获取openId
        String code = wechatLoginDTO.getCode();
        OpenIdDTO openIdDTO = wechatApi.getOpenId(code);
        // oLexy7ZRawjq2D4POGTIi31bGf_Y
        String openId = openIdDTO.getOpenId();
        if (StringUtils.isBlank(openId)) {
            // openid申请失败
            throw new CommonException(ErrorInfo.Code.LOGIN_TIMEOUT, ErrorInfo.Msg.REQUEST_FAILED);
        }

        // 根据openid查找用户
        String username = userService.selectUsernameByOpenId(openId);

        // 监测用户是否注册
        if (StringUtils.isBlank(username)) {
            EdjUser user = BeanUtils.toBean(wechatLoginDTO, EdjUser.class);
            user.setOpenId(openId);
            // 生成用户标识
            String wechatUserName = IdUtils.toWechatUserName(snowflake.nextId());
            username = wechatUserName;
            user.setUsername(wechatUserName);
            user.setNickname(wechatUserName);
            // 随机生成密码
            user.setPassword(passwordEncoder.encode(IdUtils.fastSimpleUUID()));
            userService.save(user);
            // 添加客户端用户权限
            Long id = user.getId();
            if (ObjectUtils.isEmpty(id)) {
                log.error("用户注册失败: {}", user);
                throw new ServerErrorException("用户注册失败");
            }
            userRoleService.save(EdjUserRole
                    .builder()
                    .edjUserId(id)
                    .edjRoleId((Long) EnumUtils.value(EdjSysRole.CONSUMER))
                    .build()
            );
        }

        // 获取权限
        AuthorizationUserDTO principal = (AuthorizationUserDTO) userDetailsService.loadUserByUsername(username);

        return createToken(principal, request);
    }

    @Override
    public UserTokenVO loginForPhone(PhoneLoginDTO phoneLoginDTO, HttpServletRequest request) {

        // 根据手机号查找用户
        String phone = phoneLoginDTO.getPhone();
        String verifyCode = phoneLoginDTO.getVerifyCode();
        String password = phoneLoginDTO.getPassword();
        String username = userService.selectUsernameByPhone(phone);

        // 判断用户是否存在
        if (StringUtils.isBlank(username)) {
            // 不存在创建用户
            LoginService loginService = applicationContext.getBean(LoginService.class);
            username = loginService.createWorkerUser(phone, verifyCode);
        }
        // 判断是否存在密码
        else if (StringUtils.isNotBlank(password)) {
            // 存在密码直接使用密码登录
            return loginForUsername(
                    UserLoginDTO.builder()
                            .username(username)
                            .password(password)
                            .build(),
                    request
            );
        } else {
            // 使用验证码登录
            SmsCodeDTO verify = smsCodeApi.verify(phone, verifyCode);
            if (BooleanUtils.isFalse(verify.getIsSuccess())) {
                throw new BadRequestException("验证码错误");
            }
        }

        // 获取权限
        AuthorizationUserDTO principal = (AuthorizationUserDTO) userDetailsService.loadUserByUsername(username);

        return createToken(principal, request);
    }

    @Transactional
    public String createWorkerUser(String phone, String verifyCode) {
        // 校验参数
        if (StringUtils.isBlank(verifyCode)) {
            throw new BadRequestException("登录失败");
        }

        // 不存在使用验证码注册
        SmsCodeDTO verify = smsCodeApi.verify(phone, verifyCode);
        if (BooleanUtils.isFalse(verify.getIsSuccess())) {
            throw new BadRequestException("验证码错误");
        }
        // 注册
        // 生成用户标识
        String username = IdUtils.toWechatUserName(snowflake.nextId());
        EdjUser user = EdjUser
                .builder()
                .username(username)
                .nickname(username)
                // 随机生成密码
                .password(passwordEncoder.encode(IdUtils.fastSimpleUUID()))
                .phoneNumber(phone)
                .build();
        userService.save(user);

        Long id = user.getId();

        // 创建额外信息
        workerApi.add(id);

        // 添加服务端用户权限
        if (ObjectUtils.isEmpty(id)) {
            log.error("服务端手机号用户注册失败: {}", user);
            throw new ServerErrorException("用户注册失败");
        }
        userRoleService.save(EdjUserRole
                .builder()
                .edjUserId(id)
                .edjRoleId((Long) EnumUtils.value(EdjSysRole.WORKER))
                .build()
        );
        return username;
    }
}
