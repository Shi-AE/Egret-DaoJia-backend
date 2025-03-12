package com.edj.market.service;

import com.edj.api.api.market.vo.AvailableCouponVO;
import com.edj.common.domain.PageResult;
import com.edj.market.domain.dto.CouponPageDTO;
import com.edj.market.domain.dto.GrabCouponDTO;
import com.edj.market.domain.entity.EdjCoupon;
import com.edj.market.domain.vo.CouponPageVO;
import com.github.yulichang.base.MPJBaseService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 针对表【edj_coupon(用户优惠券表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2025/03/09
 */
public interface EdjCouponService extends MPJBaseService<EdjCoupon> {

    /**
     * 查询活动优惠券领取记录
     */
    PageResult<CouponPageVO> pageByActivity(CouponPageDTO couponPageDTO);

    /**
     * 我的优惠券列表
     */
    List<CouponPageVO> getMyCouponForPage(Long lastId, Integer status);

    /**
     * 抢券
     */
    void grabCoupon(GrabCouponDTO grabCouponDTO);

    /**
     * 同步抢券结果
     */
    void grabCouponSync();

    /**
     * 获取可用优惠券，按照优惠金额从大到小排序
     */
    List<AvailableCouponVO> getAvailable(BigDecimal totalAmount);
}
