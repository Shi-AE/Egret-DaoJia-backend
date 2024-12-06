package com.edj.rabbitmq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.edj.common.utils.DateUtils;
import com.edj.common.utils.JsonUtils;
import com.edj.common.utils.SqlUtils;
import com.edj.rabbitmq.domain.EdjFailMsg;
import com.edj.rabbitmq.mapper.EdjFailMsgMapper;
import com.edj.rabbitmq.service.EdjFailMsgService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 针对表【edj_fail_msg(消息队列失败消息)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/12/06
 */
@Service
@RequiredArgsConstructor
public class EdjFailMsgServiceImpl extends MPJBaseServiceImpl<EdjFailMsgMapper, EdjFailMsg> implements EdjFailMsgService {

    private final EdjFailMsgMapper failMsgMapper;

    @Override
    public void save(Long id, String exchange, String routingKey, Object msg, Long delay, Long nextFetchTime, String failMessage) {
        failMsgMapper.insert(EdjFailMsg
                .builder()
                .id(id)
                .exchange(exchange)
                .routingKey(routingKey)
                .msg(JsonUtils.toJsonStr(msg))
                .delayMsgExecuteTime(DateUtils.getCurrentTime() + delay)
                .nextFetchTime(nextFetchTime)
                .reason(failMessage)
                .build()
        );
    }

    @Override
    public boolean save(EdjFailMsg entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    public void removeById(Long msgId) {
        failMsgMapper.deleteById(msgId);
    }

    @Override
    public List<EdjFailMsg> fetch(Integer limit) {
        // 获取当前时间戳
        long currentTime = DateUtils.getCurrentTime();

        LambdaQueryWrapper<EdjFailMsg> wrapper = new LambdaQueryWrapper<EdjFailMsg>()
                .le(EdjFailMsg::getNextFetchTime, currentTime)
                .last("limit " + limit);

        List<EdjFailMsg> failMsgList = failMsgMapper.selectList(wrapper);

        List<Long> failMsgIdList = failMsgList
                .stream()
                .map(EdjFailMsg::getId)
                .toList();

        SqlUtils.actionBatch(failMsgIdList, list -> {
            LambdaUpdateWrapper<EdjFailMsg> updateWrapper = new LambdaUpdateWrapper<EdjFailMsg>()
                    .setIncrBy(EdjFailMsg::getNextFetchTime, 10)
                    .in(EdjFailMsg::getId, list);
            failMsgMapper.update(new EdjFailMsg(), updateWrapper);
        }, false);

        return failMsgList;
    }
}