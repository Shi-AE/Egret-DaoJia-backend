package com.edj.foundations.controller.operation;


import com.edj.common.domain.PageResult;
import com.edj.foundations.domain.dto.ServeAddDTO;
import com.edj.foundations.domain.dto.ServePageDTO;
import com.edj.foundations.domain.vo.ServeVO;
import com.edj.foundations.enums.EdjServeIsHot;
import com.edj.foundations.service.EdjServeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 运营端 - 区域服务管理相关接口
 *
 * @author A.E.
 * @date 2024/10/23
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/serve")
@Tag(name = "运营端 - 区域服务管理相关接口")
public class OperationServeController {

    private final EdjServeService serveService;

    /**
     * 批量新增区域服务
     */
    @PostMapping
    @Operation(summary = "批量新增区域服务")
    @PreAuthorize("hasAuthority('foundations:serve:add')")
    public void add(@RequestBody @NotEmpty List<@NotNull @Valid ServeAddDTO> serveAddDTOList) {
        serveService.add(serveAddDTOList);
    }

    /**
     * 区域服务分页查询
     */
    @PostMapping("page")
    @Operation(summary = "区域服务分页查询")
    @PreAuthorize("hasAuthority('foundations:serve:page')")
    public PageResult<ServeVO> page(@RequestBody @Validated ServePageDTO servePageDTO) {
        return serveService.page(servePageDTO);
    }

    /**
     * 区域服务价格修改
     */
    @PutMapping("{id}")
    @Operation(summary = "区域服务价格修改")
    @PreAuthorize("hasAuthority('foundations:serve:update')")
    public void update(
            @Schema(description = "区域服务id") @NotNull(message = "id不能为空") @Positive @PathVariable Long id,
            @Schema(description = "价格") @NotNull(message = "价格不能为空") @Digits(integer = 8, fraction = 2) @RequestParam BigDecimal price
    ) {
        serveService.update(id, price);
    }

    /**
     * 区域服务设置热门
     */
    @PutMapping("hot/on/{id}")
    @Operation(summary = "区域服务设置热门")
    @PreAuthorize("hasAuthority('foundations:serve:onHot')")
    public void onHot(@Schema(description = "区域服务id") @NotNull(message = "id不能为空") @Positive @PathVariable Long id) {
        serveService.changeHotStatus(id, EdjServeIsHot.HOT);
    }

    /**
     * 区域服务取消热门
     */
    @PutMapping("hot/off/{id}")
    @Operation(summary = "区域服务取消热门")
    @PreAuthorize("hasAuthority('foundations:serve:offHot')")
    public void offHot(@Schema(description = "区域服务id") @NotNull(message = "id不能为空") @Positive @PathVariable Long id) {
        serveService.changeHotStatus(id, EdjServeIsHot.NOT_HOT);
    }

    /**
     * 区域服务上架
     */
    @PutMapping("sale/on/{id}")
    @Operation(summary = "区域服务上架")
    @PreAuthorize("hasAuthority('foundations:serve:onSale')")
    public void onSale(@Schema(description = "区域服务id") @NotNull(message = "id不能为空") @Positive @PathVariable Long id) {
        serveService.onSale(id);
    }

    /**
     * 区域服务下架
     */
    @PutMapping("sale/off/{id}")
    @Operation(summary = "区域服务下架")
    @PreAuthorize("hasAuthority('foundations:serve:offSale')")
    public void offSale(@Schema(description = "区域服务id") @NotNull(message = "id不能为空") @Positive @PathVariable Long id) {
        serveService.offSale(id);
    }

    /**
     * 区域服务删除
     */
    @DeleteMapping("{id}")
    @Operation(summary = "区域服务删除")
    @PreAuthorize("hasAuthority('foundations:serve:delete')")
    public void delete(@Schema(description = "区域服务id") @NotNull(message = "id不能为空") @Positive @PathVariable Long id) {
        serveService.deleteById(id);
    }
}
