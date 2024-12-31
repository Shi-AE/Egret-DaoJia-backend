package com.edj.trade.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 交易渠道表
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjPayChannel extends EjdBaseEntity {
    /**
     * 交易渠道id
     */
    private Long id;

    /**
     * 通道名称
     */
    private String channelName;

    /**
     * 通道唯一标记
     */
    private String channelLabel;

    /**
     * 域名
     */
    private String domain;

    /**
     * 商户appid
     */
    private String appId;

    /**
     * 支付公钥
     */
    private String publicKey;

    /**
     * 商户私钥
     */
    private String merchantPrivateKey;

    /**
     * 其他配置
     */
    private String otherConfig;

    /**
     * AES混淆密钥
     */
    private String encryptKey;

    /**
     * 说明
     */
    private String remark;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 是否有效
     */
    private String enableFlag;

    /**
     * 商户ID（系统内部识别使用）
     */
    private Long enterpriseId;
}