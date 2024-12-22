package com.edj.foundations.controller.open;

import com.edj.foundations.domain.vo.ProvinceStructVO;
import com.edj.foundations.service.EdjCityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 白名单接口 - 城市相关接口
 *
 * @author A.E.
 * @date 2024/10/21
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("open/city")
@Tag(name = "白名单接口 - 城市相关接口")
public class OpenCityController {

    private final EdjCityService cityService;

    /**
     * 查询城市结构列表
     */
    @GetMapping
    @Operation(summary = "查询城市结构列表")
    @PreAuthorize("permitAll()")
    public List<ProvinceStructVO> getCity() {
        return cityService.getStruct();
    }

    /**
     * 根据城市编号获取系统id
     */
    @GetMapping("id")
    @Operation(summary = "根据城市编号获取系统id")
    @PreAuthorize("permitAll()")
    public Integer getIdByCityCode(String CityCode) {
        return cityService.getIdByCityCode(CityCode);
    }
}
