package com.edj.orders.manager.handler;

import com.edj.orders.manager.domain.dto.OrdersCancelDTO;
import com.edj.orders.manager.properties.OrdersJobProperties;
import com.edj.orders.manager.service.EdjOrdersCreateService;
import com.edj.orders.manager.service.EdjOrdersManagerService;
import com.edj.security.utils.SecurityUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单定时任务
 *
 * @author A.E.
 * @date 2025/3/3
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrdersHandler {

    private final OrdersJobProperties ordersJobProperties;

    private final EdjOrdersCreateService ordersCreateService;

    private final EdjOrdersManagerService ordersManagerService;

    /**
     * 取消支付超时订单
     * 每一分钟执行一次
     */
    @XxlJob("CancelOverTimePayOrder")
    public void cancelOverTimePayOrder() {

        log.info(">>>>>>>> 开始处理支付超时订单");

        // 获取超时订单
        Integer overTimePayOrderCount = ordersJobProperties.getOverTimePayOrderCount();
        List<Long> ordersIdList = ordersCreateService.selectOverTimePayOrdersListByCount(overTimePayOrderCount);

        ordersIdList.stream()
                .map(id -> OrdersCancelDTO.builder()
                        .id(id)
                        .currentUserId(SecurityUtils.SYSTEM_USER_ID)
                        .currentUserName(SecurityUtils.SYSTEM_USER_NAME)
                        .cancelReason("订单超时支付，自动取消")
                        .build()
                )
                .forEach(ordersManagerService::cancelForPendingPayment);

        log.info(">>>>>>>> 处理完成 {} 条支付超时订单", ordersIdList.size());
    }
}
