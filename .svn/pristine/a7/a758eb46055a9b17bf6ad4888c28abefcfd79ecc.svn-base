package com.qiaobei.hmp.modules.support;

import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.ExamLabFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

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

    /**
     * 判断文件是否已经从数据库输出到网站 fileDir/医生ID 目录 如果没有 即 马上 写到 fileDir/医生ID/目录  下面去
     *
     * @param dataFile
     * @return void
     */
    public void outPutFileToDisk(DataFile dataFile, Doctor doctor) {
        String fileAubUrl = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(File.separatorChar +
                "fileDir" + File.separatorChar + doctor.getId());
        System.out.println(fileAubUrl);
        if (dataFile != null) {
            File file = new File(fileAubUrl + File.separatorChar + dataFile.getFileName());
            if (!file.exists()) {
                this.saveFile(dataFile.getFileName(), dataFile.getContent(), fileAubUrl);
            }
        }

    }

    /**
     * @param examLabFileList
     * @param doctor
     */
    public void outPutExamLabFileToDisk(List<ExamLabFile> examLabFileList, Doctor doctor) {
        examLabFileList.forEach(examLabFile -> {
            DataFile dataFile = new DataFile();
            dataFile.setContent(examLabFile.getFileData());
            dataFile.setFileName(examLabFile.getFileName());
            this.outPutFileToDisk(dataFile, doctor);
        });
    }


    //文件下载
    public boolean dowmFile(String fileUrl, String fileName, HttpServletRequest request, HttpServletResponse response) {
        if (fileName != null) {
            File file = new File(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(File.separatorChar + fileUrl));
            if (file.exists()) {
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }


}
