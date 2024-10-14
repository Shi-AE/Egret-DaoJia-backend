package com.edj.foundations.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 服务项表
 *
 * @author A.E.
 * @date 2024/10/14
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjServeItem extends EjdBaseEntity {
    /**
     * 服务项id
     */
    private Long id;

    /**
     * 服务编码
     */
    private String code;

    /**
     * 服务类型id
     */
    private Long edjServeTypeId;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 服务图标
     */
    private String icon;

    /**
     * 服务图片
     */
    private String img;

    /**
     * 服务数量单位
     */
    private Integer unit;

    /**
     * 服务描述
     */
    private String description;

    /**
     * 服务详图
     */
    private String detailImg;

    /**
     * 参考价格
     */
    private BigDecimal referencePrice;

    /**
     * 排序字段
     */
    private Integer sortNum;

    /**
     * 活动状态（0草稿 1禁用 2启用）
     */
    private Integer activeStatus;
}