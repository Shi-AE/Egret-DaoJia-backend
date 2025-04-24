package edj.orders.grab.controller.provider;

import edj.orders.grab.domain.dto.OrdersGrabListDTO;
import edj.orders.grab.domain.vo.OrdersGrabVO;
import edj.orders.grab.service.EdjOrdersGrabService;
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
 * 提供者端 - 抢单相关接口
 *
 * @author A.E.
 * @date 2025/4/24
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "提供者端 - 抢单相关接口")
@RequestMapping("provider/orders/garb")
public class ProviderOrdersGarbController {

    private final EdjOrdersGrabService ordersGrabService;

    /**
     * 查询服务端抢单列表
     */
    @GetMapping
    @Operation(summary = "查询服务端抢单列表")
    @PreAuthorize("hasAuthority('provider:ordersGarb:list')")
    public List<OrdersGrabVO> searchList(OrdersGrabListDTO ordersGrabListDTO) {
        return ordersGrabService.searchList(ordersGrabListDTO);
    }
}
