package com.edj.orders.manager.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.api.api.trade.RefundApi;
import com.edj.api.api.trade.vo.RefundResultVO;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.common.utils.StringUtils;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.domain.entity.EdjOrdersRefund;
import com.edj.orders.base.enums.EdjOrderRefundStatus;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.base.mapper.EdjOrdersRefundMapper;
import com.edj.orders.manager.domain.dto.OrdersCancelDTO;
import com.edj.orders.manager.properties.OrdersJobProperties;
import com.edj.orders.manager.service.EdjOrdersCreateService;
import com.edj.orders.manager.service.EdjOrdersManagerService;
import com.edj.orders.manager.service.EdjOrdersRefundService;
import com.edj.security.utils.SecurityUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单定时任务
 *
 * @author A.E.
 * @date 2025/3/3
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrdersHandler {

    private final OrdersJobProperties ordersJobProperties;

    private final EdjOrdersCreateService ordersCreateService;

    private final EdjOrdersManagerService ordersManagerService;

    private final EdjOrdersRefundService ordersRefundService;

    private final RefundApi refundApi;

    private final EdjOrdersMapper ordersMapper;

    private final EdjOrdersRefundMapper ordersRefundMapper;

    /**
     * 取消支付超时订单
     * 每一分钟执行一次
     */
    @XxlJob("CancelOverTimePayOrder")
    public void cancelOverTimePayOrder() {

        log.info(">>>>>>>> 开始处理支付超时订单");

        // 获取超时订单
        Integer overTimePayOrderCount = ordersJobProperties.getOverTimePayOrderCount();
        List<Long> ordersIdList = ordersCreateService.selectOverTimePayOrdersListByCount(overTimePayOrderCount);

        ordersIdList.stream()
                .map(id -> OrdersCancelDTO.builder()
                        .id(id)
                        .currentUserId(SecurityUtils.SYSTEM_USER_ID)
                        .currentUserName(SecurityUtils.SYSTEM_USER_NAME)
                        .cancelReason("订单超时支付，自动取消")
                        .build()
                )
                .forEach(ordersManagerService::cancelForPendingPayment);

        log.info(">>>>>>>> 处理完成 {} 条支付超时订单", ordersIdList.size());
    }

    /**
     * 处理订单退款
     * 每10分钟执行一次
     */
    @XxlJob("HandleRefundOrders")
    public void handleRefundOrders() {

        log.info(">>>>>>>> 开始处理订单退款");

        // 查询退款记录
        Integer refundOrderCount = ordersJobProperties.getRefundOrderCount();
        List<EdjOrdersRefund> ordersRefundList = ordersRefundService.selectRefundOrderListByCount(refundOrderCount);

        if (CollUtils.isEmpty(ordersRefundList)) {
            log.info(">>>>>>>> 完成处理订单退款");
            return;
        }

        // 请求支付服务发起退款
        for (EdjOrdersRefund ordersRefund : ordersRefundList) {
            requestRefundOrder(ordersRefund);
        }

        log.info(">>>>>>>> 处理完成 {} 条订单退款", ordersRefundList.size());
    }

    /**
     * 请求发起退款
     */
    public void requestRefundOrder(EdjOrdersRefund ordersRefund) {
        try {
            RefundResultVO refundResultVO = refundApi.refundTrading(ordersRefund.getTradingOrderNo(), ordersRefund.getRealPayAmount());
            SpringUtil.getBean(OrdersHandler.class)
                    .refundOrder(ordersRefund, refundResultVO);
        } catch (Exception e) {
            log.error("请求发起退款: {}", e.getMessage(), e);
        }
    }

    /**
     * 更新退款状态
     * 保证两张表退款状态枚举值相同
     */
    @Transactional
    public void refundOrder(EdjOrdersRefund ordersRefund, RefundResultVO refundResultVO) {
        Integer refundStatus = refundResultVO.getRefundStatus();
        if (EnumUtils.eq(EdjOrderRefundStatus.REFUNDING, refundStatus)) {
            // 退款中不做更改
            return;
        }

        Long ordersRefundId = ordersRefund.getId();
        String refundId = refundResultVO.getRefundId();
        Long refundNo = refundResultVO.getRefundNo();
        LambdaUpdateWrapper<EdjOrders> wrapper = new LambdaUpdateWrapper<EdjOrders>()
                .eq(EdjOrders::getId, ordersRefundId)
                .ne(EdjOrders::getRefundStatus, refundStatus)
                .set(EdjOrders::getRefundStatus, refundStatus)
                .set(StringUtils.isNotBlank(refundId), EdjOrders::getRefundId, refundId)
                .set(ObjectUtils.isNotNull(refundNo), EdjOrders::getRefundNo, refundNo);

        int update = ordersMapper.update(new EdjOrders(), wrapper);

        if (update == 1) {
            // 订单状态修改成功，删除退款记录
            ordersRefundMapper.deleteById(ordersRefundId);
        }
    }
}
