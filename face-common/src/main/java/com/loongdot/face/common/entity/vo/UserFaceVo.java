package com.loongdot.face.common.entity.vo;

import com.loongdot.face.common.config.base.BaseVo;
import com.loongdot.face.common.entity.po.UserFace;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
 * @date   ：2025-04-13 22:49
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
@Schema(description = "用户人脸vo")
public class UserFaceVo extends BaseVo<UserFace> {

    @Schema(description = "用户人脸ID")
    private Long userFaceId;

    @Schema(description = "用户组编码")
    private String groupCode;

    @Schema(description = "用户认证标识")
    private String userAuthIdentifier;

    @Schema(description = "用户认证昵称")
    private String userAuthNickname;

    @Schema(description = "人脸原始图片地址")
    private String originalImageUrl;

    @Schema(description = "人脸裁剪图片地址")
    private String cropImageUrl;

    @Schema(description = "人脸特征数据")
    private String featureData;

    @Schema(description = "图片人脸质量分数")
    private Integer qualityScore;

    @Schema(description = "人脸年龄")
    private Integer age;

    @Schema(description = "人脸性别(未知、男、女)")
    private String gender;

    @Schema(description = "俯仰角度")
    private Float pitchAngle;

    @Schema(description = "偏左右角度")
    private Float leftRightAngel;

    @Schema(description = "平面角度")
    private Float planeAngel;

    @Schema(description = "注册设备现状信息")
    private String deviceSceneInfo;

    @Schema(description = "是否为个人操作（个人、管理员）")
    private Boolean personalOperation;

    @Schema(description = "绑定类型（1：普通注册；2：游客注册）")
    private Integer bindingType;

    @Schema(description = "过期时间")
    private Date expirationDateTime;

}