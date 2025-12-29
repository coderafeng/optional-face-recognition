package com.loongdot.face.arcsoftfree.service;

import com.loongdot.face.api.service.FaceService;
import com.loongdot.face.arcsoftfree.config.ArcsoftFreeConfig;
import com.loongdot.face.common.config.exception.BusinessException;
import com.loongdot.face.common.dto.UserFaceDto;
import com.loongdot.face.common.entity.dto.*;
import com.loongdot.face.common.entity.po.UserFace;
import com.loongdot.face.common.entity.vo.UserFaceVo;
import com.loongdot.face.common.utils.file.UtilsFile;
import com.loongdot.face.common.utils.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
 * @date   ：2025-04-12 19:02
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArcsoftFreeImpl implements FaceService {

    private final UserFaceDto userFaceDto;
    private final ArcsoftFreeConfig arcsoftFreeConfig;


    @Override
    public List<UserFaceVo> addUserFace(UserFaceAddDto userFaceAddDto) {
        boolean exist = userFaceDto.exist(userFaceAddDto.getUserAuthIdentifier());
        if (exist) {
            throw new BusinessException("授权账号已绑定人脸");
        }

        List<MultipartFile> files = userFaceAddDto.getFiles();
        if (files == null || files.isEmpty()) {
            throw new BusinessException("绑定人脸，人脸图片不能为空");
        }

        Boolean personalOperation = userFaceAddDto.getPersonalOperation();
        personalOperation = personalOperation == null || personalOperation;
        Integer bindingType = userFaceAddDto.getBindingType();
        bindingType = bindingType == null ? 1 : 2;

        // todo 图片质量检查


        // todo 人脸识别，检查是否存在已经注册绑定了


        // todo 保存人脸信息


        return List.of();
    }

    @Override
    public Long deleteUserFace(UserFaceDeleteDto userFaceDeleteDto) {
        List<UserFace> userFaceList = userFaceDto.findList(userFaceDeleteDto.getGroupCode(), userFaceDeleteDto.getUserAuthIdentifier(), userFaceDeleteDto.getBindingType());
        if (ObjectUtils.isEmpty(userFaceList)) {
            return 0L;
        }

        long deleteCount = userFaceDto.delete(userFaceList.stream().map(UserFace::getUserFaceId).toList());
        if (deleteCount == 0) {
            throw new BusinessException("解绑人脸失败，请重新尝试解绑");
        }

        // 删除图片
        List<String> filePathList = new ArrayList<>();
        for (UserFace userFace : userFaceList) {
            filePathList.add(userFace.getOriginalImageUrl());
            filePathList.add(userFace.getCropImageUrl());
        }
        UtilsFile.deleteFileList(filePathList);

        return deleteCount;
    }

    @Override
    public List<UserFaceVo> updateUserFace(UserFaceUpdateDto userFaceUpdateDto) {
        // todo 查询用户旧的人脸信息

        // todo 图片质量检查

        // todo 人脸识别，检查是否存在已经注册绑定了

        // todo 保存人脸信息

        // todo 删除旧人脸信息、删除人脸图片文件


        return List.of();
    }

    @Override
    public List<UserFaceVo> findUserFaceDetails(UserFaceDetailsDto userFaceDetailsDto) {
        // todo 精确查询

        return List.of();
    }

    @Override
    public PageVo<UserFaceVo> findUserFacePage(UserFacePageDto userFacePageDto) {
        // todo 分页模糊查询

        return null;
    }


}