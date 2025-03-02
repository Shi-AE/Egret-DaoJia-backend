package com.edj.orders.manager.service;

import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.manager.domain.vo.OrdersDetailVO;
import com.edj.orders.manager.domain.vo.OrdersSimpleVO;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_orders(订单表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/12/23
 */
public interface EdjOrdersManagerService extends MPJBaseService<EdjOrders> {

    /**
     * 根据id列表查询订单
     */
    List<EdjOrders> selectByIdList(List<Long> idList);

    /**
     * 订单查询
     */
    List<OrdersSimpleVO> list(Integer ordersStatus, Long lastId);

    /**
     * 根据id查询订单详细信息
     */
    OrdersDetailVO detail(Long id);
}
