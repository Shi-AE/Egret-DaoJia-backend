package com.edj.foundations.service;

import com.edj.api.api.foundations.dto.ServeTypeCategoryDTO;
import com.edj.foundations.domain.vo.RegionSimpleVO;
import com.edj.foundations.domain.vo.ServeAggregationSimpleVO;
import com.edj.foundations.domain.vo.ServeCategoryVO;
import com.edj.foundations.domain.vo.ServeTypeSimpleVo;

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

    /**
     * 根据区域id查询热门服务
     */
    List<ServeAggregationSimpleVO> getHotByRegionId(Long regionId);

    /**
     * 查询启用状态的服务项目录
     */
    List<ServeTypeCategoryDTO> getActiveServeItemCategory();

    /**
     * 根据区域id查询已开通的服务类型
     */
    List<ServeTypeSimpleVo> serveTypeListByRegionId(Long regionId);
}
