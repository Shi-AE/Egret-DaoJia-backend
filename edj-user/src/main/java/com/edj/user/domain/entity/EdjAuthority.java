package com.edj.user.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 权限信息表
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
public class EdjAuthority extends EjdBaseEntity {
    /**
     * 权限ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 权限状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}