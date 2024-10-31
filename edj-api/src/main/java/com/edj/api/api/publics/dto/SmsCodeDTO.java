package com.edj.api.api.publics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 验证码结果
 *
 * @author A.E.
 * @date 2024/10/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "验证码结果")
public class SmsCodeDTO {

    /**
     * 验证是否成功
     */
    @Schema(description = "验证是否成功")
    private Boolean isSuccess;
}
