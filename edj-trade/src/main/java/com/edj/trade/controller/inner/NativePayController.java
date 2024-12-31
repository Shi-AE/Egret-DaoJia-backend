package com.edj.trade.controller.inner;

import com.edj.api.api.trade.NativePayApi;
import com.edj.api.api.trade.dto.NativePayDTO;
import com.edj.api.api.trade.vo.NativePayVO;
import com.edj.common.utils.BeanUtils;
import com.edj.trade.domain.entity.EdjTrading;
import com.edj.trade.service.NativePayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部接口 - 支付相关接口
 *
 * @author A.E.
 * @date 2024/12/30
 */
@Validated
@RestController
@RequestMapping("inner/native")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 支付相关接口")
public class NativePayController implements NativePayApi {

    private final NativePayService nativePayService;

    /***
     * 扫码支付，收银员通过收银台或商户后台调用此接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付。
     *
     * @param nativePayDTO 扫码支付提交参数
     * @return 扫码支付响应数据，其中包含二维码路径
     */
    @Override
    @PutMapping
    @Operation(summary = "统一收单线下交易")
    public NativePayVO createDownLineTrading(@RequestBody @Validated NativePayDTO nativePayDTO) {
        EdjTrading trading = BeanUtils.toBean(nativePayDTO, EdjTrading.class);
        EdjTrading downLineTrading = nativePayService.createDownLineTrading(nativePayDTO.isChangeChannel(), trading);
        return BeanUtils.toBean(downLineTrading, NativePayVO.class);
    }
}
