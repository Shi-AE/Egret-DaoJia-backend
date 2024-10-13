package com.edj.user;

import com.edj.user.domain.entity.EdjAuthority;
import com.edj.user.mapper.EdjAuthorityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TempTest {

    @Autowired
    private EdjAuthorityMapper authorityMapper;

    @Test
    void addAuthority() {
        authorityMapper.insert(EdjAuthority
                .builder()
                .parentId(338323032326144L)
                .name("新增服务类型")
                .permission("foundations:serverType:add")
                .build()
        );
    }
}
