package com.edj.foundations.domain.dto;

import com.edj.foundations.enums.EdjServeItemUnit;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "新增服务项目")
public class ServeItemAddDTO {

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    @NotNull(message = "服务类型不能为空")
    @Positive(message = "服务类型错误")
    private Long edjServeTypeId;

    /**
     * 服务名称
     */
    @Schema(description = "服务名称")
    @NotBlank(message = "服务名称不能为空")
    private String name;

    /**
     * 服务图标
     */
    @Schema(description = "服务图标")
    @NotBlank(message = "服务图标不能为空")
    private String icon;

    /**
     * 服务图片
     */
    @Schema(description = "服务图片")
    @NotBlank(message = "服务图片不能为空")
    private String img;

    /**
     * 服务数量单位(1小时 2天 3次 4台 5个 6㎡ 7米)
     */
    @Schema(description = "服务数量单位(1小时 2天 3次 4台 5个 6㎡ 7米)")
    @NotNull
    @Enums(EdjServeItemUnit.class)
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
    @NotBlank(message = "服务详图不能为空")
    private String detailImg;

    /**
     * 参考价格
     */
    @Schema(description = "参考价格")
    @Digits(integer = 8, fraction = 2)
    @DecimalMin("0")
    @NotNull(message = "参考不能为空")
    private BigDecimal referencePrice;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    @NotNull(message = "排序字段不能为空")
    private Integer sortNum;
}
