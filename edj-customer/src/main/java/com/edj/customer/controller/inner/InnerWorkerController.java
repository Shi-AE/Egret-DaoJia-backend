package com.edj.customer.controller.inner;

import cn.hutool.core.lang.Snowflake;
import com.edj.api.api.customer.WorkerApi;
import com.edj.common.utils.IdUtils;
import com.edj.customer.domain.entity.EdjServeProvider;
import com.edj.customer.domain.entity.EdjServeProviderSettings;
import com.edj.customer.mapper.EdjServeProviderMapper;
import com.edj.customer.mapper.EdjServeProviderSettingsMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部接口 - 服务人员/机构相关接口
 *
 * @author A.E.
 * @date 2024/11/12
 */
@RestController
@RequestMapping("inner/worker")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 服务人员/机构相关接口")
public class InnerWorkerController implements WorkerApi {

    private final EdjServeProviderMapper serveProviderMapper;

    private final EdjServeProviderSettingsMapper serveProviderSettingsMapper;

    private final Snowflake snowflake;

    /**
     * 注册额外信息
     */
    @Override
    @GetMapping("register")
    @Operation(summary = "注册额外信息")
    @Transactional
    public void add(@RequestParam @Schema(description = "用户id") @Positive @NotNull Long userId) {
        serveProviderMapper.insert(EdjServeProvider
                .builder()
                .id(userId)
                .code(IdUtils.toCode(snowflake.nextId()))
                .build()
        );

        serveProviderSettingsMapper.insert(EdjServeProviderSettings
                .builder()
                .id(userId)
                .build()
        );
    }
}
