package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.IaiLoss;
import com.qiaobei.hmp.modules.entity.IaiLossDetail;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.modules.support.EntityTmpCloumsVal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface IaiLossDetilDao extends JpaRepository<IaiLossDetail, Long>, JpaSpecificationExecutor<IaiLossDetail> {
    @Query("select new com.qiaobei.hmp.modules.support.EntityTmpCloumsVal(c.validityDate,sum (c.totalNumber),c.iaiIntoDetailId,c.barCode,c.companyId)  from  IaiLossDetail c inner join c.medicine cm where cm=:medicinePrivate " +
            " and c.status=:status " +
            "group by c.validityDate order by c.validityDate,sum(c.totalNumber),c.iaiIntoDetailId,c.barCode,c.companyId")
    List<EntityTmpCloumsVal> getIaiLossDetilStockByMedPaivate(
            @Param("medicinePrivate") MedicinePrivate medicinePrivate,
            @Param("status") StatusEntity.Status status);

    @Modifying
    @Query("delete from IaiLossDetail c where c.emrId=:emrId")
    void delByEmrId(
            @Param("emrId") Long id);

    @Modifying
    @Query("update IaiLossDetail c  set c.status=:status where   c.emrMedId=:emrMedId")
    void updateStatus(@Param("status") StatusEntity.Status have_dispensing_back,
                      @Param("emrMedId") Long emrMedId
    );

    @Modifying
    @Query("update IaiLossDetail c set c.status=:status where c.emrMedId in :longList")
    void updateStausByEmrMedIdIn(@Param("status") StatusEntity.Status have_dispensing_back,
                                 @Param("longList") List<Long> longList);

    Page<IaiLossDetail> findByIaiLossAndDetailStatus(IaiLoss iaiLoss, IaiLossDetail.DetailStatus detailStatus,Pageable pageable);


    @Query(value = "select lossDetail from IaiLossDetail lossDetail where lossDetail.medicine=?1 and lossDetail.validityDate=?2 ")
    List<IaiLossDetail> findByMedIdAndValidDate(MedicinePrivate medPrivate, Date validityDate);

}
