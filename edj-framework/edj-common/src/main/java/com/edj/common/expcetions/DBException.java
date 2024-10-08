package com.edj.common.expcetions;


import static com.edj.common.constants.ErrorInfo.Msg.PROCESS_FAILED;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

/**
 * @author A.E.
 * @date 2024/9/19
 */
public class DBException extends CommonException {
    public DBException() {
        super(HTTP_INTERNAL_ERROR, PROCESS_FAILED);
    }

    public DBException(String message) {
        super(HTTP_INTERNAL_ERROR, message);
    }
}
