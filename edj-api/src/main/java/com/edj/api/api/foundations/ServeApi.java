package com.edj.api.api.foundations;

import com.edj.api.api.foundations.dto.ServeAggregationDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 内部接口 - 服务相关接口
 *
 * @author A.E.
 * @date 2024/12/24
 */
@FeignClient(contextId = "ServeApi", name = "edj-foundations", path = "inner/foundations/serve")
public interface ServeApi {

    /**
     * 获取服务详细信息
     */
    @GetMapping("{id}")
    ServeAggregationDTO findServeDetailById(@NotNull(message = "id不能为空") @PathVariable Long id);
}
