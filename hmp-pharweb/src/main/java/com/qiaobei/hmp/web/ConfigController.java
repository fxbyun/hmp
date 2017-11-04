package com.qiaobei.hmp.web;

import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.PrescriptionCategoryService;
import com.qiaobei.hmp.service.PrescriptionLibService;
import com.qiaobei.hmp.service.PrescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class ConfigController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);
    @Resource
    private PrescriptionService prescriptionService;
    @Resource
    private PrescriptionLibService prescriptionLibService;
    @Resource
    private PrescriptionCategoryService prescriptionCategoryService;

    @RequestMapping(value = "/config/symptom", method = RequestMethod.GET)
    public String configSymptom() {
        return "configSymptom";
    }

    @RequestMapping(value = "/config/diagnosis", method = RequestMethod.GET)
    public String configDiagnosis() {
        return "configDiagnosis";
    }

    @RequestMapping(value = "/config/medicine", method = RequestMethod.GET)
    public String configMedicine(Model model) {
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
