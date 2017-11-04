package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public interface SupplierDao extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {

    public List<Supplier> findByDoctor(Doctor doctor);


}
