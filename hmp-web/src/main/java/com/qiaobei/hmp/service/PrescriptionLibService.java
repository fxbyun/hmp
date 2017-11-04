package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.PrescriptionLib;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrescriptionLibService {

    PrescriptionLib getPrescriptionShare(Long id);

    Page<PrescriptionLib> findPage(Pageable page, String name, Long categoryId, Medicine.Type medicineType);

    List<PrescriptionLib> listRPLib(Medicine.Type type);

    List<PrescriptionLib> listRPLib(Medicine.Type type, Long catId);
}
