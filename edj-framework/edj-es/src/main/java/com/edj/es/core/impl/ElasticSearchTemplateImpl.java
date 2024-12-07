package com.edj.es.core.impl;

import com.edj.es.core.ElasticSearchTemplate;
import com.edj.es.core.operations.DocumentOperations;
import com.edj.es.core.operations.IndexOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
