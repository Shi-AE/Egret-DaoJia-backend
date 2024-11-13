package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjAgencyCertification;
import com.edj.customer.mapper.EdjAgencyCertificationMapper;
import com.edj.customer.service.EdjAgencyCertificationService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_agency_certification(机构认证信息表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
public class EdjAgencyCertificationServiceImpl extends MPJBaseServiceImpl<EdjAgencyCertificationMapper, EdjAgencyCertification> implements EdjAgencyCertificationService {
}