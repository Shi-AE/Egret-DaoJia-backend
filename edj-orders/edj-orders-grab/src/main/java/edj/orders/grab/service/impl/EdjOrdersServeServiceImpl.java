package edj.orders.grab.service.impl;

import com.edj.orders.base.domain.entity.EdjOrdersServe;
import com.edj.orders.base.mapper.EdjOrdersServeMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import edj.orders.grab.service.EdjOrdersServeService;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_orders_serve(订单服务表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2025/04/26
 */
@Service
public class EdjOrdersServeServiceImpl extends MPJBaseServiceImpl<EdjOrdersServeMapper, EdjOrdersServe> implements EdjOrdersServeService {
}