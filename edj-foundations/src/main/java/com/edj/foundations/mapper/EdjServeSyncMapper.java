package com.edj.foundations.mapper;

import com.edj.foundations.domain.entity.EdjServeSync;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_serve_sync(服务同步表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/12/08
 */
@Mapper
public interface EdjServeSyncMapper extends MPJBaseMapper<EdjServeSync> {
}