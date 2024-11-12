package com.edj.customer.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务范围设置
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务范围设置")
public class ServeScopeSetDTO {

    /**
     * 城市编码
     */
    @Schema(description = "城市编码")
    @NotBlank(message = "城市编码不能为空")
    private String cityCode;

    /**
     * 城市名称
     */
    @Schema(description = "城市名称")
    @NotBlank(message = "城市名称不能为空")
    private String cityName;

    /**
     * 坐标经纬度，例如经度,纬度
     */
    @Schema(description = "坐标经纬度，例如经度,纬度")
    @NotBlank(message = "坐标不能为空")
    private String location;

    /**
     * 意向接单范围
     */
    @Schema(description = "意向接单范围")
    @NotBlank(message = "意向接单范围不能为空")
    private String intentionScope;
}
