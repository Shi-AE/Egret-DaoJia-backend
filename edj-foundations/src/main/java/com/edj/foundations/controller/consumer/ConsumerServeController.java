package com.edj.foundations.controller.consumer;

import com.edj.foundations.domain.vo.ServeAggregationSimpleVO;
import com.edj.foundations.domain.vo.ServeCategoryVO;
import com.edj.foundations.service.ConsumerHomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//    @PreAuthorize("hasAuthority('consumer:serve:hot')")
    public List<ServeAggregationSimpleVO> hot(@RequestParam @Positive @Schema(description = "区域id") Long regionId) {
        return consumerHomeService.getHotByRegionId(regionId);
    }
}
