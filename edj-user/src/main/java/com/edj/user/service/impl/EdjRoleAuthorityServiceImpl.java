package com.edj.user.service.impl;

import com.edj.common.utils.CollUtils;
import com.edj.user.domain.entity.EdjAuthority;
import com.edj.user.domain.entity.EdjRoleAuthority;
import com.edj.user.enums.EdjAuthorityStatus;
import com.edj.user.mapper.EdjRoleAuthorityMapper;
import com.edj.user.service.EdjRoleAuthorityService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对表【edj_role_authority(角色权限关联表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/5
 */
@Service
@RequiredArgsConstructor
public class EdjRoleAuthorityServiceImpl extends MPJBaseServiceImpl<EdjRoleAuthorityMapper, EdjRoleAuthority> implements EdjRoleAuthorityService {

    private final EdjRoleAuthorityMapper roleAuthorityMapper;

    @Override
    public List<String> selectAuthorityByRoleIdList(List<Long> roleIdList) {

        if (CollUtils.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }

        MPJLambdaWrapper<EdjRoleAuthority> queryWrapper = new MPJLambdaWrapper<EdjRoleAuthority>()
                .select(EdjAuthority::getName)
                .innerJoin(EdjAuthority.class, EdjAuthority::getId, EdjRoleAuthority::getEdjAuthorityId)
                .in(EdjRoleAuthority::getEdjRoleId, roleIdList)
                .eq(EdjAuthority::getStatus, EdjAuthorityStatus.NORMAL);

        return roleAuthorityMapper.selectJoinList(String.class, queryWrapper)
                .stream()
                .distinct()
                .toList();
    }
}