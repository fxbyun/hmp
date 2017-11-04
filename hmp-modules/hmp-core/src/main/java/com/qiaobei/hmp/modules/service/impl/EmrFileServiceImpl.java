package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.repository.EmrFileDao;
import com.qiaobei.hmp.modules.service.EmrFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/19 0019
 * Time 17:00
 */
@Service("emrFileService")
@Transactional
public class EmrFileServiceImpl implements EmrFileService {
    private final EmrFileDao emrFileDao;

    @Autowired
    public EmrFileServiceImpl(EmrFileDao emrFileDao) {
        this.emrFileDao = emrFileDao;
    }

    @Override
    public void save(EmrFile emrFile) {
        emrFileDao.save(emrFile);
    }

    @Override
    public EmrFile findByFileNameAndDoctorAndFileType(String fileName, Doctor dcotor, EmrFile.EmrFileType emrFileType) {
//        return emrFileDao.findByFileNameAndDoctorAndFileType(fileName,dcotor,emrFileType);

        return emrFileDao.findOne((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(EmrFile_.fileName),
                        fileName
                ),
                cb.equal(
                        root.join(EmrFile_.doctor).get(Doctor_.id),
                        dcotor.getId()

                ),
                cb.equal(
                        root.get(EmrFile_.fileType),
                        emrFileType
                )
        ));
    }

    @Override
    public void delEmrFileByNotInNameList(Emr emr, List<String> emrFileNameList) {
        emrFileDao.delEmrFileByNotInNameList(emr, emrFileNameList);
    }

    @Override
    public EmrFile findByFileNameAndDoctorAndFileType(String fileName, Doctor doctor) {
        return emrFileDao.findOne((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(EmrFile_.fileName),
                        fileName
                ),
                cb.equal(
                        root.join(EmrFile_.doctor).get(Doctor_.id),
                        doctor.getId()

                )
        ));
    }
}
