package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.repository.DoctorDao;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import com.qiaobei.hmp.modules.support.EntityTmpCloumsVal;
import com.qiaobei.hmp.repository.SysMedicinePrivateDao;
import com.qiaobei.hmp.service.SysMedicinePrivateService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.javasimon.aop.Monitored;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/3/6 0006.
 */
@Service("sysMedicinePrivateService")
@Transactional
@Monitored
public class SysMedicinePrivateServiceImpl implements SysMedicinePrivateService {
    @Resource
    SysMedicinePrivateDao sysMedicinePrivateDao;

    @Resource
    IaiLossDetailService iaiLossDetailService;
    @Resource(name = "doctorDaoCode")
    DoctorDao doctorDao;

    @Override
    public List<MedicinePrivate> getMedPriByMedIdList(Long id, Doctor doctor) {
        return sysMedicinePrivateDao.findByMedAndDoctorList(new Medicine(id), doctor);
    }

    @Override
    public MedicinePrivate getMedPriByMedId(Long id, Doctor doctor) {
        return sysMedicinePrivateDao.findByMedAndDoctor(new Medicine(id), doctor);
    }

    @Override
    public void save(MedicinePrivate medicinePrivate) {
        sysMedicinePrivateDao.save(medicinePrivate);
    }

    @Override
    public void update(MedicinePrivate medicinePrivate) {

        if (medicinePrivate.getDoctor().getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            doctorDao.findAll((root, query, cb) -> cb.equal(
                    root.get(Doctor_.primaryDoctorId),
                    medicinePrivate.getDoctor().getId()
            )).forEach(doctor -> {
                List<MedicinePrivate> medicinePrivateList = sysMedicinePrivateDao.findByDoctorAndMedicine(doctor, medicinePrivate.getMedicine());
                MedicinePrivate medicinePrivate1;
                if (medicinePrivateList.size() > 0) {
                    medicinePrivate1 = medicinePrivateList.get(0).medPriveateToMedPriveate(medicinePrivate);
                } else {
                    medicinePrivate1 = new MedicinePrivate();
                    medicinePrivate1.medPriveateToMedPriveate(medicinePrivate);
                    medicinePrivate1.setDoctor(doctor);
                }
                sysMedicinePrivateDao.save(medicinePrivate1);

            });
        }
        sysMedicinePrivateDao.save(medicinePrivate);
    }

    @Override
    public MedicinePrivate getMedPrivate(Long id) {
        return sysMedicinePrivateDao.findOne(id);
    }

    @Override
    public List<MedicinePrivate> findRateByMoreUser(Long id, Pageable pageable) {
        return sysMedicinePrivateDao.findRateByMoreUser(id, pageable);
    }

    @Override
    public List<MedicinePrivate> findStandardByMoreUser(Long id, Pageable pageable) {
        return sysMedicinePrivateDao.findStandardByMoreUser(id, pageable);
    }

    @Override
    public List<MedicinePrivate> findByDoctorAndMedicine(Doctor doctor, Medicine medicine) {
        return sysMedicinePrivateDao.findByDoctorAndMedicine(doctor, medicine);
    }

    @Override
    public Page<MedicinePrivate> findTagTherapyByMedName(Doctor doctor, String medName, Pageable pageable) {
        return sysMedicinePrivateDao.findTagTherapyByMedName(doctor, "%" + medName + "%", pageable);
    }

    @Override
    public Page<MedicinePrivate> findTagTherapy(Doctor doctor, String helpCode, Pageable pageable) {
        return sysMedicinePrivateDao.findTagTherapy(doctor, "%" + helpCode + "%", pageable);
    }

    @Override
    public List<MedicinePrivate> isDoctorHasThisTherapy(Doctor doctor, Medicine medicine) {
        return sysMedicinePrivateDao.findByDoctorAndMedicine(doctor, medicine).stream().filter(
                medicinePrivate -> medicinePrivate.getType() == Medicine.Type.ChineseTherapy)
                .collect(Collectors.toList());
    }

    @Override
    public String therapyHtml(MedicinePrivate therapy, String useQty, Medicine.Unit useUnit, String useUnitValue, String therapyCopy) {
        String dataReady = therapy.getId() + "," + useQty + "," + "'" + useUnit + "'" + "," + therapyCopy;

        return "<div>" + "<strong onclick=\"paperTherapy(this," + dataReady + ")\">" + therapy.getName() + "</strong>" +
                "<span>每次" + useQty + "" + useUnitValue + "</span><span>共" + therapyCopy + "次</span>" +
                "<i class='fa fa-trash-o' onclick='fn_delTherapy(this)'></i>" +
                "<span class='therapy-value' style='display:none;'>" +
                "<input name='therapyId' value=\"" + therapy.getId() + "\"/>" +
                "<input name='therapyName' value=\"" + therapy.getName() + "\"/>" +
                "<input name='useQty' value=\"" + useQty + "\"/>" +
                "<input name='useUnit' value=\"" + useUnit + "\"/>" +
                "<input name='therapyCopy' value=\"" + therapyCopy + "\"/>" +
                "<input class='therapyPrice' name='therapyPrice' value=\"" + therapy.getPrice() + "\"/>" +
                "</span>" +
                "</div>";
    }

    @Override
    public void insertLossDetil(Emr emr) {
        iaiLossDetailService.delByEmrId(emr.getId());
        List<Medicine> medicineList = emr.getEmrMedicineList().stream().map(
                EmrMedicine::getMedicine
        ).collect(Collectors.toList());

        List<MedicinePrivate> medicinePrivateList;
        if (emr.getDoctor().getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            medicinePrivateList = sysMedicinePrivateDao.
                    findByHaveManagerAndDoctorAndMedicineIn(
                            MedicinePrivate.HaveManager.YES,
                            new Doctor(emr.getDoctor().getPrimaryDoctorId()),
                            medicineList
                    );
        } else {
            medicinePrivateList = sysMedicinePrivateDao.findByHaveManagerAndDoctorAndMedicineIn(
                    MedicinePrivate.HaveManager.YES,
                    emr.getDoctor(),
                    medicineList
            );
        }

        List<IaiLossDetail> iaiLossDetailListEnd = Lists.newArrayList();

        medicinePrivateList.forEach(
                medicinePrivate -> emr.getEmrMedicineList().forEach(
                        emrMedicine -> {
                            if (medicinePrivate.getType() == Medicine.Type.Chinese && medicinePrivate.getRate() == null) {
                                medicinePrivate.setRate(1D);
                            }
                            if (emrMedicine.getMedicine().getId().equals(medicinePrivate.getMedicine().getId())) {
                                List<EntityTmpCloumsVal> entityTmpCloumsValList =
                                        iaiLossDetailService.getIaiLossDetilStockByMedPaivate(medicinePrivate);
                                if (entityTmpCloumsValList.size() > 0) {
                                    //当前药品使用的 数量总数   数量/单位 X 份数=使用总量
                                    Double totleUse = emrMedicine.getQty() * emrMedicine.getCopies();
                                    //统计当前库存该药品总计数量    总数*换算单位=库存总量
                                    if (medicinePrivate.getRate() == null) {
                                        medicinePrivate.setRate(1D);
                                    }
                                    Double totleHave = entityTmpCloumsValList.stream().mapToDouble(
                                            EntityTmpCloumsVal::getTotlenSize).sum() * medicinePrivate.getRate();
                                    //如果 使用总量小于或等于当前库存总量  说明 为正库存
                                    if (totleUse <= totleHave) {
                                        for (EntityTmpCloumsVal entityTmpCloumsVal : entityTmpCloumsValList) {
                                            //如果
                                            if (entityTmpCloumsVal.getTotlenSize() * medicinePrivate.getRate() > totleUse && totleUse != 0D) {
                                                iaiLossDetailListEnd.add(
                                                        createIaiLossDetil(medicinePrivate,
                                                                emr.getId(),
                                                                totleUse,
                                                                entityTmpCloumsVal.getValidityDate(),
                                                                emrMedicine.getId(), emr,
                                                                entityTmpCloumsVal.getId()
                                                        )
                                                );
                                                totleUse = 0D;
                                            } else if (entityTmpCloumsVal.getTotlenSize() > 0 && totleUse != 0D) {
                                                iaiLossDetailListEnd.add(
                                                        createIaiLossDetil(medicinePrivate, emr.getId(),
                                                                entityTmpCloumsVal.getTotlenSize() * medicinePrivate.getRate()
                                                                , entityTmpCloumsVal.getValidityDate(),
                                                                emrMedicine.getId(), emr,
                                                                entityTmpCloumsVal.getId()
                                                        )
                                                );
                                                totleUse = totleUse - entityTmpCloumsVal.getTotlenSize() * medicinePrivate.getRate();
                                            }
                                        }
                                    } else {
                                        //如果 库存总数小于0 说明 所有药品已经 没有了 直接采用 最后一个有效期 进行负库存操作
                                        if (totleHave <= 0 && totleUse != 0D) {
                                            EntityTmpCloumsVal entityTmpCloumsVal = entityTmpCloumsValList.get(entityTmpCloumsValList.size() - 1);
                                            iaiLossDetailListEnd.add(
                                                    createIaiLossDetil(medicinePrivate,
                                                            emr.getId(),
                                                            totleUse, entityTmpCloumsVal.getValidityDate()
                                                            , emrMedicine.getId(), emr,
                                                            entityTmpCloumsVal.getId()
                                                    )
                                            );
                                            totleUse = 0D;
                                        } else if (totleUse != 0D) {
                                            //循环 取出每个 有效期可能还剩余的 药品数量 减去
                                            for (EntityTmpCloumsVal entityTmpCloumsVal : entityTmpCloumsValList) {
                                                if (entityTmpCloumsVal.getTotlenSize() > 0) {
                                                    iaiLossDetailListEnd.add(
                                                            createIaiLossDetil(medicinePrivate, emr.getId(),
                                                                    entityTmpCloumsVal.getTotlenSize() * medicinePrivate.getRate()
                                                                    , entityTmpCloumsVal.getValidityDate(), emrMedicine.getId(), emr,
                                                                    entityTmpCloumsVal.getId()
                                                            )
                                                    );
                                                    totleUse = totleUse - entityTmpCloumsVal.getTotlenSize() * medicinePrivate.getRate();
                                                }
                                            }
                                            //循环完毕  但是  库存还是不够用 所以直接把 最近的到期时间 负库存记录
                                            EntityTmpCloumsVal entityTmpCloumsVal = entityTmpCloumsValList.get(entityTmpCloumsValList.size() - 1);
                                            iaiLossDetailListEnd.add(
                                                    createIaiLossDetil(medicinePrivate,
                                                            emr.getId(),
                                                            totleUse,
                                                            entityTmpCloumsVal.getValidityDate(),
                                                            emrMedicine.getId(), emr,
                                                            entityTmpCloumsVal.getId())
                                            );
                                            totleUse = 0D;
                                        }
                                    }
                                }
                            }
                        }
                )
        );


        iaiLossDetailService.saveList(iaiLossDetailListEnd);
    }


    private IaiLossDetail createIaiLossDetil(MedicinePrivate medicinePrivate, Long emrId, Double totleUse, Date validityDate, Long emrMedId, Emr emr) {

        IaiLossDetail iaiLossDetail = new IaiLossDetail();
        iaiLossDetail.setMedicine(medicinePrivate);
        iaiLossDetail.setCreateDate(new Date());
        iaiLossDetail.setLossType(IaiLossDetail.IaiLossType.NORMAL);
        iaiLossDetail.setEmrId(emrId);
        iaiLossDetail.setBayingPrice(medicinePrivate.getPrice());
        iaiLossDetail.setCompanyId(medicinePrivate.getDefaultCompany().getId());
        iaiLossDetail.setStandard(medicinePrivate.getStandard());
        iaiLossDetail.setTotalNumber(totleUse);
        iaiLossDetail.setEmrMedId(emrMedId);
        iaiLossDetail.setValidityDate(validityDate);
        iaiLossDetail.setStatus(StatusEntity.Status.Normal);

        Doctor doctor = emr.getDoctor();
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            iaiLossDetail.setSubDoctorId(doctor.getId());
        }

        return iaiLossDetail;
    }

    private IaiLossDetail createIaiLossDetil(MedicinePrivate medicinePrivate, Long emrId, Double totleUse, Date validityDate, Long emrMedId, Emr emr, Long iaiDetailId) {

        IaiLossDetail iaiLossDetail = new IaiLossDetail();
        iaiLossDetail.setMedicine(medicinePrivate);
        iaiLossDetail.setCreateDate(new Date());
        iaiLossDetail.setLossType(IaiLossDetail.IaiLossType.NORMAL);
        iaiLossDetail.setEmrId(emrId);
        iaiLossDetail.setBayingPrice(medicinePrivate.getPrice());
        iaiLossDetail.setCompanyId(medicinePrivate.getDefaultCompany().getId());
        iaiLossDetail.setStandard(medicinePrivate.getStandard());
        iaiLossDetail.setTotalNumber(totleUse);
        iaiLossDetail.setEmrMedId(emrMedId);
        iaiLossDetail.setValidityDate(validityDate);
        iaiLossDetail.setStatus(StatusEntity.Status.Normal);

        //对应的iaiDetail
        iaiLossDetail.setIaiIntoDetailId(iaiDetailId);


        Doctor doctor = emr.getDoctor();
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            iaiLossDetail.setSubDoctorId(doctor.getId());
        }

        return iaiLossDetail;
    }

    @Override
    public Double getPriceAfterUnit(MedicinePrivate mp) {
        Double price = 0.00D;
        if (null != mp) {
            //数量/单位=换算单位
            if (mp.getUnit() == mp.getRealUnit()) {
                price = mp.getPrice();
            } else {
                if (null == mp.getRate() || mp.getRate() < 0) {
                    mp.setRate(1D);
                }
                price = (1 / mp.getRate()) * mp.getPrice();
            }
        }
        return DecimalCalculate.roundDown(price, 2);
    }


    @Override
    public Page<MedicinePrivate> getByTypeAndCategoryAndDoctor(Pageable page, Doctor doctor, Medicine.Type type, String category) {
        Page<MedicinePrivate> medicinePrivatePage = sysMedicinePrivateDao.findByTypeAndCategoryAndDoctor(page, doctor, type, category);
        return medicinePrivatePage;

    }

    @Override
    public String getStandardMoreDoctorUse(Medicine medicine) {
        Page<MedicineContainer> mcPage = sysMedicinePrivateDao.findstandardMoreDoctorUse(medicine, new PageRequest(0, 1));
        if (CollectionUtils.isNotEmpty(mcPage.getContent())) {
            return mcPage.getContent().get(0).getStrMoreUser();
        } else {
            return null;
        }
    }

    @Override
    public Double getRateMoreDoctorUse(Medicine medicine) {
        Page<MedicineContainer> mcPage = sysMedicinePrivateDao.findRateMoreDoctorUse(medicine, new PageRequest(0, 1));
        if (CollectionUtils.isNotEmpty(mcPage.getContent())) {
            return mcPage.getContent().get(0).getDoubleMoreUser();
        } else {
            return null;
        }
    }

    @Override
    public String getQtyMoreDoctorUse(Medicine medicine) {
        Page<MedicineContainer> mcPage = sysMedicinePrivateDao.findQtyMoreDoctorUse(medicine, new PageRequest(0, 1));
        if (CollectionUtils.isNotEmpty(mcPage.getContent())) {
            return mcPage.getContent().get(0).getStrMoreUser();
        } else {
            return null;
        }
    }

    @Override
    public Boolean getUsageFlagMoreDoctorUse(Medicine medicine) {
        Page<MedicineContainer> mcPage = sysMedicinePrivateDao.findUsageFlagMoreDoctorUse(medicine, new PageRequest(0, 1));
        if (CollectionUtils.isNotEmpty(mcPage.getContent())) {
            return mcPage.getContent().get(0).getBooleanMoreUser();
        } else {
            return null;
        }
    }

    @Override
    public String getUseModelMoreDoctorUse(Medicine medicine) {
        Page<MedicineContainer> mcPage = sysMedicinePrivateDao.findUseModelMoreDoctorUse(medicine, new PageRequest(0, 1));
        if (CollectionUtils.isNotEmpty(mcPage.getContent())) {
            return mcPage.getContent().get(0).getStrMoreUser();
        } else {
            return null;
        }
    }

    @Override
    public String getCategoryMoreDoctorUse(Medicine medicine) {
        Page<MedicineContainer> mcPage = sysMedicinePrivateDao.findCategoreMoreDoctorUse(medicine, new PageRequest(0, 1));
        if (CollectionUtils.isNotEmpty(mcPage.getContent())) {
            return mcPage.getContent().get(0).getStrMoreUser();
        } else {
            return null;
        }
    }

    @Override
    public List<MedicinePrivate> findByDoctorAndMedicineName(Doctor doctor, String medName) {
        return sysMedicinePrivateDao.findAll((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(MedicinePrivate_.doctor).get(Doctor_.id),
                        doctor.getId()
                ),
                cb.equal(
                        root.get(MedicinePrivate_.name),
                        medName
                )
        ));
    }

    @Override
    public void deleteMedicinePrivate(MedicinePrivate medicinePrivate) {
        sysMedicinePrivateDao.delete(medicinePrivate);
    }

    @Override
    public List<MedicinePrivate> getMedicinePrivateByDoctor(Doctor doctor) {
        return sysMedicinePrivateDao.findMedicinePrivateByDoctor(doctor);
    }

    //保存医生的习惯
    //当私有药品某些属性为空的时候，采用私有库中使用最多的值
    //当不为空，如果值修改了，则保存。
    @Override
    public MedicinePrivate saveDoctorHabit(MedicinePrivate medicinePrivate, MedicineContainer medicineContainer) {
        //药品分类习惯保存
        if (null == medicinePrivate.getCategory()) {
            //当为空时，取推荐的
            medicinePrivate.setCategory(getCategoryMoreDoctorUse(medicinePrivate.getMedicine()));
        }/*else {
            //当不为空时，查看是否修改了
            if(null != medicineContainer.getCategory() && !medicinePrivate.getCategory().equalsIgnoreCase(medicineContainer.getCategory())){
                medicinePrivate.setCategory(medicineContainer.getCategory());
            }
        }*/

        //治疗方式习惯保存
        if (StringUtils.isEmpty(medicinePrivate.getUseMode())) {
            medicinePrivate.setUseMode(getUseModelMoreDoctorUse(medicinePrivate.getMedicine()));
        }/*else {
            if(null != medicineContainer.getUseMode() && !medicinePrivate.getUseMode().equalsIgnoreCase(medicineContainer.getUseMode())){
                medicinePrivate.setUseMode(medicineContainer.getUseMode());
            }
        }*/

        //标准用量
        if (null == medicinePrivate.getUsageFlag()) {
            medicinePrivate.setUsageFlag(getUsageFlagMoreDoctorUse(medicinePrivate.getMedicine()));
        }/*else {
            if(null != medicineContainer.getUsageFlag() && !medicinePrivate.getUsageFlag()==medicineContainer.getUsageFlag()){
                medicinePrivate.setUsageFlag(medicineContainer.getUsageFlag());
            }
        }*/

        //数量单位realQty
        if (null == medicinePrivate.getRealQty()) {
            medicinePrivate.setRealQty(getQtyMoreDoctorUse(medicinePrivate.getMedicine()));
        }/*else {
            if(null != medicineContainer.getQty() && !medicinePrivate.getRealQty().equalsIgnoreCase(medicineContainer.getQty()+"")){
                medicinePrivate.setRealQty(medicineContainer.getQty()+"");
            }
        }*/

        //换算率
        if (null == medicinePrivate.getRate()) {
            medicinePrivate.setRate(getRateMoreDoctorUse(medicinePrivate.getMedicine()));
        }/*else {
            if(null != medicineContainer.getRate() && medicinePrivate.getRate().doubleValue()!=medicineContainer.getRate().doubleValue()){
                medicinePrivate.setRate(medicineContainer.getRate());
            }
        }*/

        try {
            //standard
            if (StringUtils.isEmpty(medicinePrivate.getStandard())) {
                medicinePrivate.setStandard(getStandardMoreDoctorUse(medicinePrivate.getMedicine()));
            }
        } catch (Exception e) {
            System.out.println("医生的Standard记录出错了啊！");
        }

        save(medicinePrivate);
        return medicinePrivate;
    }
}
