package com.edj.foundations.handler;

import com.edj.canal.listener.AbstractCanalRabbitMqMsgListener;
import com.edj.common.constants.IndexConstants;
import com.edj.common.constants.MqConstants;
import com.edj.common.utils.ThreadUtils;
import com.edj.es.core.ElasticSearchTemplate;
import com.edj.foundations.domain.entity.EdjServeSync;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
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

    /**
     * 处理消息同步至es
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    name = MqConstants.Queues.FOUNDATION_SYNC_TO_ES,
                    arguments = {@Argument(name = MqConstants.X_QUEUE_TYPE, value = MqConstants.QUORUM)}
            ),
            exchange = @Exchange(name = MqConstants.Exchanges.CANAL, type = ExchangeTypes.TOPIC),
            key = MqConstants.RoutingKeys.SERVE_SYNC_TO_ES),
            concurrency = "1"
    )
    public void onMessage(Message message) {
        parseMsg(message);
    }

    @Override
    public void batchSave(List<EdjServeSync> data) {
        Boolean success = elasticSearchTemplate.opsForDoc().batchInsert(IndexConstants.SERVE, data);
        if (!success) {
            ThreadUtils.sleep(1000);
            throw new RuntimeException("同步失败");
        }
    }

    @Override
    public void batchDelete(List<Long> idList) {
        Boolean success = elasticSearchTemplate.opsForDoc().batchDelete(IndexConstants.SERVE, idList);
        if (!success) {
            ThreadUtils.sleep(1000);
            throw new RuntimeException("同步失败");
        }
    }
}
