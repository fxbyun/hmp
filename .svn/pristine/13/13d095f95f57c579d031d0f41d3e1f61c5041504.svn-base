package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Emr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EmrService {

    Emr findById(Long id);

    List<Emr> findByPatientUid(String uid);

    void saveEmr(Emr emr);

    Page<Emr> findPage(Pageable page, Map<String, Object> searchParams);
}
