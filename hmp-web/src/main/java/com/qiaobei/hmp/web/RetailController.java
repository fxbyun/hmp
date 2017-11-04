package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.Retail;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.service.RetailMedicineService;
import com.qiaobei.hmp.modules.service.RetailService;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

@Controller
public class RetailController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(MedicineController.class);
    @Resource
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;

    @Resource
    private DoctorService doctorService;
    @Resource
    private IaiLossDetailService iaiLossDetailService;

    @Resource
    private PatientService patientService;

    @Resource
    private RetailService retailService;

    @Resource
    private RetailMedicineService retailMedicineService;

    @Resource
    private PharmacistService pharmacistService;


    @ModelAttribute("medicineTypes")
    private EnumMap<Medicine.Type, String> medicineTypes(HttpServletRequest request) {
        return MEDICINE_TYPES;
    }

    @ModelAttribute("medicineUnits")
    private EnumMap<Medicine.Unit, String> medicineUnits(HttpServletRequest request) {
        return MEDICINE_UNITS;
    }

    @ModelAttribute("medicineUseModes")
    private List<String> medicineUseModes(HttpServletRequest request) {
        return MEDICINE_USE_MODE_LIST;
    }

    @ModelAttribute("medicineUsingTimes")
    private List<String> medicineUsingTimes(HttpServletRequest request) {
        return MEDICINE_USING_TIME_LIST;
    }

    @ModelAttribute("medicineUseTimes")
    private List<String> medicineUseTimes(HttpServletRequest request) {
        return MEDICINE_USE_TIMES_LIST;
    }

    @ModelAttribute("westernMedicineCate")
    private List<String> westernMedicineCate(HttpServletRequest request) {
        return WESTERN_MEDICINE_CATE_LIST;
    }

    @ModelAttribute("chineseMedicineCate")
    private List<String> chineseMedicineCate(HttpServletRequest request) {
        return CHINESE_MEDICINE_CATE_LIST;
    }

    // TODO 购买药品记录
    @RequestMapping(value = "/retail/medicBuyDetails")
    public String medicBuyDetails(@RequestParam(value = "patientId", required = false) Long patientId,
                                  @RequestParam(value = "startTime", required = false) Date startTime,
                                  @RequestParam(value = "endTime", required = false) Date endTime,
                                  Model model) {
        Doctor doctor = SecuritySupport.getDoctor();
        List<Retail> retailList = retailService.getByPatientStartAndEndTime(
                patientService.getPatientById(patientId), startTime, endTime, doctorService.getPrimaryDoctor(doctor));

        model.addAttribute("chineseMap", retailService.getChineseMedList(retailList));
        model.addAttribute("westernMap", retailService.getWesternMedList(retailList));
        model.addAttribute("TypeMap", pharmacistService.personTypeNameMap());
        model.addAttribute("retailList", retailList);
        model.addAttribute("patientId", patientId);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        return "/fragment/medicBuyDetails";
    }
}
