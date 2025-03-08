package com.edj.market.controller.operation;

import com.edj.common.domain.PageResult;
import com.edj.market.domain.dto.ActivityPageDTO;
import com.edj.market.domain.dto.ActivitySaveDTO;
import com.edj.market.domain.vo.ActivityPageVO;
import com.edj.market.service.EdjActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 运营端 - 优惠券活动相关接口
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "运营端 - 优惠券活动相关接口")
@RequestMapping("operation/activity")
public class OperationActivityController {

    private final EdjActivityService activityService;

    /**
     * 保存优惠券活动
     */
    @PostMapping("save")
    @Operation(summary = "保存优惠券活动")
    @PreAuthorize("hasAuthority('foundations:activity:save')")
    public void save(@Validated @RequestBody ActivitySaveDTO activitySaveDTO) {
        activityService.save(activitySaveDTO);
    }

    /**
     * 运营端分页查询优惠券活动
     */
    @GetMapping("page")
    @Operation(summary = "运营端分页查询优惠券活动")
    @PreAuthorize("hasAuthority('foundations:activity:page')")
    public PageResult<ActivityPageVO> queryForPage(ActivityPageDTO activityPageDTO) {
        return activityService.page(activityPageDTO);
    }
}
