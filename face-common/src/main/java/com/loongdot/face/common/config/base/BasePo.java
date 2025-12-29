package com.loongdot.face.common.config.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

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
 * @date   ：2025-04-13 15:37
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：持久化对象(Persistent Object)
 * <p></p>
 */
// @formatter:on
public class BasePo implements Serializable {

    @Schema(description = "创建日期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDateTime;

    @Schema(description = "创建用户ID")
    private Long createUserId;

    @Schema(description = "更新日期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDateTime;

    @Schema(description = "更新用户ID")
    private Long updateUserId;

    @Schema(description = "是否逻辑删除")
    private Boolean deleted;

}