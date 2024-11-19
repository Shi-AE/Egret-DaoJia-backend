package com.edj.customer.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 最新的驳回原因
 *
 * @author A.E.
 * @date 2024/11/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "最新的驳回原因")
public class RejectReasonVO {

    /**
     * 驳回原因
     */
    @Schema(description = "驳回原因")
    private String rejectReason;
}
