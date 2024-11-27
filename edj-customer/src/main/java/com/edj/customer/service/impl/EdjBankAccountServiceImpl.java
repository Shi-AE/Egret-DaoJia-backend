package com.edj.customer.service.impl;

import com.edj.common.utils.BeanUtils;
import com.edj.customer.domain.dto.BankAccountUpsertDTO;
import com.edj.customer.domain.entity.EdjBankAccount;
import com.edj.customer.mapper.EdjBankAccountMapper;
import com.edj.customer.service.EdjBankAccountService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_bank_account(银行账户)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
public class EdjBankAccountServiceImpl extends MPJBaseServiceImpl<EdjBankAccountMapper, EdjBankAccount> implements EdjBankAccountService {
    @Override
    public void upsert(BankAccountUpsertDTO bankAccountUpsertDTO) {
        EdjBankAccount bankAccount = BeanUtils.toBean(bankAccountUpsertDTO, EdjBankAccount.class);
        Long userId = SecurityUtils.getUserId();
        bankAccount.setId(userId);
        baseMapper.insertOrUpdate(bankAccount);
    }
}