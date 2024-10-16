package com.edj.foundations.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 区域表
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjRegion extends EjdBaseEntity {
    /**
     * 区域id
     */
    private Long id;

    /**
     * 城市id
     */
    private Integer edjCityId;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 负责人名称
     */
    private String managerName;

    /**
     * 负责人电话
     */
    private String managerPhone;

    /**
     * 是否启用（0草稿 1禁用 2启用）
     */
    private Integer activeStatus;

    /**
     * 排序字段
     */
    private Integer sortNum;
}