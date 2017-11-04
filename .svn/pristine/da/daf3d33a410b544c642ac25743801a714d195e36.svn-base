package com.qiaobei.hmp.modules.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.JClassAdviceDict;
import com.qiaobei.hmp.modules.entity.JDoctorExamLab;
import com.qiaobei.hmp.modules.entity.JDoctorExamLab_;
import com.qiaobei.hmp.modules.repository.JDoctorExamLabDao;
import com.qiaobei.hmp.modules.service.JDoctorExamLabService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/4 0004
 * Time 14:30
 */
@Service("jDoctorExamLabService")
@Transactional
public class JDoctorExamLabServiceImpl implements JDoctorExamLabService {
    @Resource
    private JDoctorExamLabDao jDoctorExamLabDao;

    @Override
    public List<JDoctorExamLab> findAll(Doctor doctor) {
        return jDoctorExamLabDao.findAll((root, query, cb) -> cb.and(
                cb.equal(
                        root.get(
                                JDoctorExamLab_.doctorId
                        ),
                        doctor.getId()
                )
        ));
    }

    @Override
    public void saveList(Collection<JDoctorExamLab> jDoctorExamLabList) {
        jDoctorExamLabDao.save(jDoctorExamLabList);
    }

    @Override
    public void deleteById(Long id) {
        jDoctorExamLabDao.delete(id);
    }

    @Override
    public JDoctorExamLab findById(Long id) {
        return jDoctorExamLabDao.findOne(id);
    }

    @Override
    public void update(JDoctorExamLab jDoctorExamLab) {
        jDoctorExamLabDao.save(jDoctorExamLab);
    }

    @Override
    public JDoctorExamLab findBySpecification(Specification specification) {
        List<JDoctorExamLab> jDoctorExamLabList = jDoctorExamLabDao.findAll(specification);

        return jDoctorExamLabList.size() > 0 ? jDoctorExamLabList.get(0) : null;
    }

    @Override
    public void save(JDoctorExamLab jDoctorExamLab) {
        jDoctorExamLabDao.save(jDoctorExamLab);
    }

    @Override
    public JDoctorExamLab findByAdviceNameAndDoctorAndType(String adviceName, Doctor doctor, JClassAdviceDict.Advice_Type adviceType, Long id) {
        return jDoctorExamLabDao.findOne((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            if (adviceType == JClassAdviceDict.Advice_Type.JianCha) {
                predicateList.add(
                        cb.equal(
                                root.get(JDoctorExamLab_.subTwoId),
                                id
                        )
                );
            } else {
                predicateList.add(
                        cb.equal(
                                root.get(JDoctorExamLab_.subId),
                                id
                        )
                );
            }
            predicateList.add(
                    cb.equal(
                            root.get(JDoctorExamLab_.classAdviceDictName)
                            , adviceName
                    )
            );
            predicateList.add(
                    cb.equal(
                            root.get(JDoctorExamLab_.doctorId),
                            doctor.getId()
                    )
            );

            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }
}
