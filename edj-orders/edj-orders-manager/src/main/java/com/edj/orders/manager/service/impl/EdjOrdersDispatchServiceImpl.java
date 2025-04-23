package com.edj.orders.manager.service.impl;

import com.edj.orders.base.domain.entity.EdjOrdersDispatch;
import com.edj.orders.base.mapper.EdjOrdersDispatchMapper;
import com.edj.orders.manager.service.EdjOrdersDispatchService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_orders_dispatch(派单池)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/04/23
 */
@Service
public class EdjOrdersDispatchServiceImpl extends MPJBaseServiceImpl<EdjOrdersDispatchMapper, EdjOrdersDispatch> implements EdjOrdersDispatchService {
}