package com.qiaobei.hmp.modules.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.JDoctorExtCost;
import com.qiaobei.hmp.modules.entity.JDoctorExtCost_;
import com.qiaobei.hmp.modules.repository.JDoctorExtCostDao;
import com.qiaobei.hmp.modules.service.JDoctorExtCostService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
@Service("jDoctorExtCostService")
@Transactional
public class JDoctorExtCostServiceImpl implements JDoctorExtCostService {
    @Resource
    private JDoctorExtCostDao jDoctorExtCostDao;

    @Override
    public List<JDoctorExtCost> findAll(Doctor doctor) {
        List<Predicate> predicateList = Lists.newArrayList();
        return jDoctorExtCostDao.findAll((root, query, cb) -> {
            predicateList.add(
                    cb.equal(
                            root.get(JDoctorExtCost_.doctor), doctor
                    )
            );
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }

    @Override
    public void save(JDoctorExtCost jDoctorExtCost) {
        jDoctorExtCostDao.save(jDoctorExtCost);
    }


    @Override
    public JDoctorExtCost findById(Long doctorId) {
        return jDoctorExtCostDao.findOne(doctorId);
    }

    @Override
    public void delete(Long doctorId) {
        jDoctorExtCostDao.delete(doctorId);
    }

    @Override
    public JDoctorExtCost findBySpecification(Specification specification) {

        return jDoctorExtCostDao.findOne(specification);
    }
}
