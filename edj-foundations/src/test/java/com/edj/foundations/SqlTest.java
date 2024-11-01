package com.edj.foundations;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.foundations.domain.entity.EdjCity;
import com.edj.foundations.enums.EdjCityType;
import com.edj.foundations.mapper.EdjCityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SqlTest {

    @Autowired
    EdjCityMapper cityMapper;

    @Test
    void test() {
        LambdaQueryWrapper<EdjCity> cityLambdaQueryWrapper = new LambdaQueryWrapper<EdjCity>()
                .select(EdjCity::getSortNum, EdjCity::getName)
                .eq(EdjCity::getId, 18)
                .eq(EdjCity::getType, EdjCityType.CITY);
        EdjCity city = cityMapper.selectOne(cityLambdaQueryWrapper);
        System.out.println(city);
    }
}
