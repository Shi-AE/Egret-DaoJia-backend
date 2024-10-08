package com.edj.common.expcetions;

import static com.edj.common.constants.ErrorInfo.Msg.REQUEST_UNAUTHORIZED;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

/**
 * 身份校验异常，错误码401
 * 使用场景：网关校验token，token不合法或token过期
 *
 * @author A.E.
 * @date 2024/9/19
 */
public class RequestUnauthorizedException extends CommonException {

    public RequestUnauthorizedException() {
        this(REQUEST_UNAUTHORIZED);
    }

    public RequestUnauthorizedException(String message) {
        super(HTTP_UNAUTHORIZED, message);
    }
}
