package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Conversion;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.2.1
 * User ZW_Teemoer  teemoer@cntv.cn
 * Date 2016/7/8
 * Time 上午10:21
 */
@Controller
@RequestMapping(value = "/learn/subPage")
public class LearnSubMedController extends ConstantsController {

    @Resource
    private TagService tagService;
    @Resource
    private PrescriptionCategoryService prescriptionCategoryService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;
    @Resource
    private ConversionService conversionService;

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


    // TODO 录入页面 加载中药Tag
    @RequestMapping(value = "/chinaMedTag")
    public String loadChinaMedTag(Model model,
                                  @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                  @RequestParam(value = "name", required = false) String name) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }


        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page, 16, sort);

        Page<Medicine> medicinePage = medicineService.getMedPageByTypeAndName(pageable, name, Medicine.Type.Chinese);
        model.addAttribute("chineseMedPage", medicinePage);
        return "/learn/subPage/chinaMedTag";
    }

    // TODO 录入页面 加载西药Tag
    @RequestMapping(value = "/WesternsTag")
    public String loadWesternsTag(Model model,
                                  @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                  @RequestParam(value = "name", required = false) String name) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page, 8, sort);
        Page<Medicine> medicinePage = medicineService.getMedPageByTypeAndName(pageable, name, Medicine.Type.Western);
        model.addAttribute("westernsMedPage", medicinePage);
        return "/learn/subPage/WesternsTag";
    }


    // TODO 录入药方 选择药品
    @RequestMapping(value = "/selMed")
    public String chooseDrugs(Model model,
                              @RequestParam(value = "id") Long id
    ) {
        MedicinePrivate medicinePrivate = medicinePrivateService.getMedPriByMedId(id, SecuritySupport
                .getDoctor());
        if (null == medicinePrivate) {
            Medicine medicine = medicineService.getMedicine(id);
            medicinePrivate = new MedicinePrivate().medToMedPriveate(medicine);
            medicinePrivate.setMedicine(medicine);
            medicinePrivate.setDoctor(SecuritySupport.getDoctor());
            medicinePrivateService.save(medicinePrivate);
        }


        model.addAttribute("medicine", medicinePrivate);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "/learn/subPage/subMed/selMed";
    }

    // TODO 录入药方 选择药品
    @RequestMapping(value = "/editSelMed")
    public String editSelMed(Model model,
                             @RequestParam(value = "id") Long id,
                             @RequestParam(value = "groupId", required = false, defaultValue = "10") String groupId,
                             @RequestParam(value = "selectMedUseModType", required = false) String selectMedUseModType,
                             @RequestParam(value = "txtMedicineQty", required = false) String txtMedicineQty,
                             @RequestParam(value = "specialInstructions", required = false) String specialInstructions
    ) {
        MedicinePrivate medicinePrivate = medicinePrivateService.getMedPriByMedId(id, SecuritySupport
                .getDoctor());

        medicinePrivate.setUseQty(txtMedicineQty);
        medicinePrivate.setUseMode(selectMedUseModType);

        model.addAttribute("medicine", medicinePrivate);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("groupId", groupId);
        model.addAttribute("selectMedUseModType", selectMedUseModType);
        model.addAttribute("specialInstructions", specialInstructions);
        model.addAttribute("txtMedicineQty", txtMedicineQty);
        return "/learn/subPage/subMed/editSelMed";
    }


    @RequestMapping(value = "/selMed/conversion/select")
    public String updateForm(@RequestParam(value = "medicineId") Long medicineId, @RequestParam(value = "unit")
            Medicine.Unit unit,
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
        model.addAttribute("groupId", "10");
        model.addAttribute("msg", "如换算率不正确，请输入新的换算率，并点击“修改换算率。");
        model.addAttribute("unit", unit);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "/learn/subPage/subMed/conversionSelect";
    }


    @RequestMapping(value = "/selMed/medicine/usage/{id}", method = RequestMethod.GET)
    public String usageForm(@PathVariable("id") Long id, Model model) {
        Medicine medicine = medicineService.getMedicine(id);
        model.addAttribute("medicine", medicine);
        return "/learn/subPage/subMed/medicineUsage";
    }


}
