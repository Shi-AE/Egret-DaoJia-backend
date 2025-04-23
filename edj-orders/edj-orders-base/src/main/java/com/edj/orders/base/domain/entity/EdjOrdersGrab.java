package com.edj.orders.base.domain.entity;

import com.edj.common.domain.entity.EjdDeleteBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 抢单池
 *
 * @author A.E.
 * @date 2025/4/23
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjOrdersGrab extends EjdDeleteBaseEntity {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 服务类型id
     */
    private Long edjServeTypeId;

    /**
     * 服务项id
     */
    private Long edjServeItemId;

    /**
     * 服务类型名称
     */
    private String serveTypeName;

    /**
     * 服务项名称
     */
    private String serveItemName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 服务地址
     */
    private String serveAddress;

    /**
     * 服务项目图片
     */
    private String serveItemImg;

    /**
     * 订单金额
     */
    private BigDecimal ordersAmount;

    /**
     * 服务开始时间
     */
    private LocalDateTime serveStartTime;

    /**
     * 支付成功时间，用于计算是否进入派单
     */
    private LocalDateTime paySuccessTime;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 维度
     */
    private BigDecimal lat;

    /**
     * 服务数量
     */
    private Integer purNum;

    /**
     * 抢单是否超时
     */
    private Boolean isTimeOut;

    /**
     * 抢单列表排序字段
     */
    private Integer sortBy;
}