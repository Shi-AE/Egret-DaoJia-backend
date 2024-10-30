package com.edj.foundations.controller.operation;


import com.edj.common.domain.PageResult;
import com.edj.foundations.domain.dto.ServeTypeAddDTO;
import com.edj.foundations.domain.dto.ServeTypePageDTO;
import com.edj.foundations.domain.dto.ServeTypeUpdateDTO;
import com.edj.foundations.domain.vo.ServeTypeStatusGetVO;
import com.edj.foundations.domain.vo.ServeTypeVO;
import com.edj.foundations.enums.EdjServeTypeActiveStatus;
import com.edj.foundations.service.EdjServeTypeService;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 运营端 - 服务类型相关接口
 *
 * @author A.E.
 * @date 2024/10/11
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/serve/type")
@Tag(name = "运营端 - 服务类型相关接口")
public class OperationServeTypeController {

    private final EdjServeTypeService serveTypeService;

    /**
     * 新增服务类型
     */
    @PostMapping
    @Operation(summary = "新增服务类型")
    @PreAuthorize("hasAuthority('foundations:serverType:add')")
    public void add(@RequestBody @Validated ServeTypeAddDTO serveTypeAddDTO) {
        serveTypeService.add(serveTypeAddDTO);
    }

    /**
     * 服务类型分页查询
     */
    @PostMapping("page")
    @Operation(summary = "服务类型分页查询")
    @PreAuthorize("hasAuthority('foundations:serverType:page')")
    public PageResult<ServeTypeVO> page(@RequestBody @Validated ServeTypePageDTO serveTypePageDTO) {
        return serveTypeService.page(serveTypePageDTO);
    }

    /**
     * 启用服务类型
     */
    @PutMapping("activate/{id}")
    @Operation(summary = "启用服务类型")
    @PreAuthorize("hasAuthority('foundations:serverType:activate')")
    public void activate(@PathVariable("id") @Positive @Schema(description = "服务类型id") Long id) {
        serveTypeService.activate(id);
    }

    /**
     * 禁用服务类型
     */
    @PutMapping("deactivate/{id}")
    @Operation(summary = "禁用服务类型")
    @PreAuthorize("hasAuthority('foundations:serverType:deactivate')")
    public void deactivate(@PathVariable("id") @Positive @Schema(description = "服务类型id") Long id) {
        serveTypeService.deactivate(id);
    }

    /**
     * 服务类型删除
     */
    @DeleteMapping("{id}")
    @Operation(summary = "服务类型删除")
    @PreAuthorize("hasAuthority('foundations:serverType:delete')")
    public void delete(@PathVariable("id") @Positive @Schema(description = "服务类型id") Long id) {
        serveTypeService.deleteById(id);
    }

    /**
     * 服务类型修改
     */
    @PutMapping
    @Operation(summary = "服务类型修改")
    @PreAuthorize("hasAuthority('foundations:serverType:update')")
    public void update(@RequestBody ServeTypeUpdateDTO serveTypeUpsertReqDTO) {
        serveTypeService.update(serveTypeUpsertReqDTO);
    }

    /**
     * 根据活动状态查询服务类型 用于下拉选项
     */
    @GetMapping("status")
    @Operation(summary = "根据活动状态查询服务类型")
    @PreAuthorize("hasAuthority('foundations:serverType:status')")
    public List<ServeTypeStatusGetVO> selectByStatus(
            @RequestParam(required = false)
            @Schema(description = "状态（0草稿 1禁用 2启用）")
            @Enums(EdjServeTypeActiveStatus.class)
            Integer activeStatus
    ) {
        return serveTypeService.selectByStatus(activeStatus);
    }

    /**
     * 测试
     */
    @GetMapping("test")
    @Operation(summary = "test")
    public String test() {
        return "test";
    }
}
