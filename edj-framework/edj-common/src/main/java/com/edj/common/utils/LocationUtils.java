package com.edj.common.utils;

/**
 * 坐标相关工具类，处理经纬度
 *
 * @author A.E.
 * @date 2024/9/19
 */
public class LocationUtils {


    /**
     * 反转经纬度
     */
    public static String reversLatLon(String location) {
        return reversLatLon(location, ",");
    }

    /**
     * 反转经纬度
     */
    public static String reversLatLon(String location, String symbol) {
        if (StringUtils.isEmpty(location) || StringUtils.isEmpty(symbol)) {
            return location;
        }
        String[] locationSplit = location.split(symbol);
        if (locationSplit.length != 2) {
            return location;
        }
        return locationSplit[1] + symbol + locationSplit[0];
    }

    /**
     * 数字经纬度返回字符串经纬度
     */
    public static String getLocation(Double lon, Double lat) {
        if (lon == null || lat == null) {
            return null;
        }
        return String.format("%s,%s", lon, lat);
    }

}