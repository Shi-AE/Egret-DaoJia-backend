package com.edj.mvc.config;

import com.edj.common.utils.DateUtils;
import com.edj.mvc.serialize.BigDecimalSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;

import static com.edj.common.utils.DateUtils.*;


/**
 * @author A.E.
 * @date 2024/10/8
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
public class JsonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);
            jacksonObjectMapperBuilder.timeZone(DateUtils.TIME_ZONE_8);
            jacksonObjectMapperBuilder.serializers(new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer(dateTimeFormatter));
            jacksonObjectMapperBuilder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
            jacksonObjectMapperBuilder.serializers(new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer(dateFormatter));
            jacksonObjectMapperBuilder.deserializers(new LocalDateTimeDeserializer(dateFormatter));
            jacksonObjectMapperBuilder.serializers(new com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer(timeFormatter));
            jacksonObjectMapperBuilder.deserializers(new LocalDateTimeDeserializer(timeFormatter));
            // long -> string
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            // bigDecimal保留两位小数
            jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, BigDecimalSerializer.instance);
            //todo 是否需要反序列化？
        };
    }
}