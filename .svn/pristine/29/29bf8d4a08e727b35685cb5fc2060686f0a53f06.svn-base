package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.repository.EmrDao;
import com.qiaobei.hmp.service.EmrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("emrService")
@Transactional
public class EmrServiceImpl implements EmrService {

    private static Logger logger = LoggerFactory.getLogger(EmrServiceImpl.class);

    @Resource
    private EmrDao emrDao;

    @Override
    public Emr findById(Long id) {
        return emrDao.getOne(id);
    }

    @Override
    public List<Emr> findByPatientUid(String uid) {
        return emrDao.findByPatientUid(uid);
    }

    @Override
    public void saveEmr(Emr emr) {
        emrDao.save(emr);
    }

    @Override
    public Page<Emr> findPage(Pageable page, Map<String, Object> searchParams) {
        return emrDao.findAll(null, page);
    }
}
