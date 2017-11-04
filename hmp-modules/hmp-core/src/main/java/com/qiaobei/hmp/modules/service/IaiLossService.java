package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.IaiLoss;
import com.qiaobei.hmp.modules.entity.IaiLossDetail;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface IaiLossService {

    Page<IaiLoss> findLossPageByLossNo(Doctor doctor, String goodsNo, IaiLoss.LossStatus status, Pageable pageable);

    void delIaiLoss(Long lossId);

    IaiLoss findByUuid(String uuid);

    void save(IaiLoss iaiLoss);

    List<IaiLoss> findByCreateIdAndStatus(Long createId,IaiLoss.LossStatus status);

    void delLossList(List<IaiLoss> iaiLossList);

    IaiLoss findById(Long iaiLossId);

    long findLast(Long doctorId);

}
