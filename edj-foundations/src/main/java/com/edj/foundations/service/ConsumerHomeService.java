package com.edj.foundations.service;

import com.edj.foundations.domain.vo.RegionSimpleVO;
import com.edj.foundations.domain.vo.ServeCategoryVO;

import java.util.List;

/**
 * 用户端首页服务
 *
 * @author A.E.
 * @date 2024/11/4
 */
public interface ConsumerHomeService {

    /**
     * 已开通服务区域列表
     */
    List<RegionSimpleVO> getActiveRegionList();

    /**
     * 根据区域id获取服务图标信息
     */
    List<ServeCategoryVO> getServeIconCategoryByRegionIdCache(Long regionId);
}
