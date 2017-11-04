package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Diagnosis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiagnosisService {

    Page<Diagnosis> findPage(Pageable page, String name);

    Diagnosis findOneById(Long id);

    List<Diagnosis> findByName(String name);

}
