package com.edj.api.api.publics;

import com.edj.api.api.publics.dto.OpenIdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author A.E.
 * @date 2024/10/29
 */
@FeignClient(name = "edj-publics", path = "inner/wechat")
public interface WechatApi {

    @GetMapping("openId")
    OpenIdDTO getOpenId(@RequestParam String code);
}
