package com.edj.foundations.service;

import com.edj.common.domain.PageResult;
import com.edj.foundations.domain.dto.ServeTypeAddDTO;
import com.edj.foundations.domain.dto.ServeTypeUpdateDTO;
import com.edj.foundations.domain.dto.ServeTypePageDTO;
import com.edj.foundations.domain.entity.EdjServeType;
import com.edj.foundations.domain.vo.ServeTypeStatusGetVO;
import com.edj.foundations.domain.vo.ServeTypeVO;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

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
    PageResult<ServeTypeVO> page(ServeTypePageDTO serveTypePageDTO);

    /**
     * 启用服务类型
     */
    void activate(Long id);

    /**
     * 禁用服务类型
     */
    void deactivate(Long id);

    /**
     * 服务类型删除
     */
    void deleteById(Long id);

    /**
     * 服务类型修改
     */
    void update(ServeTypeUpdateDTO serveTypeUpdateDTO);

    /**
     * 根据活动状态查询服务类型
     */
    List<ServeTypeStatusGetVO> selectByStatus(Integer activeStatus);
}
