package com.edj.customer.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务信息设置状态
 *
 * @author A.E.
 * @date 2024/11/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务信息设置状态")
public class ServeSettingsStatusVo {
    /**
     * 认证状态，0：初始态，1：认证中，2：认证成功，3认证失败
     */
    @Schema(description = "认证状态，0：初始态，1：认证中，2：认证成功，3认证失败")
    private Integer certificationStatus;

    /**
     * 是否完成服务技能设置
     */
    @Schema(description = "是否完成服务技能设置")
    private Boolean serveSkillHasSet;

    /**
     * 是否设置服务范文设置
     */
    @Schema(description = "是否设置服务范围")
    private Boolean serveScopeHasSet;

    /**
     * 是否开启接单
     */
    @Schema(description = "是否开启接单")
    private Boolean canPickUp;

    /**
     * 设置状态，0：未完成所有设置、认证
     */
    @Schema(description = "初次设置状态，0：未完成设置、认证，1：已经完成所有设置、认证")
    private Integer settingsStatus;
}
