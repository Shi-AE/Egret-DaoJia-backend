package edj.orders.grab.handler;

import com.xxl.job.core.handler.annotation.XxlJob;
import edj.orders.grab.service.EdjOrdersGrabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 抢单定时任务
 *
 * @author A.E.
 * @date 2025/4/26
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GrabJobHandler {

    private final EdjOrdersGrabService ordersGrabService;

    /**
     * 同步抢单结果
     * 每 10 秒执行一次
     */
    @XxlJob("GrabOrdersSync")
    public void GrabOrdersSync() {
        log.info(">>>>>>>> 开始同步抢券结果");
        ordersGrabService.GrabOrdersSync();
        log.info(">>>>>>>> 完成同步抢券结果");
    }
}
