package com.edj.orders.manager.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单响应数据
 *
 * @author A.E.
 * @date 2025/3/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "订单响应数据")
public class OrdersDetailVO {

    /**
     * 订单id
     */
    @Schema(description = "订单id")
    private Long id;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long serveTypeId;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    private String serveTypeName;

    /**
     * 服务项id
     */
    @Schema(description = "服务项id")
    private Long serveItemId;

    /**
     * 服务项名称
     */
    @Schema(description = "服务项名称")
    private String serveItemName;

    /**
     * 服务项图片
     */
    @Schema(description = "服务项图片")
    private String serveItemImg;

    /**
     * 服务单位
     */
    @Schema(description = "服务单位")
    private Integer unit;

    /**
     * 服务id
     */
    @Schema(description = "服务id")
    private Long serveId;

    /**
     * 订单状态（0待支付 100派单中 200待服务 300服务中 400待评价 500订单完成 600已取消 700已关闭）
     */
    @Schema(description = "订单状态（0待支付 100派单中 200待服务 300服务中 400待评价 500订单完成 600已取消 700已关闭）")
    private Integer ordersStatus;

    /**
     * 支付状态（2待支付 4支付成功）
     */
    @Schema(description = "支付状态（2待支付 4支付成功）")
    private Integer payStatus;

    /**
     * 退款状态（0无退款 1退款中 2退款成功 3退款失败）
     */
    @Schema(description = "退款状态（0无退款 1退款中 2退款成功 3退款失败）")
    private Integer refundStatus;

    /**
     * 评价状态（0未评价 1已评价）
     */
    @Schema(description = "评价状态（0未评价 1已评价）")
    private Integer evaluationStatus;
    /**
     * 单价
     */
    @Schema(description = "单价")
    private BigDecimal price;

    /**
     * 购买数量
     */
    @Schema(description = "购买数量")
    private Integer purNum;

    /**
     * 订单总金额
     */
    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    @Schema(description = "实际支付金额")
    private BigDecimal realPayAmount;

    /**
     * 服务详细地址
     */
    @Schema(description = "服务详细地址")
    private String serveAddress;

    /**
     * 联系人手机号
     */
    @Schema(description = "联系人手机号")
    private String contactsPhone;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名")
    private String contactsName;

    /**
     * 服务人id，机构接单则该id为机构下属服务人员
     */
    @Schema(description = "服务人id，机构接单则该id为机构下属服务人员")
    private Long serverId;

    /**
     * 服务人姓名
     */
    @Schema(description = "服务人姓名")
    private String serverName;

    /**
     * 支付超时时间，该时间只对待支付有意义
     */
    @Schema(description = "支付超时时间，该时间只对待支付有意义")
    private LocalDateTime paymentTimeout;

    /**
     * 服务开始时间
     */
    @Schema(description = "服务开始时间")
    private LocalDateTime serveStartTime;

    /**
     * 服务结束时间
     */
    @Schema(description = "服务结束时间")
    private LocalDateTime serveEndTime;

    /**
     * 服务实际开始时间
     */
    @Schema(description = "服务实际开始时间")
    private LocalDateTime serveActualStartTime;

    /**
     * 服务实际结束时间
     */
    @Schema(description = "服务实际结束时间")
    private LocalDateTime serveActualEndTime;

    /**
     * 取消/关闭原因
     */
    @Schema(description = "取消/关闭原因")
    private String cancelReason;

    /**
     * 取消/退款时间
     */
    @Schema(description = "取消/退款时间")
    private LocalDateTime cancelTime;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
