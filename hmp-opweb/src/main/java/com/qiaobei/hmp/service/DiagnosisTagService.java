package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.DiagnosisTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiagnosisTagService {

    Page<DiagnosisTag> findPage(Pageable page, String name);

}
