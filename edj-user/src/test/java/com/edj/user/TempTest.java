package com.edj.user;

import cn.hutool.core.lang.Pair;
import com.edj.security.enums.EdjSysRole;
import com.edj.user.domain.entity.EdjAuthority;
import com.edj.user.domain.entity.EdjRoleAuthority;
import com.edj.user.mapper.EdjAuthorityMapper;
import com.edj.user.mapper.EdjRoleAuthorityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TempTest {

    @Autowired
    private EdjAuthorityMapper authorityMapper;

    @Autowired
    private EdjRoleAuthorityMapper roleAuthorityMapper;

    @Test
    void addAuthority() {
        // 设置权限条目
        List.of(
                        Pair.of("查询优惠券活动详情", "foundations:activity:detail")
                )
                .parallelStream()
                .unordered()
                .forEach(authority -> {

                    EdjAuthority edjAuthority = EdjAuthority
                            .builder()
                            // 设置父id
                            .parentId(53324284332089344L)
                            .name(authority.getKey())
                            .permission(authority.getValue())
                            .build();
                    authorityMapper.insert(edjAuthority);

                    // 设置对应角色
                    List.of(
                                    EdjSysRole.ADMIN.getValue()
                            )
                            .parallelStream()
                            .unordered()
                            .forEach(roleId -> roleAuthorityMapper.insert(EdjRoleAuthority
                                    .builder()
                                    .edjAuthorityId(edjAuthority.getId())
                                    .edjRoleId(roleId)
                                    .build()
                            ));
                });
    }

    @Test
    void addRoleAuthority() {
        roleAuthorityMapper.insert(EdjRoleAuthority
                .builder()
                .edjAuthorityId(10769316840742912L)
                .edjRoleId(EdjSysRole.INSTITUTION.getValue())
                .build()
        );
    }
}
