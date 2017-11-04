package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import com.qiaobei.hmp.modules.entity.DiagnosisTag_;
import com.qiaobei.hmp.modules.entity.SymptomTag;
import com.qiaobei.hmp.modules.entity.SymptomTag_;
import com.qiaobei.hmp.repository.DiagnosisTagDao;
import com.qiaobei.hmp.repository.SymptomTagDao;
import com.qiaobei.hmp.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by yanbin on 11/12/15.
 */
@Service("tagService")
@Transactional
public class TagServiceImpl implements TagService {
    @Resource
    private SymptomTagDao symptomTagDao;
    @Resource
    private DiagnosisTagDao diagnosisTagDao;

    @Override
    public SymptomTag getSymptomTag(Long id) {
        return symptomTagDao.findOne(id);
    }

    @Override
    public void removeSymptomTag(Long id) {
        symptomTagDao.delete(id);
    }

    @Override
    public void removeDiagnosisTag(Long id) {
        diagnosisTagDao.delete(id);
    }

    @Override
    public SymptomTag getSymptomTag(Long doctorId, String name) {
        return symptomTagDao.findByDoctorIdAndName(doctorId, name);
    }

    @Override
    public DiagnosisTag getDiagnosisTag(Long id) {
        return diagnosisTagDao.getOne(id);
    }

    @Override
    public DiagnosisTag getDiagnosisTag(Long doctorId, Long departmentId, String name) {
        return diagnosisTagDao.findByDoctorIdAndDepartmentIdAndName(doctorId, departmentId, name);
    }

    @Override
    public void saveSymptomTag(SymptomTag tag) {
        symptomTagDao.save(tag);
    }

    @Override
    public void saveDiagnosisTag(DiagnosisTag tag) {
        diagnosisTagDao.save(tag);
    }

    @Override
    public Page<SymptomTag> pageSymptomTags(Pageable page, Long doctorId, String name) {
        if (StringUtils.isEmpty(name)) {
            return symptomTagDao.findPageByDoctorId(page, doctorId);
        }
        //return symptomTagDao.findPageByDoctorIdAndNameLike(page, doctorId, "%" + name + "%");
        return symptomTagDao.findAll(new Specification<SymptomTag>() {
            @Override
            public Predicate toPredicate(Root<SymptomTag> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = Lists.newArrayList();

                //predicateList.add(
                //        cb.equal(root.get(SymptomTag_.doctorId), doctorId)
                //);

                if (null != name) {
                    predicateList.add(
                            cb.and(
                                    cb.or(
                                            cb.like(root.get(SymptomTag_.name), "%" + name + "%"),
                                            cb.like(root.get(SymptomTag_.helpCode), "%" + name + "%")
                                    )
                            )
                    );

                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, page);
    }

    @Override
    public Page<String> pageSymptomOtherTags(Pageable page, Long doctorId, String name) {
        if (StringUtils.isEmpty(name)) {
            return symptomTagDao.findPage4OtherTags(page, doctorId);
        }
        return symptomTagDao.findPage4OtherTags(page, doctorId, "%" + name + "%");
    }

    @Override
    public Page<DiagnosisTag> pageDiagnosisTags(Pageable page, Long doctorId, Long departmentId, String name) {


        return diagnosisTagDao.findAll(new Specification<DiagnosisTag>() {
            @Override
            public Predicate toPredicate(Root<DiagnosisTag> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = Lists.newArrayList();
                if (StringUtils.isEmpty(name)) {
                    predicateList.add(
                            cb.equal(root.get(DiagnosisTag_.doctorId), doctorId)
                    );
                }


                predicateList.add(
                        cb.equal(root.get(DiagnosisTag_.departmentId), departmentId)
                );

                if (null != name) {
                    predicateList.add(
                            cb.and(
                                    cb.or(
                                            cb.like(root.get(DiagnosisTag_.name), "%" + name + "%"),
                                            cb.like(root.get(DiagnosisTag_.helpCode), "%" + name + "%")
                                    )
                            )
                    );
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, page);
    }


    @Override
    public Page<String> pageDiagnosisOtherTags(Pageable page, Long doctorId, Long departmentId, String name) {
        if (StringUtils.isEmpty(name)) {
            return diagnosisTagDao.findPage4OtherTags(page, doctorId, departmentId);
        }
        return diagnosisTagDao.findPage4OtherTags(page, doctorId, departmentId, "%" + name + "%");
    }

    @Override
    public List<DiagnosisTag> getAllDiagnosisTag() {
        return diagnosisTagDao.findAll();
    }

}
