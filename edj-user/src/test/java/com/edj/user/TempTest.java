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
                        Pair.of("批量新增区域服务", "foundations:serve:add"),
                        Pair.of("区域服务分页查询", "foundations:serve:page"),
                        Pair.of("区域服务价格修改", "foundations:serve:update"),
                        Pair.of("区域服务设置热门", "foundations:serve:onHot"),
                        Pair.of("区域服务取消热门", "foundations:serve:offHot"),
                        Pair.of("区域服务上架", "foundations:serve:onSale"),
                        Pair.of("区域服务下架", "foundations:serve:offSale"),
                        Pair.of("区域服务删除", "foundations:serve:delete")
                )
                .parallelStream()
                .unordered()
                .forEach(x -> {
                    EdjAuthority edjAuthority = EdjAuthority
                            .builder()
                            .parentId(4265771314790400L)
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
