package com.edj.customer.controller.worker;

import com.edj.customer.domain.dto.ServePickUpDTO;
import com.edj.customer.service.EdjServeProviderSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void setPickUp(@RequestBody ServePickUpDTO servePickUpDTO) {
        serveProviderSettingsService.setPickUp(servePickUpDTO.getCanPickUp());
    }
}
