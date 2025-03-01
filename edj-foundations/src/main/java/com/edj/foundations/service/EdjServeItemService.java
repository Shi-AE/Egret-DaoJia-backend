package com.edj.foundations.service;

import com.edj.common.domain.PageResult;
import com.edj.foundations.domain.dto.ServeItemAddDTO;
import com.edj.foundations.domain.dto.ServeItemPageDTO;
import com.edj.foundations.domain.dto.ServeItemUpdateDTO;
import com.edj.foundations.domain.entity.EdjServeItem;
import com.edj.foundations.domain.vo.ServeItemVO;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_serve_item(服务项表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/14
 */
public interface EdjServeItemService extends MPJBaseService<EdjServeItem> {

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

    /**
     * 禁用服务项
     */
    void deactivate(Long id);

    /**
     * 删除服务项
     */
    void delete(Long id);

    /**
     * 分页查询服务项
     */
    PageResult<ServeItemVO> page(ServeItemPageDTO serveItemPageDTO);

    /**
     * 根据id查询服务项
     */
    ServeItemVO selectById(Long id);
}
