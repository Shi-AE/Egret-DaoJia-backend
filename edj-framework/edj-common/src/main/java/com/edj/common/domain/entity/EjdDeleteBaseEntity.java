package com.edj.common.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 物理删除实体基类
 *
 * @author A.E.
 * @date 2025/4/23
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EjdDeleteBaseEntity {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
