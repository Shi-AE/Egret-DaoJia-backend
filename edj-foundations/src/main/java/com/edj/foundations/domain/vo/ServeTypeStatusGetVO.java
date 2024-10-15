package com.edj.foundations.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 根据活动状态查询服务类型 VO
 *
 * @author A.E.
 * @date 2024/10/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "根据活动状态查询服务类型 VO")
public class ServeTypeStatusGetVO {

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long id;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    private String name;
}
