package com.edj.api.api.trade;

import com.edj.api.api.trade.dto.NativePayDTO;
import com.edj.api.api.trade.vo.NativePayVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 内部接口 - 支付相关接口
 *
 * @author A.E.
 * @date 2024/12/30
 */
@FeignClient(contextId = "NativePayApi", value = "edj-trade", path = "inner/native")
public interface NativePayApi {

    /***
     * 扫码支付，收银员通过收银台或商户后台调用此接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付。
     *
     * @param nativePayDTO 扫码支付提交参数
     * @return 扫码支付响应数据，其中包含二维码路径
     */
    @PostMapping
    NativePayVO createDownLineTrading(@RequestBody NativePayDTO nativePayDTO);
}
