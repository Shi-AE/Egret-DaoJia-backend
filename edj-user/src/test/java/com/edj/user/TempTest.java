package com.edj.user;

import cn.hutool.core.lang.Pair;
import com.edj.user.domain.entity.EdjAuthority;
import com.edj.user.domain.entity.EdjRoleAuthority;
import com.edj.security.enums.EdjSysRole;
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
        List.of(
                        Pair.of("首页热门服务列表", "consumer:serve:hot")
                )
                .parallelStream()
                .unordered()
                .forEach(authority -> {

                    EdjAuthority edjAuthority = EdjAuthority
                            .builder()
                            .parentId(10767315721527296L)
                            .name(authority.getKey())
                            .permission(authority.getValue())
                            .build();
                    authorityMapper.insert(edjAuthority);

                    List.of(
                                    EdjSysRole.ADMIN.getValue(),
                                    EdjSysRole.CONSUMER.getValue()
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
