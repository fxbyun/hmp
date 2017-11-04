package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiagnosisTagDao extends JpaRepository<DiagnosisTag, Long>, JpaSpecificationExecutor<DiagnosisTag> {
//    List<Tag> findByDepartmentId(Long departmentId);

    @Modifying
    @Query(value = "update DiagnosisTag o set o.frequency=(o.frequency+1) where o.id in ?1")
    void updateTagFrequency(List<Long> tagIds);

    DiagnosisTag findByDoctorIdAndDepartmentIdAndName(Long doctorId, Long departmentId, String name);

    Page<DiagnosisTag> findPageByDoctorIdAndDepartmentId(Pageable pageable, Long doctorId, Long departmentId);

    @Query(value = "select distinct t.name from DiagnosisTag t where t.doctorId<>?1 and t.departmentId=?2")
        //and (t.name not in(select st.name from SymptomTag st where st.doctorId = ?2))
    Page<String> findPage4OtherTags(Pageable pageable, Long doctorId, Long departmentId);

    @Query(value = "select t from DiagnosisTag t where t.doctorId= ?1 and t.departmentId = ?2 and (t.name like ?3 or " +
            "t.helpCode like ?3)")
    Page<DiagnosisTag> findPageByDoctorIdAndDepartmentIdAndNameLike(Pageable pageable, Long doctorId, Long
            departmentId, String name);

    @Query(value = "select t from DiagnosisTag t where  t.departmentId = ?1 and (t.name like ?2 or t.helpCode like " +
            "?2) group by t.name")
    Page<DiagnosisTag> findPageByDepartmentIdAndNameLike(Pageable pageable, Long departmentId, String name);

    @Query(value = "select distinct t.name from DiagnosisTag t where t.doctorId<>?1 and t.departmentId=?2 and (t.name" +
            " like ?3 or t.helpCode like ?3)")
        //and (t.name not in(select st.name from SymptomTag st where st.doctorId = ?2))
    Page<String> findPage4OtherTags(Pageable pageable, Long doctorId, Long departmentId, String name);


}

