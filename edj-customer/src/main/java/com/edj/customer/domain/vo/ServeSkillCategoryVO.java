package com.edj.customer.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 服务技能目录响应结果
 *
 * @author A.E.
 * @date 2024/11/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务技能目录响应结果")
public class ServeSkillCategoryVO {
    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long serveTypeId;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    private String serveTypeName;

    /**
     * 下属服务技能数量
     */
    @Schema(description = "下属服务技能数量")
    private Long count;

    /**
     * 服务技能项列表
     */
    @Schema(description = "服务技能项列表")
    private List<ServeSkillItemVO> serveSkillItemVOList;
}
