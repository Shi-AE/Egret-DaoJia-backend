package com.edj.orders.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "edj.orders.job")
public class OrdersJobProperties {

    /**
     * 自动评价订单数量，默认100
     */
    private Integer autoEvaluateCount = 100;

    /**
     * 退款订单数量，默认100
     */
    private Integer refundOrderCount = 100;

    /**
     * 每次执行获取超时支付订单数量，默认100
     */
    private Integer overTimePayOrderCount = 100;

    /**
     * 订单超时时间（分钟），默认15
     */
    private Integer orderOverTime = 15;

    /**
     * 派单超时订单数量，默认100
     */
    private Integer dispatchOverTimeOrderCount = 100;
}
