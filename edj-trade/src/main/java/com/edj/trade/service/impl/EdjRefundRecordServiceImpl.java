package com.edj.trade.service.impl;

import com.edj.trade.domain.entity.EdjRefundRecord;
import com.edj.trade.mapper.EdjRefundRecordMapper;
import com.edj.trade.service.EdjRefundRecordService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_refund_record(退款记录表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Service
public class EdjRefundRecordServiceImpl extends MPJBaseServiceImpl<EdjRefundRecordMapper, EdjRefundRecord> implements EdjRefundRecordService {
}