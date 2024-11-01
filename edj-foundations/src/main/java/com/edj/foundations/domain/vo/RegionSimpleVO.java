package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区域简略响应值
 *
 * @author A.E.
 * @date 2024/11/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域简略响应值")
public class RegionSimpleVO {
    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 区域名称
     */
    @Schema(description = "区域名称")
    private String name;

    /**
     * 城市编码
     */
    @Schema(description = "城市编码")
    private String cityCode;
}
