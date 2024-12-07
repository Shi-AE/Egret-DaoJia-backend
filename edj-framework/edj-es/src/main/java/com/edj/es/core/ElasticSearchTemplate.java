package com.edj.es.core;

import com.edj.es.core.operations.DocumentOperations;
import com.edj.es.core.operations.IndexOperations;

/**
 * es 操作模板
 *
 * @author A.E.
 * @date 2024/12/7
 */
public interface ElasticSearchTemplate {

    /**
     * 操作文档
     */
    DocumentOperations opsForDoc();

    /**
     * 操作索引
     */
    IndexOperations opsForIndex();
}
