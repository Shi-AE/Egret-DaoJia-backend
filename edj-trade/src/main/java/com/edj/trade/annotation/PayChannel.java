package com.edj.trade.annotation;

import com.edj.api.api.trade.enums.TradingChannel;

import java.lang.annotation.*;

/**
 * @author A.E.
 * @date 2024/12/31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PayChannel {
    TradingChannel type();
}