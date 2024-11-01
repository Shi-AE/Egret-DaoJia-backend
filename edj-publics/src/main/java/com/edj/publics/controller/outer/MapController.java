package com.edj.publics.controller.outer;

import com.edj.thirdparty.core.map.MapService;
import com.edj.thirdparty.dto.MapLocationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户端 - 地址相关接口
 *
 * @author A.E.
 * @date 2024/11/1
 */
@RestController
@RequestMapping("map")
@RequiredArgsConstructor
@Tag(name = "地图服务相关接口")
public class MapController {

    private final MapService mapService;

    @GetMapping("regeo")
    @Operation(summary = "根据经纬度查询地址信息")
    public MapLocationDTO getCityCodeByLocation(
            @RequestParam
            @NotBlank(message = "经纬度不能为空")
            @Schema(description = "经纬度")
            String location
    ) {
        return mapService.getCityCodeByLocation(location);
    }
}
