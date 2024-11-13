package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjWorkerCertification;
import com.edj.customer.mapper.EdjWorkerCertificationMapper;
import com.edj.customer.service.EdjWorkerCertificationService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_worker_certification(服务人员认证信息表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
public class EdjWorkerCertificationServiceImpl extends MPJBaseServiceImpl<EdjWorkerCertificationMapper, EdjWorkerCertification> implements EdjWorkerCertificationService {
}