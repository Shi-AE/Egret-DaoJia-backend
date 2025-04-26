package edj.orders.grab.controller.provider;

import edj.orders.grab.domain.dto.OrdersGrabListDTO;
import edj.orders.grab.domain.vo.OrdersGrabVO;
import edj.orders.grab.service.EdjOrdersGrabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    @Operation(summary = "查询服务端抢单列表")
    @PreAuthorize("hasAuthority('provider:ordersGarb:list')")
    public List<OrdersGrabVO> searchList(@RequestBody @Validated OrdersGrabListDTO ordersGrabListDTO) {
        return ordersGrabService.searchList(ordersGrabListDTO);
    }

    /**
     * 抢单
     */
    @PostMapping("{id}")
    @Operation(summary = "抢单")
    @PreAuthorize("hasAuthority('provider:ordersGarb:grab')")
    public void grab(@PathVariable Long id) {
        ordersGrabService.grab(id);
    }
}
