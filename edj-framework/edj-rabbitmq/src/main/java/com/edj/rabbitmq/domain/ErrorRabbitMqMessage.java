package com.edj.rabbitmq.domain;

import lombok.Data;

/**
 * 错误消息包装
 *
 * @author A.E.
 * @date 2024/12/7
 */
@Data
public class ErrorRabbitMqMessage {
    private String originRoutingKey;
    private String originExchange;
    private String message;
}
