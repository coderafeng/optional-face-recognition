package com.loongdot.face.arcsoftfree.engine.factory;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.enums.ErrorInfo;
import com.loongdot.face.common.config.exception.BusinessException;
import com.loongdot.face.common.utils.http.enums.HttpEnums;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

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
 * @date   ：2025-05-30 11:50
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@Slf4j
public class FaceEngineFactory extends BasePooledObjectFactory<FaceEngine> {

    private final String appId;
    private final String sdkKey;
    private final String activeKey;
    private final EngineConfiguration engineConfiguration;

    public FaceEngineFactory( String appId, String sdkKey, String activeKey, EngineConfiguration engineConfiguration) {
        this.appId = appId;
        this.sdkKey = sdkKey;
        this.activeKey = activeKey;
        this.engineConfiguration = engineConfiguration;
    }


    @Override
    public FaceEngine create() throws Exception {
        FaceEngine faceEngine = new FaceEngine();
        int activeCode = faceEngine.activeOnline(appId, sdkKey);
        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            log.error("引擎激活失败，activeCode={}", activeCode);
            throw new BusinessException(HttpEnums.Status.INTERNAL_SERVER_ERROR.getCode(), "引擎激活失败，activeCode=" + activeCode);
        }
        int initCode = faceEngine.init(engineConfiguration);
        if (initCode != ErrorInfo.MOK.getValue()) {
            log.error("引擎初始化失败，initCode={}", initCode);
            throw new BusinessException(HttpEnums.Status.INTERNAL_SERVER_ERROR.getCode(), "引擎初始化失败，initCode=" + initCode);
        }
        return faceEngine;
    }

    @Override
    public PooledObject<FaceEngine> wrap(FaceEngine faceEngine) {
        return new DefaultPooledObject<>(faceEngine);
    }

    @Override
    public void destroyObject(PooledObject<FaceEngine> p) throws Exception {
        FaceEngine faceEngine = p.getObject();
        int result = faceEngine.unInit();
        super.destroyObject(p);
    }
}