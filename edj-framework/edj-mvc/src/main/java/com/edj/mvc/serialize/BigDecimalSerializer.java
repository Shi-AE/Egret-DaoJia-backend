package com.edj.mvc.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal 序列化
 *
 * @author A.E.
 * @date 2024/9/20
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
    public static final BigDecimalSerializer instance = new BigDecimalSerializer();

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(bigDecimal.setScale(2, RoundingMode.DOWN).toString());
    }
}
