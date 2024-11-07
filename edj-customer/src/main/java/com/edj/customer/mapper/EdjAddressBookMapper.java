package com.edj.customer.mapper;

import com.edj.customer.domain.entity.EdjAddressBook;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 针对表【edj_address_book(地址簿)】的数据库操作Mapper
*
* @author A.E.
* @date 2024/11/07
*/
@Mapper
public interface EdjAddressBookMapper extends MPJBaseMapper<EdjAddressBook> {
}