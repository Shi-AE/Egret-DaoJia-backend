package com.edj.common.expcetions;

import static com.edj.common.constants.ErrorInfo.Msg.REQUEST_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;

/**
 * 权限校验被拒
 *
 * @author A.E.
 * @date 2024/9/19
 */
public class RequestForbiddenException extends CommonException {

    public RequestForbiddenException() {
        this(REQUEST_FORBIDDEN);
    }

    public RequestForbiddenException(String message) {
        super(HTTP_FORBIDDEN, message);
    }

}
