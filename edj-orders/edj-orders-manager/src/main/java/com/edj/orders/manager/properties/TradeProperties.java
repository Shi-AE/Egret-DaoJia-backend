package com.edj.orders.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 支付商户配置
 *
 * @author A.E.
 * @date 2024/12/29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "edj.trade")
public class TradeProperties {

    /**
     * 支付宝商户id
     */
    private Long aliEnterpriseId;

    /**
     * 微信支付商户id
     */
    private Long wechatEnterpriseId;
}
