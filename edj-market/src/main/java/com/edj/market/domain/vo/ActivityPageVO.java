package com.edj.market.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券活动查询
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "优惠券活动查询")
public class ActivityPageVO {
    /**
     * 优惠券活动表id
     */
    @Schema(description = "优惠券活动表id")
    private Long id;

    /**
     * 优惠券活动名称
     */
    @Schema(description = "优惠券活动名称")
    private String name;

    /**
     * 优惠券类型（1满减 2折扣）
     */
    @Schema(description = "优惠券类型（1满减 2折扣）")
    private Integer type;

    /**
     * 使用条件（最低消费金额）
     */
    @Schema(description = "使用条件（最低消费金额）")
    private BigDecimal amountCondition;

    /**
     * 折扣率（单位%，折扣类型优惠券）
     */
    @Schema(description = "折扣率（单位%，折扣类型优惠券）")
    private Integer discountRate;

    /**
     * 优惠金额（单位元，满减类型优惠券）
     */
    @Schema(description = "优惠金额（单位元，满减类型优惠券）")
    private BigDecimal discountAmount;

    /**
     * 优惠券有效期天数（0：表示指定截止时间）
     */
    @Schema(description = "优惠券有效期天数（0：表示指定截止时间）")
    private Integer validityDays;

    /**
     * 开始发放时间
     */
    @Schema(description = "开始发放时间")
    private LocalDateTime distributeStartTime;

    /**
     * 发放结束时间
     */
    @Schema(description = "发放结束时间")
    private LocalDateTime distributeEndTime;

    /**
     * 活动状态（1待生效 2进行中 3已失效 4已作废）
     */
    @Schema(description = "活动状态（1待生效 2进行中 3已失效 4已作废）")
    private Integer status;

    /**
     * 发放数量（0：表示无限量）
     */
    @Schema(description = "发放数量（0：表示无限量）")
    private Integer totalNum;

    /**
     * 领取数量
     */
    @Schema(description = "领取数量")
    private Integer receiveNum;

    /**
     * 核销数量
     */
    @Schema(description = "核销数量")
    private Integer writeOffNum;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
