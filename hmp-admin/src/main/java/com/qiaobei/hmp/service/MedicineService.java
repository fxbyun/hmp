package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicineService {

    Medicine getMedicine(Long id);

    Page<Medicine> pageMedicineTag(Pageable page, Medicine.Type type, String name);

}
