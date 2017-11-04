package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.IaiInto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface IaiIntoDao extends JpaRepository<IaiInto, Long>, JpaSpecificationExecutor<IaiInto> {
    Page<IaiInto> findByCreateBy(Long createBy, Pageable pageable);

    IaiInto findByUuid(String uuid);

    List<IaiInto> findByCreateByAndIaiIntoTypeAndStatus(Long createBy,IaiInto.IaiIntoType iaiIntoType,IaiInto.IaiIntoStatus iaiIntoStatus);


    Page<IaiInto> findByCreateByAndIaiIntoTypeAndStatus(Long createBy,IaiInto.IaiIntoType iaiIntoType,IaiInto.IaiIntoStatus iaiIntoStatus,Pageable pageable);
}
