package com.edj.market.utils;

import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.EnumUtils;
import com.edj.market.domain.entity.EdjCoupon;
import com.edj.market.enums.EdjCouponType;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 优惠券相关工具
 *
 * @author A.E.
 * @date 2025/3/12
 */
public class CouponUtils {

    /**
     * 计算优惠金额
     */
    public static BigDecimal calDiscountAmount(EdjCoupon coupon, BigDecimal totalAmount) {
        // 满减优惠
        Integer type = coupon.getType();
        if (EnumUtils.eq(EdjCouponType.AMOUNT_DISCOUNT, type)) {
            BigDecimal discountAmount = coupon.getDiscountAmount();

            // 如果优惠金额大于总金额，则降级优惠
            if (discountAmount.compareTo(totalAmount) > 0) {
                return totalAmount;
            }

            return discountAmount;
        }
        // 折扣优惠
        else if (EnumUtils.eq(EdjCouponType.RATE_DISCOUNT, type)) {
            Integer discountRate = coupon.getDiscountRate();
            return new BigDecimal(100 - discountRate)
                    // 计算优惠率
                    .divide(new BigDecimal(100), new MathContext(2, RoundingMode.UNNECESSARY))
                    // 计算优惠金额
                    .multiply(totalAmount, new MathContext(2, RoundingMode.FLOOR));
        } else {
            throw new ServerErrorException();
        }
    }
}
