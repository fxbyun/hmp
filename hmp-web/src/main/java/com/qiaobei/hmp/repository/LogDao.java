package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogDao extends JpaRepository<SystemLog, Long>, JpaSpecificationExecutor<SystemLog> {

}

