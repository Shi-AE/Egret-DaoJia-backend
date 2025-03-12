package com.edj.api.api.market;

import com.edj.api.api.market.vo.AvailableCouponVO;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * 内部接口 - 优惠券相关接口
 *
 * @author A.E.
 * @date 2025/3/12
 */
@FeignClient(contextId = "CouponApi", name = "edj-market", path = "inner/coupon")
public interface CouponApi {

    /**
     * 获取可用优惠券，按照优惠金额从大到小排序
     */
    @GetMapping("available")
    List<AvailableCouponVO> getAvailable(@RequestParam @NotNull BigDecimal totalAmount);
}
