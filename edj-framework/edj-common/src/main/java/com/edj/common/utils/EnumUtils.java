package com.edj.common.utils;

import cn.hutool.core.util.EnumUtil;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.edj.common.expcetions.ServerErrorException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class EnumUtils extends EnumUtil {

    private static final String GET_METHOD_PREFIX = "get";

    private static final ConcurrentHashMap<Enum<?>, Object> ENUM_VALUE_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取值并指定返回类型
     */
    public static <E extends Enum<E>, T> T value(Enum<E> enumName, Class<T> clazz) {
        return clazz.cast(value(enumName));
    }

    /**
     * 获取 @EnumValue 注解的值
     */
    public static <E extends Enum<E>> Object value(Enum<E> enumName) {

        if (ENUM_VALUE_CACHE.containsKey(enumName)) {
            return ENUM_VALUE_CACHE.get(enumName);
        }

        Class<E> enumClass = enumName.getDeclaringClass();

        List<Field> fieldList = Arrays.stream(enumClass.getDeclaredFields())
                .filter(x -> !x.isEnumConstant() && x.isAnnotationPresent(EnumValue.class))
                .toList();

        if (CollUtils.isEmpty(fieldList)) {
            log.error("没有找到可解析的字段");
            throw new ServerErrorException();
        } else if (fieldList.size() == 1) {
            Field field = fieldList.getFirst();
            Object value = getValue(enumName, field, enumClass);
            ENUM_VALUE_CACHE.put(enumName, value);
            return value;
        } else {
            List<Object> list = fieldList.stream()
                    .map(field -> getValue(enumName, field, enumClass))
                    .toList();
            ENUM_VALUE_CACHE.put(enumName, list);
            return list;
        }
    }

    /**
     * 判断是否与枚举值相等
     */
    public static boolean eq(Enum<?> enumName, Object enumValue) {
        return Objects.equals(value(enumName), enumValue);
    }

    /**
     * 判断是否与其中一个枚举值相等
     */
    public static boolean eqAny(Object enumValues, Enum<?>... enumNames) {
        if (ArrayUtils.isEmpty(enumNames)) {
            return false;
        }

        for (Enum<?> enumName : enumNames) {
            if (eq(enumName, enumValues)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断是否与枚举值不等
     */
    public static boolean ne(Enum<?> enumName, Object enumValue) {
        return !eq(enumName, enumValue);
    }

    /**
     * 根据字段获取值
     */
    private static <E extends Enum<E>> Object getValue(Enum<E> enumName, Field field, Class<E> enumClass) {
        try {
            String fieldName = field.getName();
            Method getterMethod = enumClass.getMethod(GET_METHOD_PREFIX + StringUtils.upperFirst(fieldName));
            return getterMethod.invoke(enumName);
        } catch (NoSuchMethodException e) {
            log.error("枚举解析未找到方法，尝试添加getter方法或者确保getter方法名正确", e);
        } catch (IllegalAccessException e) {
            log.error("getter方法无法访问", e);
        } catch (InvocationTargetException e) {
            log.error("getter方法异常，请检查getter方法内部逻辑", e);
        }
        throw new ServerErrorException();
    }

    /**
     * 根据字段值获取枚举字段
     * 仅作单值匹配
     */
    public static <E extends Enum<E>> E getEnum(Object target, Class<E> enumClass) {
        if (target == null || enumClass == null) {
            throw new ServerErrorException();
        }
        for (E enumConstant : enumClass.getEnumConstants()) {
            Object enumValue = value(enumConstant);
            if (Objects.equals(target, enumValue)) {
                return enumConstant;
            }
        }
        throw new ServerErrorException();
    }
}
