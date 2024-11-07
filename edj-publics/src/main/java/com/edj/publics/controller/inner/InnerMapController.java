package com.edj.publics.controller.inner;

import com.edj.api.api.publics.MapApi;
import com.edj.api.api.publics.dto.LocationDTO;
import com.edj.thirdparty.core.map.MapService;
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
 * 内部接口 - 地图服务相关接口
 *
 * @author A.E.
 * @date 2024/11/7
 */
@RestController
@RequestMapping("inner/map")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 地图服务相关接口")
public class InnerMapController implements MapApi {

    private final MapService mapService;

    /**
     * 根据地址查询经纬度
     */
    @Override
    @GetMapping("geo")
    @Operation(summary = "根据地址查询经纬度")
    public LocationDTO getLocationByAddress(
            @RequestParam
            @Schema(description = "详细地址")
            @NotBlank(message = "详细地址不能为空")
            String address
    ) {
        return new LocationDTO(mapService.getLocationByAddress(address));
    }
}
