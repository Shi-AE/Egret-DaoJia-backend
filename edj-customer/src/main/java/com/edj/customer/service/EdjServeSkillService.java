package com.edj.customer.service;

import com.edj.customer.domain.dto.ServeSkillUpsertDTO;
import com.edj.customer.domain.entity.EdjServeSkill;
import com.edj.customer.domain.vo.ServeSkillCategoryVO;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_serve_skill(服务技能表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/11/13
 */
public interface EdjServeSkillService extends MPJBaseService<EdjServeSkill> {

    /**
     * 批量新增或修改服务技能
     */
    void batchUpsert(List<ServeSkillUpsertDTO> serveSkillUpsertDTOList);

    /**
     * 查询服务技能目录
     */
    List<ServeSkillCategoryVO> category();
}
