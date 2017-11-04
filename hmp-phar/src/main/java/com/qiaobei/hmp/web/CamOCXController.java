package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.EmrJClassAdviceDict;
import com.qiaobei.hmp.modules.entity.ExamLabFile;
import com.qiaobei.hmp.modules.entity.QExamLabFile;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.modules.service.EmrFileService;
import com.qiaobei.hmp.modules.service.EmrJClassAdviceDictService;
import com.qiaobei.hmp.support.Result;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springside.modules.utils.ImagesZip;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2017/1/3 0003
 * Time 11:30
 */
@Controller
public class CamOCXController extends ConstantsController {
    private final EmrFileService emrFileService;
    @Resource
    private EmrJClassAdviceDictService emrJClassAdviceDictService;
    @PersistenceContext
    private EntityManager entityManager;


    public CamOCXController(EmrFileService emrFileService) {
        this.emrFileService = emrFileService;
    }

    /**
     * 上传检验/检查结果图片
     */
    @RequestMapping(value = "/pub/upLoadEmrFile")
    public Result upLoadPng(HttpServletRequest request,
                            @RequestParam(value = "doctorId", required = false) Long doctorId,
                            @RequestParam(value = "tmpId", required = false) String tmpId,
                            @RequestParam(value = "id", required = false) Long id

    ) {

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

            EmrJClassAdviceDict emrJClassAdviceDict = emrJClassAdviceDictService.findById(id);
            emrJClassAdviceDict.getExamLabFileList().clear();
            ExamLabFile examLabFile = new ExamLabFile();
            examLabFile.setFileName(tmpId + ".png");
            examLabFile.setTypes(ExamLabFile.Exam_Lab_File_Type.Png);
            System.out.println("文件上传完毕,开始压缩图片");
            String outPutFilePath = savePath + sepa + tmpId + ".png";
            examLabFile.setFileData(new ImagesZip(filePath, outPutFilePath, examLabFile.getFileData()).resizeFix());
            System.out.println("压缩图片完毕 开始保存到数据库!");
            examLabFile.setEmrJClassAdviceDict(emrJClassAdviceDict);
//                    examLabFileService.save(examLabFile);
            emrJClassAdviceDict.getExamLabFileList().add(examLabFile);
            emrJClassAdviceDict.setStatus(StatusEntity.Status.Have_Exam_Or_Lab);
            emrJClassAdviceDict.setUpdateOn(new Date());
            emrJClassAdviceDictService.save(emrJClassAdviceDict);
            System.out.println("文件保存到数据库完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    /**
     * 文件是否存在  不存在就创建
     */
    private void mkDirIfNotExists(String savePath) {
        File fileDire = new File(savePath);
        if (!fileDire.exists()) {
            fileDire.mkdir();
        }
    }

    /**
     * 查询是否上传完毕
     */
    @RequestMapping(value = "/pub/imagesLoadStatus")
    @ResponseBody
    public Result upLoadStatus(@RequestParam(value = "fileName") String fileName) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QExamLabFile qExamLabFile = QExamLabFile.examLabFile;
        if (jpaQueryFactory.from(qExamLabFile)
                .select(qExamLabFile.id).
                        where(
                                qExamLabFile.fileName.eq(fileName + ".png")
                        ).fetchFirst() != null) {
            return Result.ok();
        }
        return Result.fail();
    }
}
