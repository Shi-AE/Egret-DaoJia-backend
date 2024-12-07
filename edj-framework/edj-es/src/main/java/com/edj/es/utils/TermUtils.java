package com.edj.es.utils;

import co.elastic.clients.elasticsearch._types.FieldValue;
import com.edj.common.utils.CollUtils;

import java.util.List;

/**
 * es 条目工具
 *
 * @author A.E.
 * @date 2024/12/7
 */
public class TermUtils {
    public static <T> List<FieldValue> parse(List<T> sources) {
        if (CollUtils.isEmpty(sources)) {
            return null;
        }
        return sources
                .stream()
                .map(s -> FieldValue.of(s.toString()))
                .toList();
    }
}
