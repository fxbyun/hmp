package com.qiaobei.hmp.web;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Conversion;
import com.qiaobei.hmp.modules.entity.ConversionApply;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.ConversionApplyService;
import com.qiaobei.hmp.service.ConversionService;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.support.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import java.util.EnumMap;

/**
 * Created by yanbin on 11/4/15.
 */
@Controller
public class ConversionController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(ConversionController.class);
    @Resource
    private ConversionService conversionService;
    @Resource
    private ConversionApplyService conversionApplyService;
    @Resource
    private MedicineService medicineService;

    /**
     * 不自动绑定对象中的roleList属性，另行处理。
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("defaultCompany", "companyList");
    }

    @RequestMapping(value = "/fragment/conversion/select", method = RequestMethod.POST)
    public String updateForm(@RequestParam Long medicineId, @RequestParam Medicine.Unit unit,
                             Model model) {
        Conversion conversion = conversionService.getConversionByMedicineAndToUnit(new Medicine(medicineId), unit);
        if (conversion == null) {
            conversion = new Conversion();
            conversion.setRate(1);
            model.addAttribute("conversion", conversion);
            model.addAttribute("medicine", medicineService.getMedicine(medicineId));
        } else {
            model.addAttribute("conversion", conversion);
            model.addAttribute("medicine", conversion.getMedicine());
        }
        model.addAttribute("msg", "如换算率不正确，请输入新的换算率，并点击“修改换算率。");
        model.addAttribute("unit", unit);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "fragment/conversionSelect";
    }

    @RequestMapping(value = "/conversion/apply", method = RequestMethod.POST)
    @ResponseBody
    public Result apply(@RequestParam(value = "conversionId", required = false) Long conversionId,
                        @RequestParam(value = "medicineId", required = false) Long medicineId,
                        @RequestParam(value = "unit", required = false) Medicine.Unit unit,
                        @RequestParam Integer rate,
                        Model model) {
        ConversionApply apply = new ConversionApply();
        if (Numbers.isNotNullOrZero(conversionId)) {
            Conversion conversion = conversionService.getConversion(conversionId);
            apply.setConversion(conversion);
        } else if (Numbers.isNotNullOrZero(medicineId) && unit != null) {
            Medicine medicine = medicineService.getMedicine(medicineId);
            apply.setMedicine(medicine);
            apply.setFromUnit(medicine.getUnit());
            apply.setToUnit(unit);
        } else return Result.fail("无效的参数");
        apply.setApplyBy(SecuritySupport.getDoctor());
        apply.setApplyOn(Clock.DEFAULT.getCurrentDate());
        apply.setStatus(StatusEntity.Status.Applied);
        apply.setRate(rate);
        try {
            conversionApplyService.saveConversionApply(apply);
        } catch (SecurityException e) {
            logger.error(e.getMessage());
            return Result.fail(e.getMessage());
        }
        return Result.ok("感谢您的修改，我们会尽快确认。");
    }
//
//    /**
//     * 演示自行绑定表单中的checkBox roleList到对象中.
//     */
//    @RequestMapping(value = "/medicine/save", method = RequestMethod.POST)
//    public String save(@Valid @ModelAttribute("medicine") Medicine medicine,
//                       @RequestParam(value = "defaultCompanyId", required = false) Long defaultCompanyId,
//                       @RequestParam(value = "companyList", required = false) List<Long> companyList,
//                       RedirectAttributes redirectAttributes) {
//
//        if (Numbers.isNullOrZero(defaultCompanyId) && Collections3.isNotEmpty(companyList)) {
//            defaultCompanyId = Collections3.getFirst(companyList);
//        }
//        if (Numbers.isNotNullOrZero(defaultCompanyId)) {
//            medicine.setDefaultCompany(new Company(defaultCompanyId));
//        }
//        // bind companyList
//        medicine.getCompanyList().clear();
//        if (Collections3.isNotEmpty(companyList)) {
//            for (Long companyId : companyList) {
//                Company company = new Company(companyId);
//                medicine.getCompanyList().add(company);
//            }
//        }
//
//        redirectAttributes.addAttribute("msg", "药品信息已保存");
//        medicineService.saveMedicine(medicine);
//        return "redirect:/medicine/edit/" + medicine.getId();
//    }
//
//
//    @RequestMapping(value = "/fragment/medicine/select/{id}", method = RequestMethod.GET)
//    public String select(@PathVariable("id") Long id, Model model) {
//        Medicine medicine = medicineService.getMedicine(id);
//        model.addAttribute("medicine", medicine);
//        model.addAttribute("medicineUnits", MEDICINE_UNITS);
//        return "fragment/medicineSelect";
//    }
//
//    @RequestMapping(value = "/fragment/westernMedicines", method = RequestMethod.GET)
//    public String western(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
//        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
//        model.addAttribute("westernMedicinePage", medicineService.pageTagByWestern(pageable));
//        return "fragment/westernMedicinePage";
//    }
//
//    @RequestMapping(value = "/fragment/chineseMedicines", method = RequestMethod.GET)
//    public String chinese(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
//
//        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
//        model.addAttribute("chineseMedicinePage", medicineService.pageTagByChinese(pageable));
//        return "fragment/chineseMedicinePage";
//    }

}
