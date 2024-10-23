package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.SqlUtils;
import com.edj.foundations.domain.dto.ServeAddDTO;
import com.edj.foundations.domain.entity.EdjRegion;
import com.edj.foundations.domain.entity.EdjServe;
import com.edj.foundations.domain.entity.EdjServeItem;
import com.edj.foundations.enums.EdjServeItemActiveStatus;
import com.edj.foundations.mapper.EdjRegionMapper;
import com.edj.foundations.mapper.EdjServeItemMapper;
import com.edj.foundations.mapper.EdjServeMapper;
import com.edj.foundations.service.EdjServeService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 针对表【edj_serve(服务表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Service
@RequiredArgsConstructor
public class EdjServeServiceImpl extends MPJBaseServiceImpl<EdjServeMapper, EdjServe> implements EdjServeService {

    private final EdjServeItemMapper serveItemMapper;

    private final EdjRegionMapper regionMapper;

    @Override
    @Transactional
    public void add(List<ServeAddDTO> serveAddDTOList) {
        // 检查重复项
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
        SqlUtils.actionBatch(serveList, list -> list.forEach(serve -> baseMapper.insert(serve)));
    }
}