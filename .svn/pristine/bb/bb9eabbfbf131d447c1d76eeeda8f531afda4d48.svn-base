package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Emr;
import com.qiaobei.hmp.modules.entity.EmrMedicine;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.VitalSign;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.EmrService;
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
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;

/**
 * Created by yanbin on 11/4/15.
 */
@Controller
public class EmrController extends ConstantsController {
    @Resource
    private EmrService emrService;
    @Resource
    private MedicineService medicineService;

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
    public Collection<EmrMedicine> getEmrWesternItems(@PathVariable Long id, Model model) {
        Emr emr = emrService.getEmr(id);
        Collection<EmrMedicine> itemList = emr.getWesternItems();
        for (EmrMedicine em : itemList) {
            em.setUnitLabel(MEDICINE_UNITS.get(em.getUnit()));
            Medicine medicine = medicineService.getMedicine(em.getMedicineId());
            em.setUseTimes(medicine.getUseTimes());
            em.setUsingTime(medicine.getUsingTime());
        }
        return itemList;
    }

    @RequestMapping(value = "/emr/{id}/chineseItems", method = RequestMethod.GET)
    @ResponseBody
    public Collection<EmrMedicine> getEmrChineseItems(@PathVariable Long id, Model model) {
        Emr emr = emrService.getEmr(id);
        Collection<EmrMedicine> itemList = emr.getChineseItems();
        for (EmrMedicine em : itemList) {
            em.setUnitLabel(MEDICINE_UNITS.get(em.getUnit()));
            Medicine medicine = medicineService.getMedicine(em.getMedicineId());
            em.setUseTimes(medicine.getUseTimes());
            em.setUsingTime(medicine.getUsingTime());
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
        } else {
            //dateFilter = DateFilter.withStartPlusWeeks(-1);
        }
        model.addAttribute("patient", patient);
        model.addAttribute("dateFilter", dateFilter);
        model.addAttribute("emrPage",
                emrService.pageEmr(new PageRequest(page, size, Sort.Direction.DESC, "id"), SecuritySupport
                        .getDoctorId(), patient, dateFilter));
        return "emrList";
    }
}
