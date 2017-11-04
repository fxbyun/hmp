package com.qiaobei.hmp.modules.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.repository.IaiIntoDao;
import com.qiaobei.hmp.modules.repository.IaiIntoDetailDao;
import com.qiaobei.hmp.modules.repository.IaiLossDao;
import com.qiaobei.hmp.modules.repository.IaiLossDetilDao;
import com.qiaobei.hmp.modules.service.IaiIntoDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
@Service("iaiIntoDetailService")
@Transactional
public class IaiIntoDetailServiceImpl implements IaiIntoDetailService {



    @Resource
    private IaiIntoDetailDao iaiIntoDetailDao;

    @Resource
    private IaiIntoDao iaiIntoDao;

    @Resource
    private IaiLossDao iaiLossDao;

    @Resource
    private IaiLossDetilDao iaiLossDetilDao;

    @Override
    public List<IaiIntoDetail> findByDoctorHasSaveAndStorage(Doctor doctor) {
        return iaiIntoDetailDao.findByDoctorAndDetailStatusAndDetailType(doctor.getId(), IaiIntoDetail.DetailStatus.SAVE, IaiIntoDetail.DetailType.STORAGE);
    }

    @Override
    public void save(IaiIntoDetail iaiIntoDetail) {
        iaiIntoDetailDao.save(iaiIntoDetail);
    }

    @Override
    public void delIaiDetail(Long id) {
        iaiIntoDetailDao.delete(id);
    }


    @Override
    public Page<MedicinePrivate> findExpireMedTag(Long doctorId, Pageable pageable) {
        return iaiIntoDetailDao.findExpireMedTag(doctorId,pageable);
    }

    @Override
    public Page<IaiIntoDetail> findFastExpireDetail(Long doctorId, String medName, Pageable pageable) {
        return iaiIntoDetailDao.findFastExpireDetail(doctorId,medName,pageable);
    }

    @Override
    public Page<IaiIntoDetail> findExpireDetail(Long doctorId, String medName, Pageable pageable) {
        return iaiIntoDetailDao.findExpireDetail(doctorId,medName,pageable);
    }

    @Override
    public List<IaiIntoDetail> findExpireDetail(Long doctorId, String medName) {
        return iaiIntoDetailDao.findExpireDetail(doctorId,medName);
    }

    @Override
    public Map<Long, Double> getStockNumMap(List<IaiIntoDetail> details) {
        Map<Long,Double> stockNumMap = Maps.newHashMap();
        details.forEach(detail ->
             stockNumMap.put(detail.getId(),getStockNum(detail))
        );

        return stockNumMap;
    }

    @Override
    public Double getStockNum(IaiIntoDetail detail) {
        //有效损耗数量
        Double validLossNum = 0d;
        //无效损耗数量
        Double unVailLossNum = 0d;
        //入库数量
        Double IncomeNum=detail.getTotalNumber();
        //根据私有药品的id和入库药品的有效期，查找损耗表详情表中
        List<IaiLossDetail> lossDetails= iaiLossDetilDao.findByMedIdAndValidDate(detail.getMedicine(),detail.getValidityDate());
        for(IaiLossDetail lossDetail:lossDetails){
            //统计有效损耗数量
            if (lossDetail.getStatus() == null) {
                System.out.println(lossDetail.getId() + "-----------sdsd-----------");
            }
            if(lossDetail.getStatus()== StatusEntity.Status.Normal){
                //只有该损耗单上对应上入库详情单才能去减
                if (lossDetail.getIaiIntoDetailId() != null && lossDetail.getIaiIntoDetailId().equals(detail.getId())) {
                    validLossNum += Optional.ofNullable(lossDetail.getTotalNumber()).orElse(0D);
                }
                //解决历史遗留问题(之前lossDetail.iaiIntoDetailId)没设值
                if (lossDetail.getIaiIntoDetailId() == null) {
                    validLossNum += Optional.ofNullable(lossDetail.getTotalNumber()).orElse(0D);
                }

            }
            //统计无效效损耗数量
            if(lossDetail.getStatus()== StatusEntity.Status.Have_Dispensing){
                unVailLossNum += lossDetail.getTotalNumber();
            }
        }
        return IncomeNum - validLossNum + unVailLossNum;
    }

    @Override
    public Page<MedicinePrivate> findLossMedTag(Long doctorId, String medName,Pageable pageable) {
        return iaiIntoDetailDao.findLossMedTag(doctorId,"%"+medName+"%",pageable);
    }

    @Override
    public List<IaiIntoDetail> findByCompanyIdAndMedicine(Long companyId,MedicinePrivate medicinePrivate) {
        return iaiIntoDetailDao.findByCompanyIdAndMedicineAndStatusAndDetailType(companyId,medicinePrivate,IaiIntoDetail.DetailStatus.SAVE,
                IaiIntoDetail.DetailType.STORAGE);
    }

    @Override
    public List<IaiIntoDetail> findByMedicine(MedicinePrivate medicinePrivate) {
        return iaiIntoDetailDao.findByMedicine(medicinePrivate);
    }

    @Override
    public List<IaiIntoDetail> findNotEnoughList(Long doctorId) {
        return iaiIntoDetailDao.findNotEnoughList(doctorId);
    }

    @Override
    public Page<IaiIntoDetail> findNotEnoughPage(Long doctorId,Pageable pageable) {

        return iaiIntoDetailDao.findNotEnoughPage(doctorId,pageable);
    }

    @Override
    public Page<IaiIntoDetail> findAllPage(Doctor doctor, String medName,
                                           IaiIntoDetail.DetailStatus status,
                                           IaiIntoDetail.DetailType detailType,
                                           Pageable pageable) {
        Page<IaiIntoDetail> page = iaiIntoDetailDao.findAll((root, query, cb)->{
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(
                    cb.equal(root.join(IaiIntoDetail_.iaiInto).get(IaiInto_.createBy),doctor.getId())
            );
            //药品是保存状态的
            predicates.add(
                    cb.equal(root.get(IaiIntoDetail_.status),status)
            );

            //药品是入库的
            predicates.add(
                    cb.equal(root.get(IaiIntoDetail_.detailType),detailType)
            );


            if(StringUtils.isNotEmpty(medName)){
                predicates.add(
                        cb.like(
                                root.join(IaiIntoDetail_.medicine).get(MedicinePrivate_.name)
                                , "%" + medName + "%"
                        )
                );
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        },pageable);

        return page;
    }

    @Override
    public IaiIntoDetail findById(Long id) {
        return iaiIntoDetailDao.getOne(id);
    }

    @Override
    public List<IaiIntoDetail> findByIaiIntoId(Long iaiIntoId) {
        IaiInto iaiInto = iaiIntoDao.findOne(iaiIntoId);
        return iaiIntoDetailDao.findByIaiInto(iaiInto);
    }

    @Override
    public Page<IaiIntoDetail> findByIaiIntoPage(IaiInto iaiInto, Pageable pageable) {
        return iaiIntoDetailDao.findByIaiInto(iaiInto,pageable);
    }
}