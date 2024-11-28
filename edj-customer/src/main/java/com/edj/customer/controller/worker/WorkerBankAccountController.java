package com.edj.customer.controller.worker;

import cn.hutool.core.bean.BeanUtil;
import com.edj.customer.domain.dto.BankAccountUpsertDTO;
import com.edj.customer.domain.entity.EdjBankAccount;
import com.edj.customer.domain.vo.BankAccountVO;
import com.edj.customer.service.EdjBankAccountService;
import com.edj.security.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 服务端 - 银行账户信息相关接口
 *
 * @author A.E.
 * @date 2024/11/27
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("worker/bank/account")
@Tag(name = "服务端 - 银行账户信息相关接口")
public class WorkerBankAccountController {

    private final EdjBankAccountService bankAccountService;

    /**
     * 新增或更新银行账号信息
     */
    @PostMapping
    @Operation(summary = "新增或更新银行账号信息")
    @PreAuthorize("hasAuthority('worker:bankAccount:upsert')")
    public void upsert(@Validated @RequestBody BankAccountUpsertDTO bankAccountUpsertDTO) {
        bankAccountService.upsert(bankAccountUpsertDTO);
    }

    /**
     * 获取当前用户银行账号
     */
    @GetMapping
    @Operation(summary = "获取当前用银行账号信息")
    @PreAuthorize("hasAuthority('worker:bankAccount:get')")
    public BankAccountVO get() {
        EdjBankAccount bankAccount = bankAccountService.getById(SecurityUtils.getUserId());
        return BeanUtil.toBean(bankAccount, BankAccountVO.class);
    }
}
