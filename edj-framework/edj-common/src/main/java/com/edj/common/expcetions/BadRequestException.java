package com.edj.common.expcetions;

import static com.edj.common.constants.ErrorInfo.Msg.REQUEST_FAILED;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

/**
 * 请求异常，
 * 使用场景：请求参数不合法，频繁请求
 *
 * @author A.E.
 * @date 2024/9/19
 */
public class BadRequestException extends CommonException {

    public BadRequestException() {
        this(REQUEST_FAILED);
    }

    public BadRequestException(String message) {
        super(HTTP_BAD_REQUEST, message);
    }
}
