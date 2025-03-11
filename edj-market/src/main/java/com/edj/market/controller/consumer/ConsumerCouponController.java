package com.edj.market.controller.consumer;

import com.edj.market.domain.dto.GrabCouponDTO;
import com.edj.market.domain.vo.CouponPageVO;
import com.edj.market.enums.EdjCouponStatus;
import com.edj.market.service.EdjCouponService;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户端 - 优惠券相关接口
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "用户端 - 优惠券相关接口")
@RequestMapping("consumer/coupon")
public class ConsumerCouponController {

    private final EdjCouponService couponService;

    /**
     * 我的优惠券列表
     */
    @GetMapping("my")
    @Operation(summary = "我的优惠券列表")
    @PreAuthorize("hasAuthority('consumer:coupon:page')")
    public List<CouponPageVO> getMyCouponForPage(
            @RequestParam(required = false) @Schema(description = "上一次查询最后一张优惠券id") @Positive Long lastId,
            @RequestParam @Schema(description = "优惠券状态") @NotNull @Enums(EdjCouponStatus.class) Integer status
    ) {
        return couponService.getMyCouponForPage(lastId, status);
    }

    /**
     * 抢券
     */
    @PostMapping("grab")
    @Operation(summary = "抢券")
    @PreAuthorize("hasAuthority('consumer:coupon:grab')")
    public void grabCoupon(@Validated @RequestBody GrabCouponDTO grabCouponDTO) {
        couponService.grabCoupon(grabCouponDTO);
    }
}
