package com.edj.mysql.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.db.sql.SqlUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.edj.common.utils.AsyncUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public static <T> void actionBatch(List<T> list, Consumer<List<T>> action, boolean transaction, boolean multi) {
        actionBatch(list, action, transaction, multi, BATCH_SIZE);
    }

    /**
     * 仅执行批处理，自定义大小批处理
     * 默认加入当前事务
     * 同表更新等情况可能出现无法开启多线程
     */
    public static <T> void actionBatch(List<T> list, Consumer<List<T>> action, boolean transaction, boolean multi, int batchSize) {
        List<List<T>> split = ListUtil.split(list, batchSize);
        log.debug("actionBatch 拆分批处理 split = {}", split);
        // 开启事务并且多线程
        if (transaction && multi) {
            AsyncUtils.runAsyncTransaction(split
                    .stream()
                    .map(item -> (Runnable) () -> action.accept(item))
                    .toList()
            );
        }
        // 仅多线程
        else if (multi) {
            split.parallelStream().forEach(action);
        }
        // 仅开启事务（需要依赖主线程事务） || 都不开启
        else {
            split.forEach(action);
        }
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

    /**
     * 检查sql语句是否有更新操作
     *
     * @param wrapper sql包装
     * @return 是否有更新操作
     */
    public static boolean isUpdate(Wrapper<?> wrapper) {
        return wrapper.getSqlSet() != null;
    }

    /**
     * 获取字段字符串
     * lambda表达式转字符串
     */
    @SafeVarargs
    public static <T> String columnsToString(SFunction<T, ?>... columns) {
        return Arrays.stream(columns)
                .map(column ->
                        StringUtils.camelToUnderline(
                                PropertyNamer.methodToProperty(
                                        LambdaUtils.extract(column).getImplMethodName()
                                )
                        )
                )
                .collect(Collectors.joining(StringPool.COMMA + StringPool.SPACE));
    }
}
