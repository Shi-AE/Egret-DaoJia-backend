package com.edj.foundations.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 服务表
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjServe extends EjdBaseEntity {
    /**
     * 服务id
     */
    private Long id;

    /**
     * 服务项id
     */
    private Long edjServeItemId;

    /**
     * 区域id
     */
    private Long edjRegionId;

    /**
     * 城市id
     */
    private Integer edjCityId;

    /**
     * 售卖状态（0草稿 1下架 2上架）
     */
    private Integer saleStatus;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否为热门（0非热门 1热门）
     */
    private Integer isHot;

    /**
     * 更新为热门的时间
     */
    private LocalDateTime hotTime;
}