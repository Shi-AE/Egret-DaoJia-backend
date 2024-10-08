package com.edj.user.service;


import com.edj.user.domain.entity.EdjUserRole;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_user_role(用户角色关联表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/5
 */
public interface EdjUserRoleService extends MPJBaseService<EdjUserRole> {

    /**
     * 根据用户id获取角色id列表
     * 连表排除已停用的角色
     */
    List<Long> selectRoleIdsByUserId(Long userId);
}
