package com.qiaobei.hmp.schedule;

import com.qiaobei.hmp.modules.entity.Adverting;
import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.EmrMedicine;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.service.AdvertingService;
import com.qiaobei.hmp.service.EmrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 连晋 on 2016/9/9 0009. 10:57
 * By IDEA 2016.2.3 汉化: www.java.sx
 */
@Component("autoRemoveUnValidityAdving")
public class AutoRemoveUnValidityAdving {
    private final AdvertingService advertingService;
    private final EmrService emrService;
    private final IaiLossDetailService iaiLossDetailService;

    @Autowired
    public AutoRemoveUnValidityAdving(AdvertingService advertingService, EmrService emrService, IaiLossDetailService iaiLossDetailService) {
        this.advertingService = advertingService;
        this.emrService = emrService;
        this.iaiLossDetailService = iaiLossDetailService;
    }

    public void removeUnValidityAdving() {
        List<Adverting> advertingList = advertingService.findAdvingListNeedDel();
        advertingList.forEach(adverting -> {
            if (adverting.getIndate() != 0 && adverting.getValidityDay() <= 0) {
                advertingService.deleteAdvert(adverting);
            }
        });
    }

    public void removeHavingUpEmrIaiLossDetiel() {
        List<Emr> emrList = emrService.findByStatus(StatusEntity.Status.HANG_UP);
        emrList.forEach(
                emr -> {
                    List<EmrMedicine> emrMedicineList = emr.getEmrMedicineList();
                    emrMedicineList.forEach(
                            emrMedicine -> iaiLossDetailService.updateStatusBackMedByMedPrivateId(
                                    StatusEntity.Status.Have_Dispensing_Back,
                                    emrMedicine.getId()
                            )
                    );

                }
        );

    }
}
