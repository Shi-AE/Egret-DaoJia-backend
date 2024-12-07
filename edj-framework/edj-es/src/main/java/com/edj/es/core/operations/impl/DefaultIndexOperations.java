package com.edj.es.core.operations.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.edj.es.core.operations.IndexOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 索引操作实现
 *
 * @author A.E.
 * @date 2024/12/7
 */
@Component
@RequiredArgsConstructor
public class DefaultIndexOperations implements IndexOperations {
    private final ElasticsearchClient elasticsearchClient;
}
