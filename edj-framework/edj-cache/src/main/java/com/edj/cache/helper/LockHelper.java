package com.edj.cache.helper;

import com.edj.common.utils.NumberUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author A.E.
 * @date 2024/9/25
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LockHelper {

    private static final String LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;

    public void synchronizeLock(String key, Long waitTime, final Execution execution) {
        RLock lock = null;
        try {
            // 加锁
            lock = getRLock(key);
            if (!lock.tryLock(NumberUtils.null2Zero(waitTime), -1, TimeUnit.SECONDS)) {
                return;
            }
            // 加锁成功执行操作
            execution.execute();
        } catch (Exception e) {
            log.error("同步任务执行异常e:", e);
        } finally {
            // 解锁
            if (lock != null) {
                lock.unlock();
            }
        }
    }

    /**
     * 尝试加锁
     *
     * @param lockName  锁名
     * @param waitTime  等待时间，单位ms
     * @param leaseTime 租约时间，单位ms
     * @return 返回锁
     */
    public RLock tryLock(String lockName, long waitTime, long leaseTime, TimeUnit unit) {
        RLock lock = redissonClient.getLock(lockName);
        try {
            if (lock.tryLock(waitTime, leaseTime, unit)) {
                return lock;
            }
        } catch (InterruptedException ignored) {
        }
        return null;
    }

    /**
     * 解锁
     */
    public void unlock(RLock rLock) {
        if (rLock != null && rLock.isLocked()) {
            rLock.unlock();
        }
    }

    private RLock getRLock(String key) {
        return redissonClient.getLock(LOCK_PREFIX + key);
    }

    public interface Execution {
        /**
         * 加锁后操作
         */
        void execute();
    }

}
