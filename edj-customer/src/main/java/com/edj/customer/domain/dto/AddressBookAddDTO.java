package com.edj.customer.domain.dto;

import com.edj.customer.enums.EdjAddressBookIsDefault;
import com.edj.mvc.annotation.enums.Enums;
import com.edj.mvc.annotation.phone.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增地址薄新
 *
 * @author A.E.
 * @date 2024/11/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "新增地址薄新")
public class AddressBookAddDTO {

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    @Schema(description = "名称")
    private String name;

    /**
     * 电话
     */
    @Phone
    @NotBlank(message = "电话不能为空")
    @Schema(description = "电话")
    private String phone;

    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空")
    @Schema(description = "省份")
    private String province;

    /**
     * 市
     */
    @NotBlank(message = "市不能为空")
    @Schema(description = "市")
    private String city;

    /**
     * 区 / 县
     */
    @NotBlank(message = "区 / 县不能为空")
    @Schema(description = "区 / 县")
    private String county;

    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    @Schema(description = "详细地址")
    private String address;

    /**
     * 是否为默认地址（0否 1是）
     */
    @NotNull(message = "默认选项不能为空")
    @Enums(EdjAddressBookIsDefault.class)
    @Schema(description = "是否为默认地址（0否 1是）")
    private Integer isDefault;

    /**
     * 经纬度
     */
    @Schema(description = "经纬度")
    private String location;
}
