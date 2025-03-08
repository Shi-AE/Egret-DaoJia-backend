package com.edj.market.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券活动表
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
public class EdjActivity extends EjdBaseEntity {
    /**
     * 优惠券活动表id
     */
    private Long id;

    /**
     * 优惠券活动名称
     */
    private String name;

    /**
     * 优惠券类型（1满减 2折扣）
     */
    private Integer type;

    /**
     * 使用条件（最低消费金额）
     */
    private BigDecimal amountCondition;

    /**
     * 折扣率（单位%，折扣类型优惠券）
     */
    private Integer discountRate;

    /**
     * 优惠金额（单位元，满减类型优惠券）
     */
    private BigDecimal discountAmount;

    /**
     * 优惠券有效期天数（0：表示指定截止时间）
     */
    private Integer validityDays;

    /**
     * 开始发放时间
     */
    private LocalDateTime distributeStartTime;

    /**
     * 发放结束时间
     */
    private LocalDateTime distributeEndTime;

    /**
     * 活动状态（1待生效 2进行中 3已失效 4已作废）
     */
    private Integer status;

    /**
     * 发放数量（0：表示无限量）
     */
    private Integer totalNum;
}