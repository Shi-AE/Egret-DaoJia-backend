package com.edj.common.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.db.sql.SqlUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.edj.common.domain.dto.TransactionResourceDTO;
import com.edj.common.expcetions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    private static final JdbcTransactionManager transactionManager;

    static {
        transactionManager = SpringUtil.getBean(JdbcTransactionManager.class);
    }

    /**
     * 仅执行批处理
     */
    public static <T> void actionBatch(List<T> list, Consumer<List<T>> action, boolean transaction) {
        actionBatch(list, action, transaction, BATCH_SIZE);
    }

    /**
     * 仅执行批处理，自定义大小批处理
     * 默认加入当前事务
     */
    public static <T> void actionBatch(List<T> list, Consumer<List<T>> action, boolean transaction, int batchSize) {
        List<List<T>> split = ListUtil.split(list, batchSize);
        log.debug("actionBatch 拆分批处理 split = {}", split);
        // 是否开启事务
        if (transaction) {
            // 主线程开启事务
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setTimeout(30);
            TransactionStatus status = transactionManager.getTransaction(def);

            // 获取主线程事务资源
            TransactionResourceDTO transactionResourceDTO = TransactionResourceDTO.copyTransactionResource();

            List<CompletableFuture<Void>> futureList = split
                    .stream()
                    .map(item -> AsyncUtils.runAsync(() -> {
                        // 子线程绑定事务资源
                        transactionResourceDTO.autoWiredTransactionResource();
                        try {
                            // 执行
                            action.accept(item);
                        } finally {
                            // 子线程解除事务资源
                            transactionResourceDTO.removeTransactionResource();
                        }
                    }))
                    .toList();
            try {
                CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
            } catch (Exception e) {
                // 执行失败，回滚事务
                log.debug("执行失败，回滚事务");
                transactionManager.rollback(status);
                log.debug("事务回滚完成");
                throw new BadRequestException(e.getMessage());
            }
            // 执行成功，提交事务
            log.debug("执行成功，提交事务");
            transactionManager.commit(status);
            log.debug("事务提交成功");
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
