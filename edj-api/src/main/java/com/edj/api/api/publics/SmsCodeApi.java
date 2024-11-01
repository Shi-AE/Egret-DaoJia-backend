package com.edj.api.api.publics;

import com.edj.api.api.publics.dto.SmsCodeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 内部接口 - 验证码相关接口
 *
 * @author A.E.
 * @date 2024/10/31
 */
@FeignClient(contextId = "SmsCodeApi", name = "edj-publics", path = "inner/sms/code")
public interface SmsCodeApi {

    /**
     * 校验短信验证码
     *
     * @param phone      验证手机号
     * @param verifyCode 验证码
     * @return 验证结果
     */
    @GetMapping("verify")
    SmsCodeDTO verify(
            @RequestParam @Schema(description = "验证手机号") String phone,
            @RequestParam @Schema(description = "验证码") String verifyCode
    );
}
