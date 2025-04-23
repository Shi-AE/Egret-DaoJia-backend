package edj.orders.grab.domain.dto;

import com.edj.common.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ES 抢单池信息
 *
 * @author A.E.
 * @date 2025/4/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersGrabInfo {
    /**
     * 抢单id
     */
    private Long id;

    /**
     * 城市编码
     */
    private String cityCode;

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
     * 服务地址
     */
    private String serveAddress;

    /**
     * 服务时间，格式 yyMMddHH
     */
    private Integer serveTime;

    /**
     * 地理坐标，经纬度
     */
    private Location location;

    /**
     * 服务开始时间
     */
    private LocalDateTime serveStartTime;

    /**
     * 服务数量
     */
    private Integer purNum;

    /**
     * 关键字检索字段
     */
    private String keyWords;

    /**
     * 订单金额
     */
    private BigDecimal ordersAmount;

    /**
     * 服务项目图片
     */
    private String serveItemImg;
}
