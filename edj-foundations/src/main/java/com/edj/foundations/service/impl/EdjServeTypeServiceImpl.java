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
import com.edj.common.utils.ObjectUtils;
import com.edj.common.utils.StringUtils;
import com.edj.foundations.domain.dto.ServeTypeAddDTO;
import com.edj.foundations.domain.dto.ServeTypePageDTO;
import com.edj.foundations.domain.dto.ServeTypeUpdateDTO;
import com.edj.foundations.domain.entity.EdjServeType;
import com.edj.foundations.domain.vo.ServeTypeStatusGetVO;
import com.edj.foundations.domain.vo.ServeTypeVO;
import com.edj.foundations.enums.EdjServeTypeActiveStatus;
import com.edj.foundations.mapper.EdjServeTypeMapper;
import com.edj.foundations.service.EdjServeItemService;
import com.edj.foundations.service.EdjServeTypeService;
import com.edj.mysql.utils.PageUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    private final EdjServeItemService serveItemService;

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
    public PageResult<ServeTypeVO> page(ServeTypePageDTO serveTypePageDTO) {
        Page<EdjServeType> page = PageUtils.parsePageQuery(serveTypePageDTO);
        Page<EdjServeType> serveTypePage = baseMapper.selectPage(page, new LambdaQueryWrapper<>());
        return PageUtils.toPage(serveTypePage, ServeTypeVO.class);
    }

    @Override
    @Transactional
    public void activate(Long id) {
        // 检查服务类型
        EdjServeType serveType = baseMapper.selectById(id);
        if (ObjectUtils.isNull(serveType)) {
            throw new BadRequestException("服务类型不存在");
        }

        // 检查状态
        Integer activeStatus = serveType.getActiveStatus();
        if (Objects.equals(activeStatus, EdjServeTypeActiveStatus.ENABLED.getValue())) {
            throw new BadRequestException("服务类型已启用");
        }

        // 更新
        LambdaUpdateWrapper<EdjServeType> updateWrapper = new LambdaUpdateWrapper<EdjServeType>()
                .eq(EdjServeType::getId, id)
                .set(EdjServeType::getActiveStatus, EdjServeTypeActiveStatus.ENABLED);

        baseMapper.update(new EdjServeType(), updateWrapper);
    }

    @Override
    @Transactional
    public void deactivate(Long id) {
        // 检查服务类型
        EdjServeType serveType = baseMapper.selectById(id);
        if (ObjectUtils.isNull(serveType)) {
            throw new BadRequestException("服务类型不存在");
        }

        // 检查状态
        Integer activeStatus = serveType.getActiveStatus();
        if (!Objects.equals(activeStatus, EdjServeTypeActiveStatus.ENABLED.getValue())) {
            throw new BadRequestException("服务类型未启用");
        }

        // 检查服务项是否存在启用的
        long count = serveItemService.activeServeItemCountByServeTypeId(id);
        if (count > 0) {
            throw new BadRequestException("该服务类型下有启用状态的服务项");
        }

        LambdaUpdateWrapper<EdjServeType> updateWrapper = new LambdaUpdateWrapper<EdjServeType>()
                .eq(EdjServeType::getId, id)
                .set(EdjServeType::getActiveStatus, EdjServeTypeActiveStatus.DISABLED);

        baseMapper.update(new EdjServeType(), updateWrapper);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // 检查服务类型
        EdjServeType serveType = baseMapper.selectById(id);
        if (ObjectUtils.isNull(serveType)) {
            throw new BadRequestException("服务类型不存在");
        }

        // 检查状态
        Integer activeStatus = serveType.getActiveStatus();
        if (!Objects.equals(activeStatus, EdjServeTypeActiveStatus.DRAFTS.getValue())) {
            throw new BadRequestException("只有草稿状态可删除");
        }

        baseMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void update(ServeTypeUpdateDTO serveTypeUpdateDTO) {
        String name = serveTypeUpdateDTO.getName();
        Long id = serveTypeUpdateDTO.getId();

        if (StringUtils.isNotBlank(name)) {
            // 检查服务类型名重复
            LambdaQueryWrapper<EdjServeType> repeatWrapper = new LambdaQueryWrapper<EdjServeType>()
                    .select(EdjServeType::getId)
                    .ne(EdjServeType::getId, id)
                    .eq(EdjServeType::getName, name);
            boolean exists = baseMapper.exists(repeatWrapper);
            if (exists) {
                throw new BadRequestException("服务类型名重复");
            }
        }

        EdjServeType serveType = BeanUtils.toBean(serveTypeUpdateDTO, EdjServeType.class);
        int update = baseMapper.updateById(serveType);
        if (update != 1) {
            throw new BadRequestException("服务类型不存在");
        }
    }

    @Override
    public List<ServeTypeStatusGetVO> selectByStatus(Integer activeStatus) {
        LambdaQueryWrapper<EdjServeType> queryWrapper = new LambdaQueryWrapper<EdjServeType>()
                .select(EdjServeType::getId, EdjServeType::getName)
                .eq(ObjectUtils.isNotNull(activeStatus), EdjServeType::getActiveStatus, activeStatus)
                .orderByAsc(EdjServeType::getSortNum);

        List<EdjServeType> edjServeTypes = baseMapper.selectList(queryWrapper);
        return BeanUtils.copyToList(edjServeTypes, ServeTypeStatusGetVO.class);
    }
}