package com.qiaobei.hmp.modules.service.impl;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.repository.RetailDao;
import com.qiaobei.hmp.modules.service.RetailService;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.qiaobei.hmp.modules.support.DateUtils.datetimeFormat;

/**
 *
 */
@Service("retailService")
@Transactional
public class RetailServiceImpl implements RetailService {

    @Resource
    private RetailDao retailDao;

    public static void main(String[] args) {
        System.out.println(DateUtils.date2Str(datetimeFormat).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
    }

    @Override
    public Double getRetailMedIsChinese(Retail retail) {
        return Optional.ofNullable(retail).map(ret ->
                ret.getRetailMedicineList()
                        .stream()
                        .filter(retMed ->
                                retMed.getMedicinePrivate().getType() == Medicine.Type.Chinese
                        ).mapToDouble(retMed -> retMed.getTotalPrice()).sum()
        ).orElse(0.00D);
    }

    @Override
    public Double getRetailListChineseMedPrice(List<Retail> retailList) {
        if (CollectionUtils.isNotEmpty(retailList)) {
            Double chineseMeds = retailList.stream()
                    .mapToDouble(retail ->
                            getRetailMedIsChinese(retail)
                    ).sum();
            return chineseMeds;
        } else {
            return Double.valueOf(0.00D);
        }
    }

    @Override
    public List<RetailMedicine> getChineseMedList(Retail retail) {
        return Optional.ofNullable(retail).map(ret ->
                ret.getRetailMedicineList()
                        .stream()
                        .filter(retMed ->
                                retMed.getMedicinePrivate().getType() == Medicine.Type.Chinese)
                        .collect(Collectors.toList())).orElse(Lists.emptyList());
    }

    @Override
    public Map<Long, List<RetailMedicine>> getChineseMedList(List<Retail> retailList) {
        Map<Long, List<RetailMedicine>> chMap = Maps.newHashMap();
        retailList.forEach(retail -> {
            chMap.put(retail.getId(), getChineseMedList(retail));
        });
        return chMap;
    }

    @Override
    public Map<Long, List<RetailMedicine>> getWesternMedList(List<Retail> retailList) {
        Map<Long, List<RetailMedicine>> weMap = Maps.newHashMap();
        retailList.forEach(retail -> {
            weMap.put(retail.getId(), getWesternMedList(retail));
        });
        return weMap;
    }

    @Override
    public List<RetailMedicine> getWesternMedList(Retail retail) {
        return Optional.ofNullable(retail).map(ret ->
                ret.getRetailMedicineList()
                        .stream()
                        .filter(retMed ->
                                retMed.getMedicinePrivate().getType() == Medicine.Type.Western)
                        .collect(Collectors.toList())).orElse(Lists.emptyList());
    }

    @Override
    public List<Retail> getByPatientStartAndEndTime(Patient patient, Date startTime, Date endTime, Doctor doctor) {
        if (null == startTime) {
            startTime = DateUtils.str2Date("1990-01-01", DateUtils.date_sdf);
        }

        if (null == endTime) {
            endTime = DateUtils.getDayEndMaxTime();
        }
        if (null != patient) {
            return retailDao.findByPatientAndChargeTime(patient, startTime, endTime, doctor);
        } else {
            return Lists.emptyList();
        }

    }

    @Override
    public List<Retail> getRetailByPatient(Patient patient) {
        return Optional.ofNullable(patient).map(pat ->
                retailDao.findByPatient(patient)
        ).orElse(Lists.emptyList());
    }

    @Override
    public Double getRetailListWesternMedPrice(List<Retail> retailList) {
        if (CollectionUtils.isNotEmpty(retailList)) {
            Double chineseMeds = retailList.stream()
                    .mapToDouble(retail ->
                            getRetailMedIsWestern(retail)
                    ).sum();
            return chineseMeds;
        } else {
            return Double.valueOf(0.00D);
        }
    }

    @Override
    public Double getRetailMedIsWestern(Retail retail) {
        return Optional.ofNullable(retail).map(ret ->
                ret.getRetailMedicineList()
                        .stream()
                        .filter(retMed ->
                                retMed.getMedicinePrivate().getType() == Medicine.Type.Western
                        ).mapToDouble(retMed -> retMed.getTotalPrice()).sum()
        ).orElse(0.00D);
    }

    @Override
    public Double getRetailListPriceSum(List<Retail> retailList) {
        if (CollectionUtils.isNotEmpty(retailList)) {
            //总金额
            Double allPrice = retailList
                    .stream()
                    .filter(retail -> retail.getRealCost() != null)
                    .mapToDouble(retail -> retail.getRealCost()).sum();
            return allPrice;
        } else {
            return Double.valueOf(0.00D);
        }

    }

    @Override
    public Double getAllMedPrice(Retail retail) {

        //unit不适用(想不到什么好名字了=_=)
        Double priceOne = retail.getRetailMedicineList().stream().filter(retMed ->
                retMed.getUnit() != retMed.getMedicinePrivate().getUnit() && null != retMed.getRate() && retMed.getRate() > 0
        ).mapToDouble(retMed ->
                (retMed.getQty() / retMed.getRate()) * retMed.getCopies() * retMed.getRetailPrice()
        ).sum();
        //unit适用
        Double priceTwo = retail.getRetailMedicineList().stream().filter(retMed ->
                retMed.getUnit() == retMed.getMedicinePrivate().getUnit()
        ).mapToDouble(retMed ->
                retMed.getQty() * retMed.getCopies() * retMed.getRetailPrice()
        ).sum();

        return DecimalCalculate.roundDown(priceOne + priceTwo, 2);
    }

    @Override
    public void delRetailList(List<Retail> retailList) {
        retailDao.delete(retailList);
    }

    @Override
    public void delRetail(Retail retail) {
        retailDao.delete(retail);
    }

    @Override
    public List<Retail> findByChargeTimeAndPharmacist(Date chargeTime, Pharmacist pharmacist) {
        return retailDao.findByTimeAndPharmacist(DateUtils.getDayStartTime(chargeTime), DateUtils.getDayEndMaxTime(chargeTime), pharmacist);
    }

    @Override
    public List<Retail> findByChargeTime(Date chargeTime) {
        return retailDao.findByTime(DateUtils.getDayStartTime(chargeTime), DateUtils.getDayEndMaxTime(chargeTime));
    }

    @Override
    public void save(Retail retail) {
        retailDao.save(retail);
    }

    @Override
    public Retail findById(Long retailId) {
        return retailDao.getOne(retailId);
    }

    @Override
    public Page<Retail> findAllPage(String orderId, Doctor doctor, Pageable pageable) {
        return retailDao.findByOrderIdHasSave("%" + orderId + "%", doctor, pageable);
    }

    @Override
    public Retail createOne(Patient patient, Pharmacist pharmacist) {
        Retail retail = new Retail();
        DateUtils.date2Str(datetimeFormat);
        //订单号
        retail.setOrderId(DateUtils.date2OrderStr());
        //收银人
        retail.setPharmacist(pharmacist);
        //实收金额
        retail.setRealCost(0.00D);
        //药品总金额
        retail.setAllMedCost(0.00D);
        //订单状态
        retail.setRetailStatus(Retail.Retail_Status.Not_Save);
        //收费状态
        retail.setChargeStatus(Retail.Charge_Status.Not_Charge);
        //主治医生
        retail.setDoctor(pharmacist.getDoctor());

        //是否绑定了患者信息
        if (null == patient) {
            retail.setIfBinding(false);
        } else {
            retail.setIfBinding(true);
            retail.setPatient(patient);
        }
        return retail;
    }

    @Override
    public List<Retail> findByRetailStatusToday(Doctor doctor, Retail.Retail_Status status, Date chargeTime) {

        return retailDao.findByRetailStatusToday(doctor, status,
                DateUtils.getDayStartTime(chargeTime), DateUtils.getDayEndMaxTime(chargeTime));
    }

    @Override
    public List<Retail> findByRetailStatus(Doctor doctor, Retail.Retail_Status status) {
        return retailDao.findByRetailStatus(doctor, status);
    }
}
