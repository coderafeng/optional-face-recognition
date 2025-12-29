package com.loongdot.face.arcsoftfree.engine.service.impl;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.toolkit.ImageInfo;
import com.loongdot.face.arcsoftfree.config.ArcsoftFreeConfig;
import com.loongdot.face.arcsoftfree.engine.factory.FaceEngineFactory;
import com.loongdot.face.arcsoftfree.engine.service.FaceEngineService;
import com.loongdot.face.common.config.exception.BusinessException;
import com.loongdot.face.common.utils.http.enums.HttpEnums;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 * @date   ：2025-05-30 12:17
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
@Slf4j
public class FaceEngineServiceImpl implements FaceEngineService {

    @Resource
    private ArcsoftFreeConfig arcsoftFreeConfig;

    /**
     * 人脸识别引擎池
     */
    private GenericObjectPool<FaceEngine> faceEngineDetectPool;
    /**
     * 人脸比对引擎池
     */
    private GenericObjectPool<FaceEngine> faceEngineComparePool;

    private ExecutorService compareExecutorService;


    @PostConstruct
    public void init() {
        //特征识别线程池配置
        GenericObjectPoolConfig<FaceEngine> detectPoolConfig = new GenericObjectPoolConfig<>();
        detectPoolConfig.setMaxIdle(arcsoftFreeConfig.getSdkDetectPoolSize());
        detectPoolConfig.setMaxTotal(arcsoftFreeConfig.getSdkDetectPoolSize());
        detectPoolConfig.setMinIdle(arcsoftFreeConfig.getSdkDetectPoolSize());
        detectPoolConfig.setLifo(false);
        EngineConfiguration detectCfg = new EngineConfiguration();
        FunctionConfiguration detectFunctionCfg = new FunctionConfiguration();
        //开启人脸检测功能
        detectFunctionCfg.setSupportFaceDetect(true);
        //开启人脸识别功能
        detectFunctionCfg.setSupportFaceRecognition(true);
        //开启年龄检测功能
        detectFunctionCfg.setSupportAge(true);
        //开启性别检测功能
        detectFunctionCfg.setSupportGender(true);
        //开启活体检测功能
        detectFunctionCfg.setSupportLiveness(true);
        detectCfg.setFunctionConfiguration(detectFunctionCfg);
        //图片检测模式，如果是连续帧的视频流图片，那么改成VIDEO模式
        detectCfg.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        //人脸旋转角度
        detectCfg.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        //底层库算法对象池
        faceEngineDetectPool = new GenericObjectPool<>(new FaceEngineFactory(arcsoftFreeConfig.getAppId(), arcsoftFreeConfig.getSdkKey(), null, detectCfg), detectPoolConfig);

        //特征比对线程池配置
        GenericObjectPoolConfig<FaceEngine> comparePoolConfig = new GenericObjectPoolConfig<>();
        comparePoolConfig.setMaxIdle(arcsoftFreeConfig.getSdkComparePoolSize());
        comparePoolConfig.setMaxTotal(arcsoftFreeConfig.getSdkComparePoolSize());
        comparePoolConfig.setMinIdle(arcsoftFreeConfig.getSdkComparePoolSize());
        comparePoolConfig.setLifo(false);
        EngineConfiguration compareCfg = new EngineConfiguration();
        FunctionConfiguration compareFunctionCfg = new FunctionConfiguration();
        //开启人脸识别功能
        compareFunctionCfg.setSupportFaceRecognition(true);
        compareCfg.setFunctionConfiguration(compareFunctionCfg);
        //图片检测模式，如果是连续帧的视频流图片，那么改成VIDEO模式
        compareCfg.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        //人脸旋转角度
        compareCfg.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        //底层库算法对象池
        faceEngineComparePool = new GenericObjectPool<>(new FaceEngineFactory(arcsoftFreeConfig.getAppId(), arcsoftFreeConfig.getSdkKey(), null, compareCfg), comparePoolConfig);
        compareExecutorService = Executors.newFixedThreadPool(arcsoftFreeConfig.getSdkComparePoolSize());
    }

    @Override
    public List<FaceInfo> detectFaces(ImageInfo imageInfo) {
        FaceEngine faceEngine = null;
        try {
            faceEngine = faceEngineDetectPool.borrowObject();
            if (faceEngine == null) {
                throw new BusinessException(HttpEnums.Status.INTERNAL_SERVER_ERROR.getCode(), "获取引擎失败");
            }

            //人脸检测得到人脸列表
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
            //人脸检测
            int errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
            if (errorCode == 0) {
                return faceInfoList;
            } else {
                log.error("人脸检测失败，errorCode：{}", errorCode);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                faceEngineDetectPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    @Override
    public Float compareFace(ImageInfo imageInfo1, ImageInfo imageInfo2) {
        List<FaceInfo> faceInfoList1 = detectFaces(imageInfo1);
        List<FaceInfo> faceInfoList2 = detectFaces(imageInfo2);

        if (ObjectUtils.isEmpty(faceInfoList1)) {
            throw new BusinessException(HttpEnums.Status.INTERNAL_SERVER_ERROR.getCode(), "照片1未检测到人脸");
        }
        if (ObjectUtils.isEmpty(faceInfoList2)) {
            throw new BusinessException(HttpEnums.Status.INTERNAL_SERVER_ERROR.getCode(), "照片2未检测到人脸");
        }

        byte[] feature1 = extractFaceFeature(imageInfo1, faceInfoList1.get(0));
        byte[] feature2 = extractFaceFeature(imageInfo2, faceInfoList2.get(0));

        FaceEngine faceEngine = null;
        try {
            faceEngine = faceEngineDetectPool.borrowObject();
            if (faceEngine == null) {
                throw new BusinessException(HttpEnums.Status.INTERNAL_SERVER_ERROR.getCode(), "获取引擎失败");
            }

            FaceFeature faceFeature1 = new FaceFeature();
            faceFeature1.setFeatureData(feature1);
            FaceFeature faceFeature2 = new FaceFeature();
            faceFeature2.setFeatureData(feature2);
            //提取人脸特征
            FaceSimilar faceSimilar = new FaceSimilar();
            int errorCode = faceEngine.compareFaceFeature(faceFeature1, faceFeature2, faceSimilar);
            if (errorCode == 0) {
                return faceSimilar.getScore();
            } else {
                log.error("特征提取失败，errorCode：{}", errorCode);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                faceEngineDetectPool.returnObject(faceEngine);
            }
        }

        return null;
    }

    @Override
    public byte[] extractFaceFeature(ImageInfo imageInfo, FaceInfo faceInfo) {
        return new byte[0];
    }


}