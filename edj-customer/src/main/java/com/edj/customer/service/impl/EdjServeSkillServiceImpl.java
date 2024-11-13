package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjServeSkill;
import com.edj.customer.mapper.EdjServeSkillMapper;
import com.edj.customer.service.EdjServeSkillService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_skill(服务技能表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
public class EdjServeSkillServiceImpl extends MPJBaseServiceImpl<EdjServeSkillMapper, EdjServeSkill> implements EdjServeSkillService {
}