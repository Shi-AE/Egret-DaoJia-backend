package com.edj.market.domain.dto;

import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.market.enums.EdjCouponType;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 保存优惠券活动
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "保存优惠券活动")
public class ActivitySaveDTO {
    /**
     * 优惠券活动表id
     */
    @Schema(description = "优惠券活动表id")
    @Positive(message = "修改优惠券错误")
    private Long id;

    /**
     * 优惠券活动名称
     */
    @Schema(description = "优惠券活动名称")
    @Size(max = 20, message = "优惠券活动名称不能超出20个字符")
    @NotBlank(message = "优惠券活动名称不能为空")
    private String name;

    /**
     * 优惠券类型（1满减 2折扣）
     */
    @Schema(description = "优惠券类型（1满减 2折扣）")
    @Enums(EdjCouponType.class)
    @NotNull(message = "优惠券类型不能为空")
    private Integer type;

    /**
     * 使用条件（最低消费金额）
     */
    @Schema(description = "使用条件（最低消费金额）")
    @DecimalMin(value = "0", message = "使用条件不能小于0")
    @NotNull(message = "使用条件不能为空")
    private BigDecimal amountCondition;

    /**
     * 折扣率（单位%，折扣类型优惠券）
     */
    @Schema(description = "折扣率（单位%，折扣类型优惠券）")
    @Min(value = 1, message = "折扣率不能小于1")
    @Max(value = 99, message = "折扣率不能大于99")
    private Integer discountRate;

    /**
     * 优惠金额（单位元，满减类型优惠券）
     */
    @Schema(description = "优惠金额（单位元，满减类型优惠券）")
    @DecimalMin(value = "0", inclusive = false, message = "优惠金额不能小于等于0")
    private BigDecimal discountAmount;

    /**
     * 优惠券有效期天数（0：表示指定截止时间）
     */
    @Schema(description = "优惠券有效期天数（0：表示指定截止时间）")
    @Min(value = 1, message = "优惠券有效期天数不能小于1")
    @NotNull(message = "优惠券有效期天数不能为空")
    private Integer validityDays;

    /**
     * 开始发放时间
     */
    @Schema(description = "开始发放时间")
    @NotNull(message = "开始发放时间不能为空")
    @Future(message = "开始发放时间错误")
    private LocalDateTime distributeStartTime;

    /**
     * 发放结束时间
     */
    @Schema(description = "发放结束时间")
    @NotNull(message = "发放结束时间不能为空")
    @Future(message = "发放结束时间错误")
    private LocalDateTime distributeEndTime;

    /**
     * 发放数量（0：表示无限量）
     */
    @Schema(description = "发放数量（0：表示无限量）")
    @Min(value = 0, message = "发放数量不能小于0")
    private Integer totalNum;

    public void check() {
        if (EnumUtils.eq(EdjCouponType.AMOUNT_DISCOUNT, type) && ObjectUtils.isNull(discountAmount)) {
            // 满减，优惠金额不能为空
            throw new BadRequestException("优惠金额不能为空");
        }
        if (EnumUtils.eq(EdjCouponType.RATE_DISCOUNT, type) && ObjectUtils.isNull(discountRate)) {
            // 折扣, 折扣率不能为空
            throw new BadRequestException("折扣率不能为空");
        }
        if (distributeStartTime.isAfter(distributeEndTime)) {
            throw new BadRequestException("发放结束时间不能早于发放开始时间");
        }
        if (distributeStartTime.isBefore(LocalDateTime.now().plusMinutes(30))) {
            throw new BadRequestException("发放结束时间不能早于距离当前时间30分钟后");
        }
    }
}
