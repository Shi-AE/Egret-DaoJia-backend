package com.edj.es.utils;

import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.CollUtils;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Map;

/**
 * es 文档工具
 *
 * @author A.E.
 * @date 2024/12/7
 */
public class DocumentUtils {

    /**
     * 转文档
     */
    public static <T> XContentBuilder convert(T document) {
        Map<String, Object> map = BeanUtils.beanToMap(document);
        if (CollUtils.isEmpty(map)) {
            return null;
        }
        try {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                xContentBuilder.field(entry.getKey(), entry.getValue());
            }
            return xContentBuilder.endObject();
        } catch (IOException e) {
            return null;
        }

    }
}
