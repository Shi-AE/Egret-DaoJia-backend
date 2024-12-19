package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.*;
import com.edj.foundations.domain.dto.ServeAddDTO;
import com.edj.foundations.domain.dto.ServePageDTO;
import com.edj.foundations.domain.entity.*;
import com.edj.foundations.domain.vo.ServeDetailVo;
import com.edj.foundations.domain.vo.ServeItemVO;
import com.edj.foundations.domain.vo.ServeVO;
import com.edj.foundations.enums.EdjServeIsHot;
import com.edj.foundations.enums.EdjServeItemActiveStatus;
import com.edj.foundations.enums.EdjServeSaleStatus;
import com.edj.foundations.mapper.*;
import com.edj.foundations.service.EdjServeItemService;
import com.edj.foundations.service.EdjServeService;
import com.edj.mysql.utils.PageUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.edj.cache.constants.CacheConstants.CacheManager.ONE_DAY;
import static com.edj.cache.constants.CacheConstants.CacheManager.THIRTY_MINUTES;
import static com.edj.cache.constants.CacheConstants.CacheName.*;

/**
 * 针对表【edj_serve(服务表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Service
@RequiredArgsConstructor
public class EdjServeServiceImpl extends MPJBaseServiceImpl<EdjServeMapper, EdjServe> implements EdjServeService {

    private final EdjServeTypeMapper serveTypeMapper;

    private final EdjServeItemMapper serveItemMapper;

    private final EdjServeItemService serveItemService;

    private final ApplicationContext applicationContext;

    private final EdjRegionMapper regionMapper;

    private final EdjServeSyncMapper serveSyncMapper;

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = HOME_CATEGORY_CACHE, key = "#serveAddDTOList.getFirst().edjRegionId"),
            @CacheEvict(cacheNames = HOME_SERVE_TYPE_CACHE, key = "#serveAddDTOList.getFirst().edjRegionId")
    })
    public void add(List<ServeAddDTO> serveAddDTOList) {
        // 检查提交的重复项
        boolean duplicate = serveAddDTOList.stream()
                .collect(Collectors.groupingBy(
                        x -> x.getEdjRegionId() + "_" + x.getEdjServeItemId(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .anyMatch(x -> x.getValue() != 1);
        if (duplicate) {
            throw new BadRequestException("存在重复提交");
        }

        // 校验服务项状态
        List<Long> serveItemIdList = serveAddDTOList
                .stream()
                .map(ServeAddDTO::getEdjServeItemId)
                .distinct()
                .sorted()
                .toList();

        // 批处理统计服务项目
        long serveItemCheckCount = SqlUtils.countBatch(serveItemIdList, list -> {
            LambdaQueryWrapper<EdjServeItem> serveItemCheckWrapper = new LambdaQueryWrapper<EdjServeItem>()
                    .select(EdjServeItem::getId)
                    .in(EdjServeItem::getId, list)
                    .eq(EdjServeItem::getActiveStatus, EdjServeItemActiveStatus.ENABLED);
            return serveItemMapper.selectCount(serveItemCheckWrapper);
        });
        if (serveItemCheckCount != serveItemIdList.size()) {
            throw new BadRequestException("服务项未启用或不存在");
        }

        // 检查是否重复
        long serveCount = SqlUtils.countBatch(
                serveAddDTOList,
                list -> baseMapper.batchCheckRegionItem(list)
        );
        if (serveCount > 0) {
            throw new BadRequestException("已存在相同的区域服务项");
        }

        // 查询区域对应城市id
        List<Long> regionIdList = serveAddDTOList
                .stream()
                .map(ServeAddDTO::getEdjRegionId)
                .distinct()
                .sorted()
                .toList();
        List<EdjRegion> regionCityIdList = SqlUtils.selectBatch(regionIdList, list -> {
            LambdaQueryWrapper<EdjRegion> regionQueryWrapper = new LambdaQueryWrapper<EdjRegion>()
                    .select(EdjRegion::getId, EdjRegion::getEdjCityId)
                    .in(EdjRegion::getId, list);
            return regionMapper.selectList(regionQueryWrapper);
        });
        if (regionCityIdList.size() != regionIdList.size()) {
            throw new BadRequestException("区域不存在");
        }
        Map<Long, Integer> regionCityIdMap = regionCityIdList
                .stream()
                .collect(Collectors.toMap(
                        EdjRegion::getId,
                        EdjRegion::getEdjCityId
                ));

        // 批量插入
        List<EdjServe> serveList = serveAddDTOList.stream()
                .map(x -> {
                    EdjServe serve = BeanUtils.toBean(x, EdjServe.class);
                    serve.setEdjCityId(regionCityIdMap.get(x.getEdjRegionId()));
                    return serve;
                })
                .toList();
        SqlUtils.actionBatch(serveList, list -> list.forEach(serve -> baseMapper.insert(serve)), true);
    }

    @Override
    public PageResult<ServeVO> page(ServePageDTO servePageDTO) {
        Page<ServeVO> page = PageUtils.parsePageQuery(servePageDTO);
        MPJLambdaWrapper<EdjServe> wrapper = new MPJLambdaWrapper<EdjServe>()
                .selectAll(EdjServe.class)
                .selectAs(EdjServeItem::getReferencePrice, ServeVO::getReferencePrice)
                .selectAs(EdjServeItem::getName, ServeVO::getServeItemName)
                .selectAs(EdjServeType::getName, ServeVO::getServeTypeName)
                .innerJoin(EdjServeItem.class, EdjServeItem::getId, EdjServe::getEdjServeItemId)
                .innerJoin(EdjServeType.class, EdjServeType::getId, EdjServeItem::getEdjServeTypeId)
                .eq(EdjServe::getEdjRegionId, servePageDTO.getEdjRegionId());
        Page<ServeVO> serveVOPage = baseMapper.selectJoinPage(page, ServeVO.class, wrapper);
        return new PageResult<>((int) serveVOPage.getPages(), serveVOPage.getTotal(),
                serveVOPage.getRecords());
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = SERVE_CACHE, key = "#id", beforeInvocation = true)
    public void update(Long id, BigDecimal price) {
        LambdaUpdateWrapper<EdjServe> wrapper = new LambdaUpdateWrapper<EdjServe>()
                .eq(EdjServe::getId, id)
                .set(EdjServe::getPrice, price);
        int update = baseMapper.update(new EdjServe(), wrapper);
        if (update != 1) {
            throw new BadRequestException("区域服务可能不存在");
        }

        // 更新至同步表
        serveSyncMapper.updateById(EdjServeSync
                .builder()
                .id(id)
                .price(price)
                .build());
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = HOME_HOT_SERVE_CACHE, key = "#result"),
            @CacheEvict(cacheNames = SERVE_CACHE, key = "#id", beforeInvocation = true)
    })
    public Long changeHotStatus(Long id, EdjServeIsHot edjServeIsHot) {

        LambdaQueryWrapper<EdjServe> check = new LambdaQueryWrapper<EdjServe>()
                .select(EdjServe::getIsHot, EdjServe::getEdjRegionId)
                .eq(EdjServe::getId, id);
        EdjServe serve = baseMapper.selectOne(check);
        if (ObjectUtils.isNull(serve) || EnumUtils.eq(edjServeIsHot, serve.getIsHot())) {
            throw new BadRequestException("状态已设置");
        }

        LocalDateTime now = LocalDateTime.now();

        LambdaUpdateWrapper<EdjServe> wrapper = new LambdaUpdateWrapper<EdjServe>()
                .eq(EdjServe::getId, id)
                .set(EdjServe::getIsHot, edjServeIsHot)
                .set(EdjServe::getHotTime, now);
        baseMapper.update(new EdjServe(), wrapper);

        // 更新至同步表
        serveSyncMapper.updateById(EdjServeSync
                .builder()
                .id(id)
                .isHot((Integer) EnumUtils.value(edjServeIsHot))
                .hotTime(now)
                .build());

        return serve.getEdjRegionId();
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = HOME_CATEGORY_CACHE, key = "#result"),
            @CacheEvict(cacheNames = HOME_SERVE_TYPE_CACHE, key = "#result"),
            @CacheEvict(cacheNames = HOME_HOT_SERVE_CACHE, key = "#result"),
            @CacheEvict(cacheNames = SERVE_CACHE, key = "#id", beforeInvocation = true)
    })
    public Long onSale(Long id) {
        // 检查服务
        LambdaQueryWrapper<EdjServe> checkServer = new LambdaQueryWrapper<EdjServe>()
                .select(EdjServe::getSaleStatus, EdjServe::getEdjServeItemId, EdjServe::getEdjRegionId)
                .eq(EdjServe::getId, id);
        EdjServe serve = baseMapper.selectOne(checkServer);

        // 检查存在
        if (ObjectUtils.isNull(serve)) {
            throw new BadRequestException("服务不存在");
        }

        // 检查服务状态
        Integer saleStatus = serve.getSaleStatus();
        if (EnumUtils.eq(EdjServeSaleStatus.PUBLISHED, saleStatus)) {
            throw new BadRequestException("服务已上架");
        }

        // 检查服务项
        Long edjServeItemId = serve.getEdjServeItemId();
        LambdaQueryWrapper<EdjServeItem> serveItemCheck = new LambdaQueryWrapper<EdjServeItem>()
                .select(EdjServeItem::getActiveStatus)
                .eq(EdjServeItem::getId, edjServeItemId);
        EdjServeItem serveItem = serveItemMapper.selectOne(serveItemCheck);

        // 检查存在
        if (ObjectUtils.isNull(serveItem)) {
            throw new BadRequestException("服务项不存在");
        }

        // 检查服务项状态
        Integer activeStatus = serveItem.getActiveStatus();
        if (EnumUtils.ne(EdjServeItemActiveStatus.ENABLED, activeStatus)) {
            throw new BadRequestException("服务项未启用");
        }

        // 更新上架
        LambdaUpdateWrapper<EdjServe> updateWrapper = new LambdaUpdateWrapper<EdjServe>()
                .set(EdjServe::getSaleStatus, EdjServeSaleStatus.PUBLISHED)
                .eq(EdjServe::getId, id);
        baseMapper.update(new EdjServe(), updateWrapper);

        // 同步新增至同步表
        serveSyncAdd(id);

        // 删除首页服务列表缓存 区域id key
        return serve.getEdjRegionId();
    }

    private void serveSyncAdd(Long id) {
        //服务信息
        EdjServe serve = baseMapper.selectById(id);

        //区域信息
        CompletableFuture<EdjRegion> regionTask = AsyncUtils.supplyAsync(
                () -> regionMapper.selectById(serve.getEdjRegionId())
        );

        //服务项信息
        CompletableFuture<EdjServeItem> serveItemTask = AsyncUtils.supplyAsync(
                () -> serveItemMapper.selectById(serve.getEdjServeItemId())
        );

        EdjRegion region = regionTask.join();
        EdjServeItem serveItem = serveItemTask.join();

        //服务类型
        EdjServeType serveType = serveTypeMapper.selectById(serveItem.getEdjServeTypeId());

        EdjServeSync serveSync = EdjServeSync
                .builder()
                .id(id)
                .edjServeTypeId(serveType.getId())
                .edjServeItemId(serveItem.getId())
                .edjCityId(region.getEdjCityId())
                .serveItemName(serveItem.getName())
                .serveTypeName(serveType.getName())
                .price(serve.getPrice())
                .isHot(serve.getIsHot())
                .hotTime(serve.getHotTime())
                .serveItemSortNum(serveItem.getSortNum())
                .serveTypeSortNum(serveType.getSortNum())
                .serveTypeImg(serveType.getImg())
                .serveTypeIcon(serveType.getIcon())
                .unit(serveItem.getUnit())
                .detailImg(serveItem.getDetailImg())
                .serveItemImg(serveItem.getImg())
                .serveItemIcon(serveItem.getIcon())
                .build();

        serveSyncMapper.insert(serveSync);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = HOME_CATEGORY_CACHE, key = "#result"),
            @CacheEvict(cacheNames = HOME_SERVE_TYPE_CACHE, key = "#result"),
            @CacheEvict(cacheNames = HOME_HOT_SERVE_CACHE, key = "#result"),
            @CacheEvict(cacheNames = SERVE_CACHE, key = "#id", beforeInvocation = true)
    })
    public Long offSale(Long id) {
        // 检查服务
        LambdaQueryWrapper<EdjServe> checkServer = new LambdaQueryWrapper<EdjServe>()
                .select(EdjServe::getSaleStatus, EdjServe::getEdjRegionId)
                .eq(EdjServe::getId, id);
        EdjServe serve = baseMapper.selectOne(checkServer);

        // 检查存在
        if (ObjectUtils.isNull(serve)) {
            throw new BadRequestException("服务不存在");
        }

        // 检查服务状态
        Integer saleStatus = serve.getSaleStatus();
        if (EnumUtils.ne(EdjServeSaleStatus.PUBLISHED, saleStatus)) {
            throw new BadRequestException("服务未上架");
        }

        // 跟新状态
        LambdaUpdateWrapper<EdjServe> updateWrapper = new LambdaUpdateWrapper<EdjServe>()
                .set(EdjServe::getSaleStatus, EdjServeSaleStatus.UNPUBLISHED)
                .eq(EdjServe::getId, id);
        baseMapper.update(new EdjServe(), updateWrapper);

        // 同步删除同步表数据
        serveSyncMapper.deleteById(id);

        // 删除首页服务列表缓存 区域id key
        return serve.getEdjRegionId();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = SERVE_CACHE, key = "#id", beforeInvocation = true)
    public void deleteById(Long id) {
        // 检查服务
        LambdaQueryWrapper<EdjServe> checkServer = new LambdaQueryWrapper<EdjServe>()
                .select(EdjServe::getSaleStatus)
                .eq(EdjServe::getId, id);
        EdjServe serve = baseMapper.selectOne(checkServer);

        // 检查存在
        if (ObjectUtils.isNull(serve)) {
            throw new BadRequestException("服务不存在");
        }

        // 检查服务状态
        Integer saleStatus = serve.getSaleStatus();
        if (EnumUtils.ne(EdjServeSaleStatus.DRAFT, saleStatus)) {
            throw new BadRequestException("草稿状态方可删除");
        }

        // 同步删除同步表数据
        serveSyncMapper.deleteById(id);

        baseMapper.deleteById(id);
    }

    @Override
    @Caching(cacheable = {
            // 非空缓存一天
            @Cacheable(cacheNames = SERVE_CACHE, key = "#id", unless = "#result == null", cacheManager = ONE_DAY),
            // 空结果缓存30分钟
            @Cacheable(cacheNames = SERVE_CACHE, key = "#id", unless = "#result != null", cacheManager = THIRTY_MINUTES)
    })
    public ServeVO selectById(Long id) {
        EdjServe serve = baseMapper.selectById(id);
        return BeanUtils.toBean(serve, ServeVO.class);
    }

    @Override
    public ServeDetailVo findDetailById(Long id) {
        // 查询服务
        ServeVO serve = applicationContext.getBean(EdjServeService.class).selectById(id);
        if (ObjectUtils.isNull(serve)) {
            return null;
        }

        // 查询服务项
        Long edjServeItemId = serve.getEdjServeItemId();
        ServeItemVO serveItemVO = serveItemService.selectById(edjServeItemId);
        if (ObjectUtils.isNull(serveItemVO)) {
            serveItemVO = new ServeItemVO();
        }

        ServeDetailVo serveDetailVo = BeanUtils.toBean(serve, ServeDetailVo.class);
        serveDetailVo.setServeItemName(serveItemVO.getName());
        serveDetailVo.setDetailImg(serveItemVO.getDetailImg());
        serveDetailVo.setServeItemImg(serveItemVO.getImg());
        serveDetailVo.setUnit(serveItemVO.getUnit());

        return serveDetailVo;
    }
}