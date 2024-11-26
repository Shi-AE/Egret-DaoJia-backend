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

    private final static long TIMEOUT = 30;

    private final static TimeUnit UNIT = TimeUnit.SECONDS;

    static {
        SEMAPHORE = new Semaphore(10);
    }

    /**
     * 获取许可
     */
    public static void acquire(int permits) {
        try {
            boolean tryAcquire = SEMAPHORE.tryAcquire(permits, TIMEOUT, UNIT);
            if (!tryAcquire) {
                throw new ServerErrorException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void release(int permits) {
        SEMAPHORE.release(permits);
    }
}
