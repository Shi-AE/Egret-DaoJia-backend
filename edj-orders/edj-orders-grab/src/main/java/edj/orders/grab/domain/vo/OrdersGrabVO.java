package edj.orders.grab.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 抢单信息查询
 *
 * @author A.E.
 * @date 2025/4/24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "抢单信息查询")
public class OrdersGrabVO {
    /**
     * 订单id
     */
    @Schema(description = "订单id")
    private Long id;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long edjServeTypeId;

    /**
     * 服务项id
     */
    @Schema(description = "服务项id")
    private Long edjServeItemId;

    /**
     * 服务类型名称
     */
    @Schema(description = "服务类型名称")
    private String serveTypeName;

    /**
     * 服务项名称
     */
    @Schema(description = "服务项名称")
    private String serveItemName;

    /**
     * 服务项目图片
     */
    @Schema(description = "服务项目图片")
    private String serveItemImg;

    /**
     * 服务地址
     */
    @Schema(description = "服务地址")
    private String serveAddress;

    /**
     * 服务开始时间
     */
    @Schema(description = "服务开始时间")
    private LocalDateTime serveStartTime;

    /**
     * 服务数量
     */
    @Schema(description = "服务数量")
    private Integer purNum;

    /**
     * 订单金额
     */
    @Schema(description = "订单金额")
    private BigDecimal ordersAmount;

    /**
     * 实际距离
     */
    @Schema(description = "实际距离")
    private Double realDistance;
}
