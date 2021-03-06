package com.qiaobei.hmp.modules.service.impl;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.RetailMedicine;
import com.qiaobei.hmp.modules.repository.RetailMedicineDao;
import com.qiaobei.hmp.modules.service.RetailMedicineService;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
@Service("retailMedicineService")
@Transactional
public class RetailMedicineServiceImpl implements RetailMedicineService {

    @Resource
    private RetailMedicineDao retailMedicineDao;


    @Override
    public RetailMedicine findById(Long retailMedicineId) {
        return retailMedicineDao.getOne(retailMedicineId);
    }

    @Override
    public void save(RetailMedicine retailMedicine) {
        retailMedicineDao.save(retailMedicine);
    }

    @Override
    public Map<Long, Double> getTotalNumMap(List<RetailMedicine> redMedList) {
        Map<Long, Double> totalNumMap = Maps.newHashMap();
        redMedList.forEach(redMed ->
                totalNumMap.put(redMed.getId(), getTotalNum(redMed))
        );
        return totalNumMap;
    }

    @Override
    public Double getTotalNum(RetailMedicine retailMedicine) {
        return Optional.ofNullable(retailMedicine).map(retMed -> {
            Double totalNum = 0.00D;
            if (retMed.getUnit() != retMed.getMedicinePrivate().getUnit()) {
                if (null != retMed.getRate() && retMed.getRate() > 0) {
                    totalNum = (retMed.getQty() / retMed.getRate()) * retMed.getCopies();
                }
            } else {
                totalNum = retMed.getQty() * retMed.getCopies();
            }
            return DecimalCalculate.roundDown(totalNum, 2);
        }).orElse(0.00D);
    }

    @Override
    public Double getTotalPrice(RetailMedicine retailMedicine) {
        return Optional.ofNullable(retailMedicine).map(retMed -> {
            Double totalPrice = 0.00D;
            if (retMed.getUnit() != retMed.getMedicinePrivate().getUnit()) {
                if (null != retMed.getRate() && retMed.getRate() > 0) {
                    totalPrice = (retMed.getQty() / retMed.getRate()) * retMed.getCopies() * retMed.getRetailPrice();
                }
            } else {
                totalPrice = retMed.getQty() * retMed.getCopies() * retMed.getRetailPrice();
            }
            return DecimalCalculate.roundDown(totalPrice, 2);
        }).orElse(0.00D);
    }

    @Override
    public void delRetailMed(RetailMedicine retailMedicine) {
        retailMedicineDao.delete(retailMedicine);
    }

    @Override
    public void saveList(List<RetailMedicine> retailMedicineList) {
        retailMedicineDao.save(retailMedicineList);
    }

    @Override
    public List<RetailMedicine> findAll() {
        return retailMedicineDao.findAll();
    }
}
