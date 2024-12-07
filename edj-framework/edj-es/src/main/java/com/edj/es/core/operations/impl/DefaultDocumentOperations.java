package com.edj.es.core.operations.impl;

import cn.hutool.core.lang.Snowflake;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.GeoDistanceType;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.WriteResponseBase;
import co.elastic.clients.elasticsearch._types.query_dsl.FieldAndFormat;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.edj.common.domain.PageResult;
import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.common.expcetions.ElasticSearchException;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.*;
import com.edj.es.core.operations.DocumentOperations;
import com.edj.es.utils.TermUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 文档操作实现
 *
 * @author A.E.
 * @date 2024/12/7
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultDocumentOperations implements DocumentOperations {

    private final ElasticsearchClient elasticsearchClient;

    private Snowflake snowflake;

    @Override
    public <T> Boolean insert(String index, T document) {
        try {
            CreateResponse createResponse = elasticsearchClient
                    .create(builder -> builder
                            .id(getId(document))
                            .document(document)
                            .index(index)
                    );
            log.debug("create document response : {}", createResponse);
            return isSuccess(createResponse);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <T> Boolean batchInsert(String index, List<T> documentList) {
        BulkRequest.Builder br = new BulkRequest.Builder();
        for (T document : documentList) {
            br.operations(op -> op.index(idx -> idx.index(index)
                    .id(getId(document))
                    .document(document)));
        }
        try {
            BulkResponse bulk = elasticsearchClient.bulk(br.build());
            return isSuccess(bulk);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <T> Boolean batchUpsert(String index, List<T> documentList) {
        if (CollUtils.isEmpty(documentList)) {
            return false;
        }
        List<String> idList = documentList
                .stream()
                .map(this::getId)
                .toList();
        List<?> documentListInEs = findByIds(index, idList, List.of(IdUtils.ID), documentList.getFirst().getClass());
        Set<String> idListInEs = CollUtils.isEmpty(documentListInEs)
                ? new HashSet<>()
                : documentListInEs.stream()
                .map(this::getId)
                .collect(Collectors.toSet());

        BulkRequest.Builder builder = new BulkRequest.Builder();
        for (T document : documentList) {
            String id = getId(document);
            boolean exists = idListInEs.contains(id);

            builder.operations(op -> {
                if (exists) {
                    op.update(u -> u.action(a -> a.doc(document)).index(index).id(id));
                } else {
                    op.index(idx -> idx.index(index)
                            .id(id)
                            .document(document));
                }
                return op;
            });
        }
        try {
            BulkResponse bulk = elasticsearchClient.bulk(builder.build());
            return isSuccess(bulk);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <T> Boolean updateById(String index, T document) {
        Object id = ReflectUtils.getFieldValue(document, IdUtils.ID);
        if (id == null) {
            throw new ElasticSearchException("es更新失败, id为空");
        }
        try {
            // 2.数据更新
            UpdateResponse<?> response = elasticsearchClient
                    .update(u -> u.index(index).id(id.toString()).doc(document),
                            document.getClass());
            return isSuccess(response);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <ID> Boolean deleteById(String index, ID id) {
        try {
            // 2.数据更新
            DeleteResponse response = elasticsearchClient.delete(builder -> builder.id(id.toString()).index(index));
            return isSuccess(response);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <ID> Boolean batchDelete(String index, List<ID> idList) {
        BulkRequest.Builder builder = new BulkRequest.Builder();

        idList.forEach(id ->
                builder.operations(b -> b.delete(d -> d.index(index).id(id.toString())))
        );

        try {
            BulkResponse bulk = elasticsearchClient.bulk(builder.build());
            return isSuccess(bulk);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <T, ID> T findById(String index, ID id, Class<T> clazz) {
        try {
            GetResponse<T> response = elasticsearchClient.get(GetRequest.of(builder -> builder.id(id.toString()).index(index)), clazz);
            return response.source();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <T, ID> List<T> findByIds(String index, List<ID> idList, Class<T> clazz) {
        SearchRequest.Builder searchRequestBuild = new SearchRequest.Builder();
        TermsQuery termsQuery = TermsQuery.of(t -> t.field(IdUtils.ID).terms(new TermsQueryField.Builder().value(TermUtils.parse(idList)).build()));

        searchRequestBuild
                .index(index)
                .query(builder -> builder.terms(termsQuery));
        try {
            SearchResponse<T> searchResponse = elasticsearchClient.search(searchRequestBuild.build(), clazz);
            return searchResponse.hits().hits()
                    .stream()
                    .map(Hit::source)
                    .toList();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <T, ID> List<T> findByIds(String index, List<ID> idList, List<String> fieldList, Class<T> clazz) {
        SearchRequest.Builder searchRequestBuild = new SearchRequest.Builder();
        TermsQuery termsQuery = TermsQuery.of(t -> t.field(IdUtils.ID).terms(new TermsQueryField.Builder().value(TermUtils.parse(idList)).build()));
        searchRequestBuild.index(index)
                .query(builder -> builder.terms(termsQuery));
        if (CollUtils.isNotEmpty(fieldList)) {
            List<FieldAndFormat> fieldAndFormatList = fieldList
                    .stream()
                    .map(field -> FieldAndFormat.of(builder -> builder.field(field)))
                    .toList();
            searchRequestBuild.fields(fieldAndFormatList);
        }
        try {
            SearchResponse<T> searchResponse = elasticsearchClient.search(searchRequestBuild.build(), clazz);
            return searchResponse.hits().hits()
                    .stream()
                    .map(Hit::source)
                    .toList();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }

    }

    @Override
    public <T> PageResult<T> findForPage(PageQueryDTO pageQueryDTO, Class<T> targetClass) {

//        SearchRequest.Builder builder = new SearchRequest.Builder();
//        builder.from(pageQueryDTO.calFrom());
//        builder.size(pageQueryDTO.getPageSize());

        SearchRequest searchRequest = new SearchRequest.Builder().build();
        try {
            SearchResponse<T> search = elasticsearchClient.search(searchRequest, targetClass);
            long total = Objects.requireNonNull(search.hits().total()).value();
            List<T> data = search.hits().hits()
                    .stream()
                    .map(Hit::source)
                    .toList();
            return PageResult.of(data, pageQueryDTO.getPageSize(), total);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <T> List<T> searchByWithGeo(String index, SearchRequest.Builder searchBuilder, String locationName, String location, double distance, String sortBy, Boolean isAsc, int size, Class<T> clazz) {
        // 坐标，距离
        searchBuilder.query(query ->
                query.bool(q ->
                        q.filter(filter ->
                                filter.geoDistance(geo -> {
                                    geo.distance(distance + "km");
                                    geo.field(locationName);
                                    geo.location(location1 -> location1.text(location));
                                    geo.distanceType(GeoDistanceType.Arc);
                                    return geo;
                                }))
                )
        );
        // 自定排序字段和排序
        if (StringUtils.isNotEmpty(sortBy)) {
            searchBuilder.sort(sortOptionsBuilder ->
                    sortOptionsBuilder.field(fieldSortBuilder -> {
                        fieldSortBuilder.field(sortBy);
                        fieldSortBuilder.order(BooleanUtils.isTrue(isAsc) ? SortOrder.Asc : SortOrder.Desc);
                        return fieldSortBuilder;
                    })
            );
        }
        searchBuilder.size(size);
        searchBuilder.index(index);
        try {
            SearchResponse<T> searchResponse = elasticsearchClient.search(searchBuilder.build(), clazz);

            return searchResponse.hits().hits()
                    .stream()
                    .map(Hit::source)
                    .toList();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    @Override
    public <T> SearchResponse<T> search(SearchRequest searchRequest, Class<T> clazz) {
        try {
            return elasticsearchClient.search(searchRequest, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServerErrorException();
        }
    }

    private boolean isSuccess(WriteResponseBase writeResponseBase) {
        return writeResponseBase != null &&
                (Result.Created.equals(writeResponseBase.result()) ||
                        Result.Deleted.equals(writeResponseBase.result()) ||
                        Result.Updated.equals(writeResponseBase.result()) ||
                        Result.NoOp.equals(writeResponseBase.result()));
    }

    private Boolean isSuccess(BulkResponse response) {
        log.debug("bulk response : {}", JsonUtils.toJsonStr(response));
        if (response.errors()) {
            return false;
        }
        return response.items().stream()
                .filter(item -> item.status() != 200)
                .map(item -> false)
                .findFirst().orElse(true);
    }

    public Boolean isSuccess(DeleteByQueryResponse deleteByQueryResponse) {
        return NumberUtils.null2Default(deleteByQueryResponse.deleted(), 0) > 0;
    }

    /**
     * 获取文档id， 如果文档中设置了id，使用文档的id，如果未设置，使用雪花算法生成
     */
    private <T> String getId(T document) {
        Object objectId = ReflectUtils.getFieldValue(document, IdUtils.ID);
        if (objectId == null) {
            objectId = snowflake.nextIdStr();
        }
        return objectId.toString();
    }
}
