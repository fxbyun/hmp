package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.PrescriptionLib;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrescriptionLibService {

    Page<PrescriptionLib> findPage(Pageable page, String name);

    PrescriptionLib getPrescriptionLib(Long id);

    void savePrescriptinLib(PrescriptionLib p);

    void deletePrescriptinLib(Long id);
}
