package com.edj.foundations.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 城市字典
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjCity extends EjdBaseEntity {
    /**
     * 城市ID
     */
    private Integer id;

    /**
     * 上级归属
     */
    private Integer parentId;

    /**
     * 类型（1省 2市）
     */
    private Integer type;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 城市名称
     */
    private String cityCode;

    /**
     * 城市名称拼音首字母
     */
    private String pinyinInitial;

    /**
     * 排序
     */
    private Integer sortNum;
}