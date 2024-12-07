package com.edj.es.core.operations;

import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.edj.common.domain.PageResult;
import com.edj.common.domain.dto.PageQueryDTO;

import java.util.List;

/**
 * 文档操作
 *
 * @author A.E.
 * @date 2024/12/7
 */
public interface DocumentOperations {

    /**
     * 新增文档
     *
     * @param index    文档所属索引
     * @param document 文档
     * @param <T>      document类型
     * @return 文档创建结果
     */
    <T> Boolean insert(String index, T document);

    <T> Boolean batchInsert(String index, List<T> documents);

    <T> Boolean batchUpsert(String index, List<T> documents);

    <T> Boolean updateById(String index, T document);

    <ID> Boolean deleteById(String index, ID id);

    /**
     * 批量删除
     */
    <ID> Boolean batchDelete(String index, List<ID> ids);

    <T, ID> T findById(String index, ID id, Class<T> clazz);

    <T, ID> List<T> findByIds(String index, List<ID> ids, Class<T> clazz);

    <T, ID> List<T> findByIds(String index, List<ID> ids, List<String> includes, Class<T> clazz);

    <T> PageResult<T> findForPage(PageQueryDTO pageQueryDTO, Class<T> targetClass);

    /**
     * 根据条件使用经纬度范围检索，先经过条件筛选，然后距离查询
     */
    <T> List<T> searchByWithGeo(
            String index,
            SearchRequest.Builder searchBuilder,
            String locationName,
            String location,
            double distance,
            String sortBy,
            Boolean isAsc,
            int size,
            Class<T> clazz
    );

    <T> SearchResponse<T> search(SearchRequest searchRequest, Class<T> clazz);
}
