package com.edj.foundations.controller.consumer;

import com.edj.foundations.domain.vo.RegionSimpleVO;
import com.edj.foundations.service.EdjRegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 区域表 前端控制器
 *
 * @author A.E.
 * @date 2024/11/1
 */
@RestController
@RequestMapping("consumer/region")
@RequiredArgsConstructor
@Tag(name = "用户端 - 区域相关接口")
public class ConsumerRegionController {

    private final EdjRegionService regionService;

    /**
     * 已开通服务区域列表
     */
    @GetMapping("active")
    @Operation(summary = "已开通服务区域列表")
    @PreAuthorize("permitAll()")
    public List<RegionSimpleVO> activeRegionList() {
        return regionService.getActiveRegionList();
    }
}
