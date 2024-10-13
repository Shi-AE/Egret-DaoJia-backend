package com.edj.foundations.controller.operation;


import com.edj.common.domain.Result;
import com.edj.foundations.domain.dto.ServeTypeAddDTO;
import com.edj.foundations.service.EdjServeTypeService;
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
 * 运营端 - 服务类型相关接口
 *
 * @author A.E.
 * @date 2024/10/11
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/server/type")
@Tag(name = "运营端 - 服务类型相关接口")
public class OperationServeTypeController {

    private final EdjServeTypeService serveTypeService;

    /**
     * 新增服务类型
     */
    @PostMapping
    @Operation(summary = "新增服务类型")
    @PreAuthorize("hasAuthority('foundations:serverType:add')")
    public Result<Void> add(@RequestBody @Validated ServeTypeAddDTO serveTypeAddDTO) {
        serveTypeService.add(serveTypeAddDTO);
        return Result.success();
    }
}
