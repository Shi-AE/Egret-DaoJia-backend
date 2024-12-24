package com.edj.api.api.customer;

import com.edj.api.api.customer.dto.AddressBookVO;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 内部接口 - 地址簿相关接口
 *
 * @author A.E.
 * @date 2024/12/24
 */
@FeignClient(contextId = "AddressBookApi", name = "edj-customer", path = "inner/address/book")
public interface AddressBookApi {
    /**
     * 地址薄详情
     */
    @GetMapping("{id}")
    AddressBookVO detail(@NotNull(message = "id不能为空") @PathVariable Long id);
}
