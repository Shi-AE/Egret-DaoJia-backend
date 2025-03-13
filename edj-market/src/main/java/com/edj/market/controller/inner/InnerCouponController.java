package com.edj.market.controller.inner;

import com.edj.api.api.market.CouponApi;
import com.edj.api.api.market.dto.CouponUseDTO;
import com.edj.api.api.market.vo.AvailableCouponVO;
import com.edj.api.api.market.vo.CouponUseVO;
import com.edj.market.service.EdjCouponService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 内部接口 - 优惠券相关接口
 *
 * @author A.E.
 * @date 2025/3/12
 */
@Validated
@RestController
@RequestMapping("inner/coupon")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 优惠券相关接口")
public class InnerCouponController implements CouponApi {

    private final EdjCouponService couponService;

    /**
     * 获取可用优惠券，按照优惠金额从大到小排序
     */
    @GetMapping("available")
    @Override
    public List<AvailableCouponVO> getAvailable(
            @RequestParam Long userId,
            @RequestParam @NotNull BigDecimal totalAmount
    ) {
        return couponService.getAvailable(userId, totalAmount);
    }

    /**
     * 使用优惠券，返回优惠金额
     */
    @Override
    @PostMapping("use")
    public CouponUseVO use(@Validated @RequestBody CouponUseDTO couponUseDTO) {
        return couponService.use(couponUseDTO);
    }

    /**
     * 退回优惠券
     */
    @DeleteMapping("back")
    public void back(@RequestParam @NotNull @Positive Long orderId) {
        couponService.back(orderId);
    }
}
