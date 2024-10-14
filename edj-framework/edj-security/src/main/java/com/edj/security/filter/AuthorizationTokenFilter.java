package com.edj.security.filter;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.edj.common.expcetions.BadRequestException;
import com.edj.security.domain.dto.AuthorizationUserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.edj.common.constants.AuthorizationConstants.HeaderKey.AUTHORIZATION_ACCESS_TOKEN;
import static com.edj.common.constants.AuthorizationConstants.RedisKey.ACCESS_TOKEN_KEY;
import static org.springframework.http.HttpHeaders.USER_AGENT;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationTokenFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, AuthorizationUserDTO> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("AuthorizationTokenFilter 过滤请求 url={}", request.getRequestURI());

        // token 已经由 gateway 过滤, 不存在非法请求
        String accessToken = request.getHeader(AUTHORIZATION_ACCESS_TOKEN);
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String reqUserAgent = request.getHeader(USER_AGENT);
        UserAgent parseUserAgent = UserAgentUtil.parse(reqUserAgent);
        if (parseUserAgent == null) {
            log.debug("未获取到用户设备信息");
            throw new BadRequestException();
        }

        // 获取用户登录信息
        AuthorizationUserDTO authorizationUserDTO = redisTemplate.opsForValue().get(String.format(
                ACCESS_TOKEN_KEY,
                parseUserAgent.getOs().toString(),
                parseUserAgent.getBrowser().toString(),
                accessToken
        ));
        if (authorizationUserDTO == null) {
            filterChain.doFilter(request, response);
            return;
        }
        log.debug("获取用户登录信息 authorizationUserDTO: {}", authorizationUserDTO);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authorizationUserDTO,
                null,
                authorizationUserDTO.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
