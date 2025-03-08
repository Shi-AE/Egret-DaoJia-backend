package com.edj.market.mapper;

import com.edj.market.domain.entity.EdjActivity;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_activity(优惠券活动表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Mapper
public interface EdjActivityMapper extends MPJBaseMapper<EdjActivity> {
}