package com.edj.orders.manager.controller.agency;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.mysql.utils.PageUtils;
import com.edj.orders.base.domain.entity.EdjOrdersServe;
import com.edj.orders.base.mapper.EdjOrdersServeMapper;
import com.edj.security.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机构端 - 服务单相关接口 todo 演示模拟数据
 *
 * @author A.E.
 * @date 2025/5/17
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "机构端 - 服务单相关接口")
@RequestMapping("agency/orders")
public class AgencyOrdersServeController {

    private final EdjOrdersServeMapper ordersServeMapper;

    @GetMapping("page")
    @Operation(summary = "服务单分页列表，提供给机构端")
    public PageResult<EdjOrdersServe> queryForPage(PageQueryDTO pageQueryDTO) {
        Page<EdjOrdersServe> page = PageUtils.parsePageQuery(pageQueryDTO);
        LambdaQueryWrapper<EdjOrdersServe> wrapper = new LambdaQueryWrapper<EdjOrdersServe>()
                .eq(EdjOrdersServe::getEdjServeProvidersId, SecurityUtils.getUserId());
        Page<EdjOrdersServe> res = ordersServeMapper.selectPage(page, wrapper);
        return PageUtils.toPage(res, EdjOrdersServe.class);
    }
}
