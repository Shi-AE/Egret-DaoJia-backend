package com.edj.rabbitmq.service;

import com.edj.rabbitmq.domain.EdjFailMsg;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 针对表【edj_fail_msg(消息队列失败消息)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/12/06
 */
public interface EdjFailMsgService extends MPJBaseService<EdjFailMsg> {

    /**
     * 保存失败消息
     */
    void save(Long id, String exchange, String routingKey, Object msg, Long delay, Long nextFetchTime, String failMessage);

    /**
     * 删除失败消息
     */
    void removeById(Long msgId);

    /**
     * 获取当前可执行消息，并修改执行消息执行的时间，防止重复执行
     *
     * @param limit 获取条数
     */
    List<EdjFailMsg> fetch(Integer limit);
}
