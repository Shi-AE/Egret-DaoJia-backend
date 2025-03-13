package com.edj.mvc.handler;

import cn.hutool.core.lang.Snowflake;
import com.edj.common.constants.HeaderConstants;
import com.edj.common.handler.RequestIdHandler;
import com.edj.common.utils.StringUtils;
import com.edj.mvc.utils.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.edj.common.constants.AuthorizationConstants.HeaderKey.AUTHORIZATION_ACCESS_TOKEN;
import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * @author A.E.
 * @date 2024/9/20
 */
@Component
@RequiredArgsConstructor
public class RequestIdHandlerImpl implements RequestIdHandler {

    private final Snowflake snowflake;

    @Override
    public String getRequestId() {
        // 从请求header头中获取请求id,获取不到id，生成新的请求id
        String requestId = RequestUtils.getValueFromHeader(HeaderConstants.REQUEST_ID);
        if (StringUtils.isEmpty(requestId)) {
            return snowflake.nextIdStr();
        } else {
            return requestId;
        }
    }

    @Override
    public String getAccessToken() {
        return RequestUtils.getValueFromHeader(AUTHORIZATION_ACCESS_TOKEN);
    }

    @Override
    public String getUserAgent() {
        return RequestUtils.getValueFromHeader(USER_AGENT);
    }
}
