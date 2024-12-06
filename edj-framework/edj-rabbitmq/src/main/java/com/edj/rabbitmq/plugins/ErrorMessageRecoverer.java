package com.edj.rabbitmq.plugins;

import com.edj.rabbitmq.client.RabbitClient;
import com.edj.rabbitmq.domain.ErrorRabbitMqMessage;
import com.edj.rabbitmq.properties.RabbitmqProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;

public class ErrorMessageRecoverer implements MessageRecoverer {
    private final RabbitClient rabbitClient;
    private final RabbitmqProperties rabbitmqProperties;

    public ErrorMessageRecoverer(RabbitClient rabbitClient, RabbitmqProperties rabbitmqProperties) {
        this.rabbitClient = rabbitClient;
        this.rabbitmqProperties = rabbitmqProperties;
    }

    @Override
    public void recover(Message message, Throwable cause) {
        // 指定routingKey的消息才能进入队列
        RabbitmqProperties.Error error = rabbitmqProperties.getError();
        MessageProperties messageProperties = message.getMessageProperties();
        String receivedRoutingKey = messageProperties.getReceivedRoutingKey();
        if(error.getWhiteList().contains(receivedRoutingKey)) {
            ErrorRabbitMqMessage errorRabbitMqMessage = new ErrorRabbitMqMessage();
            errorRabbitMqMessage.setOriginRoutingKey(receivedRoutingKey);
            errorRabbitMqMessage.setOriginExchange(messageProperties.getReceivedExchange());
            errorRabbitMqMessage.setMessage(new String(message.getBody()));
            rabbitClient.sendMsg(error.getExchange(), error.getRoutingKey(), errorRabbitMqMessage, null, null, false);
        }
    }
}
