package com.edj.customer.controller.consumer;

import cn.hutool.core.bean.BeanUtil;
import com.edj.customer.domain.dto.AddressBookAddDTO;
import com.edj.customer.domain.dto.AddressBookUpdateDTO;
import com.edj.customer.domain.entity.EdjAddressBook;
import com.edj.customer.domain.vo.AddressBookVO;
import com.edj.customer.enums.EdjAddressBookIsDefault;
import com.edj.customer.service.EdjAddressBookService;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("permitAll()")
    public AddressBookVO detail(@NotNull(message = "id不能为空") @PathVariable Long id) {
        EdjAddressBook addressBook = addressBookService.getById(id);
        return BeanUtil.toBean(addressBook, AddressBookVO.class);
    }

    /**
     * 地址薄设为默认/取消默认
     */
    @PutMapping("default")
    @Operation(summary = "地址薄设为默认/取消默认")
    @PreAuthorize("permitAll()")
    public void updateDefaultStatus(
            @Schema(description = "地址簿id")
            @NotNull(message = "id不能为空")
            @RequestParam
            Long id,
            @Schema(description = "是否为默认地址（0否 1是）")
            @NotNull(message = "状态值不能为空")
            @Enums(EdjAddressBookIsDefault.class)
            @RequestParam
            Integer flag
    ) {
        addressBookService.updateDefaultStatus(id, flag);
    }

    /**
     * 批量删除地址薄
     */
    @DeleteMapping("batch")
    @Operation(summary = "批量删除地址薄")
    @PreAuthorize("permitAll()")
    public void batchDelete(
            @RequestBody
            @Validated
            @Schema(description = "地址簿id列表")
            @NotNull(message = "地址簿id列表不能为空")
            @NotEmpty(message = "地址簿id列表不能为空")
            List<@Positive @NotNull Long> idList
    ) {
        addressBookService.batchDelete(idList);
    }
}
