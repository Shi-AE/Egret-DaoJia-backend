package com.edj.foundations.service;

import com.edj.foundations.domain.dto.ServeAddDTO;
import com.edj.foundations.domain.entity.EdjServe;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_serve(服务表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/23
 */
public interface EdjServeService extends MPJBaseService<EdjServe> {

    /**
     * 批量新增区域服务
     */
    void add(List<ServeAddDTO> serveAddDTOList);
}