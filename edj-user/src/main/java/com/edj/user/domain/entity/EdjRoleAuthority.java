package com.edj.user.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 角色权限关联表
 *
 * @author A.E.
 * @date 2024/9/30
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjRoleAuthority extends EjdBaseEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long edjRoleId;

    /**
     * 权限ID
     */
    private Long edjAuthorityId;
}