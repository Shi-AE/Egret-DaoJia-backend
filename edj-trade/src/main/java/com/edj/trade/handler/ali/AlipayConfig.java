package com.edj.trade.handler.ali;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alipay.easysdk.kernel.Config;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.expcetions.CommonException;
import com.edj.trade.constant.TradingConstant;
import com.edj.trade.domain.entity.EdjPayChannel;
import com.edj.trade.enums.TradingEnum;
import com.edj.trade.service.EdjPayChannelService;

/**
 * @author A.E.
 * @date 2024/12/31
 */
public class AlipayConfig {

    /**
     * 将支付渠道配置转化为支付宝的配置
     *
     * @param enterpriseId 商户ID
     * @return 支付宝的配置
     */
    public static Config getConfig(Long enterpriseId) {
        // 查询配置
        EdjPayChannelService payChannelService = SpringUtil.getBean(EdjPayChannelService.class);
        EdjPayChannel payChannel = payChannelService.findByEnterpriseId(enterpriseId, TradingConstant.TRADING_CHANNEL_ALI_PAY);

        if (ObjectUtil.isEmpty(payChannel)) {
            throw new CommonException(ErrorInfo.Code.TRADE_FAILED, TradingEnum.CONFIG_EMPTY.getValue());
        }

        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = payChannel.getDomain();
        config.signType = "RSA2";
        config.appId = payChannel.getAppId();
        // 配置应用私钥
        config.merchantPrivateKey = payChannel.getMerchantPrivateKey();
        // 配置支付宝公钥
        config.alipayPublicKey = payChannel.getPublicKey();
        // 可设置异步通知接收服务地址（可选）
        config.notifyUrl = payChannel.getNotifyUrl();
        // 设置AES密钥，调用AES加解密相关接口时需要（可选）
        config.encryptKey = payChannel.getEncryptKey();
        return config;
    }
}
