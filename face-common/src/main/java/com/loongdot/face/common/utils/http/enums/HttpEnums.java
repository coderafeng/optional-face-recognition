package com.loongdot.face.common.utils.http.enums;

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
 * @date   ：2025-04-13 17:05
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
public interface HttpEnums {

    /**
     * 常用响应码
     */
    enum Status {
        // 成功
        OK(200, "成功"),
        // 请求无效或缺少必要的参数
        BAD_REQUEST(400, "请求无效或缺少必要的参数"),
        // 未授权
        UNAUTHORIZED(401, "未认证授权，请先进行登录"),
        // 拒绝请求
        FORBIDDEN(403, "未授权，拒绝请求访问"),
        NOT_FOUND(404, "未找到的请求资源"),
        // 请求超时
        TIMEOUT(408, "请求超时"),
        // 请求实体过大
        PAYLOAD_TOO_LARGE(413, "请求实体过大"),
        // 服务器异常
        INTERNAL_SERVER_ERROR(500, "服务器异常"),
        // 服务不可用(超载或停机维护)
        SERVICE_UNAVAILABLE(503, "服务不可用，请稍后重试"),
        ;

        private final int code;
        private final String des;

        Status(int code, String des) {
            this.code = code;
            this.des = des;
        }

        public static Status valueOf(int code) {
            for (Status status : Status.values()) {
                if (status.code == code) {
                    return status;
                }
            }
            return null;
        }

        public int getCode() {
            return code;
        }

        public String getDes() {
            return des;
        }

    }

}