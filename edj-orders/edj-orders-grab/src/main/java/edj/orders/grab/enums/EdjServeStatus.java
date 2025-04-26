package edj.orders.grab.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务状态（0待分配 1待服务 2服务中 3服务完成 4取消）
 *
 * @author A.E.
 * @date 2025/3/1
 */
@Getter
@AllArgsConstructor
public enum EdjServeStatus {

    TO_ASSIGN(0, "待分配"),
    TO_SERVE(1, "待服务"),
    SERVING(2, "服务中"),
    SERVED(3, "服务完成"),
    CANCELLED(4, "取消");

    @EnumValue
    private final Integer value;
    private final String describe;
}
