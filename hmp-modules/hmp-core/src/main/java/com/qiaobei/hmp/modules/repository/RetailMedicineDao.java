package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.RetailMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public interface RetailMedicineDao extends JpaRepository<RetailMedicine, Long>, JpaSpecificationExecutor<RetailMedicine> {


}
