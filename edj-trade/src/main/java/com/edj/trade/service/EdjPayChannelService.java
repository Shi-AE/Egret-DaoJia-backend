package com.edj.trade.service;

import com.edj.trade.domain.entity.EdjPayChannel;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_pay_channel(交易渠道表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/12/31
 */
public interface EdjPayChannelService extends MPJBaseService<EdjPayChannel> {

    /**
     * 根据商户id查询渠道配置
     *
     * @param enterpriseId 商户id
     * @param channelLabel 通道唯一标记
     * @return PayChannelEntity 交易渠道对象
     */
    EdjPayChannel findByEnterpriseId(Long enterpriseId, String channelLabel);
}
