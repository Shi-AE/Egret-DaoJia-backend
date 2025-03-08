package com.edj.market.service.impl;

import com.edj.market.domain.entity.EdjActivity;
import com.edj.market.mapper.EdjActivityMapper;
import com.edj.market.service.EdjActivityService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_activity(优惠券活动表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/03/09
 */
@Service
public class EdjActivityServiceImpl extends MPJBaseServiceImpl<EdjActivityMapper, EdjActivity> implements EdjActivityService {
}