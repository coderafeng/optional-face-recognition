package com.loongdot.face.common.config.exception;


import com.loongdot.face.common.utils.http.domain.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// @formatter:off
/**
 * @author ：coderafeng
 * @email  ：
 *     - Gmail：<a href="k1994583917@gmail.com">Gmail Email</a>
 *     - QQ：<a href="1994583917@qq.com">QQ Email</a>
 * @home   ：
 *     - <a href="http://loongdot.com/community">龙点科技有限责任公司</a>
 *     - <a href="https://github.com/coderafeng">GitHub</a>
 *     - <a href="https://gitee.com/coderafeng">GitHub</a>
 *     - <a href="https://space.bilibili.com/481342296">哔哩哔哩</a>
 * @company：<a href="http://loongdot.com">龙点科技有限责任公司</a>
 * @date   ：2022-12-25 11:43
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    ApiResponse<String> handleException(Exception e) {
        log.error("服务异常", e);
        return ApiResponse.internalServerError(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    ApiResponse<String> handleBusinessException(BusinessException e) {
        log.error("服务异常", e);
        return ApiResponse.error(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    ApiResponse<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("服务异常", e);
        return ApiResponse.badRequest(e.getParameterName());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ApiResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        printParameterMap();
        return ApiResponse.badRequest(errorMessage(e.getBindingResult()));
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    ApiResponse<String> handleBindException(BindException e) {
        printParameterMap();
        return ApiResponse.badRequest(errorMessage(e.getBindingResult()));
    }

    public static String errorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError error : list) {
                errorMessage.append(",").append(error.getField()).append(" : ").append(error.getDefaultMessage());
            }
        }
        return errorMessage.toString().replaceFirst(",", "");
    }

    private void printParameterMap() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            Map<String, String[]> paramMap = request.getParameterMap();
            Map<String, String> newParamMap = convertRequestParamMap(paramMap);
            log.info(String.format("请求地址：%s", request.getRequestURL()));
            log.info(String.format("请求参数：%s", newParamMap));
        }
    }

    public static Map<String, String> convertRequestParamMap(Map<String, String[]> paramMap) {
        Map<String, String> newParamMap = new HashMap<>(10);
        if (paramMap != null) {
            for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
                String key = entry.getKey();
                String[] values = entry.getValue();
                newParamMap.put(key, values[0]);
            }
        }
        return newParamMap;
    }

}
