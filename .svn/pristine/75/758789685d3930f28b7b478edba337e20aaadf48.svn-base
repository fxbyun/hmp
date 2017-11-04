package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.support.Result;
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
public class DoctorMedicineController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(DoctorMedicineController.class);

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

    @RequestMapping(value = "/dm", method = RequestMethod.GET)
    public String rp(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                     @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                     @RequestParam(value = "name", required = false) String name,
                     Model model) {
        Pageable pageable = new PageRequest(page, size);
        model.addAttribute("medicinePage", medicineService.pageDoctorTag(pageable, SecuritySupport.getDoctor(), name));
        return "dm";
    }

    @RequestMapping(value = "/dm", method = RequestMethod.POST)
    public String query(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                        @RequestParam(value = "name", required = false) String name,
                        Model model) {
        Pageable pageable = new PageRequest(page, size);
        model.addAttribute("medicinePage", medicineService.pageDoctorTag(pageable, SecuritySupport.getDoctor(), name));
        return "dm";
    }

    @RequestMapping(value = "/dm/dmlete/{medicineId}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable Long medicineId) {
        medicineService.removeMedicineFromDoctor(SecuritySupport.getDoctor(), new Medicine(medicineId));
        return Result.ok();
    }

    @RequestMapping(value = "/dm/add", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestParam List<Long> medicineIds) {
        for (Long medicineId : medicineIds) {
            try {
                medicineService.addMedicineToDoctor(SecuritySupport.getDoctor(), new Medicine(medicineId));
            } catch (Exception e) {
                //TODO:重复添加情况
                logger.warn("重复添加待处理:{}", e.getMessage());
            }
        }
        return Result.ok();
    }


}
