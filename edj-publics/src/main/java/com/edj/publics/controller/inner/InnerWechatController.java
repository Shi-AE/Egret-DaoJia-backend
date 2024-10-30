package com.edj.publics.controller.inner;

import com.edj.api.api.publics.WechatApi;
import com.edj.api.api.publics.dto.OpenIdDTO;
import com.edj.thirdparty.core.wechat.WechatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务
 *
 * @author A.E.
 * @date 2024/10/29
 */
@Slf4j
@RestController
@RequestMapping("inner/wechat")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 微信服务相关接口")
public class InnerWechatController implements WechatApi {

    private final WechatService wechatService;

    @Override
    @GetMapping("openId")
    @Operation(summary = "获取openId")
    public OpenIdDTO getOpenId(@Schema(description = "微信登录凭证") String code) {
        String openId = wechatService.getOpenid(code);
        return new OpenIdDTO(openId);
    }
}
