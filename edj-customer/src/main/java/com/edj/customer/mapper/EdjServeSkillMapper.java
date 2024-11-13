package com.edj.customer.mapper;

import com.edj.customer.domain.entity.EdjServeSkill;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_serve_skill(服务技能表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Mapper
public interface EdjServeSkillMapper extends MPJBaseMapper<EdjServeSkill> {
}