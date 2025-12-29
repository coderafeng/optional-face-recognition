package com.loongdot.face.common.utils.file;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.loongdot.face.common.config.exception.BusinessException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;


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
 * @date   ：2023-07-27 23:22
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：图片工具类
 * <p></p>
 */
// @formatter:on
public class UtilsFile {

    /**
     * 保存文件
     *
     * @param filePath      -文件全路径
     * @param multipartFile -文件
     * @return true 成功  false 失败
     */
    public static boolean saveFile(String filePath, MultipartFile multipartFile) {
        if (multipartFile != null && StringUtils.hasText(filePath)) {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                if (!parentFile.mkdirs()) {
                    System.out.println("创建文件夹异常：" + parentFile.getPath());
                    return false;
                }
            }
            try {
                FileUtil.copyFile(multipartFile.getInputStream(), file);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 批量删除文件
     *
     * @param filePathList 文件全路径
     * @return 成功删除的数量
     */
    public static int deleteFileList(List<String> filePathList) {
        int successNumber = 0;
        if (!ObjectUtils.isEmpty(filePathList)) {
            for (String filePath : filePathList) {
                try {
                    File fileFile = new File(filePath);
                    if (fileFile.exists()) {
                        boolean delete = fileFile.delete();
                        if (delete) {
                            successNumber++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return successNumber;
    }

    /**
     * 重新设置文件名称
     *
     * @param multipartFile      -文件
     * @param userAuthIdentifier -用户授权账号
     * @param groupCode          -组code
     * @return FACE-xyz-opq-20230801666.jpg
     */
    public static String generateFileName(MultipartFile multipartFile, String userAuthIdentifier, String groupCode) {
        String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        if (!StringUtils.hasText(suffix)) {
            suffix = "";
        }
        if (!StringUtils.hasText(userAuthIdentifier)) {
            throw new BusinessException("用户授权账号不能为空");
        }
        if (StringUtils.hasText(groupCode)) {
            return String.format("%s-%s-%s", groupCode, userAuthIdentifier, DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN)) + suffix;
        }
        return String.format("%s-%s", userAuthIdentifier, DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN)) + suffix;
    }

}
