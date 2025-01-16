package com.edj.trade.domain.entity;

import com.edj.api.api.publics.dto.LocationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AT事务模式的undo表 (AT transaction mode undo table)
 *
 * @author A.E.
 * @date 2024/12/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EdjUndoLog {
    /**
     * 分支事务ID (branch transaction id)
     */
    private Long branchId;

    /**
     * 全局事务ID (global transaction id)
     */
    private String xid;

    /**
     * undo_log上下文，如序列化信息 (undo_log context, such as serialization)
     */
    private String context;

    /**
     * 日志状态，0：正常状态，1：防御状态 (log status, 0: normal status, 1: defense status)
     */
    private Integer logStatus;

    /**
     * 创建时间 (create datetime)
     */
    private LocationDTO logCreated;

    /**
     * 修改时间 (modify datetime)
     */
    private LocationDTO logModified;

    /**
     * 回滚信息 (rollback info)
     */
    private byte[] rollbackInfo;
}