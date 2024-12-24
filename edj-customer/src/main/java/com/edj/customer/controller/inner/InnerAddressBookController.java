package com.edj.customer.controller.inner;

import cn.hutool.core.bean.BeanUtil;
import com.edj.api.api.customer.AddressBookApi;
import com.edj.api.api.customer.dto.AddressBookVO;
import com.edj.customer.domain.entity.EdjAddressBook;
import com.edj.customer.service.EdjAddressBookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部接口 - 地址簿相关接口
 *
 * @author A.E.
 * @date 2024/12/24
 */
@Validated
@RestController
@RequestMapping("inner/address/book")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 地址簿相关接口")
public class InnerAddressBookController implements AddressBookApi {

    private final EdjAddressBookService addressBookService;

    /**
     * 地址薄详情
     */
    @Override
    @GetMapping("{id}")
    public AddressBookVO detail(@NotNull(message = "id不能为空") @PathVariable Long id) {
        EdjAddressBook addressBook = addressBookService.getById(id);
        return BeanUtil.toBean(addressBook, AddressBookVO.class);
    }
}
