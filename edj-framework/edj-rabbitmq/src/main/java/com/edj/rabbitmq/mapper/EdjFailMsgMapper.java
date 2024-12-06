package com.edj.rabbitmq.mapper;

import com.edj.rabbitmq.domain.EdjFailMsg;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_fail_msg(消息队列失败消息)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/12/06
 */
@Mapper
public interface EdjFailMsgMapper extends MPJBaseMapper<EdjFailMsg> {
}