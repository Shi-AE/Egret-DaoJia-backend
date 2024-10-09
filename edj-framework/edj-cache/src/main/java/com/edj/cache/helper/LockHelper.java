package com.edj.cache.helper;

import com.edj.common.expcetions.ServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author A.E.
 * @date 2024/9/25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LockHelper {

    private static final String LOCK_PREFIX = "LOCK:";

    private static final String READ_WRITE_LOCK_PREFIX = "READ_WRITE_LOCK:";

    private static final String CHECK_LOCK_KEY = "CHECK_LOCK";

    // 简单、快速操作常量
    public static final long SIMPLE_OPERATION_WAIT_TIME = 5; // 等待时间 1-5 秒

    // 中等复杂度操作常量
    public static final long MEDIUM_OPERATION_WAIT_TIME = 10; // 等待时间 5-10 秒

    // 复杂、耗时操作常量
    public static final long COMPLEX_OPERATION_WAIT_TIME = 20; // 等待时间 10-20 秒

    // 时间单位常量
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private final RedissonClient redissonClient;

    /**
     * <p> 加同步锁
     * <p> 未上锁执行加锁逻辑
     * <p> 已上锁执行逻辑
     *
     * @param lockName        锁名
     * @param waitTime        等待时长，单位s
     * @param executionUnlock 未加锁执行逻辑
     * @param executionLocked 已加锁执行逻辑
     */
    public void syncLockAndElse(String lockName, long waitTime, final Execution executionUnlock, final Execution executionLocked) {
        // 检查锁锁
        RReadWriteLock readWriteLock = null;
        try {
            // 获取读写锁
            readWriteLock = getRReadWriteLock(lockName);

            RLock readLock = readWriteLock.readLock();
            RLock writeLock = readWriteLock.writeLock();

            // 用过加锁同步获取是否加锁
            AtomicBoolean isLocked = new AtomicBoolean(false);
            syncLock(CHECK_LOCK_KEY, SIMPLE_OPERATION_WAIT_TIME, () -> {
                // 判断是否加上写锁
                isLocked.set(writeLock.isLocked());
                log.debug("判断是否加上写锁 isLocked={}", isLocked.get());

                // 提前加写锁，避免读锁偷跑
                if (!isLocked.get()) {
                    tryLock(writeLock, SIMPLE_OPERATION_WAIT_TIME, -1, TIME_UNIT);
                    log.debug("加写锁成功");
                }
            });

            if (isLocked.get()) {
                // 执行已加写锁逻辑
                // 加读锁
                tryLock(readLock, waitTime, -1, TIME_UNIT);
                log.debug("加读锁成功");
                executionUnlock.execute();
                log.debug("已加锁逻辑已完成");
            } else {
                // 执行未加写锁逻辑
                // 加写锁
                executionLocked.execute();
                log.debug("未加锁逻辑已完成");
            }
        } catch (Exception e) {
            log.error("执行加锁业务逻辑错误", e);
            unlock(readWriteLock);
        }
    }

    /**
     * 普通锁同步锁
     *
     * @param lockName  锁名
     * @param waitTime  等待时间，单位s
     * @param execution 加锁后执行函数
     */
    public void syncLock(String lockName, long waitTime, final Execution execution) {
        RLock lock = null;
        try {
            // 加锁
            lock = tryLock(lockName, waitTime, -1, TIME_UNIT);
            // 加锁成功执行操作
            execution.execute();
        } catch (Exception e) {
            log.error("同步锁任务执行异常e:", e);
        } finally {
            // 解锁
            unlock(lock);
        }
    }

    /**
     * 给传入的锁尝试加锁
     */
    public void tryLock(RLock lock, long waitTime, long leaseTime, TimeUnit unit) {
        try {
            if (lock.tryLock(waitTime, leaseTime, unit)) {
                return;
            }
        } catch (InterruptedException e) {
            log.error("尝试加锁失败", e);
        }
        log.error("尝试加锁失败");
        throw new ServerErrorException();
    }

    /**
     * 获取普通锁尝试加锁
     *
     * @param lockName  锁名
     * @param waitTime  等待时间，单位ms
     * @param leaseTime 租约时间，单位ms
     * @return 返回锁
     */
    public RLock tryLock(String lockName, long waitTime, long leaseTime, TimeUnit unit) {
        RLock lock = getRLock(lockName);
        tryLock(lock, waitTime, leaseTime, unit);
        return lock;
    }

    /**
     * 获取普通锁
     */
    private RLock getRLock(String lockName) {
        return redissonClient.getLock(LOCK_PREFIX + lockName);
    }

    /**
     * 获取读写锁
     */
    private RReadWriteLock getRReadWriteLock(String lockName) {
        return redissonClient.getReadWriteLock(READ_WRITE_LOCK_PREFIX + lockName);
    }

    /**
     * 解锁
     */
    public void unlock(RLock rLock) {
        if (rLock != null && rLock.isLocked()) {
            rLock.unlock();
        }
    }

    /**
     * 解锁全部读写锁
     */
    public void unlock(RReadWriteLock readWriteLock) {
        if (readWriteLock == null) {
            return;
        }
        RLock writeLock = readWriteLock.writeLock();
        RLock readLock = readWriteLock.readLock();
        if (writeLock.isLocked()) {
            writeLock.unlock();
        }
        if (readLock.isLocked()) {
            readLock.unlock();
        }
    }

    /**
     * 加锁操作函数接口
     */
    public interface Execution {
        /**
         * 加锁后操作
         */
        void execute();
    }

}
