package com.edj.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.api.api.publics.MapApi;
import com.edj.api.api.publics.dto.LocationDTO;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.StringUtils;
import com.edj.customer.domain.dto.AddressBookAddDTO;
import com.edj.customer.domain.entity.EdjAddressBook;
import com.edj.customer.enums.EdjAddressBookIsDefault;
import com.edj.customer.mapper.EdjAddressBookMapper;
import com.edj.customer.service.EdjAddressBookService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 针对表【edj_address_book(地址簿)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/07
 */
@Service
@RequiredArgsConstructor
public class EdjAddressBookServiceImpl extends MPJBaseServiceImpl<EdjAddressBookMapper, EdjAddressBook> implements EdjAddressBookService {

    private final MapApi mapApi;

    @Override
    @Transactional
    public void add(AddressBookAddDTO addressBookAddDTO) {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();

        // 检查默认地址
        Integer isDefault = addressBookAddDTO.getIsDefault();
        if (EnumUtils.eq(EdjAddressBookIsDefault.DEFAULT, isDefault)) {
            // 清空其他默认
            clearDefault(userId);
        }

        // 如果请求体中没有经纬度，调用第三方api根据详细地址获取经纬度
        String location = addressBookAddDTO.getLocation();
        if (StringUtils.isBlank(location)) {
            // 组装详细地址
            String completeAddress = addressBookAddDTO.getProvince() +
                    addressBookAddDTO.getCity() +
                    addressBookAddDTO.getCounty() +
                    addressBookAddDTO.getAddress();
            // 远程请求高德获取经纬度
            LocationDTO locationDto = mapApi.getLocationByAddress(completeAddress);
            // 经纬度(字符串格式：经度,纬度),经度在前，纬度在后
            location = locationDto.getLocation();
        }

        EdjAddressBook addressBook = BeanUtils.toBean(addressBookAddDTO, EdjAddressBook.class);
        addressBook.setEdjUserId(userId);

        if (StringUtils.isBlank(location)) {
            throw new BadRequestException("地址错误");
        }

        String[] locationSplit = location.split(",");
        if (locationSplit.length != 2) {
            throw new ServerErrorException();
        }

        // 经度
        addressBook.setLon(new BigDecimal(locationSplit[0]));
        // 纬度
        addressBook.setLat(new BigDecimal(locationSplit[1]));

        baseMapper.insert(addressBook);
    }

    /**
     * 清空其他默认
     */
    private void clearDefault(Long userId) {
        LambdaUpdateWrapper<EdjAddressBook> clearDefaultWrapper = new LambdaUpdateWrapper<EdjAddressBook>()
                .eq(EdjAddressBook::getEdjUserId, userId)
                .eq(EdjAddressBook::getIsDefault, EdjAddressBookIsDefault.DEFAULT)
                .set(EdjAddressBook::getIsDefault, EdjAddressBookIsDefault.NOT_DEFAULT);
        baseMapper.update(new EdjAddressBook(), clearDefaultWrapper);
    }
}