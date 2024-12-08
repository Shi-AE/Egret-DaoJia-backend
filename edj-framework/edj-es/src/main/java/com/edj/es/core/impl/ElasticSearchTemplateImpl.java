package com.edj.es.core.impl;

import com.edj.es.core.ElasticSearchTemplate;
import com.edj.es.core.operations.DocumentOperations;
import com.edj.es.core.operations.IndexOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * es 操作模板实现
 *
 * @author A.E.
 * @date 2024/12/8
 */
@Component
@RequiredArgsConstructor
public class ElasticSearchTemplateImpl implements ElasticSearchTemplate {

    private final IndexOperations indexOperations;

    private final DocumentOperations documentOperations;

    @Override
    public DocumentOperations opsForDoc() {
        return documentOperations;
    }

    @Override
    public IndexOperations opsForIndex() {
        return indexOperations;
    }
}
