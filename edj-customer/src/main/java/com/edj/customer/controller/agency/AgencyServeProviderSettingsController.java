package com.edj.customer.controller.agency;

import com.edj.customer.domain.dto.ServePickUpDTO;
import com.edj.customer.domain.dto.ServeScopeSetDTO;
import com.edj.customer.domain.vo.ServeProviderSettingsVO;
import com.edj.customer.domain.vo.ServeSettingsStatusVo;
import com.edj.customer.service.EdjServeProviderSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 机构端 - 服务设置相关接口
 *
 * @author A.E.
 * @date 2024/11/20
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("agency/serve/settings")
@Tag(name = "机构端 - 服务设置相关接口")
public class AgencyServeProviderSettingsController {

    private final EdjServeProviderSettingsService serveProviderSettingsService;

    /**
     * 接单设置
     */
    @PutMapping("pick/up")
    @Operation(summary = "接单设置")
    public void setPickUp(@RequestBody ServePickUpDTO servePickUpDTO) {
        serveProviderSettingsService.setPickUp(servePickUpDTO.getCanPickUp());
    }

    /**
     * 服务范围设置
     */
    @PutMapping("scope")
    @Operation(summary = "服务范围设置")
    public void setServeScope(@RequestBody @Validated ServeScopeSetDTO serveScopeSetDTO) {
        serveProviderSettingsService.setServeScope(serveScopeSetDTO);
    }

    /**
     * 获取服务范围设置
     */
    @GetMapping
    @Operation(summary = "获取服务范围设置")
    public ServeProviderSettingsVO getServeScope() {
        return serveProviderSettingsService.getServeScope();
    }

    /**
     * 获取所有设置状态
     */
    @GetMapping("status")
    @Operation(summary = "获取所有设置状态")
    public ServeSettingsStatusVo getStatus() {
        return serveProviderSettingsService.getSettingStatus();
    }
}
