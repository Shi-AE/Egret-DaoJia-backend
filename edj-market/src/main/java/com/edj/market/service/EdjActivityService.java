package com.edj.market.service;

import com.edj.common.domain.PageResult;
import com.edj.market.domain.dto.ActivityPageDTO;
import com.edj.market.domain.dto.ActivitySaveDTO;
import com.edj.market.domain.entity.EdjActivity;
import com.edj.market.domain.vo.ActivityPageVO;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_activity(优惠券活动表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2025/03/09
 */
public interface EdjActivityService extends MPJBaseService<EdjActivity> {

    /**
     * 保存优惠券活动
     */
    void save(ActivitySaveDTO activitySaveDTO);

    /**
     * 运营端分页查询优惠券活动
     */
    PageResult<ActivityPageVO> page(ActivityPageDTO activityPageDTO);

    /**
     * 查询优惠券活动详情
     */
    ActivityPageVO detail(Long id);

    /**
     * 撤销优惠券活动
     */
    void revoke(Long id);

    /**
     * 更新优惠券活动状态
     * 1. 到达发放开始时间状态改为“进行中”
     * 2. 到达发放结束时间状态改为“已失效”
     */
    void updateStatus();
}
