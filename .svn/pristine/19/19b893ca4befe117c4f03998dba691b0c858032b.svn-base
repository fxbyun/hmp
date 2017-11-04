package com.qiaobei.hmp.modules.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.IaiLoss;
import com.qiaobei.hmp.modules.entity.IaiLossDetail;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.modules.repository.IaiIntoDetailDao;
import com.qiaobei.hmp.modules.repository.IaiLossDetilDao;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.support.EntityTmpCloumsVal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Collections3;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
@Service("iaiLossDetailService")
@Transactional
public class IaiLossDetailServiceImpl implements IaiLossDetailService {
    @Resource
    private IaiLossDetilDao iaiLossDetilDao;
    @Resource
    private IaiIntoDetailDao iaiIntoDetailDao;

    /**
     * 获取 指定药品 每个有效期  来 分组的  总数
     *
     * @param medicinePrivate
     * @return 返回 List 的IaiLossDetil  里面
     */
    @Override
    public List<EntityTmpCloumsVal> getIaiLossDetilStockByMedPaivate(MedicinePrivate medicinePrivate) {
        //损耗详情表中统计出的有效日期和总数  ->List
        List<EntityTmpCloumsVal> entityTmpCloumsValListLoss = iaiLossDetilDao.getIaiLossDetilStockByMedPaivate(medicinePrivate, StatusEntity.Status.Normal);
        //入库详情表中统计出的有效日期和总数  ->List
        List<EntityTmpCloumsVal> entityTmpCloumsValsInto = iaiIntoDetailDao.getIaiIntoStockByMedPrivate(medicinePrivate);
        List<EntityTmpCloumsVal> entityTmpCloumsValsIntoEnd = Lists.newArrayList();
        entityTmpCloumsValsInto.forEach(
                intoEntityOne -> {
                    final boolean[] save = {true};
                    entityTmpCloumsValListLoss.forEach(lossEntityOne -> {
                        //损耗单中 过期日期和 入库详情表一致
                        if (intoEntityOne.getValidityDate().getTime() ==
                                lossEntityOne.getValidityDate().getTime()) {
                            save[0] = false;
                            EntityTmpCloumsVal entityTmpCloumsVal2 = new EntityTmpCloumsVal();
                            entityTmpCloumsVal2.setValidityDate(lossEntityOne.getValidityDate());
                            entityTmpCloumsVal2.setTotlenSize(intoEntityOne.getTotlenSize() - lossEntityOne.getTotlenSize());
                            if (null == lossEntityOne.getId()) {
                                entityTmpCloumsVal2.setId(intoEntityOne.getId());
                            } else {
                                entityTmpCloumsVal2.setId(lossEntityOne.getId());
                            }
                            entityTmpCloumsVal2.setBarCode(intoEntityOne.getBarCode());
                            entityTmpCloumsVal2.setCompanyId(intoEntityOne.getCompanyId());
                            entityTmpCloumsValsIntoEnd.add(entityTmpCloumsVal2);
                        }
                    });
                    if (save[0]) {
                        EntityTmpCloumsVal entityTmpCloumsVal2 = new EntityTmpCloumsVal();
                        entityTmpCloumsVal2.setValidityDate(intoEntityOne.getValidityDate());
                        entityTmpCloumsVal2.setTotlenSize(intoEntityOne.getTotlenSize());
                        entityTmpCloumsVal2.setId(intoEntityOne.getId());
                        entityTmpCloumsVal2.setBarCode(intoEntityOne.getBarCode());
                        entityTmpCloumsValsIntoEnd.add(entityTmpCloumsVal2);
                    }

                }
        );


//        entityTmpCloumsValList.stream()
        if (Collections3.isEmpty(entityTmpCloumsValListLoss)) {
            return entityTmpCloumsValsInto;
        }

        return entityTmpCloumsValsIntoEnd;
    }

    @Override
    public void saveList(List<IaiLossDetail> iaiLossDetailListEnd) {
        iaiLossDetilDao.save(iaiLossDetailListEnd);
    }

    @Override
    public void save(IaiLossDetail detail) {
        iaiLossDetilDao.save(detail);
    }

    @Override
    public void delByEmrId(Long id) {
        iaiLossDetilDao.delByEmrId(id);
    }

    @Override
    public void updataStatus(StatusEntity.Status removed, Long id) {
        System.out.println("fuck");
//        iaiLossDetilDao.updateStatus(StatusEntity.Status.Removed, id);
    }

    @Override
    public Page<IaiLossDetail> findByIaiLossAndStatus(IaiLoss iaiLoss, IaiLossDetail.DetailStatus detailStatus, Pageable pageable) {
        return iaiLossDetilDao.findByIaiLossAndDetailStatus(iaiLoss, detailStatus, pageable);
    }

    @Override
    public void delIaiLossDetail(Long iaiLossDetailId) {
        iaiLossDetilDao.delete(iaiLossDetailId);
    }

    @Override
    public IaiLossDetail findById(Long iaiLossDetailId) {
        return iaiLossDetilDao.getOne(iaiLossDetailId);
    }

    @Override
    public void updateStatusBackMedByMedPrivateId(StatusEntity.Status have_dispensing_back, Long emrMedId) {
        iaiLossDetilDao.updateStatus(have_dispensing_back, emrMedId);
    }
}