package com.edj.user.controller.inner;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.api.api.user.UserApi;
import com.edj.user.domain.entity.EdjUser;
import com.edj.user.service.EdjUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部接口 - 用户信息相关接口
 *
 * @author A.E.
 * @date 2024/11/18
 */
@Validated
@RestController
@RequestMapping("inner/user")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 用户信息相关接口")
public class InnerUserController implements UserApi {

    private final EdjUserService userService;

    /**
     * 根据用户id更新用户名
     */
    @Override
    @PutMapping("name")
    @Operation(summary = "根据用户id更新用户名")
    public void updateNameById(
            @RequestParam @Schema(description = "用户id") @NotNull @Positive Long userId,
            @RequestParam @Schema(description = "用户名") @NotBlank String username
    ) {
        LambdaUpdateWrapper<EdjUser> wrapper = new LambdaUpdateWrapper<EdjUser>()
                .eq(EdjUser::getId, userId)
                .set(EdjUser::getUsername, username)
                .set(EdjUser::getNickname, username);
        userService.update(wrapper);
    }
}
