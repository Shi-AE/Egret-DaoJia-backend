package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.foundations.domain.entity.EdjServeItem;
import com.edj.foundations.enums.EdjServerTypeActiveStatus;
import com.edj.foundations.mapper.EdjServeItemMapper;
import com.edj.foundations.service.EdjServeItemService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_item(服务项表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/14
 */
@Service
public class EdjServeItemServiceImpl extends MPJBaseServiceImpl<EdjServeItemMapper, EdjServeItem> implements EdjServeItemService {
    @Override
    public long activeServeItemCountByServeTypeId(long serveTypeId) {
        LambdaQueryWrapper<EdjServeItem> wrapper = new LambdaQueryWrapper<EdjServeItem>()
                .select(EdjServeItem::getId)
                .eq(EdjServeItem::getEdjServeTypeId, serveTypeId)
                .eq(EdjServeItem::getActiveStatus, EdjServerTypeActiveStatus.ENABLED);
        return baseMapper.selectCount(wrapper);
    }
}