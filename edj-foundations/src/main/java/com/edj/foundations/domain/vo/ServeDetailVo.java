package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 服务详情
 *
 * @author A.E.
 * @date 2024/12/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务详情")
public class ServeDetailVo {
    /**
     * 服务id
     */
    @Schema(description = "服务id")
    private Long id;

    /**
     * 服务项id
     */
    @Schema(description = "服务项id")
    private Long edjServeItemId;

    /**
     * 服务项名称
     */
    @Schema(description = "服务项名称")
    private String serveItemName;

    /**
     * 服务项图片
     */
    @Schema(description = "服务项图片")
    private String serveItemImg;

    /**
     * 服务单位
     */
    @Schema(description = "服务单位")
    private Integer unit;

    /**
     * 价格
     */
    @Schema(description = "价格")
    private BigDecimal price;

    /**
     * 服务详图
     */
    @Schema(description = "服务详图")
    private String detailImg;

    /**
     * 城市编码
     */
    @Schema(description = "城市编码")
    private String cityCode;
}
