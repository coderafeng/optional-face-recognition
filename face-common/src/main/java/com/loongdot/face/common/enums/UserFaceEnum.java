package com.loongdot.face.common.enums;

import lombok.Getter;


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
 * @date   ：2025-04-13 16:15
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
public interface UserFaceEnum {

    /**
     * 性别
     */
    @Getter
    enum Gender {
        /**
         * 性别
         */
        UNKNOWN("未知"),
        MALE("男"),
        FEMALE("女")

        ;
        private final String des;

        Gender(String des) {
            this.des = des;
        }
    }

}