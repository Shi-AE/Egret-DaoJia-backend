package com.edj.mysql.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edj.common.domain.PageResult;
import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.common.handler.ConvertHandler;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.IdUtils;
import com.edj.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具
 *
 * @author A.E.
 * @date 2024/9/20
 */
public class PageUtils {

    /**
     * mybatis的分页数据是否为空
     */
    public static <T> boolean isEmpty(Page<T> page) {
        return page == null || CollUtils.isEmpty(page.getRecords());
    }

    /**
     * 判断mybatis的分页数据不为空
     */
    public static <T> boolean isNotEmpty(Page<T> page) {
        return page != null && !CollUtils.isEmpty(page.getRecords());
    }

    /**
     * 分页数据转换，主要场景是从数据库中查出来的数据转换成DTO，或者VO
     *
     * @param originPage     从数据库查询出来的分页数据
     * @param targetClazz    目标对象class
     * @param convertHandler 数据库对象转换DTO或者VO的转换器，用于转换复杂的数据
     * @param <T>            目标对象类型
     * @param <O>            源对象类型
     * @return 用于传递的分页数据
     */
    public static <O, T> PageResult<T> toPage(Page<O> originPage, Class<T> targetClazz, ConvertHandler<O, T> convertHandler) {
        if (isEmpty(originPage)) {
            return new PageResult<>(0, 0L, new ArrayList<>());
        }

        return new PageResult<>((int) originPage.getPages(), originPage.getTotal(),
                BeanUtils.copyToList(originPage.getRecords(), targetClazz, convertHandler));
    }

    /**
     * 分页数据转换返给其他微服务，主要场景是从数据库中查出来的数据转换成DTO，或者VO
     *
     * @param originPage  从数据库查询出来的分页数据
     * @param targetClazz 目标对象class
     * @param <X>         目标对象类型
     * @param <Y>         源对象类型
     * @return 用于传递的分页数据
     */
    public static <X, Y> PageResult<Y> toPage(Page<X> originPage, Class<Y> targetClazz) {
        if (isEmpty(originPage)) {
            return new PageResult<>(0, 0L, new ArrayList<>());
        }

        return new PageResult<>((int) originPage.getPages(), originPage.getTotal(),
                BeanUtils.copyToList(originPage.getRecords(), targetClazz));
    }


    /**
     * 将前端传来的分页查询条件转换成数据库的查询page,
     * 如果进行排序必须填写targetClazz
     * 该方法支持简单的数据字段排序，不支持统计排序
     *
     * @param pageQueryDTO 前端传来的查询条件
     * @param <T>          查询数据库po
     * @return mybatis-plus 分页查询page
     */
    public static <T> Page<T> parsePageQuery(PageQueryDTO pageQueryDTO) {
        Page<T> page = new Page<>(pageQueryDTO.getPageNo(), pageQueryDTO.getPageSize());

        List<PageQueryDTO.OrderBy> orderByList = pageQueryDTO.getOrderByList();

        //是否排序
        if (orderByList != null) {
            List<OrderItem> orderItemList = orderByList
                    .stream()
                    .map(x -> {
                        String orderBy = x.getOrderBy();
                        Boolean isAsc = x.getIsAsc();
                        OrderItem orderItem = new OrderItem();
                        orderItem.setColumn(StringUtils.toSymbolCase(orderBy, '_'));
                        orderItem.setAsc(isAsc);
                        return orderItem;
                    })
                    .toList();
            page.addOrder(orderItemList);
        }

        // 默认按照添加逆序排序
        page.addOrder(OrderItem.desc(IdUtils.ID));

        return page;
    }

    public static Long pages(Long total, Long pageSize) {
        return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }

}
