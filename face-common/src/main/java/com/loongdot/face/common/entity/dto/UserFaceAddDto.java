package com.loongdot.face.common.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
 * @date   ：2025-04-13 22:59
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@Setter
@Getter
@ToString
@Schema(description = "人脸添加dto")
public class UserFaceAddDto {
    @NotNull(message = "组代码不能为空")
    @Schema(description = "组代码")
    private String groupCode;

    @NotBlank(message = "用户授权账号（唯一凭证）不能为空")
    @Schema(description = "用户授权账号（唯一凭证）")
    private String userAuthIdentifier;

    @NotBlank(message = "用户授权账号昵称不能为空")
    @Schema(description = "用户授权账号昵称")
    private String userAuthNickname;

    @NotNull(message = "绑定人脸图片不能为空")
    @Schema(description = "人脸图片")
    private List<MultipartFile> files;

    @Schema(description = "注册设备现状信息")
    private String deviceSceneInfo;

    @Schema(description = "是否为个人操作（个人、管理员）")
    private Boolean personalOperation;

    @Schema(description = "绑定类型（1：普通注册；2：游客注册）")
    private Integer bindingType;

}