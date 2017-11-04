package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Nation;
import com.qiaobei.hmp.repository.NationDao;
import com.qiaobei.hmp.service.NationService;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("nationService")
@Monitored
@Transactional
public class NationServiceImpl implements NationService {

    private static Logger logger = LoggerFactory.getLogger(NationServiceImpl.class);

    @Resource
    private NationDao nationDao;

    @Override
    public List<Nation> listNation(Integer parentNo) {
        return nationDao.findByParentNo(parentNo);
    }
}
