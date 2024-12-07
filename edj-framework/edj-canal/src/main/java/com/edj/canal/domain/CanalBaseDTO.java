package com.edj.canal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * canal监听传递参数
 *
 * @author A.E.
 * @date 2024/12/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanalBaseDTO {
    /**
     * 数据id
     */
    private Long id;
    /**
     * 所属数据库
     */
    private String database;
    /**
     * 所在表
     */
    private String table;
    /**
     * 是否是数据保存，两种类型，一种是保存数据，另一种是删除数据
     */
    private Boolean isSave;

    /**
     * 数据变更字段
     */
    private Map<String, Object> fieldMap;

    private Long es;
}
