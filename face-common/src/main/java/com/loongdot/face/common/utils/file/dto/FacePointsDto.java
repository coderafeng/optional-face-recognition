package com.loongdot.face.common.utils.file.dto;

import java.util.ArrayList;
import java.util.Arrays;

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
 * @date   ：2025-05-19 10:25
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
public class FacePointsDto extends ArrayList<FacePointDto> {
    /**
     * 构建一个集合
     *
     * @return -
     */
    public static FacePointsDto build() {
        return new FacePointsDto();
    }

    /**
     * 添加点
     *
     * @param point -
     * @return -
     */
    public FacePointsDto add(FacePointDto... point) {
        super.addAll(Arrays.asList(point));
        return this;
    }

    /**
     * 转换为 double数组
     *
     * @return -
     */
    public double[][] toDoubleArray() {
        double[][] arr = new double[this.size()][2];
        for (int i = 0; i < this.size(); i++) {
            arr[i][0] = this.get(i).x;
            arr[i][1] = this.get(i).y;
        }
        return arr;
    }

    /**
     * 对点进行中心旋转
     *
     * @param center 中心点
     * @param angle  旋转角度
     * @return 旋转后的角
     */
    public FacePointsDto rotation(FacePointDto center, double angle) {
        FacePointsDto facePointsDto = build();
        for (FacePointDto item : this) {
            facePointsDto.add(item.rotation(center, angle));
        }
        return facePointsDto;
    }

    /**
     * 加法操作，对所有的点都加上point的值
     *
     * @param point -
     * @return -
     */
    public FacePointsDto operateAdd(FacePointDto point) {
        FacePointsDto points = build();
        for (FacePointDto item : this) {
            float x = item.x + point.x;
            float y = item.y + point.y;
            points.add(FacePointDto.build(x, y));
        }
        return points;
    }

    /**
     * 减法操作，对所有的点都加上point的值
     *
     * @param point -
     * @return -
     */
    public FacePointsDto operateSubtract(FacePointDto point) {
        FacePointsDto facePointsDto = build();
        for (FacePointDto item : this) {
            float x = item.x - point.x;
            float y = item.y - point.y;
            facePointsDto.add(FacePointDto.build(x, y));
        }
        return facePointsDto;
    }

    /**
     * 选择关键点
     *
     * @param indexes 关键点索引号
     * @return 关键点集合
     */
    public FacePointsDto select(int... indexes) {
        FacePointsDto facePointsDto = build();
        for (int index : indexes) {
            facePointsDto.add(this.get(index));
        }
        return facePointsDto;
    }

    /**
     * 乘法操作，对所有的点都乘法scale的值
     *
     * @param scale -
     * @return -
     */
    public FacePointsDto operateMultiply(float scale) {
        FacePointsDto points = build();
        for (FacePointDto item : this) {
            float x = item.x * scale;
            float y = item.y * scale;
            points.add(FacePointDto.build(x, y));
        }
        return points;
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
    public FacePointsDto move(int left, int right, int top, int bottom) {
        FacePointsDto points = build();
        for (FacePointDto item : this) {
            points.add(item.move(left, right, top, bottom));
        }
        return points;
    }

}