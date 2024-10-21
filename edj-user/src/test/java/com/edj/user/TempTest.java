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
                        Pair.of("新增区域", "foundations:region:add"),
                        Pair.of("修改区域", "foundations:region:update"),
                        Pair.of("删除区域", "foundations:region:delete"),
                        Pair.of("区域分页查询", "foundations:region:page"),
                        Pair.of("根据id查询区域", "foundations:region:findById"),
                        Pair.of("启用区域", "foundations:region:activate"),
                        Pair.of("禁用区域", "foundations:region:deactivate")
                ).parallelStream()
                .forEach(x -> {
                    EdjAuthority edjAuthority = EdjAuthority
                            .builder()
                            .parentId(3168590889566208L)
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
