package com.edj.api.api.foundations.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 服务详细信息
 *
 * @author A.E.
 * @date 2024/12/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务详细信息")
public class ServeAggregationDTO {
    /**
     * 服务id
     */
    @Schema(description = "服务id")
    private Long id;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long serveTypeId;

    /**
     * 服务项id
     */
    @Schema(description = "服务项id")
    private Long serveItemId;

    /**
     * 服务项名称
     */
    @Schema(description = "服务项名称")
    private String serveItemName;

    /**
     * 售卖状态（0草稿 1下架 2上架）
     */
    @Schema(description = "售卖状态（0草稿 1下架 2上架）")
    private Integer saleStatus;

    /**
     * 城市代码
     */
    @Schema(description = "城市代码")
    private String cityCode;

    /**
     * 价格
     */
    @Schema(description = "价格")
    private BigDecimal price;

    /**
     * 是否是热门
     */
    @Schema(description = "是否是热门")
    private Integer isHot;

    /**
     * 更新为热门的时间戳
     */
    @Schema(description = "更新为热门的时间戳")
    private LocalDateTime hotTime;

    /**
     * 服务项排序字段
     */
    @Schema(description = "服务项排序字段")
    private Integer serveItemSortNum;

    /**
     * 服务类型排序字段
     */
    @Schema(description = "服务类型排序字段")
    private Integer serveTypeSortNum;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    private String serveTypeName;

    /**
     * 服务类型图片
     */
    @Schema(description = "服务类型图片")
    private String serveTypeImg;

    /**
     * 服务类型icon
     */
    @Schema(description = "服务类型icon")
    private String serveTypeIcon;

    /**
     * 服务收费价格单位
     */
    @Schema(description = "服务收费价格单位")
    private Integer unit;

    /**
     * 服务详情图片
     */
    @Schema(description = "服务详情图片")
    private String detailImg;

    /**
     * 服务项图片
     */
    @Schema(description = "服务详情图片")
    private String serveItemImg;

    /**
     * 服务图标
     */
    @Schema(description = "服务图标")
    private String serveItemIcon;
}
