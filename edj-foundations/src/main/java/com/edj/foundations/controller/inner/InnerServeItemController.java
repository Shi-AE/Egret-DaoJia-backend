package com.edj.foundations.controller.inner;

import com.edj.api.api.foundations.ServeItemApi;
import com.edj.api.api.foundations.dto.ServeTypeCategoryDTO;
import com.edj.foundations.service.ConsumerHomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 内部接口 - 服务项相关接口
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Validated
@RestController
@RequestMapping("inner/foundations/serve/item")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 服务项相关接口")
public class InnerServeItemController implements ServeItemApi {

    private final ConsumerHomeService consumerHomeService;

    /**
     * 查询启用状态的服务项目录
     *
     * @return 服务项目录
     */
    @Override
    @GetMapping("active/category")
    public List<ServeTypeCategoryDTO> getActiveServeItemCategory() {
        return consumerHomeService.getActiveServeItemCategory();
    }
}
