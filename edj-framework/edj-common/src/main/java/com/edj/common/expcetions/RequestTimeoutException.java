package com.edj.common.expcetions;

import static com.edj.common.constants.ErrorInfo.Msg.REQUEST_TIME_OUT;
import static java.net.HttpURLConnection.HTTP_CLIENT_TIMEOUT;

/**
 * 请求超时异常
 *
 * @author A.E.
 * @date 2024/9/19
 */
public class RequestTimeoutException extends CommonException {

    public RequestTimeoutException() {
        this(REQUEST_TIME_OUT);
    }

    public RequestTimeoutException(String message) {
        super(HTTP_CLIENT_TIMEOUT, message);
    }
}
