package com.edj.foundations.controller.operation;


import com.edj.foundations.domain.dto.ServeAddDTO;
import com.edj.foundations.service.EdjServeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 运营端 - 服务管理相关接口
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/serve")
@Tag(name = "运营端 - 服务项相关接口")
public class OperationServeController {

    private final EdjServeService serveService;

    /**
     * 批量新增区域服务
     */
    @PostMapping
    @Operation(summary = "批量新增区域服务")
    public void add(@RequestBody @NotEmpty List<@Valid ServeAddDTO> serveAddDTOList) {
        serveService.add(serveAddDTOList);
    }
}
