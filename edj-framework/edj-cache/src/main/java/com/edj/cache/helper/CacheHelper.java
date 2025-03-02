package com.edj.cache.helper;

import com.edj.common.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author A.E.
 * @date 2024/9/20
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Component
@RequiredArgsConstructor
public class CacheHelper {

    private final RedisTemplate redisTemplate;

    /**
     * 缓存实现
     */
    public <T> T get(String key, DataExecutor<T> dataExecutor, Class<T> clazz, Long ttl) {
        Object value = redisTemplate.opsForValue().get(key);
        // 缓存为“”直接返回是为了防止缓存穿透， 缓存有值直接返回
        if (StringUtils.EMPTY.equals(value)) {
            return null;
        }
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            // 自动延长redis有效期
            redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
            if (JsonUtils.isTypeJSON(value.toString())) {
                return BeanUtils.toBean(value, clazz);
            } else {
                return (T) value;
            }
        }
        // 默认有效期5分钟
        if (ttl == null) {
            ttl = 5 * 60L;
        }
        // 获取数据
        T data = dataExecutor.execute();
        // 写入缓存，
        if (data == null) {
            redisTemplate.opsForValue().set(key, StringUtils.EMPTY, ttl, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, JsonUtils.toJsonStr(data), ttl, TimeUnit.SECONDS);
        }
        return data;
    }

    /**
     * 批量获取缓存数据，按照id列表顺序返回目标数据,如果缓存不存在则查询数据库
     *
     * @param key               目标数据类型，CACHE_加dataType 为redisKey
     * @param objectIds              目标数据唯一id
     * @param batchDataQueryExecutor 批量目标数据获取执行器用于当缓存数据不存在时查询数据库
     * @param clazz                  目标数据类型class
     * @param ttl                    目标数据整体过期时间(ttl大于0才会设置有效期)
     * @param <K>                    目标数据id数据类型
     * @param <T>                    目标数据类型
     */
    public <K, T> List<T> batchGet(String key, List<K> objectIds, BatchDataQueryExecutor<K, T> batchDataQueryExecutor, Class<T> clazz, Long ttl) {
        // 1.缓存获取数据
        // 1.1.参数校验
        if (StringUtils.isEmpty(key) || CollUtils.isEmpty(objectIds)) {
            return null;
        }
        // 1.2.redis key
        // 1.3.获取缓存
        List<T> list = redisTemplate.opsForHash().multiGet(key, objectIds);

        // 2.缓存未得数据获取
        // 2.1.获取未缓存数据的objectIds序号列表
        List<Integer> noCacheObjectIdIndexList = CollUtils.getIndexListOfNullData(list);
        if (CollUtils.isEmpty(noCacheObjectIdIndexList)) {
            return BeanUtils.copyToList(list, clazz);
        }
        // 2.2.获取未缓存数据的objectId列表
        List<K> noCacheObjectIds = CollUtils.valueofIndexList(objectIds, noCacheObjectIdIndexList);
        if (batchDataQueryExecutor == null) {
            return BeanUtils.copyToList(list, clazz);
        }
        // 3.获取未缓存数据
        Map<K, T> data = batchDataQueryExecutor.execute(noCacheObjectIds, clazz);
        if (CollUtils.isEmpty(data)) {
            return BeanUtils.copyToList(list, clazz);
        }
        // 4.未加入缓存数据入缓存
        redisTemplate.opsForHash().putAll(key, data);
        // 5.动态设置缓存过期时间
        if (ttl > 0) {
            redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        }
        // 6.组装返回数据，保证数据根据objectIds列表顺序返回
        for (Integer noCacheObjectIdIndex : noCacheObjectIdIndexList) {
            list.set(noCacheObjectIdIndex, data.get(objectIds.get(noCacheObjectIdIndex)));
        }
        return BeanUtils.copyToList(list, clazz);
    }

    /**
     * 批量获取缓存数据，按照id列表顺序返回目标数据
     *
     * @param key               目标数据类型，例如评价数据
     * @param batchDataQueryExecutor 目标数据获取执行器
     * @param clazz                  目标数据类型class
     * @param ttl                    目标数据整体过期时间(ttl大于0才会设置有效期)
     * @param <K>                    目标数据id数据类型
     * @param <T>                    目标数据类型
     * @return 查询结果
     */
    public <K, T> List<T> getAll(String key, BatchDataQueryExecutor<K, T> batchDataQueryExecutor, Class<T> clazz, Long ttl) {

        // 从缓存查询数据
        Map<K, T> entries = redisTemplate.opsForHash().entries(key);
        Collection<T> values = entries.values();

        // 缓存中已查询出数据或不存在目标数据执行器，直接返回
        if (null == batchDataQueryExecutor || ObjectUtils.isNotEmpty(values)) {
            return BeanUtils.copyToList(values, clazz);
        }

        // 执行目标数据查询执行器,使用者可以在查询执行方法中写入控制，来阻止缓存穿透
        Map<K, T> data = batchDataQueryExecutor.execute(null, clazz);
        if (data.size() <= 0) {
            return BeanUtils.copyToList(values, clazz);
        }

        // 存储数据到缓存
        redisTemplate.opsForHash().putAll(key, data);
        if (ttl > 0) {
            redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        }

        // 返回查询结果
        return BeanUtils.copyToList(values, clazz);
    }

    /**
     * 推送缓存到数据库
     *
     * @param key    目标数据类型，例如评价数据
     * @param data        缓存数据
     * @param keyFunction hashKey获取函数
     * @param ttl         目标数据整体过期时间(ttl大于0才会设置有效期)
     * @param <K>         目标数据id数据雷兴国
     * @param <T>         目标数据类型
     */
    public <K, T> void doPutAll(String key, List<T> data, Function<T, K> keyFunction, Long ttl) {
        //1.构建redisKey
        if (CollUtils.isEmpty(data)) {
            return;
        }
        K apply = keyFunction.apply(data.getFirst());
        Map<K, T> map = data.stream().collect(Collectors.toMap(keyFunction, d -> d));
        //5.存储数据到缓存
        redisTemplate.opsForHash().putAll(key, map);
        if (ttl > 0) {
            redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        }
    }

    public <K> void batchRemove(String key, List<K> objectIds) {
        redisTemplate.opsForHash().delete(key, objectIds.toArray());
    }

    /**
     * 批量删除缓存
     *
     * @param keys 缓存key列表
     */
    public void batchRemove(List<String> keys) {
        // 2.批量删除
        redisTemplate.delete(keys);
    }

    /**
     * 删除缓存
     *
     * @param key 缓存key
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 分页缓存删除
     *
     * @param key 数据类型
     * @param objectId 对象id
     */
    public <K> void remove(String key, K objectId) {
        redisTemplate.opsForHash().delete(key, objectId);
    }

    public interface DataExecutor<T> {
        T execute();
    }

    /**
     * 数据查询执行器
     *
     * @param <T>
     */
    public interface BatchDataQueryExecutor<K, T> {
        /**
         * 查询key对应的值
         *
         * @param objectIds 没有命中的对象id列表
         * @param clazz     数据转换类型
         */
        Map<K, T> execute(List<K> objectIds, Class<T> clazz);
    }
}
