package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.EmrFile;
import com.qiaobei.hmp.modules.entity.QEmrFile;
import com.qiaobei.hmp.modules.service.EmrFileService;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.support.Result;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springside.modules.utils.ImagesZip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/19 0019
 * Time 14:27
 */
//高拍仪
@Controller
public class CamOCXController {
    private final EmrFileService emrFileService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CamOCXController(EmrFileService emrFileService) {
        this.emrFileService = emrFileService;
    }

    @RequestMapping(value = "/fragment/takePhotos")
    public String takePhotos(Model model,
                             @RequestParam("type") EmrFile.EmrFileType emrFileType) {
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("tmpId", UUID.randomUUID().toString().replace("-", ""));
        model.addAttribute("type", emrFileType);
        return "/fragment/takePhotos";
    }

    @RequestMapping(value = "/pub/imagesLoadStatus")
    @ResponseBody
    public Result upLoadStatus(@RequestParam(value = "doctorId") Long doctorId, @RequestParam(value = "fileName") String fileName) {

        QEmrFile qEmrFile = QEmrFile.emrFile;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        if (jpaQueryFactory.from(qEmrFile)
                .select(qEmrFile.id)
                .where(
                        qEmrFile.doctor.
                                eq(new Doctor(doctorId))
                        ,
                        qEmrFile.fileName
                                .eq(fileName)
                ).fetchFirst() != null) {
            return Result.ok();
        }
        return Result.fail();
    }

    @RequestMapping(value = "/pub/upLoadEmrFile")
    public Result upLoadPng(HttpServletRequest request,
                            @RequestParam(value = "doctorId", required = false) Long doctorId,
                            @RequestParam(value = "fileType", required = false) EmrFile.EmrFileType fileType,
                            @RequestParam(value = "tmpId", required = false) String tmpId
    ) {
        EmrFile emrFile = new EmrFile();
        try {
            ServletInputStream stream = request.getInputStream();
            String sepa = File.separator;
            //文件保存路径
            String savePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") + "fileDir" + sepa + doctorId;
            //文件上传缓冲区
            String tempPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") + "fileDir" + sepa + "temp";
            mkDirIfNotExists(savePath);
            mkDirIfNotExists(tempPath);
            String filePath = tempPath + sepa + tmpId + ".png";
            OutputStream bos = new FileOutputStream(filePath);
            int bytesRead;
            byte[] buffer = new byte[400000];
            while ((bytesRead = stream.read(buffer, 0, 400000)) != -1) {
                bos.write(buffer, 0, bytesRead);
                bos.flush();
            }
            bos.close();
            stream.close();
            //压缩图片
            System.out.println("文件上传完毕,开始压缩图片");
            String outPutFilePath = savePath + sepa + tmpId + ".png";
            emrFile.setContent(new ImagesZip(filePath, outPutFilePath, emrFile.getContent()).resizeFix());
            System.out.println("压缩图片完毕 开始保存到数据库!");
            emrFile.setDoctor(new Doctor(doctorId));
            emrFile.setFileName(tmpId);
            emrFile.setCreateOn(new Date());
            emrFile.setFileType(fileType);
            emrFileService.save(emrFile);
            System.out.println("文件保存到数据库完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }


    private void mkDirIfNotExists(String savePath) {
        File fileDire = new File(savePath);
        if (!fileDire.exists()) {
            fileDire.mkdir();
        }
    }

}
