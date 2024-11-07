package com.edj.api.api.publics;

import com.edj.api.api.publics.dto.LocationDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 内部接口 - 地图服务相关接口
 *
 * @author A.E.
 * @date 2024/11/7
 */
@FeignClient(contextId = "MapApi", name = "edj-publics", path = "inner/map")
public interface MapApi {

    /**
     * 根据地址查询经纬度
     */
    @GetMapping("geo")
    LocationDTO getLocationByAddress(
            @RequestParam
            @Schema(description = "详细地址")
            @NotBlank(message = "详细地址不能为空")
            String address
    );
}
