package com.edj.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

/**
 * 手机重置登录密码参数模型
 *
 * @author A.E.
 * @date 2024/11/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "手机重置登录密码参数模型")
public class PhoneResetPasswordDTO {
    /**
     * 手机号
     */
    @Schema(description = "手机号", requiredMode = REQUIRED)
    @NotBlank
    private String phone;

    /**
     * 登录密码
     */
    @Schema(description = "登录密码", requiredMode = NOT_REQUIRED)
    @NotBlank
    private String password;

    /**
     * 验证码
     */
    @Schema(description = "验证码", requiredMode = NOT_REQUIRED)
    @NotBlank
    private String verifyCode;
}
