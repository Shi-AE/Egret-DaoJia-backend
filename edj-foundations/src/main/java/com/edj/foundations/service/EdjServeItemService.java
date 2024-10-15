package com.edj.foundations.service;

import com.edj.foundations.domain.dto.ServeItemAddDTO;
import com.edj.foundations.domain.dto.ServeItemUpdateDTO;
import com.edj.foundations.domain.entity.EdjServeItem;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_serve_item(服务项表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/14
 */
public interface EdjServeItemService extends MPJBaseService<EdjServeItem> {

    /**
     * 根据服务类型id查询启用的服务项数量
     */
    long activeServeItemCountByServeTypeId(long serveTypeId);

    /**
     * 新增服务项
     */
    void add(ServeItemAddDTO serveItemAddDTO);

    /**
     * 修改服务项
     */
    void update(ServeItemUpdateDTO serveItemUpdateDTO);

    /**
     * 启用服务项
     */
    void activate(Long id);
}
