package com.edj.orders.manager.listener;

import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.api.api.trade.enums.EdjTradingState;
import com.edj.common.constants.MqConstants;
import com.edj.common.domain.msg.TradeStatusMsg;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.JsonUtils;
import com.edj.common.utils.StringUtils;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.enums.EdjOrderPayStatus;
import com.edj.orders.base.enums.EdjOrderStatus;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.base.service.OrdersDiversionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.edj.orders.base.constants.OrderConstants.PRODUCT_APP_ID;

/**
 * 支付结果 mq 监听
 *
 * @author A.E.
 * @date 2025/3/8
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TradeStatusListener {

    private final EdjOrdersMapper ordersMapper;

    private final OrdersDiversionService ordersDiversionService;

    /**
     * 监听并更新支付结果
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    name = MqConstants.Queues.ORDERS_TRADE_UPDATE_STATUS,
                    arguments = {@Argument(name = MqConstants.X_QUEUE_TYPE, value = MqConstants.QUORUM)}
            ),
            exchange = @Exchange(name = MqConstants.Exchanges.TRADE, type = ExchangeTypes.TOPIC),
            key = {MqConstants.RoutingKeys.UPDATE_STATUS}
    ))
    public void listenTradeUpdatePayStatusMes(String msg) {
        log.info("接收到支付结果状态的消息 ({}) -> {}", MqConstants.Queues.ORDERS_TRADE_UPDATE_STATUS, msg);
        List<TradeStatusMsg> tradeStatusMsgList = JsonUtils.toBean(msg, new TypeReference<>() {
        }, false);

        // 只处理此服务且支付成功的订单
        tradeStatusMsgList.stream()
                .filter(m -> EnumUtils.eq(EdjTradingState.SETTLED, m.getStatusCode()) &&
                        StringUtils.equals(PRODUCT_APP_ID, m.getProductAppId()))
                .forEach(m -> {
                    // 修改订单状态
                    LambdaUpdateWrapper<EdjOrders> wrapper = new LambdaUpdateWrapper<EdjOrders>()
                            .eq(EdjOrders::getId, m.getProductOrderNo())
                            .eq(EdjOrders::getOrdersStatus, EdjOrderStatus.PENDING_PAYMENT)
                            .set(EdjOrders::getTransactionId, m.getTransactionId())
                            .set(EdjOrders::getOrdersStatus, EdjOrderStatus.DISPATCHING)
                            .set(EdjOrders::getPayStatus, EdjOrderPayStatus.SUCCESS)
                            .set(EdjOrders::getPayTime, LocalDateTime.now());
                    ordersMapper.update(new EdjOrders(), wrapper);

                    // 订单分流
                    ordersDiversionService.diversion(m.getProductOrderNo());
                });
    }
}
