package com.edj.foundations.service;

import com.edj.foundations.domain.entity.EdjConfigRegion;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_config_region(区域业务配置)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/16
 */
public interface EdjConfigRegionService extends MPJBaseService<EdjConfigRegion> {

    /**
     * 初始化区域配置
     */
    void init(Long id, Integer edjCityId);

    /**
     * 删除区域配置
     */
    void delete(Long id);
}
