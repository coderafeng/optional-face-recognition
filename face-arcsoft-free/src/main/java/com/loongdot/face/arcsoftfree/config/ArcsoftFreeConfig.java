package com.loongdot.face.arcsoftfree.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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
 * @date   ：2025-05-19 9:49
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
@Configuration
@Setter
@Getter
public class ArcsoftFreeConfig {
    @Value("${face.file-path}")
    private String filePath;
    @Value("${face.match-type:1}")
    private Integer matchType;
    @Value("${face.match-min-score:80}")
    private Float matchMinScore;
    @Value("${face.quality-min-score:70}")
    private Float qualityMinScore;
    @Value("${face.with-other-match-max-score:70}")
    private Float withOtherMatchMaxScore;
    @Value("${face.blur:60}")
    private Float blur;
    @Value("${face.sdk-path}")
    private String sdkPath;
    @Value("${face.app-id}")
    private String appId;
    @Value("${face.sdk-key}")
    private String sdkKey;
    @Value("${face.sdk-detect-pool-size:5}")
    private Integer sdkDetectPoolSize;
    @Value("${face.sdk-compare-pool-size:5}")
    private Integer sdkComparePoolSize;

}