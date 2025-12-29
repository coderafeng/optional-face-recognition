package com.loongdot.face.api.service;

import com.loongdot.face.common.entity.dto.*;
import com.loongdot.face.common.entity.vo.UserFaceVo;
import com.loongdot.face.common.utils.page.PageVo;

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
 * @date   ：2025-04-12 18:56
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
public interface FaceService {

    /**
     * 添加用户人脸
     *
     * @param userFaceAddDto -
     * @return -
     * <br>
     * @author ：coderafeng
     * @date ：2025-05-04 19:58
     * @since ：1.0.0
     */
    List<UserFaceVo> addUserFace(UserFaceAddDto userFaceAddDto);

    /**
     * 删除用户人脸
     *
     * @param userFaceDeleteDto -
     *                          <br>
     * @author ：coderafeng
     * @date ：2025-05-04 20:08
     * @since ：1.0.0
     */
    Long deleteUserFace(UserFaceDeleteDto userFaceDeleteDto);

    /**
     * 更新用户人脸
     *
     * @param userFaceUpdateDto -
     * @return -
     * <br>
     * @author ：coderafeng
     * @date ：2025-05-04 20:12
     * @since ：1.0.0
     */
    List<UserFaceVo> updateUserFace(UserFaceUpdateDto userFaceUpdateDto);

    /**
     * 查询用户人脸信息详情
     *
     * @param userFaceDetailsDto -
     * @return -
     * <br>
     * @author ：coderafeng
     * @date ：2025-05-04 20:17
     * @since ：1.0.0
     */
    List<UserFaceVo> findUserFaceDetails(UserFaceDetailsDto userFaceDetailsDto);

    /**
     * 分页查询用户人脸信息
     *
     * @param userFacePageDto -
     * @return -
     * <br>
     * @author ：coderafeng
     * @date ：2025-05-16 10:53
     * @since ：1.0.0
     */
    PageVo<UserFaceVo> findUserFacePage(UserFacePageDto userFacePageDto);

}
