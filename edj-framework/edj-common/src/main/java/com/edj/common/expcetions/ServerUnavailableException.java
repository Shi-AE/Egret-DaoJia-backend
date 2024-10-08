package com.edj.common.expcetions;

import static com.edj.common.constants.ErrorInfo.Msg.PROCESS_FAILED;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;

/**
 * 服务不可用，注册中心找不到对应服务
 *
 * @author A.E.
 * @date 2024/9/19
 */
public class ServerUnavailableException extends CommonException {
    public ServerUnavailableException() {
        this(PROCESS_FAILED);
    }

    public ServerUnavailableException(String message) {
        super(HTTP_UNAVAILABLE, message);
    }

}
