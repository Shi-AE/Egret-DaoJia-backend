package com.edj.common.expcetions;

import static com.edj.common.constants.ErrorInfo.Msg.PROCESS_FAILED;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

/**
 * 服务器异常
 * 服务异常
 *
 * @author A.E.
 * @date 2024/9/19
 */
public class ServerErrorException extends CommonException {

    public ServerErrorException() {
        this(PROCESS_FAILED);
    }

    public ServerErrorException(String message) {
        super(HTTP_INTERNAL_ERROR, message);
    }
}
