package com.edj.rabbitmq.plugins;

import com.edj.common.utils.IoUtils;
import com.edj.common.utils.JsonUtils;
import com.edj.rabbitmq.domain.ErrorRabbitMqMessage;
import com.edj.rabbitmq.properties.RabbitmqProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * mq 消息处理器
 *
 * @author A.E.
 * @date 2024/12/7
 */
@Slf4j
public class RabbitMqResender {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitmqProperties rabbitmqProperties;
    private final Channel channel;

    public RabbitMqResender(RabbitTemplate rabbitTemplate, RabbitmqProperties rabbitmqProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitmqProperties = rabbitmqProperties;
        channel = rabbitTemplate
                .getConnectionFactory()
                .createConnection()
                .createChannel(false);
    }

    /**
     * 从队列中获取一条数据并处理,如果没有消息，返回false，有消息返回true
     */
    public boolean getOneMessageAndProcess() {
        try {
            GetResponse response = channel.basicGet(rabbitmqProperties.getError().getQueue(), false);
            if (response == null) {
                return false;
            }
            ErrorRabbitMqMessage errorRabbitMqMessage = JsonUtils.toBean(new String(response.getBody()), ErrorRabbitMqMessage.class);
            Message message = MessageBuilder.withBody(errorRabbitMqMessage.getMessage().getBytes(Charset.defaultCharset())).build();
            rabbitTemplate.send(errorRabbitMqMessage.getOriginExchange(), errorRabbitMqMessage.getOriginRoutingKey(), message);
            channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
            return true;
        } catch (IOException e) {
            log.error("消息重发失败", e);
            return false;
        }
    }

    @PreDestroy
    public void destroy() {
        log.info("rabbitmq 销毁...");
        IoUtils.close(channel);
    }
}
