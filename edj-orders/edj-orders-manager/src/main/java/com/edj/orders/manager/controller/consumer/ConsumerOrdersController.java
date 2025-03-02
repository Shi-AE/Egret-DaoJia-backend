package com.edj.orders.manager.controller.consumer;

import com.edj.mvc.annotation.enums.Enums;
import com.edj.orders.base.enums.EdjOrderStatus;
import com.edj.orders.manager.domain.dto.OrdersPayDTO;
import com.edj.orders.manager.domain.dto.PlaceOrderDTO;
import com.edj.orders.manager.domain.vo.OrdersDetailVO;
import com.edj.orders.manager.domain.vo.OrdersPayVO;
import com.edj.orders.manager.domain.vo.OrdersSimpleVO;
import com.edj.orders.manager.domain.vo.PlaceOrderVO;
import com.edj.orders.manager.service.EdjOrdersCreateService;
import com.edj.orders.manager.service.EdjOrdersManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户端 - 订单相关接口
 *
 * @author A.E.
 * @date 2024/12/24
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "用户端 - 订单相关接口")
@RequestMapping("consumer/orders")
public class ConsumerOrdersController {

    private final EdjOrdersCreateService ordersCreateService;

    private final EdjOrdersManagerService ordersManagerService;

    /**
     * 订单查询
     */
    @GetMapping("list")
    @Operation(summary = "订单查询")
    @PreAuthorize("hasAuthority('consumer:orders:list')")
    public List<OrdersSimpleVO> list(
            @RequestParam(required = false) @Enums(EdjOrderStatus.class) @Schema(description = "订单状态") Integer ordersStatus,
            @RequestParam(required = false) @PositiveOrZero @Schema(description = "上一组数据最后一个订单序号") Long sortBy
    ) {
        return ordersManagerService.list(ordersStatus, sortBy);
    }

    /**
     * 下单
     */
    @PostMapping("place")
    @Operation(summary = "下单")
    @PreAuthorize("hasAuthority('consumer:orders:place')")
    public PlaceOrderVO place(@RequestBody @Validated PlaceOrderDTO placeOrderDTO) {
        return ordersCreateService.placeOrder(placeOrderDTO);
    }

    /**
     * 订单支付
     */
    @PutMapping("pay")
    @Operation(summary = "订单支付")
    @PreAuthorize("hasAuthority('consumer:orders:pay')")
    public OrdersPayVO pay(@RequestBody @Validated OrdersPayDTO ordersPayDTO) {
        return ordersCreateService.pay(ordersPayDTO);
    }

    /**
     * 查询订单支付结果
     */
    @GetMapping("/pay/{id}/result")
    @Operation(summary = "查询订单支付结果")
    @PreAuthorize("hasAuthority('consumer:orders:payResult')")
    public OrdersPayVO payResult(@PathVariable Long id) {
        // 查询支付结果
        int payStatus = ordersCreateService.getPayResult(id);
        return OrdersPayVO.builder()
                .payStatus(payStatus)
                .build();
    }

    /**
     * 根据id查询订单详细信息
     */
    @GetMapping("{id}")
    @Operation(summary = "根据id查询订单详细信息")
    @PreAuthorize("hasAuthority('consumer:orders:detail')")
    public OrdersDetailVO detail(@PathVariable Long id) {
        return ordersManagerService.detail(id);
    }
}
