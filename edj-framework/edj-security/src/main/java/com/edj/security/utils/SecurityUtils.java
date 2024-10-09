package com.edj.security.utils;

import com.edj.common.expcetions.ServerErrorException;
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
        try {
            return getLoginUser().getId();
        } catch (Exception e) {
            log.error("获取用户ID异常, {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            log.error("获取用户账户异常, {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取用户昵称
     **/
    public static String getNickname() {
        try {
            return getLoginUser().getNickname();
        } catch (Exception e) {
            log.error("获取用户昵称异常, {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取用户
     **/
    public static AuthorizationUserDTO getLoginUser() {
        try {
            return (AuthorizationUserDTO) getAuthentication().getPrincipal();
        } catch (Exception e) {
            log.error("获取用户信息异常, {}", e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
