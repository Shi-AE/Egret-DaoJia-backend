package com.edj.trade.mapper;

import com.edj.trade.domain.entity.EdjRefundRecord;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_refund_record(退款记录表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Mapper
public interface EdjRefundRecordMapper extends MPJBaseMapper<EdjRefundRecord> {
}