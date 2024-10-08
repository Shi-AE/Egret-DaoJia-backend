package com.edj.common.domain;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.HttpStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

/**
 * 基础返回消息
 *
 * @author A.E.
 * @date 2024/9/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "基础返回消息")
public class Result<T> {

    /**
     * 成功消息
     */
    public static final int SUCCESS = HttpStatus.HTTP_OK;
    public static final String OK = "OK";
    public static final int FAILED = 1;

    public static final byte[] OK_BYTES =
            String.format("{\"code\":%d,\"msg\":\"%s\",\"data\": {}}", SUCCESS, OK).getBytes(StandardCharsets.UTF_8);
    public static final byte[] OK_PREFIX =
            String.format("{\"code\":%d,\"msg\":\"%s\",\"data\": ", SUCCESS, OK).getBytes(StandardCharsets.UTF_8);
    public static final byte[] OK_SUFFIX = "}".getBytes(StandardCharsets.UTF_8);
    public static final byte[] OK_STR_PREFIX =
            String.format("{\"code\":%d,\"msg\":\"%s\",\"data\":", SUCCESS, OK).getBytes(StandardCharsets.UTF_8);
    public static final byte[] OK_STR_SUFFIX = "}".getBytes(StandardCharsets.UTF_8);

    @Schema(description = "业务状态码，200-成功，其它-失败")
    private int code;
    @Schema(description = "响应消息", example = "OK")
    private String msg;
    @Schema(description = "响应数据")
    private T data;
    @Schema(description = "请求id", example = "1af123c11412e")
    private String requestId;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result<Void> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(HttpStatus.HTTP_OK, OK, data);
    }


    public static <T> Result<T> error(String msg) {
        return error(msg, null);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
    }

    public static byte[] plainOk() {
        return OK_BYTES;
    }

    public static byte[] plainOk(byte[] data) {
        if (data == null || data.length == 0) {
            return OK_BYTES;
        }
        byte b = data[0];
        if (b == 91 || b == 123) {
            return ArrayUtil.addAll(OK_PREFIX, data, OK_SUFFIX);
        }
        return ArrayUtil.addAll(OK_STR_PREFIX, data, OK_STR_SUFFIX);
    }

    public boolean isSuccess() {
        return code == HttpStatus.HTTP_OK;
    }
}
