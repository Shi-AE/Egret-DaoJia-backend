package com.edj.rabbitmq.config;

import com.edj.common.utils.DateUtils;
import com.edj.common.utils.NumberUtils;
import com.edj.rabbitmq.client.RabbitClient;
import com.edj.rabbitmq.plugins.ErrorMessageRecoverer;
import com.edj.rabbitmq.plugins.RabbitMqResender;
import com.edj.rabbitmq.properties.RabbitmqProperties;
import com.edj.rabbitmq.service.EdjFailMsgService;
import com.edj.rabbitmq.service.impl.EdjFailMsgServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.nio.charset.Charset;

@Slf4j
@Configuration
@RequiredArgsConstructor
@MapperScan("com.edj.rabbitmq.mapper")
@Import({RabbitClient.class, EdjFailMsgServiceImpl.class})
@EnableConfigurationProperties({RabbitmqProperties.class})
@ConditionalOnProperty(prefix = "rabbitmq", name = "enable", havingValue = "true")
public class RabbitMqConfiguration implements ApplicationContextAware {

    /**
     * 并发数量
     */
    public static final int DEFAULT_CONCURRENT = 10;

    private final EdjFailMsgService failMsgService;

    @Bean
    public ErrorMessageRecoverer errorMessageRecoverer(RabbitmqProperties rabbitmqProperties, RabbitClient rabbitClient) {
        return new ErrorMessageRecoverer(rabbitClient, rabbitmqProperties);
    }

    @Bean
    public RabbitMqResender rabbitMqResender(RabbitTemplate rabbitTemplate, RabbitmqProperties rabbitmqProperties) {
        return new RabbitMqResender(rabbitTemplate, rabbitmqProperties);
    }

    /**
     * 自定义mq消费者并发连接
     *
     * @param configurer        configurer
     * @param connectionFactory connectionFactory
     * @return factory
     */
    @Bean
    @Primary
    public SimpleRabbitListenerContainerFactory defaultContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory,
            RabbitmqProperties rabbitmqProperties
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
        factory.setMaxConcurrentConsumers(DEFAULT_CONCURRENT);
        configurer.configure(factory, connectionFactory);

        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        RabbitmqProperties.Error error = rabbitmqProperties.getError();

        // 声明消费异常交换机
        Exchange errorExchange = ExchangeBuilder
                .topicExchange(error.getExchange())
                .durable(true)
                .build();
        rabbitAdmin.declareExchange(errorExchange);

        rabbitAdmin.setAutoStartup(true);

        // 声明消费异常队列
        Queue errorQueue = new Queue(error.getQueue());
        rabbitAdmin.declareQueue(errorQueue);

        // 声明消费异常消息绑定关系
        Binding binding = BindingBuilder
                .bind(errorQueue)
                .to(errorExchange)
                .with(error.getRoutingKey())
                .noargs();
        rabbitAdmin.declareBinding(binding);

        return factory;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 获取RabbitTemplate
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

        //定义returnCallback回调方法
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            Message message = returnedMessage.getMessage();
            byte[] body = message.getBody();
            //消息id
            String messageId = message.getMessageProperties().getMessageId();
            String content = new String(body, Charset.defaultCharset());
            log.info(
                    "消息发送失败, 应答码: {}, 原因: {}, 交换机: {}, 路由键: {}, 消息id: {}, 消息内容: {}",
                    returnedMessage.getReplyCode(),
                    returnedMessage.getReplyText(),
                    returnedMessage.getExchange(),
                    returnedMessage.getRoutingKey(),
                    messageId,
                    content
            );
            if (failMsgService != null) {
                failMsgService.save(NumberUtils.parseLong(messageId), returnedMessage.getExchange(), returnedMessage.getRoutingKey(), content, null, DateUtils.getCurrentTime(), "returnCallback");
            }
        });
    }
}
