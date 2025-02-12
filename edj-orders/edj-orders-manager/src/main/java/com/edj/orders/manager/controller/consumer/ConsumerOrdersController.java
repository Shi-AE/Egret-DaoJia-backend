package com.edj.orders.manager.controller.consumer;

import com.edj.orders.manager.domain.dto.OrdersPayDTO;
import com.edj.orders.manager.domain.dto.PlaceOrderDTO;
import com.edj.orders.manager.domain.vo.OrdersPayVO;
import com.edj.orders.manager.domain.vo.PlaceOrderVo;
import com.edj.orders.manager.service.EdjOrdersCreateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 下单
     */
    @Operation(summary = "下单")
    @PostMapping("place")
    @PreAuthorize("hasAuthority('consumer:orders:place')")
    public PlaceOrderVo place(@RequestBody @Validated PlaceOrderDTO placeOrderDTO) {
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
}
