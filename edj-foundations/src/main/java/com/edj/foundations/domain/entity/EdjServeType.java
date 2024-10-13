package com.edj.foundations.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 服务类型表
 *
 * @author A.E.
 * @date 2024/10/11
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjServeType extends EjdBaseEntity {
    /**
     * 服务类型id
     */
    private Long id;

    /**
     * 服务类型编码
     */
    private String code;

    /**
     * 服务类型名称
     */
    private String name;

    /**
     * 服务类型图标
     */
    private String icon;

    /**
     * 服务类型图片
     */
    private String img;

    /**
     * 排序字段
     */
    private Integer sortNum;

    /**
     * 是否启用（0草稿 1禁用 2启用）
     */
    private Integer activeStatus;
}