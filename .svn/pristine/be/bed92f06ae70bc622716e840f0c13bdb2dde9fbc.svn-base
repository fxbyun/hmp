package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.SymptomTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SymptomTagDao extends JpaRepository<SymptomTag, Long>, JpaSpecificationExecutor<SymptomTag> {

    @Modifying
    @Query(value = "update SymptomTag o set o.frequency=(o.frequency+1) where o.id in ?1")
    void updateTagFrequency(List<Long> tagIds);

    SymptomTag findByDoctorIdAndName(Long doctorId, String name);


    Page<SymptomTag> findPageByDoctorId(Pageable pageable, Long doctorId);

    @Query(value = "select distinct t.name from SymptomTag t where t.doctorId <> ?1 ")
        //and (t.name not in(select st.name from SymptomTag st where st.doctorId = ?2))
    Page<String> findPage4OtherTags(Pageable pageable, Long doctorId);


    @Query(value = "select t  from SymptomTag t where t.doctorId = ?1 and (t.name like ?2  or t.helpCode like ?2)")
    Page<SymptomTag> findPageByDoctorIdAndNameLike(Pageable pageable, Long doctorId, String name);

    @Query(value = "select t  from SymptomTag t where  (t.name like ?1  or t.helpCode like ?1) group by t.name")
    Page<SymptomTag> findPageByNameLike(Pageable pageable, String name);


    @Query(value = "select distinct t.name from SymptomTag t where t.doctorId <> ?1 and (t.name like ?2 or t.helpCode" +
            " like ?2)")
        //and (t.name not in(select st.name from SymptomTag st where st.doctorId = ?2))
    Page<String> findPage4OtherTags(Pageable pageable, Long doctorId, String name);

}

