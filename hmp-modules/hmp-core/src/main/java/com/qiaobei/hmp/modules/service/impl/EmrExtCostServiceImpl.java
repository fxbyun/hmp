package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.EmrExtCost;
import com.qiaobei.hmp.modules.repository.EmrExtCostDao;
import com.qiaobei.hmp.modules.service.EmrExtCostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/12 0012
 * Time 14:34
 */
@Service("emrExtCostService")
@Transactional
public class EmrExtCostServiceImpl implements EmrExtCostService {
    @Resource
    private EmrExtCostDao emrExtCostDao;

    @Override
    public void save(EmrExtCost emrExtCost) {
        emrExtCostDao.save(emrExtCost);
    }
}
