package com.edj.api.api.foundations;

import com.edj.api.api.foundations.dto.ConfigRegionInnerDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 内部接口 - 区域业务配置相关接口
 *
 * @author A.E.
 * @date 2025/4/23
 */
@FeignClient(contextId = "ConfigRegionApi", name = "edj-foundations", path = "inner/foundations/config/region")
public interface ConfigRegionApi {

    /**
     * 根据城市编码获取区域配置
     */
    @GetMapping
    ConfigRegionInnerDTO findByCityCode(@NotNull(message = "城市编码不能为空") @RequestParam String cityCode);
}
