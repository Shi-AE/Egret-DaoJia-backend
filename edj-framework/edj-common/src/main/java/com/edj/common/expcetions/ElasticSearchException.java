package com.edj.common.expcetions;

/**
 * @author A.E.
 * @date 2024/9/19
 */
public class ElasticSearchException extends CommonException {

    public ElasticSearchException() {
    }

    public ElasticSearchException(int code, String message) {
        super(code, message);
    }


    public ElasticSearchException(String message) {
        super(message);
    }
}
