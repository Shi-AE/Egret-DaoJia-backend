package com.edj.customer.mapper;

import com.edj.customer.domain.entity.EdjWorkerCertification;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 针对表【edj_worker_certification(服务人员认证信息表)】的数据库操作Mapper
*
* @author A.E.
* @date 2024/11/13
*/
@Mapper
public interface EdjWorkerCertificationMapper extends MPJBaseMapper<EdjWorkerCertification> {
}