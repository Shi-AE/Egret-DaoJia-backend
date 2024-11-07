package com.edj.customer.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 地址薄详情
 *
 * @author A.E.
 * @date 2024/11/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "地址薄详情")
public class AddressBookVO {
    /**
     * 地址簿id
     */
    @Schema(description = "地址簿id")
    private Long id;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long edjUserId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 电话
     */
    @Schema(description = "电话")
    private String phone;

    /**
     * 省份
     */
    @Schema(description = "省份")
    private String province;

    /**
     * 市
     */
    @Schema(description = "市")
    private String city;

    /**
     * 区 / 县
     */
    @Schema(description = "区 / 县")
    private String county;

    /**
     * 详细地址
     */
    @Schema(description = "详细地址")
    private String address;

    /**
     * 经度
     */
    @Schema(description = "经度")
    private BigDecimal lon;

    /**
     * 维度
     */
    @Schema(description = "维度")
    private BigDecimal lat;

    /**
     * 是否为默认地址（0否 1是）
     */
    @Schema(description = "是否为默认地址（0否 1是）")
    private Integer isDefault;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
