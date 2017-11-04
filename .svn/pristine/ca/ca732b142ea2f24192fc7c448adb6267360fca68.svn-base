package com.qiaobei.hmp.test;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 图片压缩处理
 */
public class ImagesZip {
    private static int width;
    private static int height;
    private Image img;

    /**
     * 构造函数
     */
    public ImagesZip(String fileName) throws IOException {
        File file = new File(fileName);// 读入文件
        img = ImageIO.read(file);      // 构造Image对象
        width = img.getWidth(null);    // 得到源图宽
        height = img.getHeight(null);  // 得到源图长
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        System.out.println("开始：" + new Date().toLocaleString());
        ImagesZip imgCom = new ImagesZip("D:\\test0.png");
        imgCom.resizeFix(width, height);
        System.out.println("结束：" + new Date().toLocaleString());
    }

    /**
     * 按照宽度还是高度进行压缩
     *
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public void resizeFix(int w, int h) throws IOException {
        if (width / height > w / h) {
            resizeByWidth(w);
        } else {
            resizeByHeight(h);
        }
    }

    /**
     * 以宽度为基准，等比例放缩图片
     *
     * @param w int 新宽度
     */
    public void resizeByWidth(int w) throws IOException {
        int h = height * w / width;
        resize(w, h);
    }

    /**
     * 以高度为基准，等比例缩放图片
     *
     * @param h int 新高度
     */
    public void resizeByHeight(int h) throws IOException {
        int w = width * h / height;
        resize(w, h);
    }

    /**
     * 强制压缩/放大图片到固定的大小
     *
     * @param w int 新宽度
     * @param h int 新高度
     */
    private void resize(int w, int h) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h, BufferedImage.SCALE_SMOOTH);
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        File destFile = new File("D:\\456.png");
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image); // JPEG编码
        out.close();
    }
}