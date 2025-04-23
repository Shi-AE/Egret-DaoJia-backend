package com.edj.foundations.controller.inner;

import com.edj.api.api.foundations.ConfigRegionApi;
import com.edj.api.api.foundations.dto.ConfigRegionInnerDTO;
import com.edj.common.utils.BeanUtils;
import com.edj.foundations.domain.entity.EdjConfigRegion;
import com.edj.foundations.service.EdjConfigRegionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部接口 - 区域业务配置相关接口
 *
 * @author A.E.
 * @date 2025/4/23
 */
@Validated
@RestController
@RequestMapping("inner/foundations/config/region")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 区域业务配置相关接口")
public class InnerConfigRegionController implements ConfigRegionApi {

    private final EdjConfigRegionService configRegionService;

    /**
     * 根据城市编码获取区域配置
     */
    @Override
    @GetMapping
    public ConfigRegionInnerDTO findByCityCode(String cityCode) {
        EdjConfigRegion configRegion = configRegionService.findByCityCode(cityCode);
        return BeanUtils.toBean(configRegion, ConfigRegionInnerDTO.class);
    }
}
