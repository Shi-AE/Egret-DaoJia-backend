package com.edj.publics.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 短信验证码下发请求模型
 *
 * @author A.E.
 * @date 2024/10/31
 */
@Data
@Schema(description = "短信验证码下发请求模型")
public class SmsCodeSendDTO {

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phoneNumber;
}
