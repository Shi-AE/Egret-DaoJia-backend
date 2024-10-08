package com.edj.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 经纬度
 *
 * @author A.E.
 * @date 2024/9/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

}
