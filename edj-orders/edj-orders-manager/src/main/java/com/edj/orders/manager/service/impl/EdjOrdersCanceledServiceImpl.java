package com.edj.orders.manager.service.impl;

import com.edj.orders.base.domain.entity.EdjOrdersCanceled;
import com.edj.orders.base.mapper.EdjOrdersCanceledMapper;
import com.edj.orders.manager.service.EdjOrdersCanceledService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_orders_canceled(订单取消表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/03
 */
@Service
public class EdjOrdersCanceledServiceImpl extends MPJBaseServiceImpl<EdjOrdersCanceledMapper, EdjOrdersCanceled> implements EdjOrdersCanceledService {
}