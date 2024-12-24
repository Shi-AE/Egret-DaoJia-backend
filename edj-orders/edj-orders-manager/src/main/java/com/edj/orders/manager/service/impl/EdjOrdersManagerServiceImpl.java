package com.edj.orders.manager.service.impl;

import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.manager.service.EdjOrdersManagerService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_orders(订单表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/23
 */
@Service
public class EdjOrdersManagerServiceImpl extends MPJBaseServiceImpl<EdjOrdersMapper, EdjOrders> implements EdjOrdersManagerService {
}