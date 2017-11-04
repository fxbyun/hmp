package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.IaiLoss;
import com.qiaobei.hmp.modules.entity.IaiLossDetail;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.modules.support.EntityTmpCloumsVal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface IaiLossDetailService {
    List<EntityTmpCloumsVal> getIaiLossDetilStockByMedPaivate(MedicinePrivate medicinePrivate);

    void saveList(List<IaiLossDetail> iaiLossDetailListEnd);

    void delByEmrId(Long id);

    void save(IaiLossDetail detail);

    void updateStatusBackMedByMedPrivateId(StatusEntity.Status have_dispensing_back, Long emrMedId);

    Page<IaiLossDetail> findByIaiLossAndStatus(IaiLoss iaiLoss, IaiLossDetail.DetailStatus detailStatus,Pageable pageable);

    IaiLossDetail findById(Long iaiLossDetailId);

    void delIaiLossDetail(Long iaiLossDetailId);

    void updataStatus(StatusEntity.Status removed, Long id);
}
