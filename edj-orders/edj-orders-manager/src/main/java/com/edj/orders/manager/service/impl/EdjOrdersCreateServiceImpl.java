package com.edj.orders.manager.service.impl;

import com.edj.api.api.customer.AddressBookApi;
import com.edj.api.api.customer.dto.AddressBookVO;
import com.edj.api.api.foundations.ServeApi;
import com.edj.api.api.foundations.dto.ServeAggregationDTO;
import com.edj.common.utils.DateUtils;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.manager.domain.dto.PlaceOrderDTO;
import com.edj.orders.manager.domain.vo.PlaceOrderVo;
import com.edj.orders.manager.service.EdjOrdersCreateService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.edj.common.constants.CommonRedisConstants.Generator.ORDERS_KEY_ID_GENERATOR;
import static com.edj.common.utils.DateUtils.DEFAULT_DATE_FORMAT_SIMPLE_COMPACT;

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

    private final AddressBookApi addressBookApi;

    private final ServeApi serveApi;

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
    public PlaceOrderVo placeOrder(PlaceOrderDTO placeOrderDTO) {
        // 获取地址信息
        Long addressBookId = placeOrderDTO.getAddressBookId();
        AddressBookVO addressBook = addressBookApi.detail(addressBookId);
        log.debug("addressBook: {}", addressBook);
        // 获取服务信息
        Long serveId = placeOrderDTO.getServeId();
        ServeAggregationDTO serveDetail = serveApi.findServeDetailById(serveId);
        log.debug("serveDetail: {}", serveDetail);
        return null;
    }
}