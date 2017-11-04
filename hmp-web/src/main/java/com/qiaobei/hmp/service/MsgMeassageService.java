package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgMeassage;
import com.qiaobei.hmp.modules.entity.MsgMoney;

import java.util.List;

/**
 * Created by IntelliJ IDEA 15.0
 * User ZW_Teemoer
 * Date 2016/7/8 0008
 * Time 16:08
 */
public interface MsgMeassageService {
    MsgMeassage getMeassageByDoctor(Doctor doctor);

    void delMeassageByDoctor(Doctor doctor);

    void save(MsgMeassage msgMeassage);

    List<MsgMeassage> getAll();

    MsgMoney findByDoctor(Doctor doctor);
}
