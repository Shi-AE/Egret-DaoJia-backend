package com.edj.user.service;

import com.edj.user.domain.dto.InstitutionRegisterDTO;

/**
 * 注册服务
 *
 * @author A.E.
 * @date 2024/11/1
 */
public interface RegisterService {

    /**
     * 机构端注册
     */
    void institutionRegister(InstitutionRegisterDTO institutionRegisterDTO);
}
