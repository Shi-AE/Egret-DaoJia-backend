package com.edj.common.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.db.sql.SqlUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
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

    private static final PlatformTransactionManager transactionManager;

    static {
        transactionManager = SpringUtil.getBean(PlatformTransactionManager.class);
    }

    /**
     * 仅执行批处理
     */
    public static <T> void actionBatch(List<T> list, Consumer<List<T>> action, boolean transaction) {
        actionBatch(list, action, transaction, BATCH_SIZE);
    }

    /**
     * 仅执行批处理，自定义大小批处理
     */
    public static <T> void actionBatch(List<T> list, Consumer<List<T>> action, boolean transaction, int batchSize) {
        List<List<T>> split = ListUtil.split(list, batchSize);
        log.debug("actionBatch 拆分批处理 split = {}", split);
        // 是否开启事务
        if (transaction) {
            // 高并发写入线程安全队列
            Queue<TransactionStatus> transactionStatusList = new ConcurrentLinkedQueue<>();

            // 批处理
            try {
                split.parallelStream().forEach(item -> {
                    // 开启事务
                    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                    TransactionStatus status = transactionManager.getTransaction(def);
                    // 将事务交由主线程总结
                    transactionStatusList.add(status);
                    // 执行
                    action.accept(item);
                });

                // 执行成功，提交事务
                transactionStatusList.forEach(transactionManager::commit);
            } catch (RuntimeException e) {
                // 执行失败，回滚事务
                transactionStatusList.forEach(transactionManager::rollback);
                // 继续上抛异常
                throw e;
            }
        } else {
            split.parallelStream().forEach(action);
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
}
