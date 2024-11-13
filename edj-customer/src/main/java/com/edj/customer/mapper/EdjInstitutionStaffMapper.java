package com.edj.customer.mapper;

import com.edj.customer.domain.entity.EdjInstitutionStaff;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_institution_staff(机构下属服务人员)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Mapper
public interface EdjInstitutionStaffMapper extends MPJBaseMapper<EdjInstitutionStaff> {
}