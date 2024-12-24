package com.edj.foundations.service;

import com.edj.api.api.foundations.dto.ServeAggregationDTO;
import com.edj.common.domain.PageResult;
import com.edj.foundations.domain.dto.ServeAddDTO;
import com.edj.foundations.domain.dto.ServePageDTO;
import com.edj.foundations.domain.dto.ServeSearchDTO;
import com.edj.foundations.domain.entity.EdjServe;
import com.edj.foundations.domain.vo.ServeDetailVo;
import com.edj.foundations.domain.vo.ServeSimpleVO;
import com.edj.foundations.domain.vo.ServeVO;
import com.edj.foundations.enums.EdjServeIsHot;
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

    /**
     * 区域服务设置是否热门状态
     *
     * @return 删除缓存 区域id key
     */
    Long changeHotStatus(Long id, EdjServeIsHot edjServeIsHot);

    /**
     * 区域服务上架
     *
     * @return 删除缓存 区域id key
     */
    Long onSale(Long id);

    /**
     * 区域服务下架
     *
     * @return 删除缓存 区域id key
     */
    Long offSale(Long id);

    /**
     * 区域服务删除
     */
    void deleteById(Long id);

    /**
     * 根据id查询服务信息
     */
    ServeVO selectById(Long id);

    /**
     * 根据id查询服务详情
     */
    ServeDetailVo findDetailById(Long id);

    /**
     * 服务搜索
     */
    List<ServeSimpleVO> search(ServeSearchDTO serveSearchDTO);

    /**
     * 根据id查询服务详情
     */
    ServeAggregationDTO findServeDetailById(Long id);
}
