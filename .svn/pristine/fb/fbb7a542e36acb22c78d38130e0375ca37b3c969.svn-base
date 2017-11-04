package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.IaiLoss;
import com.qiaobei.hmp.modules.entity.IaiLossDetail;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface IaiLossDao extends JpaRepository<IaiLoss, Long>, JpaSpecificationExecutor<IaiLoss> {


    Page<IaiLoss> findIaiLossByCreateIdAndLossNoLikeAndStatus(Long createId, String lossNo, IaiLoss.LossStatus status, Pageable pageable);

    IaiLoss findByUuid(String uuid);

    List<IaiLoss> findByCreateIdAndStatus(Long createId, IaiLoss.LossStatus status);

    Page<IaiLoss> findByCreateIdAndStatus(Long createId, IaiLoss.LossStatus status,Pageable pageable);

}
