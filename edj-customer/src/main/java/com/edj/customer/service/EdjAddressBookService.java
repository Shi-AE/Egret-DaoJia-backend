package com.edj.customer.service;

import com.edj.customer.domain.dto.AddressBookAddDTO;
import com.edj.customer.domain.dto.AddressBookUpdateDTO;
import com.edj.customer.domain.entity.EdjAddressBook;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_address_book(地址簿)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/11/07
 */
public interface EdjAddressBookService extends MPJBaseService<EdjAddressBook> {

    /**
     * 地址薄新增
     */
    void add(AddressBookAddDTO addressBookAddDTO);

    /**
     * 修改地址薄
     */
    void update(Long id, AddressBookUpdateDTO addressBookUpdateDTO);
}
