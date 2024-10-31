package com.edj.user.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统角色枚举
 *
 * @author A.E.
 * @date 2024/10/30
 */
@Getter
@AllArgsConstructor
public enum EdjSysRole {

    ADMIN(1L, "管理员角色"),
    CONSUMER(2L, "客户端角色"),
    WORKER(3L, "服务端角色");

    @EnumValue
    private final Long value;
    private final String describe;
}
