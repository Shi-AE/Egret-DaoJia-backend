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
 * @date 2025/3/4
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EjdCreateTimeBaseEntity {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 删除标志（0存在 1删除）
     */
    private Integer isDelete;
}
