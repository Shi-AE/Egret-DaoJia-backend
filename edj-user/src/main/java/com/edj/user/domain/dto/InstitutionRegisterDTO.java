package com.edj.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机构端注册模型
 *
 * @author A.E.
 * @date 2024/11/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "机构端注册模型")
public class InstitutionRegisterDTO {

    /**
     * 机构注册手机号
     */
    @Schema(description = "机构注册手机号")
    @NotBlank(message = "注册手机号不能为空")
    private String phone;

    /**
     * 注册密码
     */
    @Schema(description = "注册密码")
    @NotBlank(message = "密码输入格式错误，请重新输入")
    @Size(min = 8, message = "密码输入格式错误，请重新输入")
    private String password;

    /**
     * 短信验证码
     */
    @Schema(description = "短信验证码")
    @NotBlank(message = "验证码错误，请重新输入")
    private String verifyCode;
}
