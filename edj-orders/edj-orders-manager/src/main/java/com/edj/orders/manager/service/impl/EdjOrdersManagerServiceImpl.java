package com.edj.orders.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.cache.helper.CacheHelper;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.*;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.domain.entity.EdjOrdersCanceled;
import com.edj.orders.base.enums.EdjOrderDisplay;
import com.edj.orders.base.enums.EdjOrderStatus;
import com.edj.orders.base.mapper.EdjOrdersCanceledMapper;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.manager.domain.dto.OrdersCancelDTO;
import com.edj.orders.manager.domain.vo.OrdersDetailVO;
import com.edj.orders.manager.domain.vo.OrdersSimpleVO;
import com.edj.orders.manager.service.EdjOrdersManagerService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.edj.cache.constants.CacheConstants.CacheName.USER_ORDERS_CACHE;
import static com.edj.cache.constants.CacheConstants.Ttl.MEDIUM_TERM;

/**
 * 针对表【edj_orders(订单表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/23
 */
@Service
@RequiredArgsConstructor
public class EdjOrdersManagerServiceImpl extends MPJBaseServiceImpl<EdjOrdersMapper, EdjOrders> implements EdjOrdersManagerService {

    private final CacheHelper cacheHelper;

    private final EdjOrdersCanceledMapper ordersCanceledMapper;

    @Override
    public List<EdjOrders> selectByIdList(List<Long> idList) {

        if (CollUtils.isEmpty(idList)) {
            return new ArrayList<>();
        }

        LambdaQueryWrapper<EdjOrders> wrapper = new LambdaQueryWrapper<EdjOrders>()
                .in(EdjOrders::getId, idList);

        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<OrdersSimpleVO> list(Integer ordersStatus, Long sortBy) {

        // 获取用户
        Long userId = SecurityUtils.getUserId();

        // 构造条件
        LambdaQueryWrapper<EdjOrders> wrapper = new LambdaQueryWrapper<EdjOrders>()
                .select(EdjOrders::getId)
                .eq(ObjectUtils.isNotNull(ordersStatus), EdjOrders::getOrdersStatus, ordersStatus)
                .lt(ObjectUtils.isNotNull(sortBy), EdjOrders::getSortBy, sortBy)
                .eq(EdjOrders::getEdjUserId, userId)
                .eq(EdjOrders::getDisplay, EdjOrderDisplay.DISPLAY);
        Page<EdjOrders> page = new Page<>();
        // 根据id倒排
        page.addOrder(OrderItem.desc(IdUtils.ID));
        // 关闭总数统计
        page.setSearchCount(false);

        // 查询订单id列表
        List<EdjOrders> ordersList = baseMapper.selectPage(page, wrapper).getRecords();
        if (ObjectUtils.isEmpty(ordersList)) {
            return new ArrayList<>();
        }

        // 提取订单id列表
        List<Long> ordersIdList = ordersList.stream().map(EdjOrders::getId).toList();

        // 缓存并获取数据
        return cacheHelper.batchGet(String.format(USER_ORDERS_CACHE, userId), ordersIdList, (objectIds, clazz) -> {
            List<EdjOrders> ordersCacheList = selectByIdList(objectIds);
            if (CollUtils.isEmpty(ordersCacheList)) {
                return new HashMap<>();
            }

            return ordersCacheList
                    .stream()
                    .collect(Collectors.toMap(
                            EdjOrders::getId,
                            o -> BeanUtil.toBean(o, OrdersSimpleVO.class)
                    ));
        }, OrdersSimpleVO.class, MEDIUM_TERM);
    }

    @Override
    public OrdersDetailVO detail(Long id) {
        LambdaQueryWrapper<EdjOrders> wrapper = new LambdaQueryWrapper<EdjOrders>()
                .eq(EdjOrders::getId, id)
                .eq(EdjOrders::getEdjUserId, SecurityUtils.getUserId());

        EdjOrders orders = baseMapper.selectOne(wrapper);

        if (orders == null) {
            throw new BadRequestException("订单不存在");
        }

        OrdersDetailVO detailVO = BeanUtils.toBean(orders, OrdersDetailVO.class);
        detailVO.setServeTypeId(orders.getEdjServeTypeId());
        detailVO.setServeItemId(orders.getEdjServeItemId());
        detailVO.setServeId(orders.getEdjServeId());

        // todo 派单后服务人信息

        return detailVO;
    }

    @Override
    public void cancel(OrdersCancelDTO ordersCancelDTO) {
        // 订单id
        Long id = ordersCancelDTO.getId();
        Long currentUserId = ordersCancelDTO.getCurrentUserId();

        LambdaQueryWrapper<EdjOrders> wrapper = new LambdaQueryWrapper<EdjOrders>()
                .select(EdjOrders::getId, EdjOrders::getOrdersStatus)
                .eq(EdjOrders::getId, id)
                .eq(EdjOrders::getEdjUserId, currentUserId);
        EdjOrders orders = baseMapper.selectOne(wrapper);

        if (ObjectUtils.isNull(orders)) {
            throw new BadRequestException("订单不存在");
        }

        // 订单状态
        Integer ordersStatus = orders.getOrdersStatus();

        if (EnumUtils.eq(EdjOrderStatus.PENDING_PAYMENT, ordersStatus)) {
            // 待支付订单取消操作
            cancelForPendingPayment(ordersCancelDTO);
        } else if (EnumUtils.eq(EdjOrderStatus.DISPATCHING, ordersStatus)) {
            // 已付款订单取消操作
            cancelForDispatching(ordersCancelDTO);
        } else {
            throw new BadRequestException("当前状态订单不支持取消");
        }

        // todo 删除此订单的缓存
    }

    public void cancelForPendingPayment(OrdersCancelDTO ordersCancelDTO) {
        // 添加订单取消记录
        EdjOrdersCanceled ordersCanceled = EdjOrdersCanceled.builder()
                .cancellerId(ordersCancelDTO.getCurrentUserId())
                .cancellerName(ordersCancelDTO.getCurrentUserName())
                .cancelReason(ordersCancelDTO.getCancelReason())
                .cancelTime(LocalDateTime.now())
                .build();
        int insert = ordersCanceledMapper.insert(ordersCanceled);
        if (insert != 1) {
            throw new ServerErrorException("添加订单取消记录失败");
        }

        // 修改订单状态
        LambdaUpdateWrapper<EdjOrders> wrapper = new LambdaUpdateWrapper<EdjOrders>()
                .eq(EdjOrders::getId, ordersCancelDTO.getId())
                .eq(EdjOrders::getOrdersStatus, EdjOrderStatus.PENDING_PAYMENT)
                .set(EdjOrders::getOrdersStatus, EdjOrderStatus.CANCELLED);
        int update = baseMapper.update(new EdjOrders(), wrapper);
        if (update != 1) {
            throw new ServerErrorException("订单取消失败");
        }
    }

    public void cancelForDispatching(OrdersCancelDTO ordersCancelDTO) {
    }
}