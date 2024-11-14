package com.edj.api.api.foundations.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 服务技能目录响应结果
 *
 * @author A.E.
 * @date 2024/11/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务技能目录响应结果")
public class ServeTypeCategoryDTO {
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
     * 服务技能项列表
     */
    @Schema(description = "服务技能项列表")
    private List<ServeItemCategoryDTO> serveItemCategoryDTOList;
}
