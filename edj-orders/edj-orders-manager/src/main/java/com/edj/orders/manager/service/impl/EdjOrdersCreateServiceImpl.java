package com.edj.orders.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.api.api.customer.AddressBookApi;
import com.edj.api.api.customer.dto.AddressBookVO;
import com.edj.api.api.foundations.ServeApi;
import com.edj.api.api.foundations.dto.ServeAggregationDTO;
import com.edj.api.api.trade.NativePayApi;
import com.edj.api.api.trade.dto.NativePayDTO;
import com.edj.api.api.trade.enums.TradingChannel;
import com.edj.api.api.trade.vo.NativePayVO;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.*;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.enums.EdjOrderPayStatus;
import com.edj.orders.base.enums.EdjOrderStatus;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.manager.domain.dto.OrdersPayDTO;
import com.edj.orders.manager.domain.dto.PlaceOrderDTO;
import com.edj.orders.manager.domain.vo.OrdersPayVO;
import com.edj.orders.manager.domain.vo.PlaceOrderVo;
import com.edj.orders.manager.porperties.TradeProperties;
import com.edj.orders.manager.service.EdjOrdersCreateService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.edj.common.constants.CommonRedisConstants.Generator.ORDERS_KEY_ID_GENERATOR;
import static com.edj.common.utils.DateUtils.DEFAULT_DATE_FORMAT_SIMPLE_COMPACT;
import static com.edj.orders.base.constants.OrderConstants.PRODUCT_APP_ID;

/**
 * 针对表【edj_orders(订单表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EdjOrdersCreateServiceImpl extends MPJBaseServiceImpl<EdjOrdersMapper, EdjOrders> implements EdjOrdersCreateService {

    private final TradeProperties tradeProperties;

    private final AddressBookApi addressBookApi;

    private final ServeApi serveApi;

    private final NativePayApi nativePayApi;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 生成订单id 格式：{yyMMdd}{13位id}
     */
    private Long generatorOrderId() {
        //通过Redis自增序列得到序号
        Long id = Objects.requireNonNull(redisTemplate.opsForValue().increment(ORDERS_KEY_ID_GENERATOR, 1));
        Long data = Objects.requireNonNull(DateUtils.getFormatDate(LocalDateTime.now(), DEFAULT_DATE_FORMAT_SIMPLE_COMPACT));
        return data * 10000000000000L + id;
    }

    @Override
    @Transactional
    public PlaceOrderVo placeOrder(PlaceOrderDTO placeOrderDTO) {
        // 获取地址信息
        Long addressBookId = placeOrderDTO.getAddressBookId();
        CompletableFuture<AddressBookVO> future1 = AsyncUtils.supplyAsync(() -> addressBookApi.detail(addressBookId));

        // 获取服务信息
        Long serveId = placeOrderDTO.getServeId();
        CompletableFuture<ServeAggregationDTO> future2 = AsyncUtils.supplyAsync(() -> serveApi.findServeDetailById(serveId));

        AddressBookVO addressBook = future1.join();
        ServeAggregationDTO serveDetail = future2.join();
        log.debug("addressBook: {}", addressBook);
        log.debug("serveDetail: {}", serveDetail);

        EdjOrders orders = new EdjOrders();
        // 订单id
        Long id = generatorOrderId();
        orders.setId(id);
        // 下单人id
        orders.setEdjUserId(SecurityUtils.getUserId());
        // 基础信息
        orders.setEdjServeTypeId(serveDetail.getServeTypeId());
        orders.setEdjServeItemId(serveDetail.getServeItemId());
        orders.setEdjServeId(serveDetail.getId());
        orders.setServeTypeName(serveDetail.getServeTypeName());
        orders.setServeItemName(serveDetail.getServeItemName());
        orders.setServeItemImg(serveDetail.getServeItemImg());
        orders.setUnit(serveDetail.getUnit());
        // 价格信息
        BigDecimal price = serveDetail.getPrice();
        orders.setPrice(price);
        Integer purNum = NumberUtils.null2Default(placeOrderDTO.getPurNum(), 1);
        orders.setPurNum(purNum);
        BigDecimal totalAmount = price.multiply(new BigDecimal(purNum));
        orders.setTotalAmount(totalAmount);
        BigDecimal discountAmount = BigDecimal.ZERO;
        orders.setDiscountAmount(discountAmount);
        BigDecimal realPayAmount = totalAmount.subtract(discountAmount);
        orders.setRealPayAmount(realPayAmount);
        // 用户信息
        orders.setCityCode(serveDetail.getCityCode());
        orders.setServeAddress(
                addressBook.getProvince() +
                        addressBook.getCity() +
                        addressBook.getCounty() +
                        addressBook.getAddress()
        );
        orders.setContactsPhone(addressBook.getPhone());
        orders.setContactsName(addressBook.getName());
        orders.setLon(addressBook.getLon());
        orders.setLat(addressBook.getLat());
        // 服务时间
        LocalDateTime serveStartTime = placeOrderDTO.getServeStartTime();
        orders.setServeStartTime(serveStartTime);
        // 状态信息
        orders.setOrdersStatus(EnumUtils.value(EdjOrderStatus.PENDING_PAYMENT, Integer.class));
        orders.setPayStatus(EnumUtils.value(EdjOrderPayStatus.PENDING, Integer.class));
        // 排序字段
        orders.setSortBy(DateUtils.toEpochMilli(serveStartTime) + orders.getId() % 100000);

        int insert = baseMapper.insert(orders);
        if (insert != 1) {
            throw new ServerErrorException("下单失败");
        }

        return PlaceOrderVo.builder().id(id).build();
    }

    @Override
    public OrdersPayVO pay(OrdersPayDTO ordersPayDTO) {
        EdjOrders orders = baseMapper.selectById(ordersPayDTO.getId());
        if (ObjectUtils.isNull(orders)) {
            throw new BadRequestException("订单不存在");
        }

        // 订单支付状态
        Integer payStatus = orders.getPayStatus();
        if (EnumUtils.eq(EdjOrderPayStatus.SUCCESS, payStatus)) {
            OrdersPayVO ordersPayResDTO = new OrdersPayVO();
            BeanUtil.copyProperties(orders, ordersPayResDTO);
            ordersPayResDTO.setProductOrderNo(orders.getId());
            return ordersPayResDTO;
        } else {
            // 调用支付接口生成二维码
            // 判断支付渠道
            TradingChannel tradingChannel = ordersPayDTO.getTradingChannel();
            Long enterpriseId = EnumUtils.eq(TradingChannel.ALI_PAY, tradingChannel) ?
                    tradeProperties.getAliEnterpriseId() : tradeProperties.getWechatEnterpriseId();
            if (ObjectUtils.isNull(enterpriseId)) {
                log.error("商户号缺失");
                throw new ServerErrorException();
            }

            // 构建支付请求参数
            NativePayDTO nativePayDTO = NativePayDTO
                    .builder()
                    .productOrderNo(orders.getId())
                    .tradingChannel(tradingChannel)
                    .tradingAmount(orders.getRealPayAmount())
                    .enterpriseId(enterpriseId)
                    .productAppId(PRODUCT_APP_ID)
                    .build();

            // 判断是否切换支付渠道
            if (ObjectUtils.isNotEmpty(orders.getTradingChannel()) &&
                    ObjectUtils.notEqual(orders.getTradingChannel(), tradingChannel.toString())
            ) {
                nativePayDTO.setChangeChannel(true);
            }

            // 生成支付二维码
            NativePayVO downLineTrading = nativePayApi.createDownLineTrading(nativePayDTO);

            //将交易单信息更新到订单中
            LambdaUpdateWrapper<EdjOrders> wrapper = new LambdaUpdateWrapper<EdjOrders>()
                    .eq(EdjOrders::getId, orders.getId())
                    .set(EdjOrders::getTradingOrderNo, downLineTrading.getTradingOrderNo())
                    .set(EdjOrders::getTradingChannel, downLineTrading.getTradingChannel());
            baseMapper.update(new EdjOrders(), wrapper);

            return BeanUtil.toBean(downLineTrading, OrdersPayVO.class);
        }
    }
}