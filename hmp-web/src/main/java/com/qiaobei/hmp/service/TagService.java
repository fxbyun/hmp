package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import com.qiaobei.hmp.modules.entity.SymptomTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    SymptomTag getSymptomTag(Long id);

    void removeSymptomTag(Long id);

    void removeDiagnosisTag(Long id);

    SymptomTag getSymptomTag(Long doctorId, String name);

    DiagnosisTag getDiagnosisTag(Long id);

    DiagnosisTag getDiagnosisTag(Long doctorId, Long departmentId, String name);

    void saveSymptomTag(SymptomTag tag);

    void saveDiagnosisTag(DiagnosisTag tag);

    Page<SymptomTag> pageSymptomTags(Pageable page, Long doctorId, String name);

    Page<String> pageSymptomOtherTags(Pageable page, Long doctorId, String name);

    Page<DiagnosisTag> pageDiagnosisTags(Pageable page, Long doctorId, Long departmentId, String name);

    Page<String> pageDiagnosisOtherTags(Pageable page, Long doctorId, Long departmentId, String name);

    List<DiagnosisTag> getAllDiagnosisTag();

    List<String> getByNameOrHelpCode(String key);
}
