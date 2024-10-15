package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 分页查询服务项
 *
 * @author A.E.
 * @date 2024/10/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询服务项")
public class ServeItemVO {
    /**
     * 服务项id
     */
    @Schema(description = "服务项id")
    private Long id;

    /**
     * 服务编码
     */
    @Schema(description = "服务编码")
    private String code;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long edjServeTypeId;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    private String serveTypeName;

    /**
     * 服务名称
     */
    @Schema(description = "服务名称")
    private String name;

    /**
     * 服务图标
     */
    @Schema(description = "服务图标")
    private String icon;

    /**
     * 服务图片
     */
    @Schema(description = "服务图片")
    private String img;

    /**
     * 服务数量单位(1小时 2天 3次 4台 5个 6㎡ 7米)
     */
    @Schema(description = "服务数量单位(1小时 2天 3次 4台 5个 6㎡ 7米)")
    private Integer unit;

    /**
     * 服务描述
     */
    @Schema(description = "服务描述")
    private String description;

    /**
     * 服务详图
     */
    @Schema(description = "服务详图")
    private String detailImg;

    /**
     * 参考价格
     */
    @Schema(description = "参考价格")
    private BigDecimal referencePrice;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private Integer sortNum;

    /**
     * 活动状态（0草稿 1禁用 2启用）
     */
    @Schema(description = "活动状态（0草稿 1禁用 2启用）")
    private Integer activeStatus;

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
