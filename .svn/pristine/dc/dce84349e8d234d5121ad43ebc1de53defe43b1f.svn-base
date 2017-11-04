package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.IaiInto;
import com.qiaobei.hmp.modules.entity.IaiIntoDetail;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.modules.support.EntityTmpCloumsVal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface IaiIntoDetailDao extends JpaRepository<IaiIntoDetail, Long>, JpaSpecificationExecutor<IaiIntoDetail> {
    @Query("select new com.qiaobei.hmp.modules.support.EntityTmpCloumsVal(c.validityDate,sum(c.totalNumber),c.id,c.barCode,c.companyId)  from  IaiIntoDetail c inner join c.medicine cm where cm=:medicinePrivate " +
            "group by c.validityDate order by c.validityDate,sum(c.totalNumber),c.id,c.barCode,c.companyId")
    List<EntityTmpCloumsVal> getIaiIntoStockByMedPrivate(
            @Param("medicinePrivate") MedicinePrivate medicinePrivate);


    Page<IaiIntoDetail> findByIaiInto(IaiInto iaiInto, Pageable pageable);

    List<IaiIntoDetail> findByIaiInto(IaiInto iaiInto);


    //
    @Query(value = "select detail from IaiIntoDetail detail inner join detail.iaiInto iaiInto inner join detail.medicine mp  where iaiInto.createBy=?1 and detail.status=1 and detail.detailType=0 and mp.name like ?2 and DATEDIFF(detail.validityDate,NOW())<0")
    Page<IaiIntoDetail> findExpireDetail(Long doctorId,String medName,Pageable pageable);

    @Query(value = "select detail from IaiIntoDetail detail inner join detail.iaiInto iaiInto inner join detail.medicine mp  where iaiInto.createBy=?1 and detail.status=1 and detail.detailType=0 and mp.name like ?2 and DATEDIFF(detail.validityDate,NOW())<0")
    List<IaiIntoDetail> findExpireDetail(Long doctorId,String medName);

    //过期的药品有哪些，去掉重复的
    @Query(value = "select distinct mp from IaiIntoDetail detail inner join detail.iaiInto iaiInto inner join detail.medicine mp  where iaiInto.createBy=?1 and detail.status=1 and detail.detailType=0 and DATEDIFF(detail.validityDate,NOW())<0")
    Page<MedicinePrivate> findExpireMedTag(Long doctorId,Pageable pageable);

    //损耗的药品列表
    @Query(value = "select distinct mp from IaiIntoDetail detail inner join detail.iaiInto iaiInto inner join detail.medicine mp  where iaiInto.createBy=?1 and mp.name like ?2 and detail.status=1 and detail.detailType=0 ")
    Page<MedicinePrivate> findLossMedTag(Long doctorId,String medName,Pageable pageable);


    @Query(value = "select detail from IaiIntoDetail detail inner join detail.iaiInto iaiInto inner join detail.medicine mp  where iaiInto.createBy=?1 and detail.status=1 and detail.detailType=0 and (mp.name like ?2) and DATEDIFF(detail.validityDate,NOW())>=0 and PERIOD_DIFF(DATE_FORMAT(detail.validityDate,'%Y%m'),DATE_FORMAT(NOW(),'%Y%m'))<3 and PERIOD_DIFF(DATE_FORMAT(detail.validityDate,'%Y%m'),DATE_FORMAT(NOW(),'%Y%m'))>=0")
    Page<IaiIntoDetail> findFastExpireDetail(Long doctorId,String medName,Pageable pageable);


    //#FangXB 这里记得改totalNumber并不是剩余量
    @Query("select detail from IaiIntoDetail detail inner join detail.iaiInto iaiInto where iaiInto.createBy=?1 and detail.status=1 and detail.detailType=0 and detail.totalNumber<detail.warnLine")
    public Page<IaiIntoDetail> findNotEnoughPage(Long doctorId,Pageable pageable);

    //#FangXB 这里记得改totalNumber并不是剩余量
    @Query("select detail from IaiIntoDetail detail inner join detail.iaiInto iaiInto where iaiInto.createBy=?1 and detail.status=1 and detail.detailType=0 and detail.totalNumber<detail.warnLine")
    public List<IaiIntoDetail> findNotEnoughList(Long doctorId);


    public List<IaiIntoDetail> findByMedicine(MedicinePrivate medicinePrivate);

    public List<IaiIntoDetail> findByCompanyIdAndMedicineAndStatusAndDetailType(Long companyId,
                                                                                MedicinePrivate medicinePrivate,
                                                                                IaiIntoDetail.DetailStatus status,
                                                                                IaiIntoDetail.DetailType detailType);

    @Query(value = "select detail from IaiIntoDetail detail inner join detail.iaiInto iaiInto where iaiInto.createBy=?1 and detail.status=?2 and detail.detailType=?3 ")
    public List<IaiIntoDetail> findByDoctorAndDetailStatusAndDetailType(Long doctorId, IaiIntoDetail.DetailStatus status,
                                                                        IaiIntoDetail.DetailType type);

}
