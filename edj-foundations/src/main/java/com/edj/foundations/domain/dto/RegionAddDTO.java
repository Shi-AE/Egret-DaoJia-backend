package com.edj.foundations.domain.dto;

import com.edj.mvc.annotation.phone.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增服务项目
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "新增服务项目")
public class RegionAddDTO {

    /**
     * 城市id
     */
    @Schema(description = "城市id")
    @NotNull(message = "城市不能为空")
    @Positive(message = "城市错误")
    private Integer edjCityId;

    /**
     * 区域名称
     */
    @Schema(description = "区域名称")
    @NotBlank(message = "区域名称不能为空")
    private String name;

    /**
     * 负责人名称
     */
    @Schema(description = "负责人名称")
    @NotBlank(message = "负责人名称不能为空")
    private String managerName;

    /**
     * 负责人电话
     */
    @Schema(description = "负责人电话")
    @Phone(message = "负责人电话格式不正确")
    private String managerPhone;
}
