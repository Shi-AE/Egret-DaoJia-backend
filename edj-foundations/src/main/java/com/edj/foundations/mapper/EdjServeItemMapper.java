package com.edj.foundations.mapper;

import com.edj.foundations.domain.entity.EdjServeItem;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_serve_item(服务项表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/10/14
 */
@Mapper
public interface EdjServeItemMapper extends MPJBaseMapper<EdjServeItem> {
}