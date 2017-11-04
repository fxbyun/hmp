package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.SystemLog;
import com.qiaobei.hmp.repository.LogDao;
import com.qiaobei.hmp.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.annotation.Resource;
import java.util.Map;

@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;

    @Transactional
    public void save(SystemLog systemLog) {
        logDao.save(systemLog);
    }

    @Override
    public Page<SystemLog> findPage(Pageable page, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<SystemLog> spec = DynamicSpecifications.bySearchFilter(filters.values(), SystemLog.class);
        return logDao.findAll(spec, page);
    }
}
