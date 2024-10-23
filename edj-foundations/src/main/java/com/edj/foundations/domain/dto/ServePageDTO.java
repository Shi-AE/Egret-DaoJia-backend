package com.edj.foundations.domain.dto;

import com.edj.common.domain.dto.PageQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 区域服务分页查询
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "区域服务分页查询")
public class ServePageDTO extends PageQueryDTO {

    /**
     * 区域id
     */
    @Schema(description = "区域id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Positive
    @NotNull(message = "区域id不能为空")
    private Long edjRegionId;
}
