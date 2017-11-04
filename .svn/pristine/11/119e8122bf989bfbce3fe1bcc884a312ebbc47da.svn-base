package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {

    Page<Notice> findPage(Pageable page, String subject);

    void save(Notice notice);

    void delete(Long id);

    Notice findById(Long id);

}
