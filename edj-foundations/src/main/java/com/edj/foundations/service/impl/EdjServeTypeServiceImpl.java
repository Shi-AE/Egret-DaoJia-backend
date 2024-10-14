package com.edj.foundations.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.IdUtils;
import com.edj.foundations.domain.dto.ServeTypeAddDTO;
import com.edj.foundations.domain.dto.ServerTypePageDTO;
import com.edj.foundations.domain.entity.EdjServeType;
import com.edj.foundations.domain.vo.ServerTypeVO;
import com.edj.foundations.enums.EdjActiveStatus;
import com.edj.foundations.mapper.EdjServeTypeMapper;
import com.edj.foundations.service.EdjServeTypeService;
import com.edj.mysql.utils.PageUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    @Override
    @Transactional
    public void add(ServeTypeAddDTO serveTypeAddDTO) {
        // 服务名称查重
        String name = serveTypeAddDTO.getName();
        LambdaQueryWrapper<EdjServeType> checkWrapper = new LambdaQueryWrapper<EdjServeType>()
                .select(EdjServeType::getId)
                .eq(EdjServeType::getName, name);
        boolean exists = baseMapper.exists(checkWrapper);
        if (exists) {
            throw new ServerErrorException("服务名重复");
        }

        // 生成实体
        EdjServeType serveType = BeanUtils.toBean(serveTypeAddDTO, EdjServeType.class);

        // 设置编号
        long codeId = snowflake.nextId();
        serveType.setCode(IdUtils.toCode(codeId));

        baseMapper.insert(serveType);
    }

    @Override
    public PageResult<ServerTypeVO> page(ServerTypePageDTO serverTypePageDTO) {
        Page<EdjServeType> page = PageUtils.parsePageQuery(serverTypePageDTO);
        Page<EdjServeType> serveTypePage = baseMapper.selectPage(page, new LambdaQueryWrapper<>());
        return PageUtils.toPage(serveTypePage, ServerTypeVO.class);
    }

    @Override
    @Transactional
    public void activate(Long id) {
        EdjServeType serveType = baseMapper.selectById(id);
        if (serveType == null) {
            throw new BadRequestException("服务类型不存在");
        }

        Integer activeStatus = serveType.getActiveStatus();
        if (Objects.equals(activeStatus, EdjActiveStatus.ENABLED.getValue())) {
            throw new BadRequestException("服务类型已启用");
        }

        LambdaUpdateWrapper<EdjServeType> updateWrapper = new LambdaUpdateWrapper<EdjServeType>()
                .eq(EdjServeType::getId, id)
                .set(EdjServeType::getActiveStatus, EdjActiveStatus.ENABLED);

        baseMapper.update(updateWrapper);
    }
}