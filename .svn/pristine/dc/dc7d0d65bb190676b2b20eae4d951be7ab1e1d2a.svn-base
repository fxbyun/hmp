package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.repository.IaiLossDao;
import com.qiaobei.hmp.modules.service.IaiLossService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */

@Service("iaiLossService")
@Transactional
public class IaiLossServiceImpl implements IaiLossService {

    @Resource
    private IaiLossDao iaiLossDao;

    @Override
    public long findLast(Long doctorId) {
        Pageable pageable = new PageRequest(0,1, Sort.Direction.DESC,"id");
        List<IaiLoss> losses= iaiLossDao.findByCreateIdAndStatus(doctorId, IaiLoss.LossStatus.SAVE,pageable).getContent();
        if(CollectionUtils.isEmpty(losses)){
            return 0;
        }else {
            return losses.get(0).getId();
        }
    }

    @Override
    public IaiLoss findById(Long iaiLossId) {
        return iaiLossDao.getOne(iaiLossId);
    }

    @Override
    public void delLossList(List<IaiLoss> iaiLossList) {
        iaiLossDao.delete(iaiLossList);
    }

    @Override
    public List<IaiLoss> findByCreateIdAndStatus(Long createId, IaiLoss.LossStatus status) {
        return iaiLossDao.findByCreateIdAndStatus(createId, IaiLoss.LossStatus.NOT_SAVE);
    }



    @Override
    public void save(IaiLoss iaiLoss) {
        iaiLossDao.save(iaiLoss);
    }

    @Override
    public IaiLoss findByUuid(String uuid) {
        return iaiLossDao.findByUuid(uuid);
    }

    @Override
    public void delIaiLoss(Long lossId) {
        iaiLossDao.delete(lossId);
    }

    @Override
    public Page<IaiLoss> findLossPageByLossNo(Doctor doctor, String lossNo, IaiLoss.LossStatus status, Pageable pageable) {
        return iaiLossDao.findIaiLossByCreateIdAndLossNoLikeAndStatus(doctor.getId(),"%"+lossNo+"%", IaiLoss.LossStatus.SAVE,pageable);
    }
}