package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Department;
import com.qiaobei.hmp.repository.DepartmentDao;
import com.qiaobei.hmp.service.DepartmentService;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/1/15
 * Time 14:56
 */
@Service("departmentService")
@Transactional
@Monitored
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;

    @Override
    @Transactional(readOnly = true)
    public Department getDepartment(Long id) {
        return departmentDao.findOne(id);
    }

    @Override
    public void saveDepartment(Department department) {
        departmentDao.save(department);
    }

    @Override
    public void removeDepartment(Long id) {
        departmentDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }
}
