package com.loongdot.face.baiduonline.service;

import com.loongdot.face.api.service.FaceService;
import com.loongdot.face.common.entity.dto.*;
import com.loongdot.face.common.entity.vo.UserFaceVo;
import com.loongdot.face.common.utils.page.PageVo;
import org.springframework.stereotype.Service;

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
 * @date   ：2025-04-12 19:25
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@Service
public class BaiduOnlineImpl implements FaceService {

    @Override
    public List<UserFaceVo> addUserFace(UserFaceAddDto userFaceAddDto) {
        return List.of();
    }

    @Override
    public Long deleteUserFace(UserFaceDeleteDto userFaceDeleteDto) {
        return null;
    }

    @Override
    public List<UserFaceVo> updateUserFace(UserFaceUpdateDto userFaceUpdateDto) {
        return List.of();
    }

    @Override
    public List<UserFaceVo> findUserFaceDetails(UserFaceDetailsDto userFaceDetailsDto) {
        return List.of();
    }

    @Override
    public PageVo<UserFaceVo> findUserFacePage(UserFacePageDto userFacePageDto) {
        return null;
    }
}