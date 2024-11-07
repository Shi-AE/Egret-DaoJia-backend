package com.edj.api.api.publics;

import com.edj.api.api.publics.dto.OpenIdDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author A.E.
 * @date 2024/10/29
 */
@FeignClient(contextId = "WechatApi", name = "edj-publics", path = "inner/wechat")
public interface WechatApi {

    @GetMapping("openId")
    OpenIdDTO getOpenId(@RequestParam @Schema(description = "微信登录凭证") @NotBlank String code);
}
