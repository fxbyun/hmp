package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluateDao extends JpaRepository<Evaluate, Long>, JpaSpecificationExecutor<Evaluate> {

    List<Evaluate> findByReadFlagFalseAndDoctorIdAndTypeNot(Long doctorId, Evaluate.Type type);

    @Query(value="SELECT e from Evaluate e where e.doctorId=?1 and e.type=?2")
    List<Evaluate> findEvaluateByELE(Long doctorId,Evaluate.Type type);
}

