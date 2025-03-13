package com.edj.common.utils;

import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static cn.hutool.jwt.JWTHeader.ALGORITHM;
import static cn.hutool.jwt.RegisteredPayload.*;

public class JWTUtils extends JWTUtil {

    /**
     * 密钥
     */
    private static final byte[] JWT_KEY = "SecretKeyGuatComputerScienceAndEngineeringSoftwareEngineeringEgretDaoJiaDevelop".getBytes();
    private static final Map<String, Object> header = new HashMap<>();

    static {
        header.put(ALGORITHM, HmacAlgorithm.HmacSHA256);
    }

    /**
     * 生成token
     *
     * @param claims 负载
     * @return token
     */
    public static String createToken(Map<String, Object> claims) {
        return createToken(header, setPayload(claims), JWT_KEY);
    }

    /**
     * 默认头部生成token
     *
     * @param claims 负载
     * @return token
     */
    public static String createTokenDefault(Map<String, Object> claims) {
        return createToken(setPayload(claims), JWT_KEY);
    }

    /**
     * 设置公共负载数据
     */
    private static Map<String, Object> setPayload(Map<String, Object> claims) {
        // 签发时间
        LocalDateTime now = LocalDateTime.now();
        claims.put(ISSUED_AT, now);

        // 过期时间
        LocalDateTime expires = now.plus(Payload.EXPIRES_TIME, Payload.EXPIRES_UNIT);
        claims.put(EXPIRES_AT, expires);

        // 设置 jwt id 防止 token 重复
        claims.put(JWT_ID, IdUtils.fastSimpleUUID());

        return claims;
    }

    /**
     * 根据原 token 数据生成新 token
     */
    public static String refreshCreateToken(String refreshToken) {
        // 解析出负载加入新token
        JWT jwt = parseToken(refreshToken);
        JWTPayload payload = jwt.getPayload();
        Object id = payload.getClaim(Payload.ID);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Payload.ID, id);

        return createToken(claims);
    }

    /**
     * 负载常量
     */
    public final static class Payload {
        /**
         * id
         */
        private static final String ID = "id";

        /**
         * 过期时长
         */
        private static final Integer EXPIRES_TIME = 1;

        /**
         * 过期时长单位
         */
        private static final ChronoUnit EXPIRES_UNIT = ChronoUnit.DAYS;
    }
}
