package com.edj.trade.config;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.edj.api.api.trade.enums.TradingChannel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

/**
 * 二维码生成参数配置
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "edj.qrcode")
public class QRCodeConfig {

    private static Image WECHAT_LOGO;
    private static Image ALIPAY_LOGO;

    static {
        WECHAT_LOGO = ImgUtil.read(ResourceUtil.getResource("logos/wxpay.png"));
        ALIPAY_LOGO = ImgUtil.read(ResourceUtil.getResource("logos/alipay.png"));
    }

    /**
     * 边距，二维码和背景之间的边距
     */
    private Integer margin = 2;

    /**
     * 二维码颜色，默认黑色
     */
    private String foreColor = "#000000";

    /**
     * 背景色，默认白色
     */
    private String backColor = "#ffffff";

    /**
     * 纠错级别，可选参数：L、M、Q、H，默认：M
     * 低级别的像素块更大，可以远距离识别，但是遮挡就会造成无法识别。高级别则相反，像素块小，允许遮挡一定范围，但是像素块更密集。
     */
    private String errorCorrectionLevel = "M";

    /**
     * 宽
     */
    private Integer width = 300;

    /**
     * 高
     */
    private Integer height = 300;

    /**
     * 获取logo
     */
    public Image getLogo(TradingChannel tradingChannel) {
        return switch (tradingChannel) {
            case ALI_PAY -> ALIPAY_LOGO;
            case WECHAT_PAY -> WECHAT_LOGO;
        };
    }
}
