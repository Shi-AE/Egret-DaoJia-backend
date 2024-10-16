package com.edj.foundations.domain.dto;

import com.edj.mvc.annotation.phone.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改区域
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "修改区域")
public class RegionUpdateDTO {

    /**
     * 区域id
     */
    @Schema(description = "区域id")
    @NotNull(message = "区域id不能为空")
    @Positive(message = "区域不正确")
    private Long id;

    /**
     * 负责人名称
     */
    @Schema(description = "负责人名称")
    private String managerName;

    /**
     * 负责人电话
     */
    @Schema(description = "负责人电话")
    @Phone(message = "负责人电话格式不正确")
    private String managerPhone;
}
