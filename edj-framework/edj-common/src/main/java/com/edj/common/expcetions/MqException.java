package com.edj.common.expcetions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * mq 失效
 *
 * @author A.E.
 * @date 2024/9/19
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MqException extends CommonException {

    private Long mqId;

    public MqException() {
    }

    public MqException(int code, String message) {
        super(code, message);
    }

    public MqException(String message) {
        super(message);
    }
}
