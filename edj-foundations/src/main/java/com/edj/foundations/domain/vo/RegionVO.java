package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 区域分页查询
 *
 * @author A.E.
 * @date 2024/10/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域分页查询")
public class RegionVO {

    /**
     * 区域id
     */
    @Schema(description = "区域id")
    private Long id;

    /**
     * 城市id
     */
    @Schema(description = "城市id")
    private Integer edjCityId;

    /**
     * 区域名称
     */
    @Schema(description = "区域名称")
    private String name;

    /**
     * 负责人名称
     */
    @Schema(description = "负责人名称")
    private String managerName;

    /**
     * 负责人电话
     */
    @Schema(description = "负责人电话")
    private String managerPhone;

    /**
     * 是否启用（0草稿 1禁用 2启用）
     */
    @Schema(description = "是否启用（0草稿 1禁用 2启用）")
    private Integer activeStatus;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private Integer sortNum;

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

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private Long createBy;

    /**
     * 更新者
     */
    @Schema(description = "更新者")
    private Long updateBy;
}
