package com.qiaobei.hmp.modules.wxpay.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

public class QrCodeUtil {
    /**
     * 返回接口获取到code_url的二维码图片image流
     * 使用
     * ImageIO.write(image, "png", new File("d:" + "csyor.com.png")
     * 输出到图片
     *
     * @param width  宽度
     * @param height 高度
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static BufferedImage getBufImg(String qrCodeUrl, int width, int height)
            throws IOException, WriterException {
        if (width <= 0 || height <= 0) {
            return null;
        }
        Hashtable<EncodeHintType, String> hints =
                new Hashtable<EncodeHintType, String>();
        // 内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(qrCodeUrl, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

}
