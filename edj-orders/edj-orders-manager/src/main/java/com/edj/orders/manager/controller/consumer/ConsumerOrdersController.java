package com.edj.orders.manager.controller.consumer;

import com.edj.orders.manager.domain.dto.PlaceOrderDTO;
import com.edj.orders.manager.domain.vo.PlaceOrderVo;
import com.edj.orders.manager.service.EdjOrdersCreateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
