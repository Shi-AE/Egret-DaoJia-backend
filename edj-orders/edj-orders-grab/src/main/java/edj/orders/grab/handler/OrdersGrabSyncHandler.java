package edj.orders.grab.handler;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.edj.canal.listener.AbstractCanalRabbitMqMsgListener;
import com.edj.common.constants.IndexConstants;
import com.edj.common.constants.MqConstants;
import com.edj.common.domain.Location;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.ThreadUtils;
import com.edj.es.core.ElasticSearchTemplate;
import com.edj.orders.base.domain.entity.EdjOrdersGrab;
import edj.orders.grab.domain.dto.OrdersGrabInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.edj.cache.constants.CacheConstants.CacheName.ORDERS_STOCK_CACHE;
import static com.edj.common.constants.RedisConstants.QUEUE_NUM;

/**
 * 抢单池同步
 *
 * @author A.E.
 * @date 2025/4/23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OrdersGrabSyncHandler extends AbstractCanalRabbitMqMsgListener<EdjOrdersGrab> {

    private final ElasticSearchTemplate elasticSearchTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 处理消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    name = MqConstants.Queues.ORDERS_GRAB_TO_ES,
                    arguments = {@Argument(name = MqConstants.X_QUEUE_TYPE, value = MqConstants.QUORUM)}
            ),
            exchange = @Exchange(name = MqConstants.Exchanges.CANAL, type = ExchangeTypes.TOPIC),
            key = MqConstants.RoutingKeys.ORDERS_GRAB_TO_ES),
            concurrency = "1"
    )
    public void onMessage(Message message) {
        parseMsg(message);
    }

    @Override
    public void batchSave(List<EdjOrdersGrab> data) {
        List<OrdersGrabInfo> ordersSeizeInfoList = data
                .stream()
                .map(ordersGrab -> {
                    OrdersGrabInfo ordersGrabInfo = BeanUtils.toBean(ordersGrab, OrdersGrabInfo.class);
                    // 设置服务开始时间(yyMMddHH)
                    ordersGrabInfo.setServeTime(Integer.parseInt(LocalDateTimeUtil.format(ordersGrab.getServeStartTime(), "yyMMddHH")));
                    ordersGrabInfo.setLocation(new Location(ordersGrab.getLon(), ordersGrab.getLat()));
                    ordersGrabInfo.setKeyWords(ordersGrab.getServeTypeName() +
                            ordersGrab.getServeItemName() +
                            ordersGrab.getServeAddress()
                    );
                    return ordersGrabInfo;
                })
                .toList();

        // 写入 es
        Boolean success = elasticSearchTemplate.opsForDoc().batchInsert(IndexConstants.ORDERS_GRAB, ordersSeizeInfoList);
        if (!success) {
            ThreadUtils.sleep(1000);
            throw new RuntimeException("同步失败");
        }

        // 写入 redis 库存
        for (OrdersGrabInfo ordersGrabInfo : ordersSeizeInfoList) {
            Long id = ordersGrabInfo.getId();
            long index = id % QUEUE_NUM;
            String key = String.format(ORDERS_STOCK_CACHE, index);
            redisTemplate.opsForHash().putIfAbsent(key, id, 1);
        }
    }

    @Override
    public void batchDelete(List<Long> idList) {
        Boolean success = elasticSearchTemplate.opsForDoc().batchDelete(IndexConstants.ORDERS_GRAB, idList);
        if (!success) {
            ThreadUtils.sleep(1000);
            throw new RuntimeException("同步失败");
        }
    }
}
