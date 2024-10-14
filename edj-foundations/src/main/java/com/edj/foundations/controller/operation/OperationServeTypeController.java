package com.edj.foundations.controller.operation;


import com.edj.common.domain.PageResult;
import com.edj.common.domain.Result;
import com.edj.foundations.domain.dto.ServeTypeAddDTO;
import com.edj.foundations.domain.dto.ServerTypePageDTO;
import com.edj.foundations.domain.vo.ServerTypeVO;
import com.edj.foundations.service.EdjServeTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Negative;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 服务类型分页查询
     */
    @PostMapping("page")
    @Operation(summary = "服务类型分页查询")
    @PreAuthorize("hasAuthority('foundations:serverType:page')")
    public PageResult<ServerTypeVO> page(@RequestBody @Validated ServerTypePageDTO serverTypePageDTO) {
        return serveTypeService.page(serverTypePageDTO);
    }

    /**
     * 启用服务类型
     */
    @PutMapping("/activate/{id}")
    @Operation(summary = "启用服务类型")
    @PreAuthorize("hasAuthority('foundations:serverType:activate')")
    public void activate(@PathVariable("id") @Negative @Schema(description = "服务类型id") Long id) {
        serveTypeService.activate(id);
    }

    /**
     * 禁用服务类型
     */
    @PutMapping("/deactivate/{id}")
    @Operation(summary = "禁用服务类型")
    @PreAuthorize("hasAuthority('foundations:serverType:deactivate')")
    public void deactivate(@PathVariable("id") @Negative @Schema(description = "服务类型id") Long id) {
        serveTypeService.deactivate(id);
    }

    /**
     * 服务类型删除
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "服务类型删除")
    @PreAuthorize("hasAuthority('foundations:serverType:delete')")
    public void delete(@PathVariable("id") @Negative @Schema(description = "服务类型id") Long id) {
        serveTypeService.deleteById(id);
    }
}
