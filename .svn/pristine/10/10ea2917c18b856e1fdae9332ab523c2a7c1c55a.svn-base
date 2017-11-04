package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgMoney;
import com.qiaobei.hmp.repository.MsgMoneyDao;
import com.qiaobei.hmp.service.MsgMoneyService;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/6/28 0028
 * Time 15:00
 */

@Service("msgMoneyService")
@Transactional
@Monitored
public class MsgMoneyServiceImpl implements MsgMoneyService {
    @Resource
    MsgMoneyDao msgMoneyDao;

    @Override
    public MsgMoney getByDoctor(Doctor doctor) {
        return msgMoneyDao.findByDoctor(doctor);
    }

    @Override
    public void updateOrSave(MsgMoney msgMoney) {
        msgMoneyDao.saveAndFlush(msgMoney);
    }
}