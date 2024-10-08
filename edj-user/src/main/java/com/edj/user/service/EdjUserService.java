package com.edj.user.service;


import com.edj.user.domain.entity.EdjUser;
import com.github.yulichang.base.MPJBaseService;

/**
 * edj_user
 *
 * @author A.E.
 * @date 2024/10/5
 */
public interface EdjUserService extends MPJBaseService<EdjUser> {

    /**
     * 根据用户名查找用户基础信息
     */
    EdjUser selectByUsername(String username);

    /**
     * 异步任务记录用户登录信息
     */
    Runnable RecordLoginInfo(Long id, String getLoginIp);
}
