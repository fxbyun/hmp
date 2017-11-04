package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import com.qiaobei.hmp.modules.entity.DiagnosisTag_;
import com.qiaobei.hmp.modules.entity.SymptomTag;
import com.qiaobei.hmp.repository.DiagnosisTagDao;
import com.qiaobei.hmp.repository.SymptomTagDao;
import com.qiaobei.hmp.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/12/15
 * Time 15:04
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
        return symptomTagDao.findPageByNameLike(page, "%" + name + "%");
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
        if (StringUtils.isEmpty(name)) {
            return diagnosisTagDao.findPageByDoctorIdAndDepartmentId(page, doctorId, departmentId);
        }
        //return diagnosisTagDao.findPageByDoctorIdAndDepartmentIdAndNameLike(page, doctorId, departmentId, "%" +
        // name + "%");
        return diagnosisTagDao.findPageByDepartmentIdAndNameLike(page, departmentId, "%" + name + "%");
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

    @Override
    public List<String> getByNameOrHelpCode(String key) {
        return diagnosisTagDao.findAll((root, query, cb) -> cb.or(
                cb.like(
                        root.get(DiagnosisTag_.name),
                        "%" + key + "%"
                ),
                cb.like(
                        root.get(DiagnosisTag_.helpCode),
                        "%" + key + "%"
                )
        )).stream().map(DiagnosisTag::getName).collect(Collectors.toList());
    }
}
