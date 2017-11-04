package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.EmrService;
import com.qiaobei.hmp.service.MedicinePrivateService;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.DateFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/4/15
 * Time 13:35
 */
@Controller
public class EmrController extends ConstantsController {
    @Resource
    private EmrService emrService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;

    @ModelAttribute("vitalSignLabels")
    private EnumMap<VitalSign.Type, String> vitalSignLabelMap(HttpServletRequest request) {
        return VITAL_SIGN_LABELS;
    }

    @ModelAttribute("vitalSignUnits")
    private EnumMap<VitalSign.Type, String> vitalSignUnitMap(HttpServletRequest request) {
        return VITAL_SIGN_UNITS;
    }

    @ModelAttribute("medicineTypes")
    private EnumMap<Medicine.Type, String> medicineTypes(HttpServletRequest request) {
        return MEDICINE_TYPES;
    }

    @ModelAttribute("medicineUnits")
    private EnumMap<Medicine.Unit, String> medicineUnits(HttpServletRequest request) {
        return MEDICINE_UNITS;
    }

    @RequestMapping(value = "/emr/{id}", method = RequestMethod.GET)
    public String getEmr(@PathVariable Long id, Model model) {
        model.addAttribute("emr", emrService.getEmr(id));
        model.addAttribute("genderMap", GENDER_MAP);
        return "emrDetail";
    }

    @RequestMapping(value = "/emr/{id}/westernItems", method = RequestMethod.GET)
    @ResponseBody
    public Collection<EmrMedicine> getEmrWesternItems(@PathVariable Long id) {
        Emr emr = emrService.getEmr(id);
        Collection<EmrMedicine> itemList = emr.getWesternItems();
        for (EmrMedicine em : itemList) {
            em.setUnitLabel(MEDICINE_UNITS.get(em.getUnit()));
            Medicine medicine = medicineService.getMedicine(em.getMedicineId());
            /*不明白这里为啥要写em.setUseTimes(medicine.getUseTimes())*/
            if (null == em.getUseTimes()) {
                em.setUseTimes(medicine.getUseTimes());
            }
            if (null == em.getUsingTime()) {
                em.setUsingTime(medicine.getUsingTime());
            }
            List<MedicinePrivate> medicinePrivateList = medicinePrivateService.getMedPriByMedIdList(medicine.getId(), SecuritySupport.getDoctor());
            MedicinePrivate medicinePrivate;

            DecimalFormat df = new DecimalFormat("######0.00");
            if (medicinePrivateList.size() > 0) {
                medicinePrivate = medicinePrivateList.get(0);
                em.setMedicinePrivateId(medicinePrivate.getId());
                em.setUnitPrice(medicinePrivate.getPrice());
                if (medicinePrivate.getRate() == null || medicinePrivate.getRate() == 0) {
                    medicinePrivate.setRate(1D);
                }
                if (medicinePrivate.getPrice() == null || medicinePrivate.getPrice() == 0) {
                    medicinePrivate.setPrice(0D);
                }
                Double qty;
                try {
                    qty = Double.valueOf(medicinePrivate.getUseQty());
                } catch (Exception e) {
                    qty = 1D;
                }

                try {
                    em.setPrice(qty * (medicinePrivate.getPrice() / medicinePrivate.getRate()));
                    em.setPrice(Double.valueOf(df.format(em.getPrice())));
                } catch (Exception e) {
                    em.setPrice(0D);
                }
//                em.setPrice(qty * (medicinePrivate.getPrice() / medicinePrivate.getRate()));
                System.out.println(em.getMedicineName() + ":");
                System.err.println(em.getPrice() + "=" + qty + "*(" + medicinePrivate.getPrice() + "/" + medicinePrivate.getRate() + ")");
                System.out.println();
            } else {
                medicinePrivate = new MedicinePrivate().medToMedPriveate(em.getMedicine());
                medicinePrivate.setDoctor(SecuritySupport.getDoctor());
                medicinePrivateService.save(medicinePrivate);
                em.setMedicinePrivateId(medicinePrivate.getId());
                em.setUnitPrice(medicinePrivate.getPrice());
                if (medicinePrivate.getRate() == null || medicinePrivate.getRate() == 0) {
                    medicinePrivate.setRate(1D);
                }
                if (medicinePrivate.getPrice() == null || medicinePrivate.getPrice() == 0) {
                    medicinePrivate.setPrice(0D);
                }
                Double qty;
                try {
                    qty = Double.valueOf(medicinePrivate.getUseQty());
                } catch (Exception e) {
                    qty = 1D;
                }

                try {
                    em.setPrice(qty * (medicinePrivate.getPrice() / medicinePrivate.getRate()));
                    em.setPrice(Double.valueOf(df.format(em.getPrice())));
                } catch (Exception e) {
                    em.setPrice(0D);
                }
            }

        }
        //清空后缀ss(曾伟真是个坑比！！！)
        itemList.forEach(em -> em.setMultiplexTag(null));

        return itemList;
    }

    @RequestMapping(value = "/emr/{id}/chineseItems", method = RequestMethod.GET)
    @ResponseBody
    public Collection<EmrMedicine> getEmrChineseItems(@PathVariable Long id) {
        Emr emr = emrService.getEmr(id);
        Collection<EmrMedicine> itemList = emr.getChineseItems();
        for (EmrMedicine em : itemList) {
            em.setUnitLabel(MEDICINE_UNITS.get(em.getUnit()));
            Medicine medicine = medicineService.getMedicine(em.getMedicineId());
            em.setUseTimes(medicine.getUseTimes());
            em.setUsingTime(medicine.getUsingTime());
            List<MedicinePrivate> medicinePrivateList = medicinePrivateService.getMedPriByMedIdList(medicine.getId(), SecuritySupport.getDoctor());
            if (medicinePrivateList.size() > 0) {
                MedicinePrivate medicinePrivate1 = medicinePrivateList.get(0);
                em.setMedicinePrivateId(medicinePrivate1.getId());
                em.setUnitPrice(medicinePrivate1.getPrice());
                if (medicinePrivate1.getRate() == null || medicinePrivate1.getRate() == 0) {
                    medicinePrivate1.setRate(1D);
                }
                if (medicinePrivate1.getPrice() == null || medicinePrivate1.getPrice() == 0) {
                    medicinePrivate1.setPrice(0D);
                }
                Double qty;
                try {
                    qty = Double.valueOf(medicinePrivate1.getUseQty());
                } catch (Exception e) {
                    qty = 1D;
                }


                em.setPrice(qty * (medicinePrivate1.getPrice() / medicinePrivate1.getRate()));
            }
        }
        return itemList;
    }

    @RequestMapping(value = "/emr", method = RequestMethod.GET)
    public String emrList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                          Model model) {
//        DateFilter dateFilter = DateFilter.withStartPlusWeeks(-1);
//        model.addAttribute("dateFilter", dateFilter);
        model.addAttribute("emrPage",
                emrService.pageEmr(new PageRequest(page, size, Sort.Direction.DESC, "id"), SecuritySupport
                        .getDoctorId(), "", null));
        return "emrList";
    }

    @RequestMapping(value = "/emr", method = RequestMethod.POST)
    public String query(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                        @RequestParam(value = "patient", required = false) String patient,
                        @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date startDate,
                        @RequestParam @DateTimeFormat(pattern = Constants.PATTERN_DATE) Date endDate,
                        Model model) {
        DateFilter dateFilter = null;
        if (null != startDate && null != endDate) {
            dateFilter = new DateFilter(startDate, endDate);
        }
        model.addAttribute("patient", patient);
        model.addAttribute("dateFilter", dateFilter);
        model.addAttribute("emrPage",
                emrService.pageEmr(new PageRequest(page, size, Sort.Direction.DESC, "id"), SecuritySupport
                        .getDoctorId(), patient, dateFilter));
        return "emrList";
    }
}
