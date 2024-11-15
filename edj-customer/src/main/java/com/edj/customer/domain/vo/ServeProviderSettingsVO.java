package com.edj.customer.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取服务范围设置
 *
 * @author A.E.
 * @date 2024/11/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "获取服务范围设置")
public class ServeProviderSettingsVO {
    /**
     * 服务所在城市编码
     */
    @Schema(description = "服务所在城市编码")
    private String cityCode;
    /**
     * 服务所在城市名称
     */
    @Schema(description = "服务所在城市名称")
    private String cityName;
    /**
     * 服务范围经纬度，格式经度,纬度
     */
    @Schema(description = "服务范围经纬度，格式经度,纬度")
    private String location;
    /**
     * 意向接单范围
     */
    @Schema(description = "意向接单范围")
    private String intentionScope;

    /**
     * 是否有技能
     */
    @Schema(description = "是否有技能")
    private Boolean haveSkill;

    /**
     * 是否开启接单
     */
    @Schema(description = "是否可以接单")
    private Boolean canPickUp;
}
