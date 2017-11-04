package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MsgRechargeDetail;
import com.qiaobei.hmp.modules.entity.PayType;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * Created by fangxiaobin on 2016/6/28 0028.
 */
public interface MsgRechargeDetailService {
    Page<MsgRechargeDetail> getPageByDoctor(Pageable page, Doctor doctor, PayType payType);

    void saveOrUpdate(MsgRechargeDetail msgRechargeDetail);

    MsgRechargeDetail getMsgRecByRechargeNum(String rechargeNum);

    /**
     * Recharge分页、条件查询
     */
    Page<MsgRechargeDetail> findPage(Pageable page, String doctorName, Double addMoney, DateFilter dateFilter);

    Long count();
}
