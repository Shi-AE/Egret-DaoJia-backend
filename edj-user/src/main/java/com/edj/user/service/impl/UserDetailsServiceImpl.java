package com.edj.user.service.impl;

import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.CollUtils;
import com.edj.security.domain.dto.AuthorizationUserDTO;
import com.edj.user.domain.entity.EdjUser;
import com.edj.user.service.EdjRoleAuthorityService;
import com.edj.user.service.EdjUserRoleService;
import com.edj.user.service.EdjUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EdjUserService userService;
    private final EdjUserRoleService userRoleService;
    private final EdjRoleAuthorityService roleAuthorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据用户名查找用户详细信息
        AuthorizationUserDTO authorizationUserDTO = new AuthorizationUserDTO();

        // 根据用户名查找用户基础信息
        EdjUser user = userService.selectByUsername(username);
        BeanUtils.copyProperties(user, authorizationUserDTO);


        // 根据id查询用户权限
        Long id = user.getId();

        // 设置管理员权限
        if (id == 1) {
            authorizationUserDTO.setAuthorities(
                    Set.of(new SimpleGrantedAuthority("*:*:*"))
            );
            return authorizationUserDTO;
        }

        // 查询角色
        List<Long> roleIdList = userRoleService.selectRoleIdsByUserId(id);
        if (CollUtils.isEmpty(roleIdList)) {
            authorizationUserDTO.setAuthorities(new HashSet<>());
            return authorizationUserDTO;
        }

        // 查询权限
        List<String> authorityNameList = roleAuthorityService.selectAuthorityByRoleIdList(roleIdList);
        authorizationUserDTO.setAuthorities(authorityNameList
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet())
        );

        return authorizationUserDTO;
    }
}
