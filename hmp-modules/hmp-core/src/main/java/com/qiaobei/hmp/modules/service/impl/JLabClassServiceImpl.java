package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.JLabClass;
import com.qiaobei.hmp.modules.repository.JLabClassDao;
import com.qiaobei.hmp.modules.service.JLabClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/4 0004
 * Time 11:36
 */
@Service("jLabClassService")
@Transactional
public class JLabClassServiceImpl implements JLabClassService {
    @Resource
    private JLabClassDao jLabClassDao;

    @Override
    public List<JLabClass> findAll() {
        return jLabClassDao.findAll();
    }

    @Override
    public List<JLabClass> findByIdList(List<Long> ids) {
        return jLabClassDao.findByIdIn(ids);
    }

    @Override
    public JLabClass findById(Long examLabId) {
        return jLabClassDao.findOne(examLabId);
    }
}
