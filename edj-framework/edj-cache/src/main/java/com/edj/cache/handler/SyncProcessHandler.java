package com.edj.cache.handler;


import com.edj.cache.domain.SyncMessage;

import java.util.List;

public interface SyncProcessHandler<T> {

    /**
     * 批量处理同步队列中的数据
     */
    void batchProcess(List<SyncMessage<T>> multiData);

    /**
     * 单一处理数据
     */
    void singleProcess(SyncMessage<T> singleData);
}
