package com.edj.foundations.controller.consumer;

import com.edj.foundations.domain.vo.ServeAggregationSimpleVO;
import com.edj.foundations.domain.vo.ServeCategoryVO;
import com.edj.foundations.domain.vo.ServeDetailVo;
import com.edj.foundations.service.ConsumerHomeService;
import com.edj.foundations.service.EdjServeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户端 - 服务相关接口
 *
 * @author A.E.
 * @date 2024/11/4
 */
@RestController
@RequestMapping("consumer/serve")
@RequiredArgsConstructor
@Tag(name = "用户端 - 服务相关接口")
public class ConsumerServeController {

    private final ConsumerHomeService consumerHomeService;

    private final EdjServeService serveService;

    /**
     * 获取首页服务列表
     */
    @GetMapping("category")
    @Operation(summary = "获取首页服务列表")
    @PreAuthorize("hasAuthority('consumer:serve:category')")
    public List<ServeCategoryVO> serveCategory(@RequestParam @Positive @Schema(description = "区域id") Long regionId) {
        return consumerHomeService.getServeIconCategoryByRegionIdCache(regionId);
    }

    /**
     * 首页热门服务列表
     */
    @GetMapping("hot")
    @Operation(summary = "首页热门服务列表")
    @PreAuthorize("hasAuthority('consumer:serve:hot')")
    public List<ServeAggregationSimpleVO> hot(@RequestParam @Positive @Schema(description = "区域id") Long regionId) {
        return consumerHomeService.getHotByRegionId(regionId);
    }

    /**
     * 查询服务详情
     */
    @GetMapping("{id}")
    @Operation(summary = "查询服务详情")
    public ServeDetailVo detail(
            @Schema(description = "服务id")
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        return serveService.findDetailById(id);
    }
}
