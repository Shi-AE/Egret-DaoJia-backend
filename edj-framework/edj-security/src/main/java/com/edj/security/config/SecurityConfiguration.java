package com.edj.security.config;

import com.edj.security.domain.dto.AuthorizationUserDTO;
import com.edj.security.filter.AuthorizationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * spring security 配置
 *
 * @author A.E.
 * @date 2024/9/29
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Import(AuthorizationTokenFilter.class)
public class SecurityConfiguration {

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, RedisTemplate<String, AuthorizationUserDTO> redisTemplate) throws Exception {
        return http
                // 禁用csrf保护
                .csrf(AbstractHttpConfigurer::disable)
                // 禁用basic明文验证
                .httpBasic(AbstractHttpConfigurer::disable)
                // 禁用默认登录页
                .formLogin(AbstractHttpConfigurer::disable)
                // 禁用默认登出页
                .logout(AbstractHttpConfigurer::disable)
                .headers(configure -> configure
                        // 禁用HTTP响应标头
                        .cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                        // 禁用X-Frame-Options
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests(authorize -> authorize
                        // 放行
                        // swagger 文档
                        .requestMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
                        // 登录 \ 注册
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        // 拦截
                        .anyRequest().authenticated()
                )
                .sessionManagement(configurer -> configurer
                        // 无状态 session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new AuthorizationTokenFilter(redisTemplate), BasicAuthenticationFilter.class)
                .build();
    }
}
