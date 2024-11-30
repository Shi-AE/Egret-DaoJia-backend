package com.edj.foundations.controller.consumer;

import com.edj.foundations.domain.vo.ServeTypeSimpleVo;
import com.edj.foundations.service.ConsumerHomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户端 - 服务类型相关接口
 *
 * @author A.E.
 * @date 2024/12/1
 */
@RestController
@RequestMapping("consumer/serve/type")
@RequiredArgsConstructor
@Tag(name = "用户端 - 服务类型相关接口")
public class ConsumerServeTypeController {

    private final ConsumerHomeService homeService;

    /**
     * 查询已开通的服务类型
     */
    @GetMapping
    @Operation(summary = "查询已开通的服务类型")
    @PreAuthorize("hasAuthority('consumer:serveType:get')")
    public List<ServeTypeSimpleVo> serveTypeList(
            @Schema(description = "区域id")
            @RequestParam
            @NotNull
            @Positive
            Long regionId
    ) {
        return homeService.serveTypeListByRegionId(regionId);
    }
}
