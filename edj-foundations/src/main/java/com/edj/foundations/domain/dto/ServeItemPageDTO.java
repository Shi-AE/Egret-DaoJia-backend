package com.edj.foundations.domain.dto;

import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.foundations.enums.EdjServeItemActiveStatus;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

/**
 * 分页查询服务项 DTO
 *
 * @author A.E.
 * @date 2024/10/15
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "分页查询服务项 DTO")
public class ServeItemPageDTO extends PageQueryDTO {

    /**
     * 服务项名称
     */
    @Schema(description = "服务项名称", requiredMode = NOT_REQUIRED)
    private String name;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id", requiredMode = NOT_REQUIRED)
    @Positive(message = "服务类型错误")
    private Long serveTypeId;

    /**
     * 活动状态（0草稿 1禁用 2启用）
     */
    @Schema(description = "活动状态（0草稿 1禁用 2启用）", requiredMode = NOT_REQUIRED)
    @Enums(EdjServeItemActiveStatus.class)
    private Integer activeStatus;
}
