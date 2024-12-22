package com.edj.foundations.domain.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 服务类型简单响应
 *
 * @author A.E.
 * @date 2024/11/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务类型简单响应")
public class ServeCategoryVO {

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
     * 服务类型图标
     */
    @Schema(description = "服务类型图标")
    private String serveTypeIcon;

    /**
     * 服务类型排序字段
     */
    @Schema(description = "服务类型排序字段")
    private Integer serveTypeSortNum;

    /**
     * 服务项图标列表
     */
    @Schema(description = "服务项列表")
    private List<ServeIconVO> serveIconVOList;
}
