package com.edj.security.utils;

import com.edj.security.domain.dto.AuthorizationUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全服务工具类
 *
 * @author A.E.
 * @date 2024/10/9
 */
@Slf4j
public class SecurityUtils {
    private SecurityUtils() {
    }

    /**
     * 用户ID
     **/
    public static Long getUserId() {
        AuthorizationUserDTO loginUser = getLoginUser();
        if (loginUser == null) {
            return null;
        }
        return loginUser.getId();
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        AuthorizationUserDTO loginUser = getLoginUser();
        if (loginUser == null) {
            return null;
        }
        return loginUser.getUsername();
    }

    /**
     * 获取用户昵称
     **/
    public static String getNickname() {
        AuthorizationUserDTO loginUser = getLoginUser();
        if (loginUser == null) {
            return null;
        }
        return loginUser.getNickname();
    }

    /**
     * 获取用户
     **/
    public static AuthorizationUserDTO getLoginUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof AuthorizationUserDTO)) {
            return null;
        }
        return (AuthorizationUserDTO) principal;
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}