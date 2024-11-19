package com.edj.publics.controller.inner;

import com.edj.api.api.publics.SmsCodeApi;
import com.edj.api.api.publics.dto.SmsCodeDTO;
import com.edj.publics.service.SmsCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部接口 - 验证码相关接口
 *
 * @author A.E.
 * @date 2024/10/31
 */
@Validated
@RestController
@RequestMapping("inner/sms/code")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 验证码相关接口")
public class InnerSmsCodeController implements SmsCodeApi {

    private final SmsCodeService smsCodeService;

    /**
     * 校验短信验证码
     *
     * @param phone      验证手机号
     * @param verifyCode 验证码
     * @return 验证结果
     */
    @Override
    @GetMapping("verify")
    @Operation(summary = "校验短信验证码")
    public SmsCodeDTO verify(
            @RequestParam @Schema(description = "验证手机号") @NotBlank String phone,
            @RequestParam @Schema(description = "验证码") @NotBlank String verifyCode
    ) {
        return new SmsCodeDTO(smsCodeService.verify(phone, verifyCode));
    }
}
