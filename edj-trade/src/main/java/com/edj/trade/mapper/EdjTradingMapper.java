package com.edj.trade.mapper;

import com.edj.trade.domain.entity.EdjTrading;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_trading(交易订单表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/12/30
 */
@Mapper
public interface EdjTradingMapper extends MPJBaseMapper<EdjTrading> {
}