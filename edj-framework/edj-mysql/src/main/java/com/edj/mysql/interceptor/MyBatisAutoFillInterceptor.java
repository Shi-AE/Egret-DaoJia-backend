package com.edj.mysql.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.ObjectUtils;
import com.edj.common.utils.ReflectUtils;
import com.edj.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.Map;

import static com.edj.mysql.constants.DbFiledConstants.*;
import static com.edj.mysql.constants.DbValueConstants.EXIST;


/**
 * @author A.E.
 * @date 2024/9/20
 */
@Slf4j
public class MyBatisAutoFillInterceptor implements InnerInterceptor {

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        // 更新操作
        updateExe(parameter);
        // 插入操作
        insertExe(ms, parameter);
    }

    private void insertExe(MappedStatement ms, Object parameter) {
        // 判断当前操作是否是插入操作
        if (ms.getSqlCommandType().equals(SqlCommandType.INSERT)) {
            // 判断是否有createBy字段，如果
            if (ObjectUtils.isNotNull(parameter) && ReflectUtils.containField(CREATE_BY, parameter.getClass())) {

                // 有userId也存在并设置createBy
                Long userId = SecurityUtils.getUserId();
                if (ObjectUtils.isNotNull(userId)) {
                    //4.当前操作人设置到创建人字段
                    ReflectUtils.setFieldValue(parameter, CREATE_BY, userId);
                }
            }

            // 判断是否有is_delete字段，
            if (ObjectUtils.isNotNull(parameter) && ReflectUtils.containField(IS_DELETE, parameter.getClass())) {
                ReflectUtils.setFieldValue(parameter, IS_DELETE, EXIST);
            }
        }
    }

    private void updateExe(Object parameter) {
        // 判断是否有updater字段
        if (ObjectUtils.isNotNull(parameter)) {

            Long userId = SecurityUtils.getUserId();

            // 如果有userId也存在并设置updater
            if (ObjectUtils.isNotNull(userId)) {

                Object t = parameter;
                // 如果通过 update wrapper 更新
                if (t instanceof Map<?, ?>) {
                    t = ((Map<?, ?>) t).get("et");
                }

                if (t == null) {
                    log.error("修改代码格式为: update(new Entity(), updateWrapper)");
                    throw new ServerErrorException();
                }

                // 当前用户设置到更新人字段
                if (ReflectUtils.containField(UPDATE_BY, t.getClass())) {
                    ReflectUtils.setFieldValue(t, UPDATE_BY, userId);
                }
            }
        }
    }
}
