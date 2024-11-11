package com.edj.user;

import cn.hutool.core.lang.Pair;
import com.edj.user.domain.entity.EdjAuthority;
import com.edj.user.domain.entity.EdjRoleAuthority;
import com.edj.user.enums.EdjSysRole;
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
                        Pair.of("地址薄新增", "consumer:addressBook:add"),
                        Pair.of("修改地址薄", "consumer:addressBook:update"),
                        Pair.of("地址薄详情", "consumer:addressBook:detail"),
                        Pair.of("地址薄设为默认/取消默认", "consumer:addressBook:updateDefault"),
                        Pair.of("批量删除地址薄", "consumer:addressBook:delete"),
                        Pair.of("地址薄分页查询", "consumer:addressBook:page"),
                        Pair.of("获取默认地址", "consumer:addressBook:default")
                )
                .parallelStream()
                .unordered()
                .forEach(authority -> {

                    EdjAuthority edjAuthority = EdjAuthority
                            .builder()
                            .parentId(10772346994696192L)
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
}
