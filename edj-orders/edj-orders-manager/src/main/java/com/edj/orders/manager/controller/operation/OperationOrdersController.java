package com.edj.orders.manager.controller.operation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.mysql.utils.PageUtils;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.manager.domain.vo.OrdersDetailVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端 - 订单相关接口 todo 演示模拟数据
 *
 * @author A.E.
 * @date 2025/5/16
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "管理端 - 订单相关接口")
@RequestMapping("operation/orders")
public class OperationOrdersController {

    private final EdjOrdersMapper ordersMapper;

    /**
     * 订单分页查询
     */
    @GetMapping("page")
    @Operation(summary = "订单分页查询")
    public PageResult<OrdersDetailVO> page(PageQueryDTO pageQueryDTO) {
        Page<EdjOrders> page = PageUtils.parsePageQuery(pageQueryDTO);
        Page<EdjOrders> ordersPage = ordersMapper.selectPage(page, new LambdaQueryWrapper<>());
        return PageUtils.toPage(ordersPage, OrdersDetailVO.class);
    }
}
