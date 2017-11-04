package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.SystemLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface LogService {

    /**
     * 保存日志信息
     *
     * @param systemLog
     */
    public void save(SystemLog systemLog);

    /**
     * log分页、条件查询
     *
     * @param page
     * @param searchParams
     * @return
     */
    public Page<SystemLog> findPage(Pageable page, Map<String, Object> searchParams);
}
