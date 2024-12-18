package com.edj.foundations.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务同步表
 *
 * @author A.E.
 * @date 2024/12/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EdjServeSync {
    /**
     * 服务id
     */
    private Long id;

    /**
     * 服务类型id
     */
    private Long edjServeTypeId;

    /**
     * 服务项id
     */
    private Long edjServeItemId;

    /**
     * 城市编码
     */
    private Integer edjCityId;

    /**
     * 服务项名称
     */
    private String serveItemName;

    /**
     * 服务类型名称
     */
    private String serveTypeName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否为热门（0非热门 1热门）
     */
    private Integer isHot;

    /**
     * 更新为热门的时间戳
     */
    private Date hotTime;

    /**
     * 服务项排序字段
     */
    private Integer serveItemSortNum;

    /**
     * 服务类型排序字段
     */
    private Integer serveTypeSortNum;

    /**
     * 服务类型图片
     */
    private String serveTypeImg;

    /**
     * 服务类型icon
     */
    private String serveTypeIcon;

    /**
     * 服务数量单位
     */
    private Integer unit;

    /**
     * 服务项详情图片
     */
    private String detailImg;

    /**
     * 服务项图片
     */
    private String serveItemImg;

    /**
     * 服务项图标
     */
    private String serveItemIcon;
}