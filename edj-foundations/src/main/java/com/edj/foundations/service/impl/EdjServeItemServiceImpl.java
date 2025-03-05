package com.edj.foundations.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.*;
import com.edj.foundations.domain.dto.ServeItemAddDTO;
import com.edj.foundations.domain.dto.ServeItemPageDTO;
import com.edj.foundations.domain.dto.ServeItemUpdateDTO;
import com.edj.foundations.domain.entity.EdjServe;
import com.edj.foundations.domain.entity.EdjServeItem;
import com.edj.foundations.domain.entity.EdjServeSync;
import com.edj.foundations.domain.entity.EdjServeType;
import com.edj.foundations.domain.vo.ServeItemVO;
import com.edj.foundations.enums.EdjServeItemActiveStatus;
import com.edj.foundations.enums.EdjServeSaleStatus;
import com.edj.foundations.enums.EdjServeTypeActiveStatus;
import com.edj.foundations.mapper.EdjServeItemMapper;
import com.edj.foundations.mapper.EdjServeMapper;
import com.edj.foundations.mapper.EdjServeSyncMapper;
import com.edj.foundations.mapper.EdjServeTypeMapper;
import com.edj.foundations.service.EdjServeItemService;
import com.edj.mysql.utils.PageUtils;
import com.edj.mysql.utils.SqlUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.edj.cache.constants.CacheConstants.CacheManager.ONE_DAY;
import static com.edj.cache.constants.CacheConstants.CacheManager.THIRTY_MINUTES;
import static com.edj.cache.constants.CacheConstants.CacheName.SEVER_ITEM_CACHE;

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

    private final EdjServeSyncMapper serveSyncMapper;

    private final Snowflake snowflake;

    private final EdjServeMapper serveMapper;

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
        serveItem.setCode(IdUtils.toCode(snowflake.nextId()));
        baseMapper.insert(serveItem);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = SEVER_ITEM_CACHE, key = "#serveItemUpdateDTO.id", beforeInvocation = true)
    public void update(ServeItemUpdateDTO serveItemUpdateDTO) {
        Long id = serveItemUpdateDTO.getId();
        String img = serveItemUpdateDTO.getImg();
        String icon = serveItemUpdateDTO.getIcon();
        Integer sortNum = serveItemUpdateDTO.getSortNum();
        Integer unit = serveItemUpdateDTO.getUnit();
        String detailImg = serveItemUpdateDTO.getDetailImg();

        // 检查名称重复
        String name = serveItemUpdateDTO.getName();
        if (StringUtils.isNotBlank(name)) {
            LambdaQueryWrapper<EdjServeItem> checkWrapper = new LambdaQueryWrapper<EdjServeItem>()
                    .select(EdjServeItem::getId)
                    .eq(EdjServeItem::getName, name)
                    .ne(EdjServeItem::getId, id);
            boolean exists = baseMapper.exists(checkWrapper);
            if (exists) {
                throw new BadRequestException("存在相同的服务项名称");
            }
        }

        // 检查服务类型存在
        Long edjServeTypeId = serveItemUpdateDTO.getEdjServeTypeId();
        if (ObjectUtils.isNotNull(edjServeTypeId)) {
            LambdaQueryWrapper<EdjServeType> existsWrapper = new LambdaQueryWrapper<EdjServeType>()
                    .select(EdjServeType::getId)
                    .eq(EdjServeType::getId, edjServeTypeId)
                    .eq(EdjServeType::getActiveStatus, EdjServeTypeActiveStatus.ENABLED);
            boolean ServeItemExists = serveTypeMapper.exists(existsWrapper);
            if (!ServeItemExists) {
                throw new BadRequestException("服务类型不存在或者未启用");
            }
        }

        // 更新
        EdjServeItem serveItem = BeanUtil.toBean(serveItemUpdateDTO, EdjServeItem.class);
        baseMapper.updateById(serveItem);

        // 同步更新至同步表
        LambdaUpdateWrapper<EdjServeSync> wrapper = new LambdaUpdateWrapper<EdjServeSync>()
                .eq(EdjServeSync::getEdjServeItemId, id)
                .set(StringUtils.isNotBlank(detailImg), EdjServeSync::getDetailImg, detailImg)
                .set(StringUtils.isNotBlank(name), EdjServeSync::getServeItemName, name)
                .set(StringUtils.isNotBlank(img), EdjServeSync::getServeItemImg, img)
                .set(StringUtils.isNotBlank(icon), EdjServeSync::getServeItemIcon, icon)
                .set(ObjectUtils.isNotNull(sortNum), EdjServeSync::getServeItemSortNum, sortNum)
                .set(ObjectUtils.isNotNull(unit), EdjServeSync::getUnit, unit);

        // 如果修改服务类型，则需要同步更换服务类型信息
        if (ObjectUtils.isNotNull(edjServeTypeId)) {
            EdjServeType serveType = serveTypeMapper.selectById(edjServeTypeId);
            wrapper.set(EdjServeSync::getEdjServeTypeId, serveType.getId())
                    .set(EdjServeSync::getServeTypeName, serveType.getName())
                    .set(EdjServeSync::getServeTypeSortNum, serveType.getSortNum())
                    .set(EdjServeSync::getServeTypeImg, serveType.getImg())
                    .set(EdjServeSync::getServeTypeIcon, serveType.getIcon());
        }

        if (SqlUtils.isUpdate(wrapper)) {
            serveSyncMapper.update(new EdjServeSync(), wrapper);
        }
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = SEVER_ITEM_CACHE, key = "#id", beforeInvocation = true)
    public void activate(Long id) {
        // 查询服务项状态
        LambdaQueryWrapper<EdjServeItem> serveItemLambdaQueryWrapper = new LambdaQueryWrapper<EdjServeItem>()
                .select(EdjServeItem::getActiveStatus, EdjServeItem::getEdjServeTypeId)
                .eq(EdjServeItem::getId, id);
        EdjServeItem serveItem = baseMapper.selectOne(serveItemLambdaQueryWrapper);

        // 检查不存在
        if (ObjectUtils.isNull(serveItem)) {
            throw new BadRequestException("服务项不存在");
        }

        // 检查已启用
        Integer serveItemStatus = serveItem.getActiveStatus();
        if (EnumUtils.eq(EdjServeItemActiveStatus.ENABLED, serveItemStatus)) {
            throw new BadRequestException("服务项已启用");
        }

        // 检查服务类型
        Long edjServeTypeId = serveItem.getEdjServeTypeId();
        LambdaQueryWrapper<EdjServeType> serveTypeLambdaQueryWrapper = new LambdaQueryWrapper<EdjServeType>()
                .select(EdjServeType::getActiveStatus)
                .eq(EdjServeType::getId, edjServeTypeId);
        EdjServeType serveType = serveTypeMapper.selectOne(serveTypeLambdaQueryWrapper);

        // 检查不存在
        if (ObjectUtils.isNull(serveType)) {
            throw new BadRequestException("服务类型不存在");
        }

        Integer serveTypeStatus = serveType.getActiveStatus();
        // 服务类型为草稿
        if (EnumUtils.eq(EdjServeTypeActiveStatus.DRAFTS, serveTypeStatus)) {
            throw new BadRequestException("服务类型未启用");
        }

        // 服务类型禁用
        if (EnumUtils.eq(EdjServeTypeActiveStatus.DISABLED, serveTypeStatus)) {
            throw new BadRequestException("服务类型已禁用");
        }

        LambdaUpdateWrapper<EdjServeItem> updateWrapper = new LambdaUpdateWrapper<EdjServeItem>()
                .eq(EdjServeItem::getId, id)
                .set(EdjServeItem::getActiveStatus, EdjServeItemActiveStatus.ENABLED);

        baseMapper.update(new EdjServeItem(), updateWrapper);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = SEVER_ITEM_CACHE, key = "#id", beforeInvocation = true)
    public void deactivate(Long id) {
        // 查询服务项状态
        LambdaQueryWrapper<EdjServeItem> serveItemLambdaQueryWrapper = new LambdaQueryWrapper<EdjServeItem>()
                .select(EdjServeItem::getActiveStatus)
                .eq(EdjServeItem::getId, id);
        EdjServeItem serveItem = baseMapper.selectOne(serveItemLambdaQueryWrapper);

        // 检查不存在
        if (ObjectUtils.isNull(serveItem)) {
            throw new BadRequestException("服务项不存在");
        }

        // 检查已未启用
        Integer serveItemStatus = serveItem.getActiveStatus();
        if (EnumUtils.ne(EdjServeItemActiveStatus.ENABLED, serveItemStatus)) {
            throw new BadRequestException("服务项未启用");
        }

        // 检查区域使用服务
        LambdaQueryWrapper<EdjServe> serveCheckWrapper = new LambdaQueryWrapper<EdjServe>()
                .select(EdjServe::getId)
                .eq(EdjServe::getEdjServeItemId, id)
                .eq(EdjServe::getSaleStatus, EdjServeSaleStatus.PUBLISHED);
        boolean exists = serveMapper.exists(serveCheckWrapper);
        if (exists) {
            throw new BadRequestException("存在区域服务，正在使用该服务项");
        }

        LambdaUpdateWrapper<EdjServeItem> updateWrapper = new LambdaUpdateWrapper<EdjServeItem>()
                .eq(EdjServeItem::getId, id)
                .set(EdjServeItem::getActiveStatus, EdjServeItemActiveStatus.DISABLED);
        baseMapper.update(new EdjServeItem(), updateWrapper);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = SEVER_ITEM_CACHE, key = "#id", beforeInvocation = true)
    public void delete(Long id) {
        // 查询服务项状态
        EdjServeItem serveItem = baseMapper.selectById(id);

        // 检查不存在
        if (ObjectUtils.isNull(serveItem)) {
            throw new BadRequestException("服务项不存在");
        }

        // 检查已未启用
        Integer serveItemStatus = serveItem.getActiveStatus();
        if (EnumUtils.ne(EdjServeItemActiveStatus.DRAFTS, serveItemStatus)) {
            throw new BadRequestException("只有草稿状态可删除");
        }

        baseMapper.deleteById(id);
    }

    @Override
    public PageResult<ServeItemVO> page(ServeItemPageDTO serveItemPageDTO) {

        String name = serveItemPageDTO.getName();
        Integer activeStatus = serveItemPageDTO.getActiveStatus();
        Long edjServeTypeId = serveItemPageDTO.getEdjServeTypeId();

        Page<ServeItemVO> page = PageUtils.parsePageQuery(serveItemPageDTO);
        MPJLambdaWrapper<EdjServeItem> queryWrapper = new MPJLambdaWrapper<EdjServeItem>()
                .selectAll(EdjServeItem.class)
                .selectAs(EdjServeType::getName, ServeItemVO::getServeTypeName)
                .innerJoin(EdjServeType.class, EdjServeType::getId, EdjServeItem::getEdjServeTypeId)
                .eq(StringUtils.isNotBlank(name), EdjServeItem::getName, name)
                .eq(ObjectUtils.isNotNull(activeStatus), EdjServeItem::getActiveStatus, activeStatus)
                .eq(ObjectUtils.isNotNull(edjServeTypeId), EdjServeType::getId, edjServeTypeId);

        Page<ServeItemVO> serveItemPageVOPage = baseMapper.selectJoinPage(page, ServeItemVO.class, queryWrapper);
        return new PageResult<>((int) serveItemPageVOPage.getPages(), serveItemPageVOPage.getTotal(),
                serveItemPageVOPage.getRecords());
    }

    @Override
    @Caching(cacheable = {
            // 非空缓存一天
            @Cacheable(cacheNames = SEVER_ITEM_CACHE, key = "#id", unless = "#result == null", cacheManager = ONE_DAY),
            // 空结果缓存30分钟
            @Cacheable(cacheNames = SEVER_ITEM_CACHE, key = "#id", unless = "#result != null", cacheManager = THIRTY_MINUTES)
    })
    public ServeItemVO selectById(Long id) {
        MPJLambdaWrapper<EdjServeItem> queryWrapper = new MPJLambdaWrapper<EdjServeItem>()
                .selectAll(EdjServeItem.class)
                .selectAs(EdjServeType::getName, ServeItemVO::getServeTypeName)
                .innerJoin(EdjServeType.class, EdjServeType::getId, EdjServeItem::getEdjServeTypeId)
                .eq(EdjServeItem::getId, id);

        return baseMapper.selectJoinOne(ServeItemVO.class, queryWrapper);
    }
}