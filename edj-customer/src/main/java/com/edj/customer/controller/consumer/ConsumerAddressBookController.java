package com.edj.customer.controller.consumer;

import cn.hutool.core.bean.BeanUtil;
import com.edj.customer.domain.dto.AddressBookAddDTO;
import com.edj.customer.domain.dto.AddressBookUpdateDTO;
import com.edj.customer.domain.entity.EdjAddressBook;
import com.edj.customer.domain.vo.AddressBookVO;
import com.edj.customer.service.EdjAddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 修改地址薄
     */
    @PutMapping("{id}")
    @Operation(summary = "修改地址薄")
    @PreAuthorize("permitAll()")
    public void update(
            @NotNull(message = "id不能为空") @PathVariable Long id,
            @RequestBody @Validated AddressBookUpdateDTO addressBookUpdateDTO
    ) {
        addressBookService.update(id, addressBookUpdateDTO);
    }

    /**
     * 地址薄详情
     */
    @GetMapping("{id}")
    @Operation(summary = "地址薄详情")
    public AddressBookVO detail(@NotNull(message = "id不能为空") @PathVariable Long id) {
        EdjAddressBook addressBook = addressBookService.getById(id);
        return BeanUtil.toBean(addressBook, AddressBookVO.class);
    }
}
