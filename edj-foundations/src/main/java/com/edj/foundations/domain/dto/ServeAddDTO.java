package com.edj.foundations.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 区域服务新增
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域服务新增")
public class ServeAddDTO {

    /**
     * 区域id
     */
    @Schema(description = "区域id")
    @NotNull(message = "区域id不能为空")
    @Positive(message = "区域id格式错误")
    private Long edjRegionId;

    /**
     * 服务项id
     */
    @Schema(description = "服务项id")
    @NotNull(message = "服务项id不能为空")
    @Positive(message = "服务项id格式错误")
    private Long edjServeItemId;

    /**
     * 价格
     */
    @Schema(description = "价格")
    @Digits(integer = 8, fraction = 2)
    @NotNull(message = "价格不能为空")
    private BigDecimal price;
}
