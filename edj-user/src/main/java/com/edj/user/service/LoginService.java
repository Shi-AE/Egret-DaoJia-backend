package com.edj.user.service;

import com.edj.security.domain.dto.AuthorizationUserDTO;
import com.edj.user.domain.dto.UserLoginDTO;
import com.edj.user.domain.dto.WechatLoginDTO;
import com.edj.user.domain.vo.UserTokenVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 登录服务
 *
 * @author A.E.
 * @date 2024/10/29
 */
public interface LoginService {

    /**
     * 解析用户信息，生成token，异步记录登录信息
     */
    UserTokenVO createToken(AuthorizationUserDTO principal, HttpServletRequest request);

    /**
     * 账号密码登录服务
     */
    UserTokenVO loginForUsername(UserLoginDTO userLoginDTO, HttpServletRequest request);

    /**
     * 微信登录服务
     */
    UserTokenVO loginForWechat(WechatLoginDTO wechatLoginDTO, HttpServletRequest request);
}
