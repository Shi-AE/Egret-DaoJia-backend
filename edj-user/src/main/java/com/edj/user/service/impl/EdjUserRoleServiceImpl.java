package com.edj.user.service.impl;

import com.edj.user.domain.entity.EdjRole;
import com.edj.user.domain.entity.EdjUserRole;
import com.edj.user.enums.EdjRoleStatus;
import com.edj.user.mapper.EdjUserRoleMapper;
import com.edj.user.service.EdjUserRoleService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 针对表【edj_user_role(用户角色关联表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/5
 */
@Service
@RequiredArgsConstructor
public class EdjUserRoleServiceImpl extends MPJBaseServiceImpl<EdjUserRoleMapper, EdjUserRole> implements EdjUserRoleService {

    private final EdjUserRoleMapper userRoleMapper;

    @Override
    public List<Long> selectRoleIdsByUserId(Long userId) {

        MPJLambdaWrapper<EdjUserRole> queryWrapper = new MPJLambdaWrapper<EdjUserRole>()
                .select(EdjUserRole::getEdjRoleId)
                .innerJoin(EdjRole.class, EdjRole::getId, EdjUserRole::getEdjRoleId)
                .eq(EdjUserRole::getEdjUserId, userId)
                .eq(EdjRole::getStatus, EdjRoleStatus.NORMAL);

        // 查询结果确定去重排序
        return userRoleMapper.selectJoinList(Long.class, queryWrapper);
    }
}




