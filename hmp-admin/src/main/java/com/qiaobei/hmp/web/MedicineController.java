package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.support.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.List;

@Controller
public class MedicineController extends ConstantsController {

    private static Logger logger = LoggerFactory.getLogger(MedicineController.class);
    @Resource
    private MedicineService medicineService;

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

    @RequestMapping(value = "/fragment/medicines/{type}", method = RequestMethod.GET)
    public String medicines(@PathVariable Medicine.Type type,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "name", required = false) String name,
                            Model model) {
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGZ_SIZE);
        model.addAttribute("type", type);
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        model.addAttribute("medicinePage", medicineService.pageMedicineTag(pageable, type, name));
        return "fragment/medicinePage";
    }

    @RequestMapping(value = "/fragment/medicine/select/{id}", method = RequestMethod.GET)
    public String select(@PathVariable("id") Long id, Model model) {
        model.addAttribute("medicine", medicineService.getMedicine(id));
        return "fragment/medicineSelect";
    }

    @RequestMapping(value = "/fragment/medicine/usage/{id}", method = RequestMethod.GET)
    public String usageForm(@PathVariable("id") Long id, Model model) {
        Medicine medicine = medicineService.getMedicine(id);
        model.addAttribute("medicine", medicine);
        return "fragment/medicineUsage";
    }

    @RequestMapping(value = "/fragment/medicine/medicineDataImport")
    public String medicineDataImport(Model model) {

        model.addAttribute("", "");
        return "/fragment/medicineDataImport";
    }
}
