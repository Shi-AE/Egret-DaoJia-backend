package com.edj.rabbitmq.client;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.edj.common.expcetions.MqException;
import com.edj.common.utils.DateUtils;
import com.edj.common.utils.JsonUtils;
import com.edj.common.utils.NumberUtils;
import com.edj.rabbitmq.plugins.DelayMessagePostProcessor;
import com.edj.rabbitmq.service.EdjFailMsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitClient {

    private final RabbitTemplate rabbitTemplate;

    private final EdjFailMsgService failMsgService;

    private final Snowflake snowflake;

    /**
     * 发送消息 重试4次
     *
     * @param exchange   交换机
     * @param routingKey 路由key
     * @param msg        消息对象，会将对象序列化成json字符串发出
     * @param delay      延迟时间 秒
     * @param msgId      消息id
     * @param isFailMsg  是否是失败消息
     */
    @Retryable(
            retryFor = MqException.class,
            maxAttempts = 4,
            backoff = @Backoff(value = 3000, multiplier = 1.5),
            recover = "saveFailMag"
    )
    public void sendMsg(String exchange, String routingKey, Object msg, Long delay, Long msgId, boolean isFailMsg) {
        // 1.发送消息前准备
        // 1.1获取消息内容，如果非字符串将其序列化
        String jsonMsg = JsonUtils.toJsonStr(msg);
        // 1.2.全局唯一消息id，如果调用者设置了消息id，使用调用者消息id，如果为配置，默认雪花算法生成消息id
        Long defaultMsgId = NumberUtils.null2Default(msgId, snowflake.nextId());
        // 1.3.设置默认延迟时间，默认立即发送
        Long defaultDelay = NumberUtils.null2Default(delay, -1);
        log.debug("消息发送! exchange = {}, routingKey = {}, msg = {}, msgId = {}", exchange, routingKey, jsonMsg, defaultMsgId);

        // 1.5.CorrelationData设置
        CorrelationData correlationData = new CorrelationData(defaultMsgId.toString());
        correlationData.getFuture().thenAccept(result -> {
            // 处理成功
            if (failMsgService == null) {
                return;
            }
            if (!result.isAck()) {
                // 执行失败保存失败信息，如果已经存在保存信息，如果不在信息信息
                failMsgService.save(defaultMsgId, exchange, routingKey, msg, defaultDelay, DateUtils.getCurrentTime() + 10, "MQ回复nack");
            } else if (isFailMsg) {
                // 如果发送的是失败消息，当收到ack需要从fail_msg删除该消息
                failMsgService.removeById(defaultMsgId);
            }
        }).exceptionally(ex -> {
            // 处理失败
            if (failMsgService == null) {
                return null;
            }
            failMsgService.save(defaultMsgId, exchange, routingKey, msg, defaultDelay, DateUtils.getCurrentTime() + 10, ExceptionUtil.getMessage(ex));
            return null;
        });

        // 1.6.构造消息对象
        Message message = MessageBuilder.withBody(StrUtil.bytes(jsonMsg, CharsetUtil.CHARSET_UTF_8))
                //持久化
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                //消息id
                .setMessageId(defaultMsgId.toString())
                .build();

        try {
            // 2.发送消息
            rabbitTemplate.convertAndSend(exchange, routingKey, message, new DelayMessagePostProcessor(defaultDelay), correlationData);
        } catch (Exception e) {
            log.error("send error", e);
            // 3.构建异常回调，并抛出异常
            MqException mqException = new MqException();
            mqException.setMessage(ExceptionUtil.getMessage(e));
            mqException.setMqId(defaultMsgId);
            throw mqException;
        }
    }

    /**
     * 发送消息失败，需要将消息持久化到数据库，通过任务调度的方式处理失败的消息
     *
     * @param mqException mq异常消息
     * @param exchange    交换机
     * @param routingKey  路由key
     * @param msg         mq消息
     * @param delay       延迟消息
     * @param msgId       消息id
     */
    @Recover
    public void saveFailMag(MqException mqException, String exchange, String routingKey, Object msg, Long delay, String msgId) {
        failMsgService.save(mqException.getMqId(), exchange, routingKey, JsonUtils.toJsonStr(msg), delay, DateUtils.getCurrentTime() + 10, ExceptionUtil.getMessage(mqException));
    }
}
