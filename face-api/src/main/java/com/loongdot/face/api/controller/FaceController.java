package com.loongdot.face.api.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.loongdot.face.api.service.FaceService;
import com.loongdot.face.common.entity.dto.*;
import com.loongdot.face.common.entity.vo.UserFaceVo;
import com.loongdot.face.common.utils.http.domain.ApiResponse;
import com.loongdot.face.common.utils.page.PageVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * @date   ：2025-04-12 18:02
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@RestController
@RequestMapping("/user/face")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FaceController {

    private final FaceService faceService;



    @ApiOperationSupport(order = 10)
    @Operation(summary = "绑定人脸", description = "注册绑定操作，不可省略组代码")
    @PostMapping("/v1/add")
    public ApiResponse<List<UserFaceVo>> addUserFace(@RequestParam @Validated UserFaceAddDto userFaceAddDto) {
        return ApiResponse.ok(faceService.addUserFace(userFaceAddDto));
    }

    @ApiOperationSupport(order = 20)
    @Operation(summary = "删除人脸", description = "注册绑定操作，不可省略组代码")
    @PostMapping("/v1/delete")
    public ApiResponse<Long> deleteUserFace(@Validated UserFaceDeleteDto userFaceDeleteDto) {
        return ApiResponse.ok(faceService.deleteUserFace(userFaceDeleteDto));
    }

    @ApiOperationSupport(order = 30)
    @Operation(summary = "更新人脸", description = "不可省略组代码")
    @PostMapping("/v1/update")
    public ApiResponse<List<UserFaceVo>> updateUserFace(UserFaceUpdateDto userFaceUpdateDto) {
        return ApiResponse.ok(faceService.updateUserFace(userFaceUpdateDto));
    }

    @ApiOperationSupport(order = 40)
    @Operation(summary = "查询人脸详情", description = "指定用户账号")
    @PostMapping("/v1/details")
    public ApiResponse<List<UserFaceVo>> findUserFaceDetails(UserFaceDetailsDto userFaceDetailsDto) {
        return ApiResponse.ok(faceService.findUserFaceDetails(userFaceDetailsDto));
    }

    @ApiOperationSupport(order = 50)
    @Operation(summary = "查询人脸分页", description = "分页查询")
    @PostMapping("/v1/details")
    public ApiResponse<PageVo<UserFaceVo>> findUserFacePage(UserFacePageDto userFacePageDto) {
        return ApiResponse.ok(faceService.findUserFacePage(userFacePageDto));
    }


}