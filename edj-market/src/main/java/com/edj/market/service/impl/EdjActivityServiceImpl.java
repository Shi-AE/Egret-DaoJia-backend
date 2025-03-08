package com.edj.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.common.utils.StringUtils;
import com.edj.market.domain.dto.ActivityPageDTO;
import com.edj.market.domain.dto.ActivitySaveDTO;
import com.edj.market.domain.entity.EdjActivity;
import com.edj.market.domain.vo.ActivityPageVO;
import com.edj.market.mapper.EdjActivityMapper;
import com.edj.market.service.EdjActivityService;
import com.edj.mysql.utils.PageUtils;
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
    @Override
    public void save(ActivitySaveDTO activitySaveDTO) {
        // 参数校验
        activitySaveDTO.check();

        // 数据组装
        EdjActivity activity = BeanUtils.toBean(activitySaveDTO, EdjActivity.class);

        // 保存数据
        saveOrUpdate(activity);
    }

    @Override
    public PageResult<ActivityPageVO> page(ActivityPageDTO activityPageDTO) {

        Page<EdjActivity> page = PageUtils.parsePageQuery(activityPageDTO);

        Long id = activityPageDTO.getId();
        String name = activityPageDTO.getName();
        Integer type = activityPageDTO.getType();
        Integer status = activityPageDTO.getStatus();
        LambdaQueryWrapper<EdjActivity> wrapper = new LambdaQueryWrapper<EdjActivity>()
                .eq(ObjectUtils.isNotNull(id), EdjActivity::getId, id)
                .like(StringUtils.isNotBlank(name), EdjActivity::getName, name)
                .eq(ObjectUtils.isNotNull(type), EdjActivity::getType, type)
                .eq(ObjectUtils.isNotNull(status), EdjActivity::getStatus, status)
                .orderByDesc(EdjActivity::getId);

        Page<EdjActivity> activityPage = baseMapper.selectPage(page, wrapper);

        return PageUtils.toPage(activityPage, ActivityPageVO.class);
    }
}