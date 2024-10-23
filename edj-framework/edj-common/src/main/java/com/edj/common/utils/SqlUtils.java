package com.edj.common.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.db.sql.SqlUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author A.E.
 * @date 2024/10/23
 */
@Slf4j
public class SqlUtils extends SqlUtil {

    /**
     * 批处理默认大小
     */
    public static final int BATCH_SIZE = 2;

    /**
     * 仅执行批处理
     */
    public static <T> void actionBatch(List<T> list, Consumer<List<T>> action) {
        actionBatch(list, action, BATCH_SIZE);
    }

    /**
     * 仅执行批处理，自定义大小批处理
     */
    public static <T> void actionBatch(List<T> list, Consumer<List<T>> action, int batchSize) {
        List<List<T>> split = ListUtil.split(list, batchSize);
        log.debug("actionBatch 拆分批处理 split = {}", split);
        split.parallelStream().forEach(action);
    }

    /**
     * 执行统计批处理
     */
    public static <T> long countBatch(List<T> list, Function<List<T>, Long> counter) {
        return countBatch(list, counter, BATCH_SIZE);
    }

    /**
     * 执行统计批处理，自定义大小批处理
     */
    public static <T> long countBatch(List<T> list, Function<List<T>, Long> counter, int batchSize) {
        List<List<T>> split = ListUtil.split(list, batchSize);
        log.debug("countBatch 拆分批处理 split = {}", split);
        return split.parallelStream()
                .mapToLong(counter::apply)
                .sum();
    }

    /**
     * 执行存在判定批处理
     */
    public static <T> boolean allExistBatch(List<T> list, Function<List<T>, Boolean> checker) {
        return allExistBatch(list, checker, BATCH_SIZE);
    }

    /**
     * 执行存在判定批处理，自定义大小批处理
     */
    public static <T> boolean allExistBatch(List<T> list, Function<List<T>, Boolean> checker, int batchSize) {
        List<List<T>> split = ListUtil.split(list, batchSize);
        log.debug("allExistBatch 拆分批处理 split = {}", split);
        return split.parallelStream().allMatch(checker::apply);
    }

    /**
     * 查询列表批处理
     */
    public static <T, U> List<U> selectBatch(List<T> list, Function<List<T>, List<U>> observer) {
        return selectBatch(list, observer, BATCH_SIZE);
    }

    /**
     * 查询列表批处理，自定义大小批处理
     */
    public static <T, U> List<U> selectBatch(List<T> list, Function<List<T>, List<U>> observer, int batchSize) {
        List<List<T>> split = ListUtil.split(list, batchSize);
        log.debug("selectBatch 拆分批处理 split = {}", split);
        return split.parallelStream()
                .map(observer)
                .flatMap(List::stream)
                .toList();
    }
}
