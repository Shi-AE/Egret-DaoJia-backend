package com.edj.user.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 角色信息表
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
public class EdjRole extends EjdBaseEntity {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer roleOrder;

    /**
     * 角色状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}