package com.edj.user.service;

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
     * 微信登录服务
     */
    UserTokenVO loginForWechat(WechatLoginDTO wechatLoginDTO, HttpServletRequest request);
}
