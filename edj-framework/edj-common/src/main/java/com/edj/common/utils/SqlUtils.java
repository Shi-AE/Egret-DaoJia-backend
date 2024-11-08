package com.edj.common.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.db.sql.SqlUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.edj.common.domain.dto.TransactionResourceDTO;
import com.edj.common.expcetions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
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
            Queue<TransactionResourceDTO> transactionResourceList = new ConcurrentLinkedQueue<>();

            List<CompletableFuture<Void>> futureList = split
                    .stream()
                    .map(item -> AsyncUtils.runAsync(() -> {
                        // 开启事务
                        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                        def.setTimeout(10);
                        TransactionStatus status = transactionManager.getTransaction(def);
                        log.debug("开启事务");

                        // 复制事务资源交由主线程管理
                        transactionResourceList.add(TransactionResourceDTO.copyTransactionResource(status));
                        log.debug("复制事务资源交由主线程管理");

                        // 执行
                        action.accept(item);
                        log.debug("任务执行完成");
                    }))
                    .toList();

            try {
                CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
            } catch (Exception e) {
                // 执行失败，回滚事务
                log.debug("执行失败，回滚事务");
                transactionResourceList.forEach(tr -> {
                    tr.autoWiredTransactionResource();
                    transactionManager.rollback(tr.getTransactionStatus());
                    tr.removeTransactionResource();
                });
                log.debug("事务回滚完成");
                throw new BadRequestException(e.getMessage());
            }
            // 执行成功，提交事务
            log.debug("执行成功，提交事务");
            transactionResourceList.forEach(tr -> {
                tr.autoWiredTransactionResource();
                transactionManager.commit(tr.getTransactionStatus());
                tr.removeTransactionResource();
            });
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
