package com.edj.user;

import cn.hutool.core.lang.Pair;
import com.edj.common.utils.JsonUtils;
import com.edj.security.enums.EdjSysRole;
import com.edj.user.domain.entity.EdjAuthority;
import com.edj.user.domain.entity.EdjRoleAuthority;
import com.edj.user.mapper.EdjAuthorityMapper;
import com.edj.user.mapper.EdjRoleAuthorityMapper;
import com.edj.user.service.EdjAuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TempTest {

    @Autowired
    private EdjAuthorityMapper authorityMapper;

    @Autowired
    private EdjRoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private EdjAuthorityService authorityService;

    @Test
    void addAuthority() {
        // 设置权限条目
        List.of(
                        Pair.of("抢单", "provider:ordersGarb:grab")
                )
                .parallelStream()
                .unordered()
                .forEach(authority -> {

                    EdjAuthority edjAuthority = EdjAuthority
                            .builder()
                            // 设置父id
                            .parentId(70231767117996032L)
                            .name(authority.getKey())
                            .permission(authority.getValue())
                            .build();
                    authorityMapper.insert(edjAuthority);

                    // 设置对应角色
                    List.of(
                                    EdjSysRole.ADMIN.getValue(),
                                    EdjSysRole.WORKER.getValue(),
                                    EdjSysRole.INSTITUTION.getValue()
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

    @Test
    @Transactional
    void select() {
        Map<String, Map<String, List<String>>> r1 = new HashMap<>();
        authorityService.lambdaQuery().eq(EdjAuthority::getParentId, 0).list().forEach(o1 -> {
            Map<String, List<String>> r2 = new HashMap<>();
            r1.put(o1.getName(), r2);
            authorityService.lambdaQuery().eq(EdjAuthority::getParentId, o1.getId()).list().forEach(o2 -> {
                r2.put(o2.getName(), authorityService.lambdaQuery().eq(EdjAuthority::getParentId, o2.getId()).list().stream().map(EdjAuthority::getName).toList());
            });
        });


        System.out.println(JsonUtils.parse(r1));
    }
}
