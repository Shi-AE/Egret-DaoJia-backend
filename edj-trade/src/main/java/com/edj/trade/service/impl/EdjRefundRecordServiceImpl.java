package com.edj.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.trade.domain.entity.EdjRefundRecord;
import com.edj.trade.enums.EdjRefundStatus;
import com.edj.trade.mapper.EdjRefundRecordMapper;
import com.edj.trade.service.EdjRefundRecordService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 针对表【edj_refund_record(退款记录表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Service
public class EdjRefundRecordServiceImpl extends MPJBaseServiceImpl<EdjRefundRecordMapper, EdjRefundRecord> implements EdjRefundRecordService {
    @Override
    public List<EdjRefundRecord> findRefundingByOrderNo(Long tradingOrderNo) {
        LambdaQueryWrapper<EdjRefundRecord> wrapper = new LambdaQueryWrapper<EdjRefundRecord>()
                .eq(EdjRefundRecord::getTradingOrderNo, tradingOrderNo)
                .eq(EdjRefundRecord::getRefundStatus, EdjRefundStatus.REFUNDING)
                .orderByAsc(EdjRefundRecord::getCreateTime);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public EdjRefundRecord findByRefundNo(Long refundNo) {
        LambdaQueryWrapper<EdjRefundRecord> wrapper = new LambdaQueryWrapper<EdjRefundRecord>()
                .eq(EdjRefundRecord::getRefundNo, refundNo);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<EdjRefundRecord> findByOrderNo(Long tradingOrderNo) {
        LambdaQueryWrapper<EdjRefundRecord> wrapper = new LambdaQueryWrapper<EdjRefundRecord>()
                .eq(EdjRefundRecord::getTradingOrderNo, tradingOrderNo)
                .orderByAsc(EdjRefundRecord::getCreateTime);
        return baseMapper.selectList(wrapper);
    }
}