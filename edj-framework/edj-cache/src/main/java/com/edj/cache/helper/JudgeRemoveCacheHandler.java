package com.edj.cache.helper;

import java.util.List;

public interface JudgeRemoveCacheHandler {

    /**
     * 判断是否删除缓存
     */
    boolean judgeRemoveCache(List<Object> params);
}
