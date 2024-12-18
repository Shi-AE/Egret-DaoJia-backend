package com.edj.foundations.handler;

import com.edj.canal.listener.AbstractCanalRabbitMqMsgListener;
import com.edj.common.constants.IndexConstants;
import com.edj.common.utils.ThreadUtils;
import com.edj.es.core.ElasticSearchTemplate;
import com.edj.foundations.domain.entity.EdjServeSync;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务信息mq处理
 *
 * @author A.E.
 * @date 2024/12/8
 */
@Component
@RequiredArgsConstructor
public class ServeCanalDataSyncHandler extends AbstractCanalRabbitMqMsgListener<EdjServeSync> {

    private final ElasticSearchTemplate elasticSearchTemplate;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "canal-mq-edj-foundation"),
            exchange = @Exchange(name = "exchange.canal-edj", type = ExchangeTypes.TOPIC),
            key = "canal-mq-edj-foundation"),
            concurrency = "1"
    )
    public void onMessage(Message message) {
        parseMsg(message);
    }

    @Override
    public void batchSave(List<EdjServeSync> data) {
        Boolean aBoolean = elasticSearchTemplate.opsForDoc().batchInsert(IndexConstants.SERVE, data);
        if (!aBoolean) {
            ThreadUtils.sleep(1000);
            throw new RuntimeException("同步失败");
        }
    }

    @Override
    public void batchDelete(List<Long> idList) {
        Boolean aBoolean = elasticSearchTemplate.opsForDoc().batchDelete(IndexConstants.SERVE, idList);
        if (!aBoolean) {
            ThreadUtils.sleep(1000);
            throw new RuntimeException("同步失败");
        }
    }
}
