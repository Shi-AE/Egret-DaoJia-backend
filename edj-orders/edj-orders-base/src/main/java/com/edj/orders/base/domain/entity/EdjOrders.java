package com.edj.orders.base.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表
 *
 * @author A.E.
 * @date 2024/12/23
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjOrders extends EjdBaseEntity {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单所属人id
     */
    private Long edjUserId;

    /**
     * 服务类型id
     */
    private Long edjServeTypeId;

    /**
     * 服务项id
     */
    private Long edjServeItemId;

    /**
     * 服务id
     */
    private Long edjServeId;

    /**
     * 服务类型名称
     */
    private String serveTypeName;

    /**
     * 服务项名称
     */
    private String serveItemName;

    /**
     * 服务项图片
     */
    private String serveItemImg;

    /**
     * 服务单位
     */
    private Integer unit;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer purNum;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal realPayAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 城市编号
     */
    private String cityCode;

    /**
     * 服务详细地址
     */
    private String serveAddress;

    /**
     * 联系人手机号
     */
    private String contactsPhone;

    /**
     * 联系人姓名
     */
    private String contactsName;

    /**
     * 服务开始时间
     */
    private LocalDateTime serveStartTime;

    /**
     * 实际服务完成时间
     */
    private LocalDateTime realServeEndTime;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 订单状态（0待支付 100派单中 200待服务 300服务中 400待评价 500订单完成 600已取消 700已关闭）
     */
    private Integer ordersStatus;

    /**
     * 支付状态（2待支付 4支付成功）
     */
    private Integer payStatus;

    /**
     * 退款状态（0无退款 1退款中 2退款成功 3退款失败）
     */
    private Integer refundStatus;

    /**
     * 评价时间
     */
    private LocalDateTime evaluationTime;

    /**
     * 评价状态（0未评价 1已评价）
     */
    private Integer evaluationStatus;

    /**
     * 支付服务交易单号
     */
    private Long tradingOrderNo;

    /**
     * 第三方支付的交易单号
     */
    private String transactionId;

    /**
     * 支付服务退款单号
     */
    private Long refundNo;

    /**
     * 第三方支付的退款单号
     */
    private String refundId;

    /**
     * 支付渠道
     */
    private String tradingChannel;

    /**
     * 用户端是否展示（1展示 0隐藏）
     */
    private Integer display;

    /**
     * 排序字段（serve_start_time秒级时间戳+订单id后六位）
     */
    private Long sortBy;
}