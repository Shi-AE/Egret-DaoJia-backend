package com.edj.orders.base.service;


/**
 * 订单分流服务
 *
 * @author A.E.
 * @date 2025/4/22
 */
public interface OrdersDiversionService {

    /**
     * 订单分流
     */
    void diversion(Long orders);
}
