package com.edj.trade.mapper;

import com.edj.trade.domain.entity.EdjUndoLog;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_undo_log(AT事务模式的undo表 (AT transaction mode undo table))】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Mapper
public interface EdjUndoLogMapper extends MPJBaseMapper<EdjUndoLog> {
}