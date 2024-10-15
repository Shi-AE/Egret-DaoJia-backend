package com.edj.foundations.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "修改服务类型")
public class ServeTypeUpdateDTO {

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    @Positive(message = "服务类型id格式错误")
    @NotNull(message = "服务类型id不能为空")
    private Long id;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称", requiredMode = NOT_REQUIRED)
    private String name;

    /**
     * 服务类型图标
     */
    @Schema(description = "服务类型图标", requiredMode = NOT_REQUIRED)
    private String icon;

    /**
     * 服务类型图片
     */
    @Schema(description = "服务类型图片", requiredMode = NOT_REQUIRED)
    private String img;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段", requiredMode = NOT_REQUIRED)
    private Integer sortNum;
}
