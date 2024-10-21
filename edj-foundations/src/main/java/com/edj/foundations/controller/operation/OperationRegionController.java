package com.edj.foundations.controller.operation;

import cn.hutool.core.bean.BeanUtil;
import com.edj.common.domain.PageResult;
import com.edj.foundations.domain.dto.RegionAddDTO;
import com.edj.foundations.domain.dto.RegionPageDTO;
import com.edj.foundations.domain.dto.RegionUpdateDTO;
import com.edj.foundations.domain.entity.EdjRegion;
import com.edj.foundations.domain.vo.RegionVO;
import com.edj.foundations.service.EdjRegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 运营端 - 区域相关接口
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/region")
@Tag(name = "运营端 - 区域相关接口")
public class OperationRegionController {

    private final EdjRegionService regionService;

    /**
     * 新增区域
     */
    @PostMapping
    @Operation(summary = "新增区域")
    @PreAuthorize("hasAuthority('foundations:region:add')")
    public void add(@RequestBody @Validated RegionAddDTO regionAddDTO) {
        regionService.add(regionAddDTO);
    }

    /**
     * 修改区域
     */
    @PutMapping
    @Operation(summary = "修改区域")
    @PreAuthorize("hasAuthority('foundations:region:update')")
    public void update(@RequestBody @Validated RegionUpdateDTO regionUpdateDTO) {
        regionService.update(regionUpdateDTO);
    }

    /**
     * 删除区域
     */
    @DeleteMapping("{id}")
    @Operation(summary = "删除区域")
    @PreAuthorize("hasAuthority('foundations:region:delete')")
    public void delete(@PathVariable("id") @Positive @Schema(description = "区域id") Long id) {
        regionService.deleteById(id);
    }

    /**
     * 区域分页查询
     */
    @GetMapping("page")
    @Operation(summary = "区域分页查询")
    @PreAuthorize("hasAuthority('foundations:region:page')")
    public PageResult<RegionVO> page(RegionPageDTO regionPageDTO) {
        return regionService.page(regionPageDTO);
    }

    /**
     * 根据id查询
     */
    @GetMapping("{id}")
    @Operation(summary = "根据id查询")
    @PreAuthorize("hasAuthority('foundations:region:findById')")
    public RegionVO findById(@PathVariable("id") @Positive @Schema(description = "区域id") Long id) {
        EdjRegion region = regionService.getById(id);
        return BeanUtil.toBean(region, RegionVO.class);
    }
}
