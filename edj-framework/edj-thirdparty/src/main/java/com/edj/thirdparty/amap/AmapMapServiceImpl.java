package com.edj.thirdparty.amap;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.edj.common.expcetions.BadRequestException;
import com.edj.thirdparty.amap.properties.AmapProperties;
import com.edj.thirdparty.core.map.MapService;
import com.edj.thirdparty.dto.MapLocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 高德地图服务
 *
 * @author A.E.
 * @date 2024/9/23
 */
@Service
@ConditionalOnBean(AmapProperties.class)
@RequiredArgsConstructor
public class AmapMapServiceImpl implements MapService {

    /**
     * 高德地理编码请求地址
     */
    private static final String GEO_URL = "https://restapi.amap.com/v3/geocode/geo?";
    /**
     * 高德地理逆编码请求地址
     */
    private static final String REGEO_URL = "https://restapi.amap.com/v3/geocode/regeo?";
    private final AmapProperties amapProperties;

    /**
     * 根据地址获取经纬度坐标
     *
     * @param address 地址
     * @return 经纬度坐标
     */
    @Override
    public String getLocationByAddress(String address) {
        //1.封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("address", address);
        params.put("key", amapProperties.getKey());

        //3.向高德发送请求，示例：https://restapi.amap.com/v3/geocode/geo?address=北京市朝阳区阜通东大街6号&output=XML&key=<用户的key>
        String jsonStr = HttpRequest.get(GEO_URL).form(params).execute().body();

        //4.从json中解析出经纬度坐标信息
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        JSONArray geocodes = JSONUtil.parseArray(jsonObject.get("geocodes"));
        if (geocodes.isEmpty()) {
            throw new BadRequestException("地址错误");
        }
        Object location = JSONUtil.parseObj(geocodes.getFirst()).get("location");
        if (ObjectUtil.isNull(location)) {
            throw new BadRequestException("地址错误");
        }

        return location.toString();
    }

    /**
     * 根据经纬度获取城市编码
     *
     * @param location 经纬度，经度在前，纬度在后
     * @return 城市信息
     */
    @Override
    public MapLocationDTO getCityCodeByLocation(String location) {
        //1.封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("location", location);
        params.put("key", amapProperties.getKey());
        params.put("extensions", "base");

        //2.向高德发送请求，示例：https://restapi.amap.com/v3/geocode/regeo?output=xml&location=116.310003,39.991957&key=<用户的key>&radius=1000&extensions=all
        String jsonStr = HttpRequest.get(REGEO_URL).form(params).execute().body();

        //3.从json中解析出经纬度坐标信息
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        JSONObject regeocode = jsonObject.getJSONObject("regeocode");
        JSONObject addressComponent = regeocode.getJSONObject("addressComponent");

        //城市编码
        String cityCode = (String) addressComponent.get("citycode");

        //省份
        Object province = addressComponent.get("province");

        //市
        Object city = addressComponent.get("city");

        //区
        Object district = addressComponent.get("district");

        //详细地址
        Object fullAddress = regeocode.get("formatted_address");

        //4.封装响应结果
        return MapLocationDTO
                .builder()
                .province(ObjectUtil.isEmpty(province) ? null : province.toString())
                .city(ObjectUtil.isEmpty(city) ? null : city.toString())
                .district(ObjectUtil.isEmpty(district) ? null : district.toString())
                .fullAddress(ObjectUtil.isEmpty(fullAddress) ? null : fullAddress.toString())
                .cityCode(cityCode)
                .build();
    }
}
