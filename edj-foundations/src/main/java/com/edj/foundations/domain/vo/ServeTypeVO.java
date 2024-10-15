package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务类型查询结果")
public class ServeTypeVO {
    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long id;

    /**
     * 服务类型编码
     */
    @Schema(description = "服务类型编码")
    private String code;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    private String name;

    /**
     * 服务类型图标
     */
    @Schema(description = "服务类型图标")
    private String icon;

    /**
     * 服务类型图片
     */
    @Schema(description = "服务类型图片")
    private String img;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private Integer sortNum;

    /**
     * 是否启用（0草稿 1禁用 2启用）
     */
    @Schema(description = "是否启用（0草稿 1禁用 2启用）")
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
