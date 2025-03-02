package com.edj.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.IterUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 继承自 hutool 的集合工具类
 */
public class CollUtils extends CollectionUtil {

    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    public static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    public static <T> Set<T> singletonSet(T t) {
        return Collections.singleton(t);
    }

    public static <T> List<T> singletonList(T t) {
        return Collections.singletonList(t);
    }

    public static List<Integer> convertToInteger(List<String> originList) {
        return CollUtils.isNotEmpty(originList) ? originList.stream().map(NumberUtils::parseInt).collect(Collectors.toList()) : null;
    }

    public static List<Long> convertToLong(List<String> originLIst) {
        return CollUtils.isNotEmpty(originLIst) ? originLIst.stream().map(NumberUtils::parseLong).collect(Collectors.toList()) : null;
    }

    /**
     * 以 conjunction 为分隔符将集合转换为字符串 如果集合元素为数组、Iterable或Iterator，则递归组合其为字符串
     *
     * @param collection  集合
     * @param conjunction 分隔符
     * @param <T>         集合元素类型
     * @return 连接后的字符串
     * See Also: IterUtil.join(Iterator, CharSequence)
     */
    public static <T> String join(Collection<T> collection, CharSequence conjunction) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        return IterUtil.join(collection.iterator(), conjunction);
    }

    public static <T> String joinIgnoreNull(Collection<T> collection, CharSequence conjunction) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (T t : collection) {
            if (t == null) continue;
            sb.append(t).append(",");
        }
        if (sb.length() <= 0) {
            return null;
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 将元素加入到集合中，为null的过滤掉
     *
     * @param list 集合
     * @param data 要添加的数据
     * @param <T>  元素类型
     */
    @SafeVarargs
    public static <T> void add(Collection<T> list, T... data) {
        if (list == null || ArrayUtils.isEmpty(data)) {
            return;
        }
        for (T t : data) {
            if (ObjectUtils.isNotEmpty(t)) {
                list.add(t);
            }
        }
    }

    //将两个集合出现次数相加
    public static Map<Long, Integer> union(Map<Long, Integer> map1, Map<Long, Integer> map2) {
        if (CollUtils.isEmpty(map1)) {
            return map2;
        } else if (CollUtils.isEmpty(map2)) {
            return map1;
        }
        for (Map.Entry<Long, Integer> entry : map1.entrySet()) {
            map2.compute(entry.getKey(), (k, num) -> NumberUtils.null2Zero(num) + entry.getValue());
        }
        return map2;
    }

    public static <T, R> R getFiledOfFirst(List<T> list, Function<T, R> function) {
        if (CollUtils.isEmpty(list)) {
            return null;
        }
        return function.apply(list.getFirst());
    }

    /**
     * 将枚举集合表转换成列表
     *
     * @param enumeration 枚举集合
     * @param <T>         元素类型
     * @return 列表
     */
    public static <T> List<T> toList(Enumeration<T> enumeration) {
        if (!enumeration.hasMoreElements()) {
            return new ArrayList<>();
        }
        List<T> data = new ArrayList<>();
        T current;
        while ((current = enumeration.nextElement()) != null) {
            data.add(current);
        }
        return data;
    }

    /**
     * 获取数组为空的序号列表
     */
    public static List<Integer> getIndexListOfNullData(List<?> list) {
        if (isEmpty(list)) {
            return null;
        }

        return IntStream.range(0, list.size())
                .filter(i -> list.get(i) == null) // 过滤出值为 null 的索引
                .boxed()
                .toList();
    }

    public static <T> List<T> valueofIndexList(List<T> list, List<Integer> indexs) {
        if (CollUtils.isEmpty(indexs) || CollUtils.isEmpty(list)) {
            return null;
        }

        return indexs.stream()
                .map(list::get)
                .collect(Collectors.toList());
    }

    /**
     * 获取集合中某一列的值集合
     */
    public static <T, R> List<R> getFieldValues(List<T> list, Function<T, R> function) {
        if (isEmpty(list)) {
            return null;
        }
        return list.stream().map(function)
                .collect(Collectors.toList());
    }

    /**
     * 将两个列表合并出第三个列表
     */
    public static <T> List<T> union(List<T> list1, List<T> list2) {
        if (isEmpty(list1)) {
            return list2;
        }
        if (isEmpty(list2)) {
            return list1;
        }
        List<T> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    public static <K, V> Map<K, V> defaultIfEmpty(Map<K, V> originMap, Map<K, V> defaultMap) {
        return CollUtils.isEmpty(originMap) ? defaultMap : originMap;
    }
}