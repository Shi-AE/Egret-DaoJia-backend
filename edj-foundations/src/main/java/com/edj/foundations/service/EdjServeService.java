package com.edj.foundations.service;

import com.edj.common.domain.PageResult;
import com.edj.foundations.domain.dto.ServeAddDTO;
import com.edj.foundations.domain.dto.ServePageDTO;
import com.edj.foundations.domain.entity.EdjServe;
import com.edj.foundations.domain.vo.ServeVO;
import com.github.yulichang.base.MPJBaseService;

import java.math.BigDecimal;
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

    /**
     * 区域服务分页查询
     */
    PageResult<ServeVO> page(ServePageDTO servePageDTO);

    /**
     * 区域服务价格修改
     */
    void update(Long id, BigDecimal price);
}
