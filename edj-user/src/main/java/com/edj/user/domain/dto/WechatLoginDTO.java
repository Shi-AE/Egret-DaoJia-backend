package com.edj.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

/**
 * c端微信端登录参数模型
 *
 * @author A.E.
 * @date 2024/10/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "c端微信端登录参数模型")
public class WechatLoginDTO {
    /**
     * 微信授权code
     */
    @Schema(description = "微信授权code", requiredMode = REQUIRED)
    @NotBlank
    private String code;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;
}
