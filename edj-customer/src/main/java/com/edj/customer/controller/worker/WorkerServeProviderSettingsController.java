package com.edj.customer.controller.worker;

import com.edj.customer.domain.dto.ServePickUpDTO;
import com.edj.customer.domain.dto.ServeScopeSetDTO;
import com.edj.customer.domain.vo.ServeProviderSettingsVO;
import com.edj.customer.domain.vo.ServeSettingsStatusVo;
import com.edj.customer.service.EdjServeProviderSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 服务端 - 服务设置相关接口
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("worker/serve/settings")
@Tag(name = "服务端 - 服务设置相关接口")
public class WorkerServeProviderSettingsController {

    private final EdjServeProviderSettingsService serveProviderSettingsService;

    /**
     * 接单设置
     */
    @PutMapping("pick/up")
    @Operation(summary = "接单设置")
    @PreAuthorize("hasAuthority('worker:serveSettings:setPickUp')")
    public void setPickUp(@RequestBody ServePickUpDTO servePickUpDTO) {
        serveProviderSettingsService.setPickUp(servePickUpDTO.getCanPickUp());
    }

    /**
     * 服务范围设置
     */
    @PutMapping("scope")
    @Operation(summary = "设置服务范围")
    @PreAuthorize("hasAuthority('worker:serveSettings:setServeScope')")
    public void setServeScope(@RequestBody @Validated ServeScopeSetDTO serveScopeSetDTO) {
        serveProviderSettingsService.setServeScope(serveScopeSetDTO);
    }

    /**
     * 获取服务范围设置
     */
    @GetMapping
    @Operation(summary = "获取服务范围设置")
    @PreAuthorize("hasAuthority('worker:serveSettings:getServeScope')")
    public ServeProviderSettingsVO getServeScope() {
        return serveProviderSettingsService.getServeScope();
    }

    /**
     * 获取所有设置状态
     */
    @GetMapping("status")
    @Operation(summary = "获取所有设置状态")
    @PreAuthorize("hasAuthority('worker:serveSettings:getStatus')")
    public ServeSettingsStatusVo getStatus() {
        return serveProviderSettingsService.getSettingStatus();
    }
}
