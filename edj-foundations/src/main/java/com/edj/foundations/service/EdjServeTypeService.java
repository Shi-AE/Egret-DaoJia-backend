package com.edj.foundations.service;

import com.edj.common.domain.PageResult;
import com.edj.foundations.domain.dto.ServeTypeAddDTO;
import com.edj.foundations.domain.dto.ServerTypePageDTO;
import com.edj.foundations.domain.entity.EdjServeType;
import com.edj.foundations.domain.vo.ServerTypeVO;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_serve_type(服务类型表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/12
 */
public interface EdjServeTypeService extends MPJBaseService<EdjServeType> {

    /**
     * 新增服务类型
     */
    void add(ServeTypeAddDTO serveTypeAddDTO);

    /**
     * 服务类型分页查询
     */
    PageResult<ServerTypeVO> page(ServerTypePageDTO serverTypePageDTO);

    /**
     * 启用服务类型
     */
    void activate(Long id);
}
