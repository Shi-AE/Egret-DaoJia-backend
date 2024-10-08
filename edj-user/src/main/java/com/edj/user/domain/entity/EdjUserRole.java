package com.edj.user.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 用户角色关联表
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
public class EdjUserRole extends EjdBaseEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long edjUserId;

    /**
     * 角色ID
     */
    private Long edjRoleId;
}