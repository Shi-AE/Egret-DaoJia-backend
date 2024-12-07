package com.edj.canal.domain;

import com.edj.canal.constants.OperateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author A.E.
 * @date 2024/12/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanalMqInfo {
    private String database;
    private String table;
    private Boolean isDd1;
    private String type;
    private Long es;
    private Long ts;

    /**
     * 数据列表
     */
    private List<Map<String, Object>> data;

    public boolean getIsSave() {
        return !OperateType.DELETE.equals(type);
    }
}
