package com.edj.foundations.controller.operation;

import com.edj.foundations.domain.dto.ServeItemAddDTO;
import com.edj.foundations.service.EdjServeItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 服务项新增
     */
    @PostMapping
    @Operation(summary = "服务项新增")
    @PreAuthorize("hasAuthority('foundations:serveItem:add')")
    public void add(@RequestBody ServeItemAddDTO serveItemAddDTO) {
        serveItemService.add(serveItemAddDTO);
    }
}
