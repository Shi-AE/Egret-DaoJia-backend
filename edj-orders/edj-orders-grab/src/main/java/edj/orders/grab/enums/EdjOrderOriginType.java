package edj.orders.grab.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单来源类型（1抢单 2派单）
 *
 * @author A.E.
 * @date 2025/3/1
 */
@Getter
@AllArgsConstructor
public enum EdjOrderOriginType {

    GRAB(1, "抢单"),
    ASSIGN(2, "派单");

    @EnumValue
    private final Integer value;
    private final String describe;
}
