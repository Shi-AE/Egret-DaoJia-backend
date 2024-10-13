package com.edj.gateway.filter;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.edj.cache.helper.LockHelper;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.BooleanUtils;
import com.edj.common.utils.JWTUtils;
import com.edj.gateway.properties.ApiProperties;
import com.edj.gateway.utils.GatewayWebUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.edj.cache.helper.LockHelper.MEDIUM_OPERATION_WAIT_TIME;
import static com.edj.common.constants.AuthorizationConstants.HeaderKey.AUTHORIZATION_ACCESS_TOKEN;
import static com.edj.common.constants.AuthorizationConstants.HeaderKey.AUTHORIZATION_REFRESH_TOKEN;
import static com.edj.common.constants.AuthorizationConstants.RedisKey.ACCESS_TOKEN_KEY;
import static com.edj.common.constants.AuthorizationConstants.RedisKey.REFRESH_TOKEN_KEY;
import static com.edj.common.constants.AuthorizationConstants.Timeout.ACCESS_TIMEOUT;
import static com.edj.common.constants.AuthorizationConstants.Timeout.REFRESH_TIMEOUT;
import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter implements GlobalFilter, Ordered {

    private final StringRedisTemplate stringRedisTemplate;

    private final ApiProperties apiProperties;

    private final LockHelper lockHelper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1.黑名单和白名单校验
        // 1.1.黑名单校验
        String uri = GatewayWebUtils.getUri(exchange);
        log.debug("uri: {}", uri);

        if (apiProperties.getAccessPathBlackList().contains(uri)) {
            log.debug("黑名单：{}", uri);
            return GatewayWebUtils.toResponse(exchange,
                    HttpStatus.FORBIDDEN.value(),
                    ErrorInfo.Msg.REQUEST_FORBIDDEN);
        }

        // 1.2.访问白名单
        if (apiProperties.getAccessPathWhiteList().contains(uri)) {
            log.debug("白名单：{}", uri);
            return chain.filter(exchange);
        }

        // 获取用户设备等信息
        String reqUserAgent = GatewayWebUtils.getRequestHeader(exchange, USER_AGENT);
        if (reqUserAgent == null) {
            log.debug("未获取到用户设备信息");
            return GatewayWebUtils.toResponse(exchange,
                    HttpStatus.FORBIDDEN.value(),
                    ErrorInfo.Msg.REQUEST_FORBIDDEN);
        }
        UserAgent parseUserAgent = UserAgentUtil.parse(reqUserAgent);
        String os = parseUserAgent.getOs().toString();
        String browser = parseUserAgent.getBrowser().toString();

        // 获取用户 accessToken
        String accessToken = GatewayWebUtils.getRequestHeader(exchange, AUTHORIZATION_ACCESS_TOKEN);
        if (accessToken == null) {
            log.debug("未获取到用户 accessToken");
            return GatewayWebUtils.toResponse(exchange,
                    HttpStatus.FORBIDDEN.value(),
                    ErrorInfo.Msg.REQUEST_FORBIDDEN);
        }

        // 检验 redis 是否存在 accessToken
        Boolean hasAccessTokenKey = stringRedisTemplate.hasKey(String.format(
                ACCESS_TOKEN_KEY,
                os,
                browser,
                accessToken
        ));
        if (BooleanUtils.isFalse(hasAccessTokenKey)) {
            // accessToken 失效，检验 refreshToken
            log.debug("accessToken 失效，检验 refreshToken, accessToken: {}", accessToken);
            // 获取用户refresh token
            String refreshToken = GatewayWebUtils.getRequestHeader(exchange, AUTHORIZATION_REFRESH_TOKEN);
            if (refreshToken == null) {
                log.debug("获取用户refresh token");
                return GatewayWebUtils.toResponse(exchange,
                        HttpStatus.FORBIDDEN.value(),
                        ErrorInfo.Msg.REQUEST_FORBIDDEN);
            }

            // 检验 redis 是否存在 refreshToken
            Boolean hasRefreshTokenKey = stringRedisTemplate.hasKey(String.format(
                    REFRESH_TOKEN_KEY,
                    os,
                    browser,
                    refreshToken
            ));
            if (BooleanUtils.isFalse(hasRefreshTokenKey)) {
                log.debug("refreshToken 失效可能存在网络波动 refreshToken: {}", refreshToken);
                return GatewayWebUtils.toResponse(exchange,
                        HttpStatus.FORBIDDEN.value(),
                        ErrorInfo.Msg.REQUEST_FORBIDDEN);
            }
            log.debug("refreshToken 检验成功尝试更新 token");

            // todo 优化更新逻辑
            // 加锁更新，更新完成后统一放行
            lockHelper.syncLockAndElse(
                    "gateway:filter:AuthorizationFilter:" + refreshToken,
                    MEDIUM_OPERATION_WAIT_TIME,
                    // 未加锁执行逻辑，token未更新
                    () -> {
                        // noinspection UnnecessaryLocalVariable
                        String updateAccessToken = refreshToken;
                        String updateRefreshToken = JWTUtils.refreshCreateToken(refreshToken);
                        // 更新 token
                        log.debug("更新 token");

                        // 获取并删除 refresh token
                        String userStr = stringRedisTemplate.opsForValue().getAndDelete(String.format(
                                REFRESH_TOKEN_KEY,
                                os,
                                browser,
                                refreshToken
                        ));
                        if (userStr == null) {
                            log.error("根据 refresh token 更新，但无法获取 redis value 。refreshToken: {}", refreshToken);
                            throw new ServerErrorException();
                        }
                        log.debug("获取 redis 值 userStr: {}", userStr);

                        // 设置 redis token
                        log.debug("设置 redis token, updateAccessToken: {}, updateRefreshToken: {}", updateAccessToken, updateRefreshToken);
                        stringRedisTemplate.opsForValue().set(String.format(
                                ACCESS_TOKEN_KEY,
                                os,
                                browser,
                                updateAccessToken
                        ), userStr, ACCESS_TIMEOUT);

                        stringRedisTemplate.opsForValue().set(String.format(
                                REFRESH_TOKEN_KEY,
                                os,
                                browser,
                                updateRefreshToken
                        ), userStr, REFRESH_TIMEOUT);

                        log.debug("token更新完成，修改凭证 {}:{}", AUTHORIZATION_ACCESS_TOKEN, updateAccessToken);
                        GatewayWebUtils.setRequestHeader(exchange, AUTHORIZATION_ACCESS_TOKEN, updateAccessToken);
                        // 设置响应头
                        log.debug("设置响应头, updateAccessToken: {}, updateRefreshToken: {}", updateAccessToken, updateRefreshToken);
                        HttpHeaders headers = exchange.getResponse().getHeaders();
                        headers.add(AUTHORIZATION_ACCESS_TOKEN, updateAccessToken);
                        headers.add(AUTHORIZATION_REFRESH_TOKEN, updateRefreshToken);
                    },
                    // 已加锁执行逻辑，token已更新
                    () -> {
                        // token更新完成，放行其余请求，并修改凭证
                        log.debug("token更新完成，放行其余请求，并修改凭证 {}:{}", AUTHORIZATION_ACCESS_TOKEN, refreshToken);
                        GatewayWebUtils.setRequestHeader(exchange, AUTHORIZATION_ACCESS_TOKEN, refreshToken);
                    }
            );
            return chain.filter(exchange);
        }
        log.debug("通过accessToken验证");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
