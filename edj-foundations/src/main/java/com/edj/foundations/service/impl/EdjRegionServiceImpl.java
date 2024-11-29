package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.AsyncUtils;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.foundations.domain.dto.RegionAddDTO;
import com.edj.foundations.domain.dto.RegionPageDTO;
import com.edj.foundations.domain.dto.RegionUpdateDTO;
import com.edj.foundations.domain.entity.EdjCity;
import com.edj.foundations.domain.entity.EdjRegion;
import com.edj.foundations.domain.entity.EdjServe;
import com.edj.foundations.domain.vo.RegionVO;
import com.edj.foundations.enums.EdjCityType;
import com.edj.foundations.enums.EdjRegionActiveStatus;
import com.edj.foundations.enums.EdjServeSaleStatus;
import com.edj.foundations.mapper.EdjCityMapper;
import com.edj.foundations.mapper.EdjRegionMapper;
import com.edj.foundations.mapper.EdjServeMapper;
import com.edj.foundations.service.EdjConfigRegionService;
import com.edj.foundations.service.EdjRegionService;
import com.edj.mysql.utils.PageUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.edj.cache.constants.CacheConstants.CacheName.ACTIVE_REGION_CACHE;

/**
 * 针对表【edj_region(区域表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Service
@RequiredArgsConstructor
public class EdjRegionServiceImpl extends MPJBaseServiceImpl<EdjRegionMapper, EdjRegion> implements EdjRegionService {

    private final EdjCityMapper cityMapper;

    private final EdjConfigRegionService configRegionService;

    private final EdjServeMapper serveMapper;

    @Override
    @Transactional
    public void add(RegionAddDTO regionAddDTO) {
        // 检查城市重复
        Integer edjCityId = regionAddDTO.getEdjCityId();
        LambdaQueryWrapper<EdjRegion> checkWrapper = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getId)
                .eq(EdjRegion::getEdjCityId, edjCityId);
        boolean exists = baseMapper.exists(checkWrapper);
        if (exists) {
            throw new BadRequestException("提交重复城市");
        }

        // 检查城市
        LambdaQueryWrapper<EdjCity> cityLambdaQueryWrapper = new LambdaQueryWrapper<EdjCity>()
                .select(EdjCity::getSortNum, EdjCity::getName)
                .eq(EdjCity::getId, edjCityId)
                .eq(EdjCity::getType, EdjCityType.CITY);
        EdjCity city = cityMapper.selectOne(cityLambdaQueryWrapper);
        // 检查存在
        if (ObjectUtils.isNull(city)) {
            throw new BadRequestException("城市不存在或者选项为省份");
        }
        // 获取城市排序位
        Integer sortNum = city.getSortNum();

        // 获取城市名称
        String name = city.getName();

        // 新增区域
        EdjRegion region = BeanUtils.toBean(regionAddDTO, EdjRegion.class);
        region.setSortNum(sortNum);
        region.setName(name);
        baseMapper.insert(region);

        // 初始化区域配置
        Long id = region.getId();
        configRegionService.init(id, edjCityId);
    }

    @Override
    @Transactional
    public void update(RegionUpdateDTO regionUpdateDTO) {
        EdjRegion region = BeanUtils.toBean(regionUpdateDTO, EdjRegion.class);
        baseMapper.updateById(region);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // 检查区域
        LambdaQueryWrapper<EdjRegion> wrapper = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getActiveStatus)
                .eq(EdjRegion::getId, id);
        EdjRegion region = baseMapper.selectOne(wrapper);

        // 检查不存在
        if (ObjectUtils.isNull(region)) {
            throw new BadRequestException("区域不存在");
        }
        // 检查状态
        Integer activeStatus = region.getActiveStatus();
        if (EnumUtils.ne(EdjRegionActiveStatus.DRAFTS, activeStatus)) {
            throw new BadRequestException("草稿状态方可删除");
        }

        baseMapper.deleteById(id);

        // 异步移除区域配置
        AsyncUtils.runAsyncComplete(() -> configRegionService.delete(id));

        // 异步删除已添加的区域服务，在草稿状态下添加的区域服务
        AsyncUtils.runAsyncComplete(() -> {
            LambdaQueryWrapper<EdjServe> serveDeleteWrapper = new LambdaQueryWrapper<EdjServe>()
                    .eq(EdjServe::getEdjRegionId, id);
            serveMapper.delete(serveDeleteWrapper);
        });
    }

    @Override
    public PageResult<RegionVO> page(RegionPageDTO regionPageDTO) {
        Page<EdjRegion> page = PageUtils.parsePageQuery(regionPageDTO);
        Page<EdjRegion> serveTypePage = baseMapper.selectPage(page, null);
        return PageUtils.toPage(serveTypePage, RegionVO.class);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = ACTIVE_REGION_CACHE, key = "'ActiveRegion'", beforeInvocation = true)
    })
    public void active(Long id) {
        // 检查区域
        LambdaQueryWrapper<EdjRegion> checkWrapper = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getActiveStatus)
                .eq(EdjRegion::getId, id);
        EdjRegion edjRegion = baseMapper.selectOne(checkWrapper);

        // 检查存在
        if (ObjectUtils.isNull(edjRegion)) {
            throw new BadRequestException("区域不存在");
        }

        // 检查启用
        Integer activeStatus = edjRegion.getActiveStatus();
        if (EnumUtils.eq(EdjRegionActiveStatus.ENABLED, activeStatus)) {
            throw new BadRequestException("区域已启用");
        }

        // 检查区域服务
        LambdaQueryWrapper<EdjServe> serveCheckWrapper = new LambdaQueryWrapper<EdjServe>()
                .select(EdjServe::getId)
                .eq(EdjServe::getEdjRegionId, id)
                .eq(EdjServe::getSaleStatus, EdjServeSaleStatus.PUBLISHED);
        boolean exists = serveMapper.exists(serveCheckWrapper);
        if (!exists) {
            throw new BadRequestException("区域下不存在上架的服务");
        }

        // 更新状态
        LambdaUpdateWrapper<EdjRegion> updateWrapper = new LambdaUpdateWrapper<EdjRegion>()
                .eq(EdjRegion::getId, id)
                .set(EdjRegion::getActiveStatus, EdjRegionActiveStatus.ENABLED);
        baseMapper.update(new EdjRegion(), updateWrapper);

        // todo 如果是启用操作，刷新缓存：启用区域列表、首页图标、热门服务、服务类型
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = ACTIVE_REGION_CACHE, key = "'ActiveRegion'", beforeInvocation = true)
    })
    public void deactivate(Long id) {
        // 检查区域
        LambdaQueryWrapper<EdjRegion> checkWrapper = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getActiveStatus)
                .eq(EdjRegion::getId, id);
        EdjRegion edjRegion = baseMapper.selectOne(checkWrapper);

        // 检查存在
        if (ObjectUtils.isNull(edjRegion)) {
            throw new BadRequestException("区域不存在");
        }

        // 检查启用
        Integer activeStatus = edjRegion.getActiveStatus();
        if (EnumUtils.ne(EdjRegionActiveStatus.ENABLED, activeStatus)) {
            throw new BadRequestException("区域未已启用");
        }

        // 检查区域服务上架
        LambdaQueryWrapper<EdjServe> serveCheckWrapper = new LambdaQueryWrapper<EdjServe>()
                .select(EdjServe::getId)
                .eq(EdjServe::getEdjRegionId, id)
                .eq(EdjServe::getSaleStatus, EdjServeSaleStatus.PUBLISHED);
        boolean exists = serveMapper.exists(serveCheckWrapper);
        if (exists) {
            throw new BadRequestException("区域下存在已上架的服务");
        }

        // 更新状态
        LambdaUpdateWrapper<EdjRegion> updateWrapper = new LambdaUpdateWrapper<EdjRegion>()
                .eq(EdjRegion::getId, id)
                .set(EdjRegion::getActiveStatus, EdjRegionActiveStatus.DISABLED);
        baseMapper.update(new EdjRegion(), updateWrapper);
    }
}