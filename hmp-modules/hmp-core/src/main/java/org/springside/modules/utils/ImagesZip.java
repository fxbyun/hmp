package org.springside.modules.utils;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.lang3.ArrayUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 图片压缩处理
 */
public class ImagesZip {
    private Image img;
    private int width;
    private int height;
    private String outPath;
    private String fileLoadPath;
    private byte[] content;

    /**
     * 构造函数
     */
    public ImagesZip(String fileLoadPath, String outPath, byte[] content) throws IOException {
        this.content = content;
        this.outPath = outPath;
        this.fileLoadPath = fileLoadPath;
        File file = new File(fileLoadPath);// 读入文件
        img = ImageIO.read(file);      // 构造Image对象
        width = img.getWidth(null);    // 得到源图宽
        height = img.getHeight(null);  // 得到源图长
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        System.out.println("开始：" + new Date().toLocaleString());
//        ImagesZip imgCom = new ImagesZip("D:\\test0.png", "D:\\456.png");
        System.out.println("结束：" + new Date().toLocaleString());
    }

    /**
     */
    public byte[] resizeFix() throws IOException {
        this.resizeFix(width, height);
        return content;
    }

    /**
     * 按照宽度还是高度进行压缩
     *
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    private void resizeFix(int w, int h) throws IOException {
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
    private void resizeByWidth(int w) throws IOException {
        int h = height * w / width;
        resize(w, h);
    }

    /**
     * 以高度为基准，等比例缩放图片
     *
     * @param h int 新高度
     */
    private void resizeByHeight(int h) throws IOException {
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
        File destFile = new File(outPath);
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        encoder.encode(image); // JPEG编码
        out.close();
        File file = new File(outPath);// 读入文件
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[400000];
        while (inputStream.read(buffer, 0, 400000) != -1) {
            content = ArrayUtils.addAll(content, buffer);
        }
        inputStream.close();
        File delTmpFile = new File(fileLoadPath);
        if (delTmpFile.exists()) {
            delTmpFile.delete();
        }
    }
}