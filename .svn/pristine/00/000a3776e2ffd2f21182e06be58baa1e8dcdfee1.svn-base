package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.repository.IaiLossDetilDao;
import com.qiaobei.hmp.modules.service.EmrMedicineService;
import com.qiaobei.hmp.repository.EmrDao;
import com.qiaobei.hmp.service.CardService;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.service.EmrService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

@Service("emrService")
@Transactional
public class EmrServiceImpl implements EmrService {

    private static Logger logger = LoggerFactory.getLogger(EmrServiceImpl.class);

    @Resource
    private EmrDao emrDao;
    @Resource
    private EmrMedicineService emrMedicineService;
    @Resource
    private IaiLossDetilDao iaiLossDetilDao;
    @Resource
    private DoctorService doctorService;
    @Resource
    private CardService cardService;

    @Override
    @Transactional(readOnly = true)
    public Emr getEmr(Long id) {
        return emrDao.getOne(id);
    }

    @Override
    public Page<Emr> getEmrListByDoctor(Doctor doctor, StatusEntity.Status status, Pageable pageable, Date startDate, Date endDate, String cardPwd, String name, String phone) {

        return emrDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
                List<Doctor> doctorList = doctorService.getSubDoctor(doctor);
                CriteriaBuilder.In<Doctor> doctorIn = cb.in(root.get(Emr_.doctor));
                doctorList.forEach(doctorIn::value);
                doctorIn.value(doctor);
                predicateList.add(
                        doctorIn
                );
            } else {
                predicateList.add(
                        cb.equal(
                                root.get(Emr_.doctor), doctor
                        )
                );
            }

            if (StringUtils.isNotEmpty(cardPwd)) {
                Card card = cardService.getCardByUdid(cardPwd);
                if (null != card) {
                    predicateList.add(
                            cb.equal(
                                    root.get(Emr_.patientUid), card.getCardNo()
                            )
                    );
                }
            }

            if (StringUtils.isNotEmpty(phone)) {
                predicateList.add(
                        cb.equal(
                                root.get(Emr_.patient).get(Patient_.mobile), phone
                        )
                );
            }

            if (StringUtils.isNotEmpty(name)) {
                predicateList.add(
                        cb.like(
                                root.get(Emr_.patient).get(Patient_.name), "%" + name + "%"
                        )
                );
            }

            if (status == null) {
//                predicateList.add(
//                        cb.equal(
//                                root.get("status").as(StatusEntity.Status.class), StatusEntity.Status.Normal
//                        )
//                );
                predicateList.add(
                        cb.and(
                                cb.or(
                                        cb.equal(
                                                root.join(Emr_.emrMedicineList, JoinType.LEFT).get(EmrMedicine_.status),
                                                StatusEntity.Status.Normal
                                        ),
                                        cb.equal(
                                                root.get(Emr_.status),
                                                StatusEntity.Status.Normal
                                        ),
                                        cb.equal(
                                                root.join(Emr_.emrJClassAdviceDicts, JoinType.LEFT).get(EmrJClassAdviceDict_.status),
                                                StatusEntity.Status.Normal
                                        )
                                )

                        )

                );
                predicateList.add(
                        cb.and(
                                cb.notEqual(
                                        root.get(Emr_.status),
                                        StatusEntity.Status.HANG_UP
                                )
                        )
                );


            } else if (status == StatusEntity.Status.CHARGE) {
                predicateList.add(
                        cb.and(
                                cb.or(
                                        cb.equal(
                                                root.join(Emr_.emrMedicineList, JoinType.LEFT).get(EmrMedicine_.status),
                                                StatusEntity.Status.CHARGE
                                        ),
                                        cb.equal(
                                                root.get(Emr_.status),
                                                StatusEntity.Status.CHARGE
                                        ),
                                        cb.equal(
                                                root.join(Emr_.emrJClassAdviceDicts, JoinType.LEFT).get(EmrJClassAdviceDict_.status),
                                                StatusEntity.Status.CHARGE
                                        ),
                                        cb.equal(
                                                root.get(Emr_.status),
                                                StatusEntity.Status.Have_Dispensing_Back
                                        )
                                )
                        )
                );
                predicateList.add(
                        cb.and(
                                cb.notEqual(
                                        root.get(Emr_.status),
                                        StatusEntity.Status.Back_Money_Success
                                )
                        )
                );

            } else if (status == StatusEntity.Status.HANG_UP) {
                predicateList.add(
                        cb.equal(
                                root.get(Emr_.status), StatusEntity.Status.HANG_UP
                        )
                );
            } else if (status == StatusEntity.Status.Have_Dispensing) {
                predicateList.add(
                        cb.equal(
                                root.get(Emr_.status), StatusEntity.Status.Have_Dispensing
                        )
                );
            } else if (status == StatusEntity.Status.Have_Dispensing_Back) {
                predicateList.add(
                        cb.and(
                                cb.or(
                                        cb.equal(
                                                root.get(Emr_.status), StatusEntity.Status.Have_Dispensing_Back
                                        ),
                                        cb.and(
                                                cb.equal(
                                                        root.get(Emr_.status), StatusEntity.Status.Back_Money_Success
                                                ),
                                                cb.equal(
                                                        root.join(Emr_.emrMedicineList, JoinType.LEFT).get(EmrMedicine_.status),
                                                        StatusEntity.Status.Have_Dispensing_Back
                                                )
                                        )
                                )
                        )
                );
            } else if (status == StatusEntity.Status.Back_Money_Success) {
                predicateList.add(
                        cb.and(
                                cb.equal(
                                        root.get(Emr_.status),
                                        StatusEntity.Status.Back_Money_Success
                                )
                        )
                );
            }

            if (startDate != null && endDate != null) {
                predicateList.add(
                        cb.between(
                                root.get(Emr_.createOn), startDate, endDate
                        )
                );
            } else if (startDate != null) {
                predicateList.add(
                        cb.greaterThan(
                                root.get(Emr_.createOn), startDate
                        )
                );
            } else if (endDate != null) {
                predicateList.add(
                        cb.lessThan(
                                root.get(Emr_.createOn), endDate
                        )
                );
            }
            query.where(cb.and(
                    predicateList.toArray(new Predicate[predicateList.size()])));
            query.distinct(true);
            return query.getRestriction();

        }, pageable);


    }

    @Override
    public void updateEmr(Emr emr) {
        emrDao.save(emr);
    }

    @Override
    public void save(Emr emr) {
        emrDao.save(emr);
    }

    @Override
    public void backMedByEmrMedIds(List<Long> emrMedidList) {
//        emrMedicineService.findByIdIn(longList);
        //修改emrmed为退药状态
        emrMedicineService.updateEmrMedBackByIdIn(emrMedidList);
        //修改 iailossDetole 为退药状态
        iaiLossDetilDao.updateStausByEmrMedIdIn(StatusEntity.Status.Have_Dispensing_Back, emrMedidList);
    }

    @Override
    public List<Emr> findAllByDoctorListAndTimeAndShouYinId(List<Doctor> doctorList, Date startDate, Date endDate, String patientName, Long shouYinId) {
        return emrDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            CriteriaBuilder.In<Doctor> doctorIn = cb.in(root.get(Emr_.doctor));
            doctorList.forEach(doctorIn::value);
            predicateList.add(doctorIn);

            if (startDate != null) {
                predicateList.add(
                        cb.greaterThan(
                                root.get(Emr_.createOn),
                                startDate
                        )
                );
            }

            if (endDate != null) {
                predicateList.add(
                        cb.lessThan(
                                root.get(Emr_.createOn),
                                endDate
                        )
                );
            }
            if (StringUtils.isNoneEmpty(patientName)) {
                predicateList.add(
                        cb.equal(
                                root.join(Emr_.patient).get(Patient_.name),
                                patientName
                        )
                );
            }
            if (shouYinId != null) {
                predicateList.add(
                        cb.equal(
                                root.get(Emr_.cashierId),
                                shouYinId
                        )
                );
            }

            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        });
    }
}
