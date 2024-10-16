package com.edj.foundations.service;

import com.edj.foundations.domain.dto.RegionAddDTO;
import com.edj.foundations.domain.dto.RegionUpdateDTO;
import com.edj.foundations.domain.entity.EdjRegion;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_region(区域表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/16
 */
public interface EdjRegionService extends MPJBaseService<EdjRegion> {

    /**
     * 新增区域
     */
    void add(RegionAddDTO regionAddDTO);

    /**
     * 修改区域
     */
    void update(RegionUpdateDTO regionUpdateDTO);
}
