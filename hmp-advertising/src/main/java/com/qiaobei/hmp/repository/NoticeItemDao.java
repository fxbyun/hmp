package com.qiaobei.hmp.repository;

import com.qiaobei.hmp.modules.entity.NoticeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface NoticeItemDao extends JpaRepository<NoticeItem, Long>, JpaSpecificationExecutor<NoticeItem> {

    @Query("select count(*) from NoticeItem n where n.doctorId=?1 and n.status=?2")
    Long noReadCount(Long doctor, NoticeItem.Status status);

}

