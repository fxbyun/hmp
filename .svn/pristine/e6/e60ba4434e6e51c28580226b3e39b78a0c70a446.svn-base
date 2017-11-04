package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.repository.DataFileDao;
import com.qiaobei.hmp.service.DataFileService;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("dataFileService")
@Monitored
public class DataFileServiceImpl implements DataFileService {

    private static Logger logger = LoggerFactory.getLogger(DataFileServiceImpl.class);

    @Resource
    private DataFileDao dataFileDao;

    @Override
    public DataFile getDataFile(Long logicId, DataFile.Type type) {
        return dataFileDao.findByLogicIdAndType(logicId, type);
    }
}
