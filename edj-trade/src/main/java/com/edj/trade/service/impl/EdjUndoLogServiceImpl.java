package com.edj.trade.service.impl;

import com.edj.trade.domain.entity.EdjUndoLog;
import com.edj.trade.mapper.EdjUndoLogMapper;
import com.edj.trade.service.EdjUndoLogService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_undo_log(AT事务模式的undo表 (AT transaction mode undo table))】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Service
public class EdjUndoLogServiceImpl extends MPJBaseServiceImpl<EdjUndoLogMapper, EdjUndoLog> implements EdjUndoLogService {
}