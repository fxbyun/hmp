package com.qiaobei.hmp.support;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA 2016.2
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 16/8/24
 * Time 下午6:06
 */
//@Service("FileUtilsServer")
public class FileUtilsServer {
    private String fileSavePath;

    /**
     * 保存文件到配置文件指定位置
     *
     * @param fileName 文件名字
     * @param fileByte 文件BYTE对象
     * @return 返回文件在磁盘中绝对路径
     */
    public String saveFile(String fileName, byte[] fileByte, String saveUrl) {
        if (StringUtils.isNotEmpty(saveUrl)) {
            fileSavePath = saveUrl;
        }

        File f = new File(fileSavePath + "/" + fileName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileUtils.writeByteArrayToFile(f, fileByte);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("文件保存报错!");
        }

        return f.getAbsolutePath();
    }


    public boolean isFileSave(String fileName) {
        File f = new File(fileSavePath + "/" + fileName);
        if (f.exists()) {
            return true;
        }
        return true;
    }


    public String getFileSavePath() {
        return fileSavePath;
    }

    public void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
    }
}
