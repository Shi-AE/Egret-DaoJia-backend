package com.edj.orders.base.mapper;

import com.edj.orders.base.domain.entity.EdjOrdersCanceled;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 针对表【edj_orders_canceled(订单取消表)】的数据库操作Mapper
*
* @author A.E.
* @date 2025/03/03
*/
@Mapper
public interface EdjOrdersCanceledMapper extends MPJBaseMapper<EdjOrdersCanceled> {
}