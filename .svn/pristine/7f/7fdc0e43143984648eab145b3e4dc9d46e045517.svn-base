package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.repository.DoctorDao;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import com.qiaobei.hmp.modules.support.EntityTmpCloumsVal;
import com.qiaobei.hmp.repository.MedicinePrivateDao;
import com.qiaobei.hmp.service.MedicinePrivateService;
import org.apache.commons.lang3.StringUtils;
import org.javasimon.aop.Monitored;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/6/17 0017
 * Time 17:36
 */
@Service("medicinePrivateService")
@Transactional
@Monitored
public class MedicinePrivateServiceImpl implements MedicinePrivateService {
    @Resource
    MedicinePrivateDao medicinePrivateDao;

    @Resource
    IaiLossDetailService iaiLossDetailService;

    @Resource(name = "doctorDaoCode")
    DoctorDao doctorDao;

    @Override
    public List<MedicinePrivate> getMedPriByMedIdList(Long id, Doctor doctor) {
        return medicinePrivateDao.findByMedAndDoctorList(new Medicine(id), doctor);
    }

    @Override
    public MedicinePrivate getMedPriByMedId(Long id, Doctor doctor) {
        return medicinePrivateDao.findByMedAndDoctor(new Medicine(id), doctor);
    }

    @Override
    public void save(MedicinePrivate medicinePrivate) {
        medicinePrivateDao.save(medicinePrivate);
    }

    @Override
    public void update(MedicinePrivate medicinePrivate) {

        if (medicinePrivate.getDoctor().getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
            doctorDao.findAll((root, query, cb) -> cb.equal(
                    root.get(Doctor_.primaryDoctorId),
                    medicinePrivate.getDoctor().getId()
            )).forEach(doctor -> {
                List<MedicinePrivate> medicinePrivateList = medicinePrivateDao.findByDoctorAndMedicine(doctor, medicinePrivate.getMedicine());
                MedicinePrivate medicinePrivate1;
                if (medicinePrivateList.size() > 0) {
                    medicinePrivate1 = medicinePrivateList.get(0).medPriveateToMedPriveate(medicinePrivate);
                } else {
                    medicinePrivate1 = new MedicinePrivate();
                    medicinePrivate1.medPriveateToMedPriveate(medicinePrivate);
                    medicinePrivate1.setDoctor(doctor);
                }
                medicinePrivateDao.save(medicinePrivate1);

            });
        }
        medicinePrivateDao.save(medicinePrivate);
    }

    @Override
    public MedicinePrivate getMedPrivate(Long id) {
        return medicinePrivateDao.findOne(id);
    }

    @Override
    public List<MedicinePrivate> findRateByMoreUser(Long id, Pageable pageable) {
        return medicinePrivateDao.findRateByMoreUser(id, pageable);
    }

    @Override
    public List<MedicinePrivate> findStandardByMoreUser(Long id, Pageable pageable) {
        return medicinePrivateDao.findStandardByMoreUser(id, pageable);
    }

    @Override
    public List<MedicinePrivate> findByDoctorAndMedicine(Doctor doctor, Medicine medicine) {
        return medicinePrivateDao.findByDoctorAndMedicine(doctor, medicine);
    }

    @Override
    public Page<MedicinePrivate> findTagTherapyByMedName(Doctor doctor, String medName, Pageable pageable) {
        return medicinePrivateDao.findTagTherapyByMedName(doctor, "%" + medName + "%", pageable);
    }

    @Override
    public Page<MedicinePrivate> findTagTherapy(Doctor doctor, String helpCode, Pageable pageable) {
        return medicinePrivateDao.findTagTherapy(doctor, "%" + helpCode + "%", pageable);
    }

    @Override
    public String therapyHtml(MedicinePrivate therapy, String useQty, Medicine.Unit useUnit, String useUnitValue, String therapyCopy) {
        String dataReady = therapy.getId() + "," + useQty + "," + "'" + useUnit + "'" + "," + therapyCopy;
        StringBuilder HTML = new StringBuilder("<div>");
        HTML.append("<strong onclick=\"paperTherapy(this," + dataReady + ")\">" + therapy.getName() + "</strong>");
        HTML.append("<span>每次" + useQty + "" + useUnitValue + "</span><span>共" + therapyCopy + "次</span>");
        HTML.append("<i class='fa fa-trash-o' onclick='fn_delTherapy(this)'></i>");

        HTML.append("<span class='therapy-value' style='display:none;'>");
        HTML.append("<input name='therapyId' value=\"" + therapy.getId() + "\"/>");
        HTML.append("<input name='therapyName' value=\"" + therapy.getMedicine().getName() + "\"/>");
        HTML.append("<input name='useQty' value=\"" + useQty + "\"/>");
        HTML.append("<input name='useUnit' value=\"" + useUnit + "\"/>");
        HTML.append("<input name='therapyCopy' value=\"" + therapyCopy + "\"/>");
        HTML.append("<input class='therapyPrice' name='therapyPrice' value=\"" + therapy.getPrice() + "\"/>");
        HTML.append("</span>");

        HTML.append("</div>");
        return HTML.toString();
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
    public Page<MedicinePrivate> findPrivateMedTag(Doctor doctor, String medName, String barCode, Pageable pageable) {

        return medicinePrivateDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            if (StringUtils.isNotEmpty(medName)) {
                predicateList.add(
                        cb.or(
                                cb.like(root.get(MedicinePrivate_.name), "%" + medName + "%"),
                                cb.like(root.get(MedicinePrivate_.helpCode), "%" + medName + "%")
                        )
                );
            }

            if (StringUtils.isNotEmpty(barCode)) {
                predicateList.add(
                        cb.and(cb.like(root.get(MedicinePrivate_.barCode), "%" + barCode + "%"))
                );
            }
            predicateList.add(
                    cb.equal(root.get(MedicinePrivate_.doctor), doctor)
            );

            predicateList.add(
                    cb.notEqual(
                            root.join(MedicinePrivate_.medicine).get(Medicine_.type), Medicine.Type.ChineseTherapy
                    )
            );

            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            query.distinct(true);
            return query.getRestriction();
        }, pageable);
    }

    @Override
    public void insertLossDetail(Retail retail) {
        retail.getRetailMedicineList().forEach(retMed -> {
            Optional.ofNullable(retMed.getMedicinePrivate()).ifPresent(medicinePrivate -> {
                List<EntityTmpCloumsVal> entityTmpCloumsValList =
                        iaiLossDetailService.getIaiLossDetilStockByMedPaivate(medicinePrivate);
                if (entityTmpCloumsValList.size() > 0) {
                    //当前药品使用的 数量总数   数量/单位 X 份数=使用总量
                    Double totleUse = retMed.getQty() * retMed.getCopies();
                    //统计当前库存该药品总计数量    总数*换算单位=库存总量
                }

            });

        });
    }

    @Override
    public void insertLossDetil(Emr emr) {
//        iaiLossDetailService.delByEmrId(emr.getId());
        List<Medicine> medicineList = emr.getEmrMedicineList().stream().map(
                emrMedicine -> emrMedicine.getMedicine()
        ).collect(Collectors.toList());

        List<MedicinePrivate> medicinePrivateList;
        if (emr.getDoctor().getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            medicinePrivateList = medicinePrivateDao.
                    findByHaveManagerAndDoctorAndMedicineIn(
                            MedicinePrivate.HaveManager.YES,
                            new Doctor(emr.getDoctor().getPrimaryDoctorId()),
                            medicineList
                    );
        } else {
            medicinePrivateList = medicinePrivateDao.findByHaveManagerAndDoctorAndMedicineIn(
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
                                            entityTmpCloumsVal -> entityTmpCloumsVal.getTotlenSize()).sum() * medicinePrivate.getRate();
                                    //如果 使用总量小于或等于当前库存总量  说明 为正库存
                                    if (totleUse <= totleHave) {
                                        boolean haveFull = false;
                                        for (EntityTmpCloumsVal entityTmpCloumsVal : entityTmpCloumsValList) {
                                            haveFull = true;
                                            //如果
                                            if (entityTmpCloumsVal.getTotlenSize() * medicinePrivate.getRate() > totleUse && totleUse != 0D) {
                                                iaiLossDetailListEnd.add(
                                                        createIaiLossDetil(medicinePrivate,
                                                                emr.getId(),
                                                                totleUse,
                                                                entityTmpCloumsVal.getValidityDate(),
                                                                emrMedicine.getId(), emr)
                                                );
                                                totleUse = 0D;
                                            } else if (entityTmpCloumsVal.getTotlenSize() > 0 && totleUse != 0D) {
                                                iaiLossDetailListEnd.add(
                                                        createIaiLossDetil(medicinePrivate, emr.getId(),
                                                                entityTmpCloumsVal.getTotlenSize() * medicinePrivate.getRate()
                                                                , entityTmpCloumsVal.getValidityDate(),
                                                                emrMedicine.getId(), emr)
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
                                                            , emrMedicine.getId(), emr)
                                            );
                                            totleUse = 0D;
                                        } else if (totleUse != 0D) {
                                            //循环 取出每个 有效期可能还剩余的 药品数量 减去
                                            for (int i = 0; i < entityTmpCloumsValList.size(); i++) {
                                                EntityTmpCloumsVal entityTmpCloumsVal = entityTmpCloumsValList.get(i);
                                                if (entityTmpCloumsVal.getTotlenSize() > 0) {
                                                    iaiLossDetailListEnd.add(
                                                            createIaiLossDetil(medicinePrivate, emr.getId(),
                                                                    entityTmpCloumsVal.getTotlenSize() * medicinePrivate.getRate()
                                                                    , entityTmpCloumsVal.getValidityDate(), emrMedicine.getId(), emr)
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
                                                            emrMedicine.getId(), emr)
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
}
