package com.edj.rabbitmq.domain;

import lombok.Data;

@Data
public class ErrorRabbitMqMessage {
    private String originRoutingKey;
    private String originExchange;
    private String message;
}
