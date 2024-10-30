package com.edj.user.service.impl;

import com.edj.api.api.publics.WechatApi;
import com.edj.api.api.publics.dto.OpenIdDTO;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.CommonException;
import com.edj.common.utils.StringUtils;
import com.edj.user.domain.dto.WechatLoginDTO;
import com.edj.user.domain.vo.UserTokenVO;
import com.edj.user.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    private final WechatApi wechatApi;

    @Override
    public UserTokenVO loginForWechat(WechatLoginDTO wechatLoginDTO, HttpServletRequest request) {
        // 根据code获取openId
        String code = wechatLoginDTO.getCode();
        OpenIdDTO openIdDTO = wechatApi.getOpenId(code);
        String openId = openIdDTO.getOpenId();
        if (StringUtils.isBlank(openId)) {
            // openid申请失败
            throw new CommonException(ErrorInfo.Code.LOGIN_TIMEOUT, ErrorInfo.Msg.REQUEST_FAILED);
        }
        log.debug(openId);
        return null;
    }
}
