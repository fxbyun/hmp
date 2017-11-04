package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.EmrMedicine;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.modules.repository.EmrMedicineDao;
import com.qiaobei.hmp.modules.service.EmrMedicineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/31 0031
 * Time 15:11
 */
@Service("emrMedicineService")
@Transactional
public class EmrMedicineServiceImpl implements EmrMedicineService {
    @Resource
    EmrMedicineDao emrMedicineDao;

    @Override
    public List<EmrMedicine> findByIdIn(List<Long> longList) {
        return emrMedicineDao.findByIdIn(longList);
    }

    @Override
    public void updateEmrMedBackByIdIn(List<Long> longList) {
        emrMedicineDao.updateEmrMedByIdIn(StatusEntity.Status.Have_Dispensing_Back, longList);
    }

    @Override
    public EmrMedicine findById(Long id) {
        return emrMedicineDao.findOne(id);
    }

    @Override
    public void save(EmrMedicine emrMedicine) {
        emrMedicineDao.save(emrMedicine);
    }
}
