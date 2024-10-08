package com.edj.user.mapper;


import com.edj.user.domain.entity.EdjUserRole;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_user_role(用户角色关联表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/9/30
 */
@Mapper
public interface EdjUserRoleMapper extends MPJBaseMapper<EdjUserRole> {
}




