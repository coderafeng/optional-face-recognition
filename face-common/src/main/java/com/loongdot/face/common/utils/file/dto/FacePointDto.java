package com.loongdot.face.common.utils.file.dto;

import java.io.Serializable;

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
 * @date   ：2025-05-19 10:20
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
public class FacePointDto implements Serializable {
    /**
     * 坐标X的值
     **/
    public float x;
    /**
     * 坐标Y的值
     **/
    public float y;

    /**
     * 构造函数
     *
     * @param x 坐标X的值
     * @param y 坐标Y的值
     */
    private FacePointDto(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 构造一个点
     *
     * @param x 坐标X的值
     * @param y 坐标Y的值
     * @return -
     */
    public static FacePointDto build(float x, float y) {
        return new FacePointDto(x, y);
    }

    /**
     * 对点进行中心旋转
     *
     * @param center 中心点
     * @param angle  旋转角度
     * @return 旋转后的角
     */
    public FacePointDto rotation(FacePointDto center, double angle) {
        double k = Math.toRadians(angle);
        float nx1 = (float) ((this.x - center.x) * Math.cos(k) + (this.y - center.y) * Math.sin(k) + center.x);
        float ny1 = (float) (-(this.x - center.x) * Math.sin(k) + (this.y - center.y) * Math.cos(k) + center.y);
        return new FacePointDto(nx1, ny1);
    }

    /**
     * 计算两点之间的距离
     *
     * @param that 点
     * @return 距离
     */
    public float distance(FacePointDto that) {
        return (float) Math.sqrt(Math.pow((this.x - that.x), 2) + Math.pow((this.y - that.y), 2));
    }

    /**
     * 将点进行平移
     *
     * @param top    向上移动的像素点数
     * @param bottom 向下移动的像素点数
     * @param left   向左移动的像素点数
     * @param right  向右移动的像素点数
     * @return 平移后的点
     */
    public FacePointDto move(int left, int right, int top, int bottom) {
        return new FacePointDto(x - left + right, y - top + bottom);
    }

}