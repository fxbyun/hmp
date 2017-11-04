package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.ExamLabFile;
import com.qiaobei.hmp.modules.repository.ExamLabFileDao;
import com.qiaobei.hmp.modules.service.ExamLabFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/11 0011
 * Time 14:18
 */
@Service("examLabFileService")
@Transactional
public class ExamLabFileServiceImpl implements ExamLabFileService {
    @Resource
    private ExamLabFileDao examLabFileDao;

    @Override
    public void save(ExamLabFile examLabFile) {
        examLabFileDao.save(examLabFile);
    }
}
