package com.edj.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.NumberUtils;
import com.edj.trade.constant.Constants;
import com.edj.trade.domain.entity.EdjTrading;
import com.edj.trade.enums.EdjTradingState;
import com.edj.trade.mapper.EdjTradingMapper;
import com.edj.trade.service.EdjTradingService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 针对表【edj_trading(交易订单表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/30
 */
@Service
public class EdjTradingServiceImpl extends MPJBaseServiceImpl<EdjTradingMapper, EdjTrading> implements EdjTradingService {

    @Override
    public EdjTrading getDuringTrading(String productAppId, Long productOrderNo, String tradingChannel) {
        LambdaQueryWrapper<EdjTrading> wrapper = new LambdaQueryWrapper<EdjTrading>()
                .eq(EdjTrading::getProductAppId, productAppId)
                .eq(EdjTrading::getProductOrderNo, productOrderNo)
                .eq(EdjTrading::getTradingChannel, tradingChannel)
                .eq(EdjTrading::getTradingState, EdjTradingState.PAYMENT_IN_PROGRESS);
        return CollUtils.getFirst(baseMapper.selectList(wrapper));
    }

    @Override
    public EdjTrading getFinishedTrading(String productAppId, Long productOrderNo) {
        LambdaQueryWrapper<EdjTrading> wrapper = new LambdaQueryWrapper<EdjTrading>()
                .eq(EdjTrading::getProductAppId, productAppId)
                .eq(EdjTrading::getProductOrderNo, productOrderNo)
                .eq(EdjTrading::getTradingState, EdjTradingState.SETTLED);
        return CollUtils.getFirst(baseMapper.selectList(wrapper));
    }

    @Override
    public List<EdjTrading> getByProductOrder(String productAppId, Long productOrderNo) {
        LambdaQueryWrapper<EdjTrading> wrapper = new LambdaQueryWrapper<EdjTrading>()
                .eq(EdjTrading::getProductOrderNo, productOrderNo)
                .eq(EdjTrading::getProductAppId, productAppId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public EdjTrading findTradByTradingOrderNo(Long tradingOrderNo) {
        LambdaQueryWrapper<EdjTrading> wrapper = new LambdaQueryWrapper<EdjTrading>()
                .eq(EdjTrading::getTradingOrderNo, tradingOrderNo);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<EdjTrading> findListByTradingState(EdjTradingState tradingState, Integer count) {
        count = NumberUtils.max(count, 10);
        LambdaQueryWrapper<EdjTrading> wrapper = new LambdaQueryWrapper<EdjTrading>()
                .eq(EdjTrading::getTradingState, tradingState)
                .eq(EdjTrading::getEnableFlag, Constants.YES)
                .orderByAsc(EdjTrading::getCreateTime)
                .last("LIMIT " + count);
        return baseMapper.selectList(wrapper);
    }
}