package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务项简单响应
 *
 * @author A.E.
 * @date 2024/12/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务项简单响应")
public class ServeSimpleVO {

    /**
     * 服务id
     */
    @Schema(description = "服务id")
    private Long id;

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
     * 服务项图标
     */
    @Schema(description = "服务项图标")
    private String serveItemIcon;

    /**
     * 服务项排序字段
     */
    @Schema(description = "服务项排序字段")
    private Integer serveItemSortNum;
}
