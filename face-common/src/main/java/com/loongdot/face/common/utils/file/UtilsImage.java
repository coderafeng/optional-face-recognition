package com.loongdot.face.common.utils.file;

import com.loongdot.face.common.utils.file.dto.FacePointsDto;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

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
 * @date   ：2025-05-19 10:01
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
// @formatter:on
public class UtilsImage {

    /**
     * MultipartFile 转 Mat
     *
     * @param file -
     * @return Mat
     */
    public static Mat convertMultipartFileToMat(MultipartFile file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            return convertBufferedImageToMat(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * BufferedImage 转 Mat
     *
     * @param im -
     * @return -
     */
    public static Mat convertBufferedImageToMat(BufferedImage im) {
        im = convertRgbaToGba(im, BufferedImage.TYPE_3BYTE_BGR);
        byte[] pixels = ((DataBufferByte) im.getRaster().getDataBuffer()).getData();
        Mat image = new Mat(im.getHeight(), im.getWidth(), 16);
        image.put(0, 0, pixels);
        return image;
    }

    /**
     * 8-bit RGBA 转换 8-bit RGB
     *
     * @param original  -
     * @param imageType -
     * @return -
     */
    public static BufferedImage convertRgbaToGba(BufferedImage original, int imageType) {
        if (original == null) {
            throw new IllegalArgumentException("original == null");
        }
        if (original.getType() == imageType) {
            return original;
        }
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), imageType);
        Graphics2D graphics2D = image.createGraphics();
        try {
            graphics2D.setComposite(AlphaComposite.Src);
            graphics2D.drawImage(original, 0, 0, null);
        } finally {
            graphics2D.dispose();
        }
        return image;
    }

    public static FacePointsDto getFacePlaceInfo(MultipartFile multipartFile) {
        Mat m = null;
        Mat im = null;
        Mat img = null;


        return null;
    }


    /**
     * 裁剪图片并重新装换大小
     *
     * @param multipartFile -图片文件
     * @param imageFilePath -保存文件全路径
     * @param posX          -x坐标轴
     * @param posY          -y坐标轴
     * @param width         -宽度
     * @param height        -高度
     * @return true：剪切成功，剪切失败
     */
    public static boolean imageCut(MultipartFile multipartFile, String imageFilePath, int posX, int posY, int width, int height) {
        if (multipartFile == null || !StringUtils.hasText(imageFilePath)) {
            return false;
        }
        // MultipartFile 转换 Mat
        Mat mat = convertMultipartFileToMat(multipartFile);
        if (mat == null) {
            System.out.println("MultipartFile转换Mat异常");
            return false;
        }
        try {
            double cols = mat.cols();
            int rows = mat.rows();
            // 校验图像ROI区域是否超过了图像尺寸
            if (width > cols) {
                width = (int) Math.floor(cols);
            }
            if (height > rows) {
                height = (int) Math.floor(rows);
            }
            double col = posX + width;
            if (col > cols) {
                posX = (int) (cols - width);
            }
            double row = posY + height;
            if (row > rows) {
                posY = rows - height;
            }

            Rect rect = new Rect(posX, posY, width, height);
            // 两种方式都可以
            //Mat subMat = new Mat(mat, rect);
            Mat subMat = mat.submat(rect);
            Mat resultMat = new Mat();
            Size size = new Size(500, 500);
            //人脸截图
            Imgproc.resize(subMat, resultMat, size);
            //生成剪切图片文件
            File imageFile = new File(imageFilePath);
            if (!imageFile.exists()) {
                if (!imageFile.mkdirs()) {
                    System.out.println("创建文件异常");
                    return false;
                }
            }
            Imgcodecs.imwrite(imageFilePath, resultMat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}