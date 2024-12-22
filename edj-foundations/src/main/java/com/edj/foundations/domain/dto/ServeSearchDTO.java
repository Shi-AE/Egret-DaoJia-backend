package com.edj.foundations.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务搜索参数
 *
 * @author A.E.
 * @date 2024/12/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务搜索参数")
public class ServeSearchDTO {

    /**
     * 城市id
     */
    @Schema(description = "城市id")
    @NotNull(message = "城市不能为空")
    @Positive
    private Integer cityId;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    @Positive
    private Long serveTypeId;

    /**
     * 关键词
     */
    @Schema(description = "关键词")
    private String keyword;
}
