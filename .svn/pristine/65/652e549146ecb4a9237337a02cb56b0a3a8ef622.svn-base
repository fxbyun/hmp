package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Supplier;
import com.qiaobei.hmp.modules.repository.SupplierDao;
import com.qiaobei.hmp.modules.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 供应商Dao
 */
@Service("supplierService")
@Transactional
public class SupplierServiceImpl implements SupplierService{

    @Resource
    private SupplierDao supplierDao;

    @Override
    public void save(Supplier supplier) {

        supplierDao.save(supplier);
    }

    @Override
    public void delSupplier(Long id) {
        supplierDao.delete(id);
    }

    @Override
    public Supplier findById(Long id) {
        return supplierDao.getOne(id);
    }

    @Override
    public List<Supplier> findByDoctor(Doctor doctor) {
        return supplierDao.findByDoctor(doctor);
    }
}
