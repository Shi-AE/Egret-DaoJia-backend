package com.edj.api.api.foundations;

import com.edj.api.api.foundations.dto.ServeTypeCategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 内部接口 - 服务项相关接口
 *
 * @author A.E.
 * @date 2024/11/14
 */
@FeignClient(contextId = "ServeItemApi", name = "edj-foundations", path = "inner/foundations/serve/item")
public interface ServeItemApi {

    /**
     * 查询启用状态的服务项目录
     *
     * @return 服务项目录
     */
    @GetMapping("active/category")
    List<ServeTypeCategoryDTO> getActiveServeItemCategory();
}
