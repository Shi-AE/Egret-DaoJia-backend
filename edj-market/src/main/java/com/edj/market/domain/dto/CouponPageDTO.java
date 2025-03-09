package com.edj.market.domain.dto;

import com.edj.common.domain.dto.PageQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 优惠券分页查询
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
@Schema(description = "优惠券分页查询")
public class CouponPageDTO extends PageQueryDTO {

    /**
     * 优惠券活动id
     */
    @Schema(description = "优惠券活动id")
    @NotNull(message = "请先选择活动")
    @Positive(message = "请先选择活动")
    private Long activityId;
}
