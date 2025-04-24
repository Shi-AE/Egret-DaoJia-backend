package com.edj.api.api.customer;

import com.edj.api.api.customer.dto.ProviderSettingsDetailDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 内部接口 - 服务人员/机构相关接口
 *
 * @author A.E.
 * @date 2024/11/12
 */
@FeignClient(contextId = "ProviderApi", name = "edj-customer", path = "inner/provider")
public interface ProviderApi {

    /**
     * 注册额外信息
     */
    @GetMapping("register")
    void add(@RequestParam @Schema(description = "用户id") @Positive @NotNull Long userId);

    /**
     * 查询用户设置详细信息
     */
    @GetMapping("detail")
    ProviderSettingsDetailDTO detail();
}
