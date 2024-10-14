package com.edj.mysql.utils;

import com.edj.common.domain.PageResult;
import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.common.utils.CollUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * 底层使用pageHelper实现的分页查询
 *
 * @author A.E.
 * @date 2024/9/20
 */
public class PageHelperUtils {

    /**
     * 分页查询数据
     *
     * @param pageQueryDTO 分页参数
     * @param condition    查询执行器
     * @return 查询结果
     */
    public static <T> PageResult<T> selectPage(PageQueryDTO pageQueryDTO, QueryExecutor<T> condition) {

        Integer pageSize = pageQueryDTO.getPageSize();
        PageHelper.startPage(pageQueryDTO.getPageNo(), pageSize, getOrder(pageQueryDTO));
        List<T> data = condition.query();
        if (data instanceof Page<T> page) {
            return new PageResult<>(page.getPages(), page.getTotal(), data);
        }
        long total = CollUtils.size(data);
        int pages = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        return new PageResult<>(pages, total, data);
    }

    private static String getOrder(PageQueryDTO pageQueryDTO) {
//        if (StringUtils.isEmpty(pageQueryDTO.getOrderBy1()) && StringUtils.isEmpty(pageQueryDTO.getOrderBy2())) {
//            return null;
//        }
//        StringBuilder buffer = new StringBuilder(" ");
//        if (StringUtils.isNotEmpty(pageQueryDTO.getOrderBy1())) {
//            buffer.append(StringUtils.toSymbolCase(pageQueryDTO.getOrderBy1(), '_'))
//                    .append(pageQueryDTO.getIsAsc1() ? " asc " : " desc ");
//        }
//        if (StringUtils.isNotEmpty(pageQueryDTO.getOrderBy2())) {
//            // 两个排序项需要中间添加“,”
//            if (StringUtils.isNotEmpty(pageQueryDTO.getOrderBy1())) {
//                buffer.append(",");
//            }
//            buffer.append(StringUtils.toSymbolCase(pageQueryDTO.getOrderBy2(), '_'))
//                    .append(pageQueryDTO.getIsAsc2() ? " asc " : " desc ");
//        }
//        return buffer.toString();
        return "";
    }

    /**
     * 查询执行器
     *
     * @param <T>
     */
    public interface QueryExecutor<T> {
        List<T> query();
    }
}
