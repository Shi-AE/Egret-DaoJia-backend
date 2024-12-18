package com.edj.foundations.service.impl;

import com.edj.foundations.domain.entity.EdjServeSync;
import com.edj.foundations.mapper.EdjServeSyncMapper;
import com.edj.foundations.service.EdjServeSyncService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_sync(服务同步表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/08
 */
@Service
public class EdjServeSyncServiceImpl extends MPJBaseServiceImpl<EdjServeSyncMapper, EdjServeSync> implements EdjServeSyncService {
}