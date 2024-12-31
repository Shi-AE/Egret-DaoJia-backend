package com.edj.trade.service;

import com.edj.api.api.trade.enums.TradingChannel;

/**
 * 二维码服务
 *
 * @author A.E.
 * @date 2024/12/31
 */
public interface QRCodeService {

    /**
     * 生成二维码
     *
     * @param content 二维码中的内容
     * @return 图片base64数据
     */
    String generate(String content);

    /**
     * 生成二维码，带logo
     *
     * @param content    二维码中的内容
     * @param tradingChannel 付款渠道
     * @return 图片base64数据
     */
    String generate(String content, TradingChannel tradingChannel);
}
