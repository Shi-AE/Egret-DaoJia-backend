package com.edj.foundations.controller.operation;

import com.edj.foundations.domain.dto.RegionAddDTO;
import com.edj.foundations.service.EdjRegionService;
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
    public void add(@RequestBody RegionAddDTO regionAddDTO) {
        regionService.add(regionAddDTO);
    }
}
