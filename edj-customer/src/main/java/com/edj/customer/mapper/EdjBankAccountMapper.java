package com.edj.customer.mapper;

import com.edj.customer.domain.entity.EdjBankAccount;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_bank_account(银行账户)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Mapper
public interface EdjBankAccountMapper extends MPJBaseMapper<EdjBankAccount> {
}