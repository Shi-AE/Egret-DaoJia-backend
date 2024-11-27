package com.edj.customer.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 银行账号信息
 *
 * @author A.E.
 * @date 2024/11/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "银行账号信息")
public class BankAccountVO {
    /**
     * 户名
     */
    @Schema(description = "户名")
    private String name;

    /**
     * 银行名称
     */
    @Schema(description = "银行名称")
    private String bankName;

    /**
     * 省
     */
    @Schema(description = "省")
    private String province;

    /**
     * 市
     */
    @Schema(description = "市")
    private String city;

    /**
     * 区
     */
    @Schema(description = "区")
    private String district;

    /**
     * 网点
     */
    @Schema(description = "网点")
    private String branch;

    /**
     * 银行账号
     */
    @Schema(description = "银行账号")
    private String account;

    /**
     * 开户证明
     */
    @Schema(description = "开户证明")
    private String accountCertification;

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
