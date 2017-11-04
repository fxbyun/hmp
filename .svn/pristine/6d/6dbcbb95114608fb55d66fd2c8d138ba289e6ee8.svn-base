package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.NoticeItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeItemService {

    void save(NoticeItem item);

    Page<NoticeItem> findPage(Pageable page, Long doctorId);

    Long noReadCount(Long doctorId, NoticeItem.Status status);

    NoticeItem getNoticeItem(Long id);

}
