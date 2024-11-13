package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjInstitutionStaff;
import com.edj.customer.mapper.EdjInstitutionStaffMapper;
import com.edj.customer.service.EdjInstitutionStaffService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_institution_staff(机构下属服务人员)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
public class EdjInstitutionStaffServiceImpl extends MPJBaseServiceImpl<EdjInstitutionStaffMapper, EdjInstitutionStaff> implements EdjInstitutionStaffService {
}