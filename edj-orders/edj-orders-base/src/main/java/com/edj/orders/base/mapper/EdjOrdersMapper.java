package com.edj.orders.base.mapper;

import com.edj.orders.base.domain.entity.EdjOrders;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_orders(订单表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/12/23
 */
@Mapper
public interface EdjOrdersMapper extends MPJBaseMapper<EdjOrders> {
}