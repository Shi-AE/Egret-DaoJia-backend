package com.edj.user;

import com.edj.user.domain.entity.EdjAuthority;
import com.edj.user.domain.entity.EdjRoleAuthority;
import com.edj.user.mapper.EdjAuthorityMapper;
import com.edj.user.mapper.EdjRoleAuthorityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TempTest {

    @Autowired
    private EdjAuthorityMapper authorityMapper;

    @Autowired
    private EdjRoleAuthorityMapper roleAuthorityMapper;

    @Test
    void addAuthority() {
        EdjAuthority edjAuthority = EdjAuthority
                .builder()
                .parentId(338323032326144L)
                .name("服务类型删除")
                .permission("foundations:serverType:delete")
                .build();
        authorityMapper.insert(edjAuthority);

        roleAuthorityMapper.insert(EdjRoleAuthority
                .builder()
                .edjAuthorityId(edjAuthority.getId())
                .edjRoleId(1L)
                .build()
        );
    }
}
