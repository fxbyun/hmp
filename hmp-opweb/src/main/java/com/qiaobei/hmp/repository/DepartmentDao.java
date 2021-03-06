package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentDao extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

}

