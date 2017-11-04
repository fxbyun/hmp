package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Diagnosis;
import com.qiaobei.hmp.repository.DiagnosisDao;
import com.qiaobei.hmp.service.DiagnosisService;
import org.javasimon.aop.Monitored;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/5/30 0030
 * Time 14:56
 */

@Service("diagnosisService")
@Transactional
@Monitored
public class DiagnosisServiceImpl implements DiagnosisService {

    @Resource
    private DiagnosisDao diagnosisDao;

    @Override
    public Page<Diagnosis> findPage(Pageable page, String name) {
        return null;
    }

    @Override
    public Diagnosis findOneById(Long id) {
        return diagnosisDao.getOne(id);
    }

    @Override
    public List<Diagnosis> findByName(String name) {
        return diagnosisDao.findByName(name);
    }
}