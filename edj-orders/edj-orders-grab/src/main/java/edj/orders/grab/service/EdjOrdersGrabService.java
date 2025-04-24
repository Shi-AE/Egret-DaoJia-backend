package edj.orders.grab.service;

import com.edj.orders.base.domain.entity.EdjOrdersGrab;
import com.github.yulichang.base.MPJBaseService;
import edj.orders.grab.domain.dto.OrdersGrabListDTO;
import edj.orders.grab.domain.vo.OrdersGrabVO;

import java.util.List;

/**
 * 针对表【edj_orders_grab(抢单池)】的数据库操作Service
 *
 * @author A.E.
 * @date 2025/04/23
 */
public interface EdjOrdersGrabService extends MPJBaseService<EdjOrdersGrab> {

    /**
     * 查询服务端抢单列表
     */
    List<OrdersGrabVO> searchList(OrdersGrabListDTO ordersGrabListDTO);
}
