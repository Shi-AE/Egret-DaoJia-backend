package com.edj.foundations.controller.operation;

import com.edj.foundations.domain.dto.ServeItemAddDTO;
import com.edj.foundations.domain.dto.ServeItemUpdateDTO;
import com.edj.foundations.service.EdjServeItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 运营端 - 服务项相关接口
 *
 * @author A.E.
 * @date 2024/10/15
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/serve/item")
@Tag(name = "运营端 - 服务项相关接口")
public class OperationServeItemController {

    private final EdjServeItemService serveItemService;

    /**
     * 新增服务项
     */
    @PostMapping
    @Operation(summary = "新增服务项")
    @PreAuthorize("hasAuthority('foundations:serveItem:add')")
    public void add(@RequestBody ServeItemAddDTO serveItemAddDTO) {
        serveItemService.add(serveItemAddDTO);
    }

    /**
     * 修改服务项
     */
    @PutMapping
    @Operation(summary = "修改服务项")
    @PreAuthorize("hasAuthority('foundations:serveItem:update')")
    public void update(@RequestBody ServeItemUpdateDTO serveItemUpdateDTO) {
        serveItemService.update(serveItemUpdateDTO);
    }

    /**
     * 启用服务项
     */
    @PutMapping("activate/{id}")
    @Operation(summary = "启用服务项")
    public void activate(@PathVariable("id") @Positive @Schema(description = "服务项id") Long id) {
        serveItemService.activate(id);
    }

    /**
     * 禁用服务项
     */
    @PutMapping("deactivate/{id}")
    @Operation(summary = "禁用服务项")
    public void deactivate(@PathVariable("id") @Positive @Schema(description = "服务项id") Long id) {
        serveItemService.deactivate(id);
    }

    /**
     * 删除服务项
     */
    @DeleteMapping("{id}")
    @Operation(summary = "删除服务项")
    public void delete(@PathVariable("id") @Positive @Schema(description = "服务项id") Long id) {
        serveItemService.delete(id);
    }
}
