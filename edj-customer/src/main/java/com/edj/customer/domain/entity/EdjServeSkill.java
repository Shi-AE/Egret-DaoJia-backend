package com.edj.customer.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 服务技能表
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjServeSkill extends EjdBaseEntity {
    /**
     * 服务技能主键
     */
    private Long id;

    /**
     * 服务人员/机构id
     */
    private Long serveProviderId;

    /**
     * 服务类型id
     */
    private Long edjServeTypeId;

    /**
     * 服务类型名称
     */
    private String serveTypeName;

    /**
     * 服务项id
     */
    private Long edjServeItemId;

    /**
     * 服务项名称
     */
    private String serveItemName;
}