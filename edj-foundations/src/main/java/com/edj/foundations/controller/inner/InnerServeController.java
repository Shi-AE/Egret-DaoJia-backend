package com.edj.foundations.controller.inner;

import com.edj.api.api.foundations.ServeApi;
import com.edj.api.api.foundations.dto.ServeAggregationDTO;
import com.edj.foundations.service.EdjServeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部接口 - 服务项相关接口
 *
 * @author A.E.
 * @date 2024/12/24
 */
@Validated
@RestController
@RequestMapping("inner/foundations/serve")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 服务项相关接口")
public class InnerServeController implements ServeApi {

    private final EdjServeService serveService;

    /**
     * 获取服务详细信息
     */
    @Override
    @GetMapping("{id}")
    public ServeAggregationDTO findServeDetailById(@NotNull(message = "id不能为空") @PathVariable Long id) {
        return serveService.findServeDetailById(id);
    }
}
