package com.loongdot.face.common.entity.po;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.EntityProxy;
import com.easy.query.core.annotation.Table;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.loongdot.face.common.config.base.BasePo;
import com.loongdot.face.common.entity.po.proxy.UserFaceProxy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

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
 * @date   ：2025-04-12 19:43
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
@Table(value = "user_face", comment = "用户人脸表")
@EntityProxy
@FieldNameConstants
public class UserFace extends BasePo implements ProxyEntityAvailable<UserFace, UserFaceProxy> {

    @Column(primaryKey = true, comment = "用户人脸ID")
    private Long userFaceId;

    @Column(comment = "用户组编码")
    private String groupCode;

    @Column(comment = "用户认证标识")
    private String userAuthIdentifier;

    @Column(comment = "用户认证昵称")
    private String userAuthNickname;

    @Column(comment = "人脸原始图片地址")
    private String originalImageUrl;

    @Column(comment = "人脸裁剪图片地址")
    private String cropImageUrl;

    @Column(comment = "人脸特征数据", dbType = "text")
    private String featureData;

    @Column(comment = "图片人脸质量分数")
    private Integer qualityScore;

    @Column(comment = "人脸年龄")
    private Integer age;

    @Column(comment = "人脸性别(未知、男、女)")
    private String gender;

    @Column(comment = "俯仰角度")
    private Float pitchAngle;

    @Column(comment = "偏左右角度")
    private Float leftRightAngel;

    @Column(comment = "平面角度")
    private Float planeAngel;

    @Column(comment = "注册设备现状信息", dbType = "text")
    private String deviceSceneInfo;

    @Column(comment = "是否为个人操作（个人、管理员）")
    private Boolean personalOperation;

    @Column(comment = "绑定类型（1：普通注册；2：游客注册）")
    private Integer bindingType;

    @Column(comment = "过期时间")
    private Date expirationDateTime;

}