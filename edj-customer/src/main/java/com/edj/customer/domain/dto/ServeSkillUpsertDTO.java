package com.edj.customer.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 批量新增或修改服务技能
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "批量新增或修改服务技能")
public class ServeSkillUpsertDTO {

    /**
     * 服务类型id
     */
    @NotNull(message = "服务类型不能为空")
    @Positive(message = "服务类型错误")
    @Schema(description = "服务类型id")
    private Long serveTypeId;

    /**
     * 服务类型名称
     */
    @NotBlank(message = "服务类型名称不能为空")
    @Schema(description = "服务类型名称")
    private String serveTypeName;

    /**
     * 服务项id
     */
    @NotNull(message = "服务项不能为空")
    @Positive(message = "服务项错误")
    @Schema(description = "服务项id")
    private Long serveItemId;

    /**
     * 服务项名称
     */
    @NotBlank(message = "服务项名称不能为空")
    @Schema(description = "服务项名称")
    private String serveItemName;
}
