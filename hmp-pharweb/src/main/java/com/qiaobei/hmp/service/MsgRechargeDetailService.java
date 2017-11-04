package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgRechargeDetail;
import com.qiaobei.hmp.modules.entity.PayType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
public interface MsgRechargeDetailService {
    Page<MsgRechargeDetail> getPageByDoctor(Pageable page, Doctor doctor, PayType payType);

    void saveOrUpdate(MsgRechargeDetail msgRechargeDetail);

    MsgRechargeDetail getMsgRecByRechargeNum(String rechargeNum);
}
