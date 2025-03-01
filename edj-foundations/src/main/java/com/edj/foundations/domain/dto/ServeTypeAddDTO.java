package com.edj.foundations.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "新增服务类型")
public class ServeTypeAddDTO {

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    @NotBlank(message = "服务类型名称不能为空")
    private String name;

    /**
     * 服务类型图标
     */
    @Schema(description = "服务类型图标")
    @NotBlank(message = "图标不能为空")
    private String icon;

    /**
     * 服务类型图片
     */
    @Schema(description = "服务类型图片")
    @NotBlank(message = "服务类型图片必填")
    private String img;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    @NotNull(message = "排序字段")
    private Integer sortNum;
}
