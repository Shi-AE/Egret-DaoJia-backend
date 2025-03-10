package com.edj.market.job;

import com.edj.market.service.EdjActivityService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 优惠券服务定时任务
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MarketJob {

    private final EdjActivityService activityService;

    /**
     * 优惠券活动状态自动更新
     * 每1分钟执行一次
     * 1. 到达发放开始时间状态改为“进行中”
     * 2. 到达发放结束时间状态改为“已失效”
     */
    @XxlJob("UpdateActivityStatus")
    public void updateActivityStatus() {
        log.info(">>>>>>>> 开始优惠券活动状态自动更新");

        try {
            activityService.updateStatus();
            log.info(">>>>>>>> 完成优惠券活动状态自动更新");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 优惠券活动预热
     */
    @XxlJob("ActivityPerHeat")
    public void activityPerHeat() {
        log.info(">>>>>>>> 开始优惠券活动预热");

        try {
            activityService.perHeat();
            log.info(">>>>>>>> 完成优惠券活动预热");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 优惠券库存预热
     */
    @XxlJob("ActivityStockPerHeat")
    public void activityStockPerHeat() {
        log.info(">>>>>>>> 开始优惠券库存预热");

        try {
            activityService.stockPerHeat();
            log.info(">>>>>>>> 完成优惠券库存预热");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
