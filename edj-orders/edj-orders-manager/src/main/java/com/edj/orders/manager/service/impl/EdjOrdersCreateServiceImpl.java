package com.edj.orders.manager.service.impl;

import com.edj.api.api.customer.AddressBookApi;
import com.edj.api.api.customer.dto.AddressBookVO;
import com.edj.api.api.foundations.ServeApi;
import com.edj.api.api.foundations.dto.ServeAggregationDTO;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.manager.domain.dto.PlaceOrderDTO;
import com.edj.orders.manager.domain.vo.PlaceOrderVo;
import com.edj.orders.manager.service.EdjOrdersCreateService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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