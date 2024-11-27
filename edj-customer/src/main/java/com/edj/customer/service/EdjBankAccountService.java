package com.edj.customer.service;

import com.edj.customer.domain.dto.BankAccountUpsertDTO;
import com.edj.customer.domain.entity.EdjBankAccount;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_bank_account(银行账户)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/11/13
 */
public interface EdjBankAccountService extends MPJBaseService<EdjBankAccount> {

    /**
     * 新增或更新银行账号信息
     */
    void upsert(BankAccountUpsertDTO bankAccountUpsertDTO);
}
