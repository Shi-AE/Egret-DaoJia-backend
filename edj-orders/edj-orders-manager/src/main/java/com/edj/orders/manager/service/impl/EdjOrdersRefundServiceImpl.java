package com.edj.orders.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.domain.entity.EjdBaseEntity;
import com.edj.orders.base.domain.entity.EdjOrdersRefund;
import com.edj.orders.base.mapper.EdjOrdersRefundMapper;
import com.edj.orders.manager.service.EdjOrdersRefundService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 针对表【edj_orders_refund(订单退款表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/04
 */
@Service
public class EdjOrdersRefundServiceImpl extends MPJBaseServiceImpl<EdjOrdersRefundMapper, EdjOrdersRefund> implements EdjOrdersRefundService {
    @Override
    public List<EdjOrdersRefund> selectRefundOrderListByCount(Integer refundOrderCount) {

        LambdaQueryWrapper<EdjOrdersRefund> queryWrapper = new LambdaQueryWrapper<EdjOrdersRefund>()
                .orderByAsc(EjdBaseEntity::getCreateTime)
                .last("LIMIT " + refundOrderCount);

        return baseMapper.selectList(queryWrapper);
    }
}