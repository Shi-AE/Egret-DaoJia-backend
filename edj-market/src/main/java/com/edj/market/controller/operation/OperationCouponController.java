package com.edj.market.controller.operation;

import com.edj.common.domain.PageResult;
import com.edj.market.domain.dto.CouponPageDTO;
import com.edj.market.domain.vo.CouponPageVO;
import com.edj.market.service.EdjCouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运营端 - 优惠券相关接口
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "运营端 - 优惠券相关接口")
@RequestMapping("operation/coupon")
public class OperationCouponController {

    private final EdjCouponService couponService;

    /**
     * 查询活动优惠券领取记录
     */
    @GetMapping("page")
    @Operation(summary = "查询活动优惠券领取记录")
    @PreAuthorize("hasAuthority('foundations:coupon:page')")
    public PageResult<CouponPageVO> pageByActivity(@Validated CouponPageDTO couponPageDTO) {
        return couponService.pageByActivity(couponPageDTO);
    }
}
