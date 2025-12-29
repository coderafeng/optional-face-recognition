package com.loongdot.face.baiduonline.constant;

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
 * @date   ：2025-04-13 16:21
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
public interface UrlConstant {

    /**
     * 获取accessToken
     */
    public static final String ACCESS_TOKEN = "https://aip.baidubce.com/oauth/2.0/token?";
    /**
     * 人脸检测
     */
    public static final String URL_DETECT = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
    /**
     *人脸注册
     */
    public static final String URL_USER_ADD = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
    /**
     *查询用户信息
     */
    public static final String URL_GET_USER_INFO = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get";
    /**
     *人脸识别 1:N
     */
    public static final String URL_SEARCH = "https://aip.baidubce.com/rest/2.0/face/v3/search";
    /**
     *人脸识别 M:N
     */
    public static final String URL_MULTI_SEARCH = "https://aip.baidubce.com/rest/2.0/face/v3/multi-search";
    /**
     *人脸对比（两张图片对比）
     */
    public static final String URL_MATCH = "https://aip.baidubce.com/rest/2.0/face/v3/match";
    /**
     *人脸更新
     */
    public static final String URL_USER_UPDATE = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";
    /**
     *人脸删除
     */
    public static final String URL_USER_FACE_DELETE = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete";
    /**
     *用户删除
     */
    public static final String URL_USER_DELETE = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete";
    /**
     *用户组删除
     */
    public static final String URL_USER_GROUP_DELETE = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/delete";
    /**
     *组查询
     */
    public static final String URL_SEARCH_GROUP = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getlist";



}
