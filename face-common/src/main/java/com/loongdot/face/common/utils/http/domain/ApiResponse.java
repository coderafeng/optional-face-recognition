package com.loongdot.face.common.utils.http.domain;

import com.loongdot.face.common.utils.http.enums.HttpEnums;

import java.io.Serializable;

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
 * @date   ：2025-04-13 16:50
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on

public class ApiResponse<T> implements Serializable {
    private int code;

    private String message;

    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResponse() {
    }

    public ApiResponse(HttpEnums.Status status, String message, T data) {
        this.code = status.getCode();
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpEnums.Status status, String message) {
        this.code = status.getCode();
        this.message = message;
    }


    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<T>(HttpEnums.Status.OK, "操作成功");
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<T>(HttpEnums.Status.OK, "操作成功", data);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<T>(HttpEnums.Status.OK, message, data);
    }

    public static <T> ApiResponse<T> error(Integer code, String message) {
        HttpEnums.Status status = HttpEnums.Status.valueOf(code);
        if (status == null) {
            status = HttpEnums.Status.INTERNAL_SERVER_ERROR;
        }
        return new ApiResponse<T>(status, message);
    }

    public static <T> ApiResponse<T> error(HttpEnums.Status status, String message) {
        return new ApiResponse<T>(status, message);
    }

    public static <T> ApiResponse<T> badRequest() {
        return new ApiResponse<T>(HttpEnums.Status.BAD_REQUEST, HttpEnums.Status.BAD_REQUEST.getDes(), (T) null);
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        if (message == null || message.isBlank()) {
            message = HttpEnums.Status.BAD_REQUEST.getDes();
        }
        return new ApiResponse<T>(HttpEnums.Status.BAD_REQUEST, message, (T) null);
    }

    public static <T> ApiResponse<T> unauthorized() {
        return new ApiResponse<T>(HttpEnums.Status.UNAUTHORIZED, HttpEnums.Status.UNAUTHORIZED.getDes(), (T) null);
    }

    public static <T> ApiResponse<T> unauthorized(String message) {
        if (message == null || message.isBlank()) {
            message = HttpEnums.Status.BAD_REQUEST.getDes();
        }
        return new ApiResponse<T>(HttpEnums.Status.UNAUTHORIZED, message, (T) null);
    }

    public static <T> ApiResponse<T> forbidden() {
        return new ApiResponse<T>(HttpEnums.Status.FORBIDDEN, HttpEnums.Status.FORBIDDEN.getDes(), (T) null);
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        if (message == null || message.isBlank()) {
            message = HttpEnums.Status.FORBIDDEN.getDes();
        }
        return new ApiResponse<T>(HttpEnums.Status.FORBIDDEN, message, (T) null);
    }

    public static <T> ApiResponse<T> notFound() {
        return new ApiResponse<T>(HttpEnums.Status.NOT_FOUND, HttpEnums.Status.NOT_FOUND.getDes(), (T) null);
    }

    public static <T> ApiResponse<T> notFound(String message) {
        if (message == null || message.isBlank()) {
            message = HttpEnums.Status.NOT_FOUND.getDes();
        }
        return new ApiResponse<T>(HttpEnums.Status.NOT_FOUND, message, (T) null);
    }

    public static <T> ApiResponse<T> timeOut() {
        return new ApiResponse<T>(HttpEnums.Status.TIMEOUT, HttpEnums.Status.TIMEOUT.getDes(), (T) null);
    }

    public static <T> ApiResponse<T> timeOut(String message) {
        if (message == null || message.isBlank()) {
            message = HttpEnums.Status.TIMEOUT.getDes();
        }
        return new ApiResponse<T>(HttpEnums.Status.TIMEOUT, message, (T) null);
    }

    public static <T> ApiResponse<T> payloadTooLarge() {
        return new ApiResponse<T>(HttpEnums.Status.PAYLOAD_TOO_LARGE, HttpEnums.Status.PAYLOAD_TOO_LARGE.getDes(), (T) null);
    }

    public static <T> ApiResponse<T> payloadTooLarge(String message) {
        if (message == null || message.isBlank()) {
            message = HttpEnums.Status.PAYLOAD_TOO_LARGE.getDes();
        }
        return new ApiResponse<T>(HttpEnums.Status.PAYLOAD_TOO_LARGE, message, (T) null);
    }

    public static <T> ApiResponse<T> internalServerError() {
        return new ApiResponse<T>(HttpEnums.Status.INTERNAL_SERVER_ERROR, HttpEnums.Status.INTERNAL_SERVER_ERROR.getDes(), (T) null);
    }

    public static <T> ApiResponse<T> internalServerError(String message) {
        if (message == null || message.isBlank()) {
            message = HttpEnums.Status.INTERNAL_SERVER_ERROR.getDes();
        }
        return new ApiResponse<T>(HttpEnums.Status.INTERNAL_SERVER_ERROR, message, (T) null);
    }

    public static <T> ApiResponse<T> serviceUnavailable() {
        return new ApiResponse<T>(HttpEnums.Status.SERVICE_UNAVAILABLE, HttpEnums.Status.SERVICE_UNAVAILABLE.getDes(), (T) null);
    }

    public static <T> ApiResponse<T> serviceUnavailable(String message) {
        if (message == null || message.isBlank()) {
            message = HttpEnums.Status.SERVICE_UNAVAILABLE.getDes();
        }
        return new ApiResponse<T>(HttpEnums.Status.SERVICE_UNAVAILABLE, message, (T) null);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}