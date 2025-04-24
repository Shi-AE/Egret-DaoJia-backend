package com.edj.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.edj.common.constants.ConnectSemaphore;
import com.edj.common.expcetions.ServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * 异步工具
 *
 * @author A.E.
 * @date 2024/10/6
 */
@Slf4j
@SuppressWarnings("UnusedReturnValue")
public class AsyncUtils {

    /**
     * 核心线程数，不会因线程空闲而被销毁的线程
     */
    public static final int CORE_POOL_SIZE = 50;

    /**
     * 最大线程数
     */
    public static final int MAXIMUM_POOL_SIZE = 100;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    public static final long KEEP_ALIVE_TIME = 300;

    /**
     * 非核心线程最大存活时长单位
     */
    public static final TimeUnit UNIT = TimeUnit.SECONDS;

    /**
     * 任务队列最大长度
     */
    public static final int CAPACITY = 1000;

    /**
     * 线程池
     */
    public static final ThreadPoolExecutor ASYNC_POOL;

    /**
     * 事务管理器
     */
    public static final PlatformTransactionManager TRANSACTION_MANAGER;

    // 加载事务管理器
    static {
        TRANSACTION_MANAGER = SpringUtil.getBean(PlatformTransactionManager.class);
    }

    /**
     * 任务队列拒绝策略
     *
     * @author A.E.
     * @date 2024/10/7
     */
    private static final class CallerRunsPolicyWithLog implements RejectedExecutionHandler {
        /**
         * Creates a {@code CallerRunsPolicyWithLog}.
         */
        public CallerRunsPolicyWithLog() {
        }

        /**
         * 记录 ERROR 日志提示队列溢出。
         * 在调用者的线程中执行任务r，除非执行器被关闭，在这种情况下，任务会被丢弃。
         *
         * @param r 请求执行的runnable任务
         * @param e 试图执行此任务的执行器进程
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            log.error("任务队列已满，考虑修改队列长度：rejectedExecution({}, {})", r, e);
            if (!e.isShutdown()) {
                r.run();
            }
        }
    }

    /**
     * 自定义线程工厂
     *
     * @author A.E.
     * @date 2024/10/7
     */
    private static class EdjThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        EdjThreadFactory() {
            group = Thread.currentThread().getThreadGroup();
            namePrefix = "edj-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (thread.isDaemon())
                thread.setDaemon(false);
            if (thread.getPriority() != Thread.NORM_PRIORITY)
                thread.setPriority(Thread.NORM_PRIORITY);

            thread.setUncaughtExceptionHandler(AsyncUtils::exceptionHandle);

            return thread;
        }
    }

    // 加载线程池
    static {
        ASYNC_POOL = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                UNIT,
                new LinkedBlockingQueue<>(CAPACITY),
                new EdjThreadFactory(),
                new CallerRunsPolicyWithLog()
        );
    }

    /**
     * 线程统一异常处理
     */
    private static void exceptionHandle(Thread t, Throwable e) {
        String threadName = t.getName();
        log.error("线程发生异常, 线程名: {}", threadName, e);
    }

    /**
     * 异步执行一个没有返回值的任务
     */
    public static CompletableFuture<Void> runAsync(Runnable runnable) {
        if (runnable == null) throw new ServerErrorException("异步任务为空");
        return CompletableFuture.runAsync(runnable, ASYNC_POOL);
    }

    /**
     * 异步执行多个并行的没有返回值的任务
     * 使用事务进行管理
     */
    public static void runAsyncTransaction(List<Runnable> runnableList) {
        if (CollUtils.isEmpty(runnableList)) {
            return;
        }

        // 任务数
        final int taskCount = runnableList.size();

        // 创建等量闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(taskCount);
        // 任务失败标志
        final AtomicBoolean wrong = new AtomicBoolean(false);
        // 记录任务执行异常
        final List<Exception> exceptionList = new ArrayList<>();

        // 执行任务
        List<CompletableFuture<Void>> futureList = runnableList
                .stream()
                .map(runnable -> CompletableFuture.runAsync(() -> {
                    // 检验是否可执行任务
                    if (wrong.get()) {
                        countDownLatch.countDown();
                        return;
                    }

                    // 开启事务
                    TransactionStatus status = TRANSACTION_MANAGER.getTransaction(new DefaultTransactionDefinition());

                    // 执行任务
                    // 获取数据库连接许可
                    ConnectSemaphore.acquire();
                    try {
                        if (!wrong.get()) {
                            // 执行 数据库\非数据库 任务
                            runnable.run();
                        }
                    } catch (Exception e) {
                        wrong.set(true);
                        exceptionList.add(e);
                    } finally {
                        countDownLatch.countDown();
                        // 释放数据库许可
                        ConnectSemaphore.release();
                    }

                    // 等待任务全部结束
                    try {
                        boolean success = countDownLatch.await(30, TimeUnit.SECONDS);
                        if (!success) {
                            wrong.set(true);
                        }
                    } catch (InterruptedException e) {
                        log.error("多线程任务执行中断错误: {}", e.getMessage(), e);
                        wrong.set(true);
                    }

                    // 事务管理
                    if (wrong.get()) {
                        // 失败回滚
                        log.debug("多线程事务失败回滚");
                        TRANSACTION_MANAGER.rollback(status);
                    } else {
                        // 成功提交
                        log.debug("多线程事务成功提交");
                        TRANSACTION_MANAGER.commit(status);
                    }

                }, ASYNC_POOL))
                .toList();
        // 聚合等待任务
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();

        // 抛出异常
        if (CollUtils.isNotEmpty(exceptionList)) {
            log.error("多线程任务执行报错: {}", exceptionList);
            throw new RuntimeException(exceptionList.getFirst());
        }
    }

    /**
     * 异步执行一个没有返回值的任务
     * 默认异常处理
     * 用于无需关注异常的任务
     */
    public static CompletableFuture<Void> runAsyncComplete(Runnable runnable) {
        return runAsync(runnable).whenComplete((result, throwable) -> {
            if (throwable != null) {
                exceptionHandle(Thread.currentThread(), throwable);
            }
        });
    }

    /**
     * 异步执行一个带有返回值的任务
     */
    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        if (supplier == null) throw new ServerErrorException("异步任务为空");
        return CompletableFuture.supplyAsync(supplier, ASYNC_POOL);
    }

    /**
     * 异步执行一个带有返回值的任务
     * 默认异常处理
     * 用于无需关注异常的任务
     */
    public static <U> CompletableFuture<U> supplyAsyncComplete(Supplier<U> supplier) {
        return supplyAsync(supplier).whenComplete((result, throwable) -> {
            if (throwable != null) {
                exceptionHandle(Thread.currentThread(), throwable);
            }
        });
    }

    /**
     * 异步执行一个带有返回值的任务
     */
    public static <T, U> CompletableFuture<U> thenApplyAsync(CompletableFuture<T> apply, Function<T, U> function) {
        if (apply == null || function == null) throw new ServerErrorException("异步任务为空");
        return apply.thenApplyAsync(function, ASYNC_POOL);
    }

    /**
     * 异步执行一个带有返回值的任务
     * 默认异常处理
     * 用于无需关注异常的任务
     */
    public static <T, U> CompletableFuture<U> thenApplyAsyncComplete(CompletableFuture<T> apply, Function<T, U> function) {
        return thenApplyAsync(apply, function).whenComplete((result, throwable) -> {
            if (throwable != null) {
                exceptionHandle(Thread.currentThread(), throwable);
            }
        });
    }
}
