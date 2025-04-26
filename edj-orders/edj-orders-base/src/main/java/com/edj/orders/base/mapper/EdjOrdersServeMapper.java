package com.edj.orders.base.mapper;

import com.edj.orders.base.domain.entity.EdjOrdersServe;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_orders_serve(订单服务表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2025/04/26
 */
@Mapper
public interface EdjOrdersServeMapper extends MPJBaseMapper<EdjOrdersServe> {
}