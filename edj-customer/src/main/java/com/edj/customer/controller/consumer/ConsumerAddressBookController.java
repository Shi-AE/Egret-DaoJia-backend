package com.edj.customer.controller.consumer;

import com.edj.customer.domain.dto.AddressBookAddDTO;
import com.edj.customer.service.EdjAddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户端 - 地址薄相关接口
 *
 * @author A.E.
 * @date 2024/11/7
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("consumer/address/book")
@Tag(name = "用户端 - 地址薄相关接口")
public class ConsumerAddressBookController {

    private final EdjAddressBookService addressBookService;

    /**
     * 新增地址薄
     */
    @PostMapping
    @Operation(summary = "地址薄新增")
    @PreAuthorize("permitAll()")
    public void add(@RequestBody @Validated AddressBookAddDTO addressBookAddDTO) {
        addressBookService.add(addressBookAddDTO);
    }
}
