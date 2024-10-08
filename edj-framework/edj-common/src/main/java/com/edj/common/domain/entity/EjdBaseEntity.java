package com.edj.common.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 实体基类
 *
 * @author A.E.
 * @date 2024/9/29
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EjdBaseEntity {

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标志（0存在 1删除）
     */
    private Integer isDelete;
}
