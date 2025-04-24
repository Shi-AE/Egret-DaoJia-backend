package com.edj.common.constants;

import com.edj.common.expcetions.ServerErrorException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 数据库连接信号量
 *
 * @author A.E.
 * @date 2024/11/26
 */
@Slf4j
public class ConnectSemaphore {

    private ConnectSemaphore() {
    }

    private final static Semaphore SEMAPHORE;

    /**
     * 时间限制
     */
    private final static long TIMEOUT = 30;

    /**
     * 限制时间单位
     */
    private final static TimeUnit UNIT = TimeUnit.SECONDS;

    /**
     * 最大连接量
     */
    private final static int MAX_CONNECTIONS = 20;

    static {
        SEMAPHORE = new Semaphore(MAX_CONNECTIONS);
    }

    /**
     * 获取许可
     */
    public static void acquire(int permits) {
        if (permits > MAX_CONNECTIONS) {
            throw new ServerErrorException("非法获取数据库连接信号量");
        }
        try {
            boolean tryAcquire = SEMAPHORE.tryAcquire(permits, TIMEOUT, UNIT);
            if (!tryAcquire) {
                throw new ServerErrorException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取默认数量许可
     */
    public static void acquire() {
        acquire(1);
    }

    /**
     * 回收许可
     */
    public static void release(int permits) {
        SEMAPHORE.release(permits);
    }

    /**
     * 回收默认数量许可
     */
    public static void release() {
        release(1);
    }
}
