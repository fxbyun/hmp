package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgMoney;

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
public interface MsgMoneyService {
    MsgMoney getByDoctor(Doctor doctor);

    void updateOrSave(MsgMoney msgMoney);
}
