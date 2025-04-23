package com.edj.orders.base.mapper;

import com.edj.orders.base.domain.entity.EdjOrdersDispatch;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_orders_dispatch(派单池)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2025/04/23
 */
@Mapper
public interface EdjOrdersDispatchMapper extends MPJBaseMapper<EdjOrdersDispatch> {
}