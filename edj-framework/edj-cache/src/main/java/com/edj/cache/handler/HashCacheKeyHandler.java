package com.edj.cache.handler;

import java.util.List;

/**
 * @author A.E.
 * @date 2024/9/20
 */
public interface HashCacheKeyHandler {

    /**
     * 获取redis hash表的key表达式
     *
     * @param params 适配器入参
     * @return dataType
     */
    String key(List<Object> params);
}
