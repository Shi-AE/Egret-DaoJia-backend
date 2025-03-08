package com.edj.market.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 活动状态（1待生效 2进行中 3已失效 4已作废）
 *
 * @author A.E.
 * @date 2025/3/9
 */
@Getter
@AllArgsConstructor
public enum EdjActivityStatus {

    PENDING(1, "待生效"),
    ONGOING(2, "进行中"),
    EXPIRED(3, "已失效"),
    CANCELLED(4, "已作废");

    @EnumValue
    private final Integer value;
    private final String describe;
}
