package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务类型简单返回模型
 *
 * @author A.E.
 * @date 2024/12/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务类型简单返回模型")
public class ServeTypeSimpleVo {
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
     * 服务类型图片
     */
    @Schema(description = "服务类型图片")
    private String serveTypeImg;

    /**
     * 服务类型排序字段
     */
    @Schema(description = "服务类型排序字段")
    private Integer serveTypeSortNum;
}
