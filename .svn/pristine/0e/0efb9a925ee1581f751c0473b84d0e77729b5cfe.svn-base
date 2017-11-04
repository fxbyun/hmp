package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.EmrMedicine;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/31 0031
 * Time 15:10
 */
public interface EmrMedicineDao extends JpaRepository<EmrMedicine, Long>, JpaSpecificationExecutor<EmrMedicine> {
    List<EmrMedicine> findByIdIn(List<Long> longList);

    @Modifying
    @Query("update EmrMedicine e set e.status=:status where e.id in :longList")
    void updateEmrMedByIdIn(@Param("status") StatusEntity.Status status,
                            @Param("longList") List<Long> longList);
}
