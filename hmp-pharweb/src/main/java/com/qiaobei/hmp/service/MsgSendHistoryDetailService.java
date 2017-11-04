package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.MsgSendHistoryDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by teemoer@cntv.cn on 2016/6/28 0028.
 */
public interface MsgSendHistoryDetailService {
    void updateOrSave(MsgSendHistoryDetail msgSendHistoryDetail);

    Page<MsgSendHistoryDetail> getListByMSHAndName(Pageable pageable, String name, Long id);
}
