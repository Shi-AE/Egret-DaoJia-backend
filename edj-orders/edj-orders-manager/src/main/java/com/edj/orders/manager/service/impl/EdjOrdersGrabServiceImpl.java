package com.edj.orders.manager.service.impl;

import com.edj.orders.base.domain.entity.EdjOrdersGrab;
import com.edj.orders.base.mapper.EdjOrdersGrabMapper;
import com.edj.orders.manager.service.EdjOrdersGrabService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_orders_grab(抢单池)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/04/23
 */
@Service
public class EdjOrdersGrabServiceImpl extends MPJBaseServiceImpl<EdjOrdersGrabMapper, EdjOrdersGrab> implements EdjOrdersGrabService {
}