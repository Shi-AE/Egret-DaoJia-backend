package com.edj.customer.mapper;

import com.edj.customer.domain.entity.EdjAgencyCertification;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_agency_certification(机构认证信息表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Mapper
public interface EdjAgencyCertificationMapper extends MPJBaseMapper<EdjAgencyCertification> {
}