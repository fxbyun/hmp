package com.qiaobei.hmp.web;

import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.service.TagService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/4/15
 * Time 13:31
 */
@Controller
public class DepartmentController extends BaseController {
    @Resource
    private MedicineService medicineService;
    @Resource
    private TagService tagService;

    @RequestMapping(value = "/fragment/department/select/{id}", method = RequestMethod.GET)
    public String select(@PathVariable("id") Long id, Model model) {

        Pageable pageable = new PageRequest(0, 20);
        model.addAttribute("symptomTags", tagService.pageSymptomTags(pageable, SecuritySupport.getDoctorId(), null));
        model.addAttribute("diagnosisTags", tagService.pageDiagnosisTags(pageable, SecuritySupport.getDoctorId(), id,
                null));
        model.addAttribute("westernMedicines", medicineService.pageTagByWestern(pageable));
        model.addAttribute("chineseMedicines", medicineService.pageTagByChinese(pageable));
//        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "fragment/departmentSelect";
    }
}
