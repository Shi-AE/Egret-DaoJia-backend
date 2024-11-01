package com.edj.publics.controller.outer;

import com.edj.publics.domain.dto.SmsCodeSendDTO;
import com.edj.publics.service.SmsCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码
 *
 * @author A.E.
 * @date 2024/10/31
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sms/code")
@Tag(name = "验证码相关接口")
public class SmsCodeController {

    private final SmsCodeService smsCodeService;

    @PostMapping("send")
    @Operation(summary = "发送短信验证码")
    public void smsCodeSend(@RequestBody SmsCodeSendDTO smsCodeSendDTO) {
        smsCodeService.smsCodeSend(smsCodeSendDTO.getPhone());
    }
}
