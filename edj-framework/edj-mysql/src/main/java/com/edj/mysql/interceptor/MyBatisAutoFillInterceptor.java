package com.edj.mysql.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.edj.common.expcetions.ServerErrorException;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.common.utils.ReflectUtils;
import com.edj.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;

import static com.edj.mysql.constants.DbFiledConstants.*;
import static com.edj.mysql.constants.DbValueConstants.EXIST;


/**
 * @author A.E.
 * @date 2024/9/20
 */
@Slf4j
public class MyBatisAutoFillInterceptor implements InnerInterceptor {

    private static final int COLUMN_WIDTH = 20;

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        // 更新操作
        updateExe(parameter);
        // 插入操作
        insertExe(ms, parameter);
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {

        // 只有debug级别输出explain
        if (!log.isDebugEnabled()) {
            return;
        }

        // 获取配置
        Configuration configuration = ms.getConfiguration();
        // 获取参数对象
        Object parameterObject = boundSql.getParameterObject();
        // 获取参数映射
        List<ParameterMapping> params = boundSql.getParameterMappings();
        // 获取到执行的SQL
        String sql = boundSql.getSql();
        // SQL中多个空格使用一个空格代替
        sql = sql.replaceAll("\\s+", " ");
        if (!ObjectUtils.isEmpty(params) && !ObjectUtils.isEmpty(parameterObject)) {
            // TypeHandlerRegistry 是 MyBatis 用来管理 TypeHandler 的注册器 TypeHandler 用于在 Java 类型和 JDBC 类型之间进行转换
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            // 如果参数对象的类型有对应的 TypeHandler，则使用 TypeHandler 进行处理
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                // 否则，逐个处理参数映射
                for (ParameterMapping param : params) {
                    // 获取参数的属性名
                    String propertyName = param.getProperty();
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    // 检查对象中是否存在该属性的 getter 方法，如果存在就取出来进行替换
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        if (obj.getClass().isEnum()) {
                            obj = EnumUtils.value((Enum<?>) obj);
                        }
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                        // 检查 BoundSql 对象中是否存在附加参数
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        // SQL匹配不上，带上“缺失”方便找问题
                        sql = sql.replaceFirst("\\?", "缺失");
                    }
                }
            }
        }

        // 构造 EXPLAIN 查询
        String explainSql = "EXPLAIN " + sql;

        // 获取数据库连接
        Connection connection = executor.getTransaction().getConnection();

        // 执行 EXPLAIN 查询
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(explainSql)) {
            log.debug("<== {}", sql);
            while (rs.next()) {
                log.debug("""
                        <== EXPLAIN:
                        | id            | {}
                        | select_type   | {}
                        | table         | {}
                        | partitions    | {}
                        | type          | {}
                        | possible_keys | {}
                        | key           | {}
                        | key_len       | {}
                        | ref           | {}
                        | rows          | {}
                        | filtered      | {}
                        | Extra         | {}""",
                        rs.getString("id"),
                        rs.getString("select_type"),
                        rs.getString("table"),
                        rs.getString("partitions"),
                        rs.getString("type"),
                        rs.getString("possible_keys"),
                        rs.getString("key"),
                        rs.getString("key_len"),
                        rs.getString("ref"),
                        rs.getInt("rows"),
                        rs.getInt("filtered"),
                        rs.getString("Extra")
                );
            }
        } catch (Exception e) {
            log.error("Failed to execute EXPLAIN for SQL: {}", sql, e);
        }
    }

    private static String getParameterValue(Object object) {
        String value = "";
        if (object instanceof String) {
            value = "'" + object + "'";
        } else if (object instanceof Date) {
            DateFormat format = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + format.format((Date) object) + "'";
        } else if (!ObjectUtils.isEmpty(object)) {
            value = object.toString();
        }
        return value;
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
