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

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "修改服务项")
public class ServeItemUpdateDTO {

    /**
     * 服务项id
     */
    @Schema(description = "服务项id")
    @NotNull(message = "服务项不能为空")
    @Positive(message = "服务项不正确")
    private Long id;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id", requiredMode = NOT_REQUIRED)
    private Long edjServeTypeId;

    /**
     * 服务名称
     */
    @Schema(description = "服务名称", requiredMode = NOT_REQUIRED)
    private String name;

    /**
     * 服务图标
     */
    @Schema(description = "服务图标", requiredMode = NOT_REQUIRED)
    private String icon;

    /**
     * 服务图片
     */
    @Schema(description = "服务图片", requiredMode = NOT_REQUIRED)
    private String img;

    /**
     * 服务数量单位(1小时 2天 3次 4台 5个 6㎡ 7米)
     */
    @Schema(description = "服务数量单位(1小时 2天 3次 4台 5个 6㎡ 7米)", requiredMode = NOT_REQUIRED)
    @Enums(EdjServeItemUnit.class)
    private Integer unit;

    /**
     * 服务描述
     */
    @Schema(description = "服务描述", requiredMode = NOT_REQUIRED)
    private String description;

    /**
     * 服务详图
     */
    @Schema(description = "服务详图", requiredMode = NOT_REQUIRED)
    private String detailImg;

    /**
     * 参考价格
     */
    @Schema(description = "参考价格", requiredMode = NOT_REQUIRED)
    @Digits(integer = 8, fraction = 2)
    @DecimalMin("0")
    private BigDecimal referencePrice;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段", requiredMode = NOT_REQUIRED)
    private Integer sortNum;
}
