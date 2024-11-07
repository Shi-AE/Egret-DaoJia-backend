package com.edj.api.api.publics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 经纬度
 *
 * @author A.E.
 * @date 2024/11/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    /**
     * 经纬度
     */
    private String location;
}
