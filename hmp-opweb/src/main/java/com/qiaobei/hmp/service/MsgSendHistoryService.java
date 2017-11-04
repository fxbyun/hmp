package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgSendHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Map;

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
public interface MsgSendHistoryService {
    Map<String, String> getDoctorMsgInfo(Doctor doctor);

    void updateOrSave(MsgSendHistory msgSendHistory);

    Page<MsgSendHistory> getListByDoctor(Pageable page, Doctor doctor);

    Page<MsgSendHistory> findByDoctorAndCreateDateBetween(Pageable pageable, Doctor doctor, Date month, Date endDate);

    Page<MsgSendHistory> getListByDoctorAndSendType(Pageable pageable, Doctor doctor, MsgSendHistory.SendType sendType);
}
