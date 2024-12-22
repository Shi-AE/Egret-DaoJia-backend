package com.edj.foundations.service;

import com.edj.foundations.domain.entity.EdjCity;
import com.edj.foundations.domain.vo.ProvinceStructVO;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_city(城市字典)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/16
 */
public interface EdjCityService extends MPJBaseService<EdjCity> {

    /**
     * 查询城市结构列表
     */
    List<ProvinceStructVO> getStruct();

    /**
     * 根据城市编号获取系统id
     */
    Integer getIdByCityCode(String cityCode);
}
