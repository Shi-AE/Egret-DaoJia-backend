package com.edj.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.trade.constant.Constants;
import com.edj.trade.domain.entity.EdjPayChannel;
import com.edj.trade.mapper.EdjPayChannelMapper;
import com.edj.trade.service.EdjPayChannelService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_pay_channel(交易渠道表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Service
public class EdjPayChannelServiceImpl extends MPJBaseServiceImpl<EdjPayChannelMapper, EdjPayChannel> implements EdjPayChannelService {
    @Override
    public EdjPayChannel findByEnterpriseId(Long enterpriseId, String channelLabel) {
        LambdaQueryWrapper<EdjPayChannel> wrapper = new LambdaQueryWrapper<EdjPayChannel>()
                .eq(EdjPayChannel::getEnterpriseId, enterpriseId)
                .eq(EdjPayChannel::getChannelLabel, channelLabel)
                .eq(EdjPayChannel::getEnableFlag, Constants.YES);
        return baseMapper.selectOne(wrapper);
    }
}