package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.JExamClass;
import com.qiaobei.hmp.modules.repository.JExamClassDao;
import com.qiaobei.hmp.modules.service.JExamClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/4 0004
 * Time 11:34
 */
@Service("jExamClassService")
@Transactional
public class JExamClassServiceImpl implements JExamClassService {
    @Resource
    private JExamClassDao jExamClassDao;

    @Override
    public List<JExamClass> findAll() {
        return jExamClassDao.findAll();
    }

    @Override
    public List<JExamClass> finyByIdList(List<Long> ids) {
        return jExamClassDao.findByIdIn(ids);
    }

    @Override
    public JExamClass findById(Long examLabId) {
        return jExamClassDao.findOne(examLabId);
    }
}
