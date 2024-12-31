package com.edj.trade.mapper;

import com.edj.trade.domain.entity.EdjPayChannel;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_pay_channel(交易渠道表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Mapper
public interface EdjPayChannelMapper extends MPJBaseMapper<EdjPayChannel> {
}