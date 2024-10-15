package com.edj.user;

import cn.hutool.core.lang.Pair;
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
        List.of(
                        Pair.of("新增服务项", "foundations:serveItem:add"),
                        Pair.of("修改服务项", "foundations:serveItem:update"),
                        Pair.of("启用服务项", "foundations:serveItem:activate"),
                        Pair.of("禁用服务项", "foundations:serveItem:deactivate"),
                        Pair.of("删除服务项", "foundations:serveItem:delete"),
                        Pair.of("分页查询服务项", "foundations:serveItem:page"),
                        Pair.of("根据id查询服务项", "foundations:serveItem:findById")
                ).parallelStream()
                .forEach(x -> {
                    EdjAuthority edjAuthority = EdjAuthority
                            .builder()
                            .parentId(1084430179975168L)
                            .name(x.getKey())
                            .permission(x.getValue())
                            .build();
                    authorityMapper.insert(edjAuthority);

                    roleAuthorityMapper.insert(EdjRoleAuthority
                            .builder()
                            .edjAuthorityId(edjAuthority.getId())
                            .edjRoleId(1L)
                            .build()
                    );
                });
    }
}
