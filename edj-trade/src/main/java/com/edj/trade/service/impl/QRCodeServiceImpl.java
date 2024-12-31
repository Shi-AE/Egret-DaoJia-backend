package com.edj.trade.service.impl;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.edj.api.api.trade.enums.TradingChannel;
import com.edj.trade.config.QRCodeConfig;
import com.edj.trade.service.QRCodeService;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 二维码服务实现
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Service
@RequiredArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    private final QRCodeConfig qrCodeConfig;

    @Override
    public String generate(String content) {
        return generate(content, null);
    }

    @Override
    public String generate(String content, TradingChannel tradingChannel) {
        QrConfig qrConfig = new QrConfig();
        // 设置边距
        qrConfig.setMargin(qrCodeConfig.getMargin());
        // 二维码颜色
        qrConfig.setForeColor(HexUtil.decodeColor(qrCodeConfig.getForeColor()));
        // 设置背景色
        qrConfig.setBackColor(HexUtil.decodeColor(qrCodeConfig.getBackColor()));
        // 纠错级别
        qrConfig.setErrorCorrection(ErrorCorrectionLevel.valueOf(qrCodeConfig.getErrorCorrectionLevel()));
        // 设置宽
        qrConfig.setWidth(qrCodeConfig.getWidth());
        // 设置高
        qrConfig.setHeight(qrCodeConfig.getHeight());
        if (ObjectUtil.isNotEmpty(tradingChannel)) {
            // 设置logo
            qrConfig.setImg(qrCodeConfig.getLogo(tradingChannel));
        }
        return QrCodeUtil.generateAsBase64(content, qrConfig, ImgUtil.IMAGE_TYPE_PNG);
    }
}
