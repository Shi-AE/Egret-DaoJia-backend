package com.edj.cache.sync.impl;

import com.edj.cache.properties.RedisSyncProperties;
import com.edj.cache.sync.SyncManager;
import com.edj.cache.sync.SyncThread;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import static com.edj.cache.constants.RedisSyncQueueConstants.STORAGE_TYPE_HASH;


@Slf4j
@Component
@RequiredArgsConstructor
public class SyncManagerImpl implements SyncManager {

    private static final ThreadPoolTaskExecutor DEFAULT_SYNC_EXECUTOR;

    static {
        DEFAULT_SYNC_EXECUTOR = new ThreadPoolTaskExecutor();
        DEFAULT_SYNC_EXECUTOR.setCorePoolSize(10);
        DEFAULT_SYNC_EXECUTOR.setMaxPoolSize(20);
        DEFAULT_SYNC_EXECUTOR.setQueueCapacity(999);
        DEFAULT_SYNC_EXECUTOR.setThreadNamePrefix("redis-queue-sync-");
        // 设置拒绝策略：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        DEFAULT_SYNC_EXECUTOR.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        DEFAULT_SYNC_EXECUTOR.initialize();
    }

    private final RedisTemplate redisTemplate;
    private final RedissonClient redissonClient;
    private final RedisSyncProperties redisSyncProperties;

    @Override
    public void start(String queueName, int storageType, int mode) {
        this.start(queueName, storageType, mode, DEFAULT_SYNC_EXECUTOR);
    }

    @Override
    public void start(String queueName, int storageType, int mode, final Executor dataSyncExecutor) {
        //根据队列的数量循环，将每个队列的数据同步任务提交到线程池
        for (int index = 0; index < redisSyncProperties.getQueueNum(); index++) {
            try {

                if (dataSyncExecutor == null) {//使用默认线程池
                    //使用getSyncThread方法获取任务对象
                    DEFAULT_SYNC_EXECUTOR.execute(Objects.requireNonNull(getSyncThread(queueName, index, storageType, mode)));
                } else {//使用自定义线程池
                    dataSyncExecutor.execute(Objects.requireNonNull(getSyncThread(queueName, index, storageType, mode)));
                }
            } catch (Exception e) {
                log.error("同步数据处理异常，e:", e);
            }
        }
    }

    /**
     * 获取线程对象
     *
     * @param queueName   队列名称
     * @param index       队列序号
     * @param storageType 存储结构 1：redis hash数据结构，2：redis list数据结构，3：redis zSet结构
     * @param mode        1 单条处理，2 批量处理
     */
    private SyncThread getSyncThread(String queueName, int index, Integer storageType, int mode) {
        // 目前组件支付同步Redis Hash结构的数据
        return storageType == STORAGE_TYPE_HASH ?
                new HashSyncThread(redissonClient, queueName, index, redisTemplate, redisSyncProperties.getPerCount(), mode) :
                null;
    }

}
