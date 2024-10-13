package com.edj.foundations.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.IdUtils;
import com.edj.foundations.domain.dto.ServeTypeAddDTO;
import com.edj.foundations.domain.entity.EdjServeType;
import com.edj.foundations.mapper.EdjServeTypeMapper;
import com.edj.foundations.service.EdjServeTypeService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_type(服务类型表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/12
 */
@Service
@RequiredArgsConstructor
public class EdjServeTypeServiceImpl extends MPJBaseServiceImpl<EdjServeTypeMapper, EdjServeType> implements EdjServeTypeService {

    private final Snowflake snowflake;

    private final EdjServeTypeMapper serveTypeMapper;

    @Override
    public void add(ServeTypeAddDTO serveTypeAddDTO) {
        // 服务名称查重
        String name = serveTypeAddDTO.getName();
        LambdaQueryWrapper<EdjServeType> checkWrapper = new LambdaQueryWrapper<EdjServeType>()
                .select(EdjServeType::getId)
                .eq(EdjServeType::getName, name);
        boolean exists = serveTypeMapper.exists(checkWrapper);
        if (!exists) {
            throw new ServerErrorException("服务名重复");
        }

        // 生成实体
        EdjServeType serveType = BeanUtils.toBean(serveTypeAddDTO, EdjServeType.class);

        // 设置编号
        long codeId = snowflake.nextId();
        serveType.setCode(IdUtils.toCode(codeId));

        serveTypeMapper.insert(serveType);
    }
}