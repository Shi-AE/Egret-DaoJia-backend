package com.edj.common.expcetions;


import com.edj.common.constants.ErrorInfo;

/**
 * 禁止操作异常
 *
 * @author A.E.
 * @date 2024/9/19
 */
public class ForbiddenOperationException extends CommonException {

    public ForbiddenOperationException() {
        this(ErrorInfo.Msg.FORBIDDEN_OPERATION);
    }

    public ForbiddenOperationException(String message) {
        super(ErrorInfo.Code.FORBIDDEN_OPERATION, message);
    }


}
