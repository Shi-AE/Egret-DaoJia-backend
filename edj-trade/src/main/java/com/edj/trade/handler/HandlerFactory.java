package com.edj.trade.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.edj.api.api.trade.enums.TradingChannel;
import com.edj.trade.annotation.PayChannel;

import java.util.Map;

/**
 * Handler工厂，用于获取指定类型的具体渠道的实例对象
 */
public class HandlerFactory {

    private HandlerFactory() {
    }

    public static <T> T get(TradingChannel payChannel, Class<T> handler) {
        Map<String, T> beans = SpringUtil.getBeansOfType(handler);
        for (Map.Entry<String, T> entry : beans.entrySet()) {
            PayChannel payChannelAnnotation = entry.getValue().getClass().getAnnotation(PayChannel.class);
            if (ObjectUtil.isNotEmpty(payChannelAnnotation) && ObjectUtil.equal(payChannel, payChannelAnnotation.type())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static <T> T get(String payChannel, Class<T> handler) {
        return get(TradingChannel.valueOf(payChannel), handler);
    }
}
