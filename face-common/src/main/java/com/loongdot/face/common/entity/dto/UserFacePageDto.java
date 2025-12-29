package com.loongdot.face.common.entity.dto;

import com.loongdot.face.common.utils.page.PageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @formatter:off
/**
 * @author ：coderafeng
 * @email  ：
 *     - Gmail：<a href="k1994583917@gmail.com">Gmail Email</a>
 *     - QQ：<a href="1994583917@qq.com">QQ Email</a>
 * @home   ：
 *     - <a href="http://loongdot.com/community">龙点科技有限责任公司</a>
 *     - <a href="https://github.com/yushuishu">GitHub</a>
 *     - <a href="https://gitee.com/yushuishu">GitHub</a>
 *     - <a href="https://space.bilibili.com/481342296">哔哩哔哩</a>
 * @company：<a href="http://loongdot.com">龙点科技有限责任公司</a>
 * @date   ：2025-05-16 10:49
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@Getter
@Setter
@ToString
public class UserFacePageDto extends PageDto {

    @Schema(description = "组代码")
    private String groupCode;

    @Schema(description = "用户授权账号（唯一凭证）")
    private String userAuthIdentifier;

    @Schema(description = "用户授权账号昵称")
    private String userAuthNickname;

    @Schema(description = "是否为个人操作（个人、管理员）")
    private Boolean personalOperation;

    @Schema(description = "绑定类型（1：普通注册；2：游客注册）")
    private Integer bindingType;

}