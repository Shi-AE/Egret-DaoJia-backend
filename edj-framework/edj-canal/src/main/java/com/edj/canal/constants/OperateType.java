package com.edj.canal.constants;

/**
 * 操作类型
 *
 * @author A.E.
 * @date 2024/12/7
 */
public class OperateType {
    /**
     * 新增操作
     */
    public static final String INSERT = "INSERT";
    /**
     * 修改操作
     */
    public static final String UPDATE = "UPDATE";
    /**
     * 删除操作
     */
    public static final String DELETE = "DELETE";

    public static boolean isSave(String operateType) {
        return !(!INSERT.equals(operateType) && !UPDATE.equals(operateType));
    }

    public static boolean canHandle(String operateType) {
        return INSERT.equals(operateType) || UPDATE.equals(operateType) || DELETE.equals(operateType);
    }
}
