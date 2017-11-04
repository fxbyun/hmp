package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.PrescriptionCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ConfigController extends ConstantsController {
    @Resource
    private PrescriptionCategoryService prescriptionCategoryService;

    @ModelAttribute("doctor")
    private Doctor doctor(HttpServletRequest request) {
        return SecuritySupport.getDoctor();
    }

    @RequestMapping(value = "/config/symptom", method = RequestMethod.GET)
    public String configSymptom() {
        return "configSymptom";
    }

    @RequestMapping(value = "/config/diagnosis", method = RequestMethod.GET)
    public String configDiagnosis(Model model) {
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        return "configDiagnosis";
    }

    @RequestMapping(value = "/config/medicine", method = RequestMethod.GET)
    public String configMedicine() {
        return "configMedicine";
    }

    @RequestMapping(value = "/config/rp", method = RequestMethod.GET)
    public String configRp(Model model) {
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));
        model.addAttribute("libCategories", prescriptionCategoryService.listPrescriptionLibCategory());
//        model.addAttribute("rp",prescriptionService.listRP(Medicine.Type.Western));
//        model.addAttribute("rplib",prescriptionLibService.listRPLib(Medicine.Type.Western));
        return "configPrescription";
    }

}
