package com.edj.customer.domain.dto;

import com.edj.customer.enums.EdjServeProviderSettingsCanPickUp;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接单设置模型
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "接单设置")
public class ServePickUpDTO {

    /**
     * 是否开启接单设置
     */
    @Schema(description = "是否可以接单（-1未知 0关闭接单 1开启接单）")
    @NotNull
    @Enums(EdjServeProviderSettingsCanPickUp.class)
    private Integer canPickUp;
}
