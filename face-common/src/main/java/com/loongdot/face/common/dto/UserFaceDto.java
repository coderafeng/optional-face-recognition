package com.loongdot.face.common.dto;

import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.easy.query.core.api.pagination.EasyPageResult;
import com.easy.query.core.util.EasyStringUtil;
import com.loongdot.face.common.config.exception.BusinessException;
import com.loongdot.face.common.entity.po.UserFace;
import com.loongdot.face.common.entity.po.proxy.UserFaceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

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
 * @date   ：2025-05-16 11:01
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserFaceDto {

    private final EasyEntityQuery easyEntityQuery;


    public long addUserFace(List<UserFace> userFaceList) {
        return easyEntityQuery.insertable(userFaceList).executeRows();
    }


    /**
     * 删除用户人脸信息
     *
     * @param UserFaceIdList - 删除的用户人脸信息id 集合
     * @return -
     * <br>
     * @author ：coderafeng
     * @date ：2025-05-19 11:25
     * @since ：1.0.0
     */
    public long delete(List<Long> UserFaceIdList) {
        return easyEntityQuery.deletable(UserFace.class).disableLogicDelete()
                .where(u -> u.userFaceId().in(UserFaceIdList))
                .executeRows();
    }

    /**
     * 查询人脸信息
     *
     * @param groupCode          - 组编码
     * @param userAuthIdentifier - 用户授权账号
     * @param bindingType        - 绑定类型
     * @return -
     * <br>
     * @author ：coderafeng
     * @date ：2025-05-19 11:26
     * @since ：1.0.0
     */
    public List<UserFace> findList(String groupCode, String userAuthIdentifier, Integer bindingType) {
        return easyEntityQuery.queryable(UserFace.class)
                .where(t -> {
                    t.groupCode().eq(EasyStringUtil.isNotBlank(groupCode), groupCode);
                    t.userAuthIdentifier().eq(EasyStringUtil.isNotBlank(userAuthIdentifier), userAuthIdentifier);
                    t.bindingType().eq(bindingType != null, bindingType);
                })
                .toList();
    }

    /**
     * 查询 授权账号 是否已经绑定人脸信息
     *
     * @param userAuthIdentifier - 用户授权账号
     * @return - true：存在；false：不存在
     * <br>
     * @author ：coderafeng
     * @date ：2025-05-19 11:27
     * @since ：1.0.0
     */
    public boolean exist(String userAuthIdentifier) {
        if (StringUtils.hasText(userAuthIdentifier)) {
            throw new BusinessException("用户授权账号（唯一凭证）不能为空");
        }
        EasyPageResult<Long> pageResult = easyEntityQuery.queryable(UserFace.class)
                .where(u -> u.userAuthIdentifier().eq(userAuthIdentifier))
                .select(UserFaceProxy::userFaceId)
                .toPageResult(1, 1);
        return pageResult.getTotal() > 0;
    }
}