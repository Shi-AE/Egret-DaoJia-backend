package com.edj.market.domain.dto;

import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.market.enums.EdjActivityStatus;
import com.edj.market.enums.EdjCouponType;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 优惠券活动分页查询
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "优惠券活动分页查询")
public class ActivityPageDTO extends PageQueryDTO {

    /**
     * 活动id
     */
    @Schema(description = "活动id")
    @Positive
    private Long id;

    /**
     * 活动名称
     */
    @Schema(description = "活动名称")
    private String name;

    /**
     * 优惠券类型（1满减 2折扣）
     */
    @Schema(description = "优惠券类型（1满减 2折扣）")
    @Enums(EdjCouponType.class)
    private Integer type;

    /**
     * 活动状态（1待生效 2进行中 3已失效 4已作废）
     */
    @Schema(description = "活动状态（1待生效 2进行中 3已失效 4已作废）")
    @Enums(EdjActivityStatus.class)
    private Integer status;
}
