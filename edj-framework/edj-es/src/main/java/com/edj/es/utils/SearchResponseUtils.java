package com.edj.es.utils;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.ObjectUtils;

import java.util.List;

/**
 * 搜索结果处理工具
 *
 * @author A.E.
 * @date 2024/12/7
 */
public class SearchResponseUtils {
    public static boolean isSuccess(SearchResponse<?> searchResponse) {
        return searchResponse != null && ObjectUtils.isNotEmpty(searchResponse.hits()) && CollUtils.isNotEmpty(searchResponse.hits().hits());
    }

    public static boolean isNotSuccess(SearchResponse<?> searchResponse) {
        return !isSuccess(searchResponse);
    }

    public static <T> List<T> getResponse(SearchResponse<T> searchResponse) {
        return getResponse(searchResponse, null);
    }


    public static <T> List<T> getResponse(SearchResponse<T> searchResponse, Convert<T> convert) {
        if (isNotSuccess(searchResponse)) {
            return null;
        }
        if (convert == null) {
            return searchResponse.hits().hits()
                    .stream()
                    .map(Hit::source)
                    .toList();
        }
        return searchResponse.hits().hits()
                .stream()
                .map(hit -> {
                    convert.convert(hit, hit.source());
                    return hit.source();
                })
                .toList();
    }

    public interface Convert<T> {
        void convert(Hit<T> hit, T t);
    }
}
