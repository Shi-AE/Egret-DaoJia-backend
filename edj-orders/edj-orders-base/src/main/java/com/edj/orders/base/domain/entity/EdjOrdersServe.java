package com.edj.orders.base.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单服务表
 *
 * @author A.E.
 * @date 2025/4/26
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjOrdersServe extends EjdBaseEntity {
    /**
     * 订单服务id
     */
    private Long id;

    /**
     * 消费者用户id
     */
    private Long edjUserId;

    /**
     * 服务人员或服务机构id
     */
    private Long edjServeProvidersId;

    /**
     * 机构服务人员id
     */
    private Long edjInstitutionStaffId;

    /**
     * 订单id
     */
    private Long edjOrdersId;

    /**
     * 订单来源类型（1抢单 2派单）
     */
    private Integer ordersOriginType;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 服务类型id
     */
    private Long edjServeTypeId;

    /**
     * 服务预约开始时间
     */
    private LocalDateTime serveStartTime;

    /**
     * 服务项id
     */
    private Long edjServeItemId;

    /**
     * 服务状态（0待分配 1待服务 2服务中 3服务完成 4取消）
     */
    private Integer serveStatus;

    /**
     * 结算状态（0不可结算 1待结算 2结算完成）
     */
    private Integer settlementStatus;

    /**
     * 实际服务开始时间
     */
    private LocalDateTime realServeStartTime;

    /**
     * 实际服务完结时间
     */
    private LocalDateTime realServeEndTime;

    /**
     * 服务前照片
     */
    private List<String> serveBeforeImgs;

    /**
     * 服务后照片
     */
    private List<String> serveAfterImgs;

    /**
     * 服务项图片
     */
    private String serveItemImg;

    /**
     * 服务前说明
     */
    private String serveBeforeIllustrate;

    /**
     * 服务后说明
     */
    private String serveAfterIllustrate;

    /**
     * 取消退单时间
     */
    private LocalDateTime cancelTime;

    /**
     * 订单金额
     */
    private BigDecimal ordersAmount;

    /**
     * 购买数量
     */
    private Integer purNum;

    /**
     * 排序字段（serve_start_time（秒级时间戳）+订单id（后6位））
     */
    private Long sortBy;

    /**
     * 服务端/机构端是否展示（0隐藏 1展示）
     */
    private Integer display;
}