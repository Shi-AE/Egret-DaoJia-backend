package com.edj.market.controller.consumer;

import com.edj.market.domain.vo.ActivityInfoVO;
import com.edj.market.service.EdjActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户端 - 优惠券活动相关接口
 *
 * @author A.E.
 * @date 2025/3/10
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "用户端 - 优惠券活动相关接口")
@RequestMapping("consumer/activity")
public class ConsumerActivityController {

    private final EdjActivityService activityService;

    /**
     * 查询用户端抢券列表
     */
    @GetMapping("list")
    @Operation(summary = "查询用户端抢券列表")
    @PreAuthorize("hasAuthority('consumer:activity:list')")
    public List<ActivityInfoVO> listFromCache(Integer tabType) {
        return activityService.listFromCache(tabType);
    }
}
