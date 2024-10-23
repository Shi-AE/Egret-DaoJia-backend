package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 区域服务分页查询
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域服务分页查询")
public class ServeVO {

    /**
     * 服务id
     */
    @Schema(description = "服务id")
    private Long id;

    /**
     * 服务项id
     */
    @Schema(description = "服务项id")
    private Long edjServeItemId;

    /**
     * 服务项名称
     */
    @Schema(description = "服务项名称")
    private String serveItemName;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long serveTypeId;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    private String serveTypeName;

    /**
     * 参考价格
     */
    @Schema(description = "参考价格")
    private BigDecimal referencePrice;

    /**
     * 区域id
     */
    @Schema(description = "区域id")
    private Long edjRegionId;

    /**
     * 售卖状态（0草稿 1下架 2上架）
     */
    @Schema(description = "售卖状态（0草稿 1下架 2上架）")
    private Integer saleStatus;

    /**
     * 价格
     */
    @Schema(description = "价格")
    private BigDecimal price;

    /**
     * 是否为热门（0非热门 1热门）
     */
    @Schema(description = "是否为热门（0非热门 1热门）")
    private Integer isHot;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
