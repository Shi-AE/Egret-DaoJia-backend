package com.edj.market.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 抢券
 *
 * @author A.E.
 * @date 2025/3/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "抢券")
public class GrabCouponDTO {

    /**
     * 活动id
     */
    @Schema(description = "活动id")
    @NotNull(message = "请选择活动")
    @Positive
    private Long id;
}
