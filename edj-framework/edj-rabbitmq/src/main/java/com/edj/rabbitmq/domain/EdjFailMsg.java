package com.edj.rabbitmq.domain;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 消息队列失败消息
 *
 * @author A.E.
 * @date 2024/12/6
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjFailMsg extends EjdBaseEntity {
    /**
     * 失败消息id
     */
    private Long id;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由key
     */
    private String routingKey;

    /**
     * 消息
     */
    private String msg;

    /**
     * 原因
     */
    private String reason;

    /**
     * 消息延迟执行时间
     */
    private Long delayMsgExecuteTime;

    /**
     * 下次拉取时间
     */
    private Long nextFetchTime;
}