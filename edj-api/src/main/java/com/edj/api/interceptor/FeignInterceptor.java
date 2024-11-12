package com.edj.api.interceptor;

import com.edj.common.constants.HeaderConstants;
import com.edj.common.handler.RequestIdHandler;
import com.edj.common.utils.Base64Utils;
import com.edj.common.utils.JsonUtils;
import com.edj.security.domain.dto.AuthorizationUserDTO;
import com.edj.security.utils.SecurityUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

import static com.edj.common.constants.AuthorizationConstants.HeaderKey.AUTHORIZATION_ACCESS_TOKEN;
import static com.edj.common.constants.HeaderConstants.*;

@RequiredArgsConstructor
public class FeignInterceptor implements RequestInterceptor {

    private final RequestIdHandler requestIdHandler;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 1.用户信息
        AuthorizationUserDTO loginUser = SecurityUtils.getLoginUser();
        String userInfoStr = Base64Utils.encodeStr(JsonUtils.toJsonStr(loginUser));
        requestTemplate.header(USER_INFO, userInfoStr);
        // 用户token
        requestTemplate.header(AUTHORIZATION_ACCESS_TOKEN, requestIdHandler.getAccessToken());
        // 2.访问来源信息
        requestTemplate.header(HeaderConstants.REQUEST_ORIGIN_FLAG, REQUEST_ORIGIN_FLAG_INNER);
        // 3.访问id
        requestTemplate.header(REQUEST_ID, requestIdHandler.getRequestId());
    }
}
