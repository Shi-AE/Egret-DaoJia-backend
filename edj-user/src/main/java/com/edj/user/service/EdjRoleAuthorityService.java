package com.edj.user.service;


import com.edj.user.domain.entity.EdjRoleAuthority;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_role_authority(角色权限关联表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/10/5
 */
public interface EdjRoleAuthorityService extends MPJBaseService<EdjRoleAuthority> {

    /**
     * 查询多个角色的权限名称并去重
     * 排除已停用权限
     * 连表查询
     */
    List<String> selectAuthorityByRoleIdList(List<Long> roleIdList);
}
