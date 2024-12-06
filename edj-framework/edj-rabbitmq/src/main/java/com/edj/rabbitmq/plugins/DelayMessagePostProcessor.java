package com.edj.rabbitmq.plugins;

import com.edj.common.utils.NumberUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * 延迟消息处理器
 *
 * @author A.E.
 * @date 2024/12/6
 */
public class DelayMessagePostProcessor implements MessagePostProcessor {

    // 延迟队列默认延迟5s
    private static final int DEFAULT_DELAY = 5;

    private final Long delay;

    public DelayMessagePostProcessor(Long delay) {
        this.delay = delay;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setHeader("x-delay", NumberUtils.null2Default(delay, DEFAULT_DELAY));
        return message;
    }
}
