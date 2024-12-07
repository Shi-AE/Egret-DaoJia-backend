package com.edj.canal.core;

import java.util.List;

/**
 * canal 数据处理接口
 *
 * @author A.E.
 * @date 2024/12/7
 */
public interface CanalDataHandler<T> {
    /**
     * 批量保存
     */
    void batchSave(List<T> dataList);

    /**
     * 批量删除
     */
    void batchDelete(List<Long> idList);
}
