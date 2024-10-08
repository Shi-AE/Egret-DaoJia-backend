package com.edj.thirdparty.core.map;


import com.edj.thirdparty.dto.MapLocationDTO;

/**
 * @author A.E.
 * @date 2024/10/8
 */
public interface MapService {
    /**
     * 根据地址获取经纬度坐标
     *
     * @param address 地址
     * @return 经纬度坐标
     */
    String getLocationByAddress(String address);

    /**
     * 根据经纬度获取城市编码
     *
     * @param location 经纬度，经度在前，纬度在后
     * @return 城市编码
     */
    MapLocationDTO getCityCodeByLocation(String location);
}
