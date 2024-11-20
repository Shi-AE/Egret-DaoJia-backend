package com.edj.customer.controller.inner;

import cn.hutool.core.lang.Snowflake;
import com.edj.api.api.customer.ProviderApi;
import com.edj.common.utils.AsyncUtils;
import com.edj.common.utils.IdUtils;
import com.edj.customer.domain.entity.EdjServeProvider;
import com.edj.customer.domain.entity.EdjServeProviderSettings;
import com.edj.customer.domain.entity.EdjServeProviderSync;
import com.edj.customer.mapper.EdjServeProviderMapper;
import com.edj.customer.mapper.EdjServeProviderSettingsMapper;
import com.edj.customer.mapper.EdjServeProviderSyncMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 内部接口 - 服务人员/机构相关接口
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Validated
@RestController
@RequestMapping("inner/worker")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 服务人员/机构相关接口")
public class InnerProviderController implements ProviderApi {

    private final EdjServeProviderMapper serveProviderMapper;

    private final EdjServeProviderSettingsMapper serveProviderSettingsMapper;

    private final EdjServeProviderSyncMapper serveProviderSyncMapper;

    private final Snowflake snowflake;

    /**
     * 注册额外信息
     */
    @Override
    @GetMapping("register")
    @Operation(summary = "注册额外信息")
    @Transactional
    public void add(@RequestParam @Schema(description = "用户id") @Positive @NotNull Long userId) {
        CompletableFuture<Void> future1 = AsyncUtils.runAsyncTransaction(() -> serveProviderMapper.insert(EdjServeProvider
                .builder()
                .id(userId)
                .code(IdUtils.toCode(snowflake.nextId()))
                .build()
        ));

        CompletableFuture<Void> future2 = AsyncUtils.runAsyncTransaction(() -> serveProviderSettingsMapper.insert(EdjServeProviderSettings
                .builder()
                .id(userId)
                .build()
        ));

        CompletableFuture<Void> future3 = AsyncUtils.runAsyncTransaction(() -> serveProviderSyncMapper.insert(EdjServeProviderSync
                .builder()
                .id(userId)
                .serveItemIdList(List.of())
                .build()
        ));

        CompletableFuture.allOf(future1, future2, future3).join();
    }
}