package com.edj.cache.sync.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.edj.cache.domain.SyncMessage;
import com.edj.cache.handler.SyncProcessHandler;
import com.edj.cache.sync.SyncThread;
import com.edj.cache.utils.RedisSyncQueueUtils;
import com.edj.common.utils.CollUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Data
public abstract class AbstractSyncThread<T> implements SyncThread {
    /**
     * redisKey格式
     */
    private static final String REDIS_KEY_FORMAT = "%s:%s:{%s}";
    private static final String LOCK_PREFIX = "LOCK:";
    private final RedissonClient redissonClient;
    private final String queueName;
    private final int index;

    public AbstractSyncThread(RedissonClient redissonClient, String queueName, int index) {
        this.redissonClient = redissonClient;
        this.queueName = queueName;
        this.index = index;
    }

    @Override
    public void run() {
        // 1.使用redisson看门狗模式锁定当前序号的队列
        String lockName = LOCK_PREFIX + RedisSyncQueueUtils.getQueueRedisKey(queueName, index);
        RLock lock = redissonClient.getLock(lockName);
        try {
            if (!lock.tryLock(0, -1, TimeUnit.SECONDS)) {
                return;
            }
            // 2.获取数据
            List<SyncMessage<T>> data;
            while (CollUtils.isNotEmpty(data = this.getData())) {
                // 3.处理数据
                this.process(data);
                try {
                    //noinspection BusyWait
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception ignored) {
        } finally {
            // 4.解锁
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    /**
     * 获取数据
     */
    protected abstract List<SyncMessage<T>> getData();

    protected abstract boolean process(List<SyncMessage<T>> data);

    /**
     * 先获取和队列同名的处理器，如果获取不到，获取通类型的处理器
     */
    protected SyncProcessHandler<Object> getSyncProcessHandler() {
        SyncProcessHandler<Object> syncProcessHandler = SpringUtil.getBean(queueName, SyncProcessHandler.class);
        if (syncProcessHandler != null) {
            return syncProcessHandler;
        }
        throw new RuntimeException("未找到名称（" + queueName + "）redis 队列数据处理器");
    }
}
