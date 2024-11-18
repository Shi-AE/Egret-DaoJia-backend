package com.edj.api.api.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 内部接口 - 用户信息相关接口
 *
 * @author A.E.
 * @date 2024/11/18
 */
@FeignClient(contextId = "UserApi", name = "edj-user", path = "inner/user")
public interface UserApi {

    /**
     * 根据用户id更新用户名
     */
    @PutMapping("name")
    void updateNameById(
            @RequestParam @Schema(description = "用户id") @NotNull @Positive Long userId,
            @RequestParam @Schema(description = "用户名") @NotBlank String username
    );
}
