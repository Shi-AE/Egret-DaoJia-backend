package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.BeanUtils;
import com.edj.foundations.domain.dto.ServeItemAddDTO;
import com.edj.foundations.domain.entity.EdjServeItem;
import com.edj.foundations.domain.entity.EdjServeType;
import com.edj.foundations.enums.EdjServeTypeActiveStatus;
import com.edj.foundations.mapper.EdjServeItemMapper;
import com.edj.foundations.mapper.EdjServeTypeMapper;
import com.edj.foundations.service.EdjServeItemService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 针对表【edj_serve_item(服务项表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/14
 */
@Service
@RequiredArgsConstructor
public class EdjServeItemServiceImpl extends MPJBaseServiceImpl<EdjServeItemMapper, EdjServeItem> implements EdjServeItemService {

    private final EdjServeTypeMapper serveTypeMapper;

    @Override
    public long activeServeItemCountByServeTypeId(long serveTypeId) {
        LambdaQueryWrapper<EdjServeItem> wrapper = new LambdaQueryWrapper<EdjServeItem>()
                .select(EdjServeItem::getId)
                .eq(EdjServeItem::getEdjServeTypeId, serveTypeId)
                .eq(EdjServeItem::getActiveStatus, EdjServeTypeActiveStatus.ENABLED);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    @Transactional
    public void add(ServeItemAddDTO serveItemAddDTO) {
        // 检查名称重复
        String name = serveItemAddDTO.getName();
        LambdaQueryWrapper<EdjServeItem> checkWrapper = new LambdaQueryWrapper<EdjServeItem>()
                .select(EdjServeItem::getId)
                .eq(EdjServeItem::getName, name);

        boolean serveItemExists = baseMapper.exists(checkWrapper);
        if (serveItemExists) {
            throw new BadRequestException("存在相同的服务项名称");
        }

        // 检查服务类型存在
        Long edjServeTypeId = serveItemAddDTO.getEdjServeTypeId();
        LambdaQueryWrapper<EdjServeType> existsWrapper = new LambdaQueryWrapper<EdjServeType>()
                .select(EdjServeType::getId)
                .eq(EdjServeType::getId, edjServeTypeId)
                .eq(EdjServeType::getActiveStatus, EdjServeTypeActiveStatus.ENABLED);
        boolean ServeItemExists = serveTypeMapper.exists(existsWrapper);
        if (!ServeItemExists) {
            throw new BadRequestException("服务类型不存在或者未启用");
        }

        // 插入
        EdjServeItem serveItem = BeanUtils.toBean(serveItemAddDTO, EdjServeItem.class);
        baseMapper.insert(serveItem);
    }
}