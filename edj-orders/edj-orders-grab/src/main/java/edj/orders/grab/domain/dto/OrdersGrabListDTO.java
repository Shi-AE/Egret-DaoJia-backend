package edj.orders.grab.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class OrdersGrabListDTO {

    /**
     * 关键字：服务项名称，服务类型名称，服务地址
     */
    @Schema(description = "关键字：服务项名称，服务类型名称，服务地址")
    private String keyWord;

    /**
     * 服务类型id
     */
    @Schema(description = "服务类型id")
    private Long serveTypeId;

    /**
     * 服务距离
     */
    @Schema(description = "服务距离")
    private Double serveDistance;

    /**
     * 上一页最后一条数据的距离，用于滚动分页
     */
    @Schema(description = "上一页最后一条数据的距离，用于滚动分页")
    private Double lastRealDistance;

    /**
     * 预约时间查询下限
     */
    @Schema(description = "预约时间查询下限")
    private LocalDateTime minServeStartTime;

    /**
     * 预约时间查询上限
     */
    @Schema(description = "预约时间查询上限")
    private LocalDateTime maxServeStartTime;
}
