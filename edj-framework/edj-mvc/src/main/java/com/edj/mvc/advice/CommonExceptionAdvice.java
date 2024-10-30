package com.edj.mvc.advice;

import cn.hutool.http.HttpStatus;
import com.edj.common.constants.ErrorInfo;
import com.edj.common.domain.Result;
import com.edj.common.expcetions.CommonException;
import com.edj.common.utils.Base64Utils;
import com.edj.common.utils.JsonUtils;
import com.edj.common.utils.NumberUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.mvc.constants.HeaderConstants;
import com.edj.mvc.utils.RequestUtils;
import com.edj.mvc.utils.ResponseUtils;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

import static com.edj.mvc.constants.HeaderConstants.BODY_PROCESSED;

/**
 * 异常捕获
 *
 * @author A.E.
 * @date 2024/9/19
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionAdvice {

    /**
     * 捕获feign异常
     *
     * @param e 异常
     * @return 信息
     */
    @ExceptionHandler({FeignException.class})
    public Result<?> feignException(FeignException e) {
        ResponseUtils.setResponseHeader(BODY_PROCESSED, "1");
        Object headerValue = e.responseHeaders().get(HeaderConstants.INNER_ERROR);

        if (Objects.requireNonNull(RequestUtils.getRequest()).getRequestURL().toString().contains("/inner/")) {
            // 内部接口调用内部接口，异常抛出
            if (ObjectUtils.isNull(headerValue)) {
                throw new CommonException(ErrorInfo.Msg.REQUEST_FAILED);
            }
            String encodeMsg = JsonUtils.parseArray(headerValue).getStr(0);
            String[] msgs = Base64Utils.decodeStr(encodeMsg).split("\\|");
            throw new CommonException(NumberUtils.parseInt(msgs[0]), msgs[1]);
        } else {
            // 外部接口调用内部接口异常捕获
            if (ObjectUtils.isNull(headerValue)) {
                return Result.error(ErrorInfo.Msg.REQUEST_FAILED);
            }
            String encodeMsg = JsonUtils.parseArray(headerValue).getStr(0);
            String[] msgs = Base64Utils.decodeStr(encodeMsg).split("\\|");
            return Result.error(NumberUtils.parseInt(msgs[0]), msgs[1]);
        }
    }

    /**
     * 自定义异常处理
     *
     * @param e 异常
     * @return 信息
     */
    @ExceptionHandler({CommonException.class})
    public Result<?> customException(CommonException e) {

        log.error("请求异常, message:{}", e.getMessage(), e);
        // 标识异常已被处理
        ResponseUtils.setResponseHeader(BODY_PROCESSED, "1");
        if (Objects.requireNonNull(RequestUtils.getRequest()).getRequestURL().toString().contains("/inner/")) {
            CommonException commonException = new CommonException(e.getCode(), e.getMessage());
            ResponseUtils.setResponseHeader(HeaderConstants.INNER_ERROR, Base64Utils.encodeStr(e.getCode() + "|" + e.getMessage()));
            throw commonException;
        }
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 入参校验异常
     */
    @ExceptionHandler({BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public Result<List<String>> handleValidatedException(Exception e) {
        // 标识异常已被处理
        ResponseUtils.setResponseHeader(BODY_PROCESSED, "1");
        List<String> collect = List.of();

        if (e instanceof MethodArgumentNotValidException) {
            // BeanValidation exception
            collect = ((MethodArgumentNotValidException) e)
                    .getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
        }
        if (e instanceof ConstraintViolationException) {
            // BeanValidation GET simple param
            collect = ((ConstraintViolationException) e)
                    .getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
        }
        if (e instanceof BindException) {
            // BeanValidation GET object param
            collect = ((BindException) e)
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
        }

        if (Objects.requireNonNull(RequestUtils.getRequest()).getRequestURL().toString().contains("/inner/")) {
            CommonException commonException = new CommonException(ErrorInfo.Msg.INVALID_PARAMETERS);
            ResponseUtils.setResponseHeader(HeaderConstants.INNER_ERROR, Base64Utils.encodeStr("500|" + collect));
            throw commonException;
        }
        return Result.error(ErrorInfo.Msg.INVALID_PARAMETERS, collect);
    }

    /**
     * <p> 鉴权异常 403
     * <p> AuthorizationDeniedException
     * <p> AccessDeniedException: 当用户已认证，但没有权限访问特定资源时抛出。
     * <p> AuthenticationException: 认证失败时抛出的基类异常。
     *
     * @author A.E.
     * @date 2024/10/8
     */
    @ExceptionHandler({AuthorizationDeniedException.class, AccessDeniedException.class, AccessDeniedException.class})
    public Result<?> notAccessException(Exception e) {
        // 标识异常已被处理
        ResponseUtils.setResponseHeader(BODY_PROCESSED, "1");
        if (Objects.requireNonNull(RequestUtils.getRequest()).getRequestURL().toString().contains("/inner/")) {
            CommonException commonException = new CommonException(ErrorInfo.Msg.REQUEST_FAILED);
            ResponseUtils.setResponseHeader(HeaderConstants.INNER_ERROR, Base64Utils.encodeStr("403|" + ErrorInfo.Msg.NO_PERMISSIONS));
            throw commonException;
        }
        return Result.error(HttpStatus.HTTP_FORBIDDEN, ErrorInfo.Msg.NO_PERMISSIONS);
    }

    /**
     * 非自定义异常处理
     *
     * @param e 异常
     * @return 信息
     */
    @ExceptionHandler({Exception.class})
    public Result<?> noCustomException(Exception e) {
        log.error("非自定义异常处理", e);
        // 标识异常已被处理
        ResponseUtils.setResponseHeader(BODY_PROCESSED, "1");
        if (Objects.requireNonNull(RequestUtils.getRequest()).getRequestURL().toString().contains("/inner/")) {
            CommonException commonException = new CommonException(ErrorInfo.Msg.REQUEST_FAILED);
            ResponseUtils.setResponseHeader(HeaderConstants.INNER_ERROR, Base64Utils.encodeStr("500|" + ErrorInfo.Msg.REQUEST_FAILED));
            throw commonException;
        }
        return Result.error(ErrorInfo.Msg.REQUEST_FAILED);
    }
}
