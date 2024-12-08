package com.edj.canal.listener;

import com.edj.canal.constants.OperateType;
import com.edj.canal.core.CanalDataHandler;
import com.edj.canal.domain.CanalBaseDTO;
import com.edj.canal.domain.CanalMqInfo;
import com.edj.common.utils.*;
import org.springframework.amqp.core.Message;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * mq 消息监听处理
 *
 * @author A.E.
 * @date 2024/12/7
 */
public abstract class AbstractCanalRabbitMqMsgListener<T> implements CanalDataHandler<T> {

    /**
     * 消息前置处理
     */
    public void parseMsg(Message message) {
        try {
            // 1.数据格式转换
            CanalMqInfo canalMqInfo = JsonUtils.toBean(new String(message.getBody()), CanalMqInfo.class);
            // 2.过滤数据，没有数据或者非插入、修改、删除的操作均不处理
            if (CollUtils.isEmpty(canalMqInfo.getData()) || !(OperateType.canHandle(canalMqInfo.getType()))) {
                return;
            }
            if (canalMqInfo.getData().size() > 1) {
                // 3.多条数据处理
                batchHandle(canalMqInfo);
            } else {
                // 4.单条数据处理
                singleHandle(canalMqInfo);
            }
        } catch (Exception e) {
            //出现错误延迟1秒重试
            ThreadUtils.sleep(1000);
            throw new RuntimeException(e);
        }
    }

    /**
     * 单消息处理
     */
    private void singleHandle(CanalMqInfo canalMqInfo) {
        // 1.数据转换
        CanalBaseDTO canalBaseDTO = BeanUtils.toBean(canalMqInfo, CanalBaseDTO.class);
        Map<String, Object> fieldMap = CollUtils.getFirst(canalMqInfo.getData());
        canalBaseDTO.setId(parseId(fieldMap));
        canalBaseDTO.setFieldMap(fieldMap);
        canalBaseDTO.setIsSave(canalMqInfo.getIsSave());

        Class<T> messageType = getMessageType();
        if (messageType == null) {
            return;
        }
        if (canalBaseDTO.getIsSave()) {
            T t = JsonUtils.toBean(JsonUtils.toJsonStr(canalBaseDTO.getFieldMap()), messageType);
            List<T> ts = Collections.singletonList(t);
            batchSave(ts);
        } else {
            Long id = canalBaseDTO.getId();
            List<Long> ids = Collections.singletonList(id);
            batchDelete(ids);
        }
    }

    /**
     * 批量消息处理
     */
    private void batchHandle(CanalMqInfo canalMqInfo) {
        Class<T> messageType = getMessageType();
        if (messageType == null) {
            return;
        }

        if (canalMqInfo.getIsSave()) {
            List<T> collect = canalMqInfo.getData()
                    .stream()
                    .map(fieldMap -> {
                        CanalBaseDTO canalBaseDTO = CanalBaseDTO.builder()
                                .id(parseId(fieldMap))
                                .database(canalMqInfo.getDatabase())
                                .table(canalMqInfo.getTable())
                                .isSave(canalMqInfo.getIsSave())
                                .fieldMap(fieldMap).build();
                        return JsonUtils.toBean(JsonUtils.toJsonStr(canalBaseDTO.getFieldMap()), messageType);
                    })
                    .toList();
            batchSave(collect);
        } else {
            List<Long> idList = canalMqInfo.getData()
                    .stream()
                    .map(this::parseId)
                    .toList();
            batchDelete(idList);
        }
    }

    /**
     * 解析id
     */
    private Long parseId(Map<String, Object> fieldMap) {
        Object objectId = fieldMap.get(IdUtils.ID);
        return NumberUtils.parseLong(objectId.toString());
    }

    /**
     * 获取泛型参数
     */
    @SuppressWarnings("unchecked")
    public Class<T> getMessageType() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType parameterizedType) {
            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            if (typeArgs.length > 0 && typeArgs[0] instanceof Class) {
                return (Class<T>) typeArgs[0];
            }
        }
        return null;
    }

    public abstract void batchSave(List<T> data);

    public abstract void batchDelete(List<Long> ids);
}
