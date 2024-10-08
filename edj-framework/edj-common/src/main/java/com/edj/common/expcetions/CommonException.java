package com.edj.common.expcetions;

import cn.hutool.http.HttpStatus;
import com.edj.common.constants.ErrorInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

/**
 * 通用异常
 *
 * @author A.E.
 * @date 2024/9/19
 */
@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class CommonException extends RuntimeException {
    private int code;
    private String message;

    public CommonException() {
        this.code = HTTP_BAD_REQUEST;
        this.message = ErrorInfo.Msg.PROCESS_FAILED;
    }

    public CommonException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonException(String message) {
        this(HttpStatus.HTTP_INTERNAL_ERROR, message);
    }
}
