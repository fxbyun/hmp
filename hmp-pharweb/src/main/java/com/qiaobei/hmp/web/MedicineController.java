package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.Company;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.MedicinePrivateService;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.utils.Collections3;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.EnumMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanbin on 11/4/15.
 */
@Controller
public class MedicineController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(MedicineController.class);
    @Resource
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;

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

    /**
     * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
     * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
     */
    @ModelAttribute
    public void getMedicine(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("medicine", medicineService.getMedicine(id));
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("defaultCompany", "companyList");
    }

    @RequestMapping(value = "/medicine/add", method = RequestMethod.GET)
    public String updateForm(@RequestParam(value = "type", required = false) Medicine.Type type,
                             Model model) {
        if (type == null) type = Medicine.Type.Western;
        Medicine medicine = new Medicine(type);
        Company ns = Company.NotSpecified();
        medicine.getCompanyList().add(ns);
        medicine.setDefaultCompany(ns);
        medicine.setUsed(true);
        medicine.setUseTimes("每日3次");
        medicine.setUseQty("1");
        medicine.setUseUnit(Medicine.Unit.grs);
        medicine.setUseUnit(Medicine.Unit.grs);
        if (type == Medicine.Type.Chinese)
            medicine.setUseUnit(Medicine.Unit.g);
        model.addAttribute("medicine", medicine);
        return "fragment/medicineFormaNew";
    }

    @RequestMapping(value = "/medicine/edit/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        MedicinePrivate medicinePrivate = medicinePrivateService.getMedPrivate(id);
        //medicine.setUsed(medicineService.isUsedMedicineForDoctor(SecuritySupport.getDoctor(), medicine));
        model.addAttribute("medicine", medicinePrivate);
        return "fragment/medicineForm";
    }

    @RequestMapping(value = "/medicine/usage/{id}", method = RequestMethod.GET)
    public String usageForm(@PathVariable("id") Long id, Model model) {
        MedicinePrivate medicinePrivate = medicinePrivateService.getMedPrivate(id);
        model.addAttribute("medicine", medicinePrivate);
        return "fragment/medicineUsage";
    }

    @RequestMapping(value = "/medicine/{id}/used", method = RequestMethod.POST)
    @ResponseBody
    public Result used(@PathVariable("id") Long id,
                       @RequestParam(value = "used", defaultValue = "false") boolean used,
                       Model model) {
        try {
            Doctor doctor = SecuritySupport.getDoctor();
            Medicine medicine = new Medicine(id);
            if (used) {
                medicineService.addMedicineToDoctor(doctor, medicine);
            } else {
                //medicineService.removeMedicineFromDoctor(doctor, medicine);
            }
        } catch (ServiceException e) {
            return Result.fail("修改常用药品出错");
        }
        return Result.ok();
    }

    @RequestMapping(value = "/medicine/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("medicine") Medicine medicine,
                       @RequestParam(value = "defaultCompanyId", required = false) Long defaultCompanyId,
                       @RequestParam(value = "companyList", required = false) List<Long> companyList,
                       RedirectAttributes redirectAttributes) {
        if (Numbers.isNullOrZero(defaultCompanyId) && Collections3.isNotEmpty(companyList)) {
            defaultCompanyId = Collections3.getFirst(companyList);
        }
        if (Numbers.isNotNullOrZero(defaultCompanyId)) {
            medicine.setDefaultCompany(new Company(defaultCompanyId));
        }
        // bind companyList
        medicine.getCompanyList().clear();
        if (Collections3.isNotEmpty(companyList)) {
            for (Long companyId : companyList) {
                Company company = new Company(companyId);
                medicine.getCompanyList().add(company);
            }
        }

        redirectAttributes.addFlashAttribute("msg", "药品信息已保存");
        //medicineService

        Medicine lodMedicine = medicineService.isThisMedHaveSameInSystem(medicine.getName());
        MedicinePrivate medicinePrivate = new MedicinePrivate();
        if (lodMedicine != null) {

            if (medicine.getId() != null) {
                medicineService.saveMedicine(medicine, SecuritySupport.getDoctor());
            } else {
                redirectAttributes.addFlashAttribute("msg", "保存失败,该药品已存在!");
                //查找医生私有药品
                medicinePrivate = medicinePrivateService.getMedPriByMedId(lodMedicine.getId(), SecuritySupport
                        .getDoctor());
                //存到医生私有药品库中
                if (null == medicinePrivate) {
                    medicinePrivate = new MedicinePrivate().medToMedPriveate(lodMedicine);
                    medicinePrivate.setMedicine(lodMedicine);
                    medicinePrivate.setDoctor(SecuritySupport.getDoctor());
                    medicinePrivateService.save(medicinePrivate);
                }
            }
        } else {
            medicineService.saveMedicine(medicine, SecuritySupport.getDoctor());
            medicinePrivate = medicinePrivateService.getMedPriByMedId(medicine.getId(), SecuritySupport.getDoctor());
            if (null == medicinePrivate) {
                medicinePrivate = new MedicinePrivate().medToMedPriveate(medicine);
                medicinePrivate.setMedicine(medicine);
                medicinePrivate.setDoctor(SecuritySupport.getDoctor());
                medicinePrivateService.save(medicinePrivate);
            }
        }

        return "redirect:/medicine/edit/" + medicinePrivate.getId();
    }

    @RequestMapping(value = "/medicine/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("medicinePrivate") MedicinePrivate medicinePrivate,
                         @RequestParam(value = "defaultCompanyId", required = false) Long defaultCompanyId,
                         @RequestParam(value = "companyList", required = false) List<Long> companyList,
                         RedirectAttributes redirectAttributes) {
        if (Numbers.isNullOrZero(defaultCompanyId) && Collections3.isNotEmpty(companyList)) {
            defaultCompanyId = Collections3.getFirst(companyList);
        }
        if (Numbers.isNotNullOrZero(defaultCompanyId)) {
            medicinePrivate.setDefaultCompany(new Company(defaultCompanyId));
        }
        // bind companyList
        medicinePrivate.setMedicine(medicinePrivateService.getMedPrivate(medicinePrivate.getId()).getMedicine());
        medicinePrivate.setDoctor(SecuritySupport.getDoctor());
        medicinePrivate.getMedicine().getCompanyList().clear();
        if (Collections3.isNotEmpty(companyList)) {
            for (Long companyId : companyList) {
                Company company = new Company(companyId);
                medicinePrivate.getMedicine().getCompanyList().add(company);
            }
        }

        redirectAttributes.addFlashAttribute("msg", "药品信息已保存");

        medicinePrivateService.update(medicinePrivate);

        return "redirect:/medicine/edit/" + medicinePrivate.getId();
    }

    @RequestMapping(value = "/medicine/saveUsage", method = RequestMethod.POST)
    @ResponseBody
    public Result saveUsage(@Valid @ModelAttribute("medicinePrivate") MedicinePrivate medicinePrivate,
                            RedirectAttributes redirectAttributes) {
        try {
            MedicinePrivate medicinePrivate1 = medicinePrivateService.getMedPrivate(medicinePrivate.getId());
            medicinePrivate1.setUseTimes(medicinePrivate.getUseTimes());
            medicinePrivate1.setUsingTime(medicinePrivate.getUsingTime());
            medicinePrivate1.setUseQty(medicinePrivate.getUseQty());
            medicinePrivate1.setUseUnit(medicinePrivate.getUseUnit());
            medicinePrivateService.save(medicinePrivate1);
            return Result.ok("药品用量用法已保存");
        } catch (Exception e) {
            logger.error("saveUsage error:{}", e.getMessage());
            return Result.fail("保存药品用量用法出错");
        }
    }

    @RequestMapping(value = "/medicine/saveStandard/{medPrivateId}", method = RequestMethod.GET)
    @ResponseBody
    public Result saveStandard(@PathVariable Long medPrivateId, @RequestParam(value = "standard") String standard) {
        try {
            MedicinePrivate medicinePrivate1 = medicinePrivateService.getMedPrivate(medPrivateId);
            if (medicinePrivate1 != null) {
                if (standard != null && !standard.equals("") && !medicinePrivate1.getStandard().equals(standard)) {
                    medicinePrivate1.setStandard(standard);
                    System.out.print(medicinePrivate1.getStandard());
                }
                String str = medicinePrivate1.getStandard();
                System.out.print(str);
                medicinePrivateService.save(medicinePrivate1);
                return Result.ok("医生个人药品规格保存成功");
            }
            return Result.fail("保存私人医生药品规格失败");
        } catch (Exception e) {
            logger.error("saveStandard error{}", e.getMessage());
            return Result.fail("保存私人医生药品规格失败");
        }
    }


    @RequestMapping(value = "/fragment/medicine/select/{id}", method = RequestMethod.GET)
    public String select(@PathVariable("id") String id,
                         @RequestParam(value = "type", required = false, defaultValue = "edit") String type,
                         @RequestParam(value = "groupId", required = false, defaultValue = "10") String groupId,
                         @RequestParam(value = "txtMedicineQty", required = false) String txtMedicineQty,
                         @RequestParam(value = "selectMedUseModType", required = false) String selectMedUseModType,
                         @RequestParam(value = "specialInstructions", required = false) String specialInstructions,
                         @RequestParam(value = "standard", required = false) String standard,
                         @RequestParam(value = "lastSelectXwOrFenBao", required = false) String lastSelectXwOrFenBao
            , Model model) {
        String fs = "";
        Boolean isNewMedForPriv = false;
        if ("edit".equals(type)) {
            String reg = "[s]+";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(id);
            if (matcher.find()) {
                fs = matcher.group();
                id = id.replaceAll(reg, "");
            }
        }
        MedicinePrivate medicinePrivate = medicinePrivateService.getMedPriByMedId(Long.valueOf(id), SecuritySupport
                .getDoctor());
        if (null == medicinePrivate) {
            Medicine medicine = medicineService.getMedicine(Long.valueOf(id));
            medicinePrivate = new MedicinePrivate().medToMedPriveate(medicine);
            medicinePrivate.setMedicine(medicine);
            medicinePrivate.setDoctor(SecuritySupport.getDoctor());
            isNewMedForPriv = true;
            medicinePrivateService.save(medicinePrivate);
        }

        //medicinePrivate.setUsed(medicineService.isUsedMedicineForDoctor(SecuritySupport.getDoctor(), medicine));
        if (txtMedicineQty != null && !"".equals(txtMedicineQty)) {
            medicinePrivate.setUseQty(txtMedicineQty);
        }
        //如果医生修改了药品的规格(这里不知道需不需要实时保存到医生私人库中)
        if (standard != null && !"".equals(standard)) {
            medicinePrivate.setStandard(standard);
        }
        model.addAttribute("medicine", medicinePrivate);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("openType", type);
        model.addAttribute("haveMore", fs);
        model.addAttribute("groupId", groupId);
        model.addAttribute("txtMedicineQty", txtMedicineQty);
        model.addAttribute("isNewMedForPriv", isNewMedForPriv);
        model.addAttribute("selectMedUseModType", selectMedUseModType);
        model.addAttribute("lastSelectXwOrFenBao", lastSelectXwOrFenBao);
        model.addAttribute("specialInstructions", specialInstructions);
        model.addAttribute("standard", standard);
        return "fragment/medicineSelect";
        //return "";
    }

    @RequestMapping(value = "/fragment/medicines/Western", method = RequestMethod.GET)
    public String western(@RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "name", required = false) String name, @RequestParam(value =
            "diagonsisName", required = false)
                                  String diagonsisName, Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, 10, sort);


        return "fragment/westernMedicinePage";
    }


    @RequestMapping(value = "/medicine/updateUsageFlag/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUsage(@PathVariable Long id, boolean usageFlag) {
        try {
            MedicinePrivate medicinePrivate = medicinePrivateService.getMedPrivate(id);
            medicinePrivate.setUsageFlag(usageFlag);
            medicinePrivateService.update(medicinePrivate);
            return Result.ok();
        } catch (Exception e) {
            logger.error("updateUsageFlag error:{}", e.getMessage());
            return Result.fail("保存药品UsageFlag出错！");
        }
    }


    @RequestMapping(value = "/medicine/updateCate/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result updateCate(@PathVariable Long id, @RequestParam String category) {
        MedicinePrivate m = medicinePrivateService.getMedPrivate(id);
        m.setCategory(category);
        medicinePrivateService.update(m);
        return Result.ok();
    }

    @RequestMapping(value = "/fragment/myMedicine/usage/{id}", method = RequestMethod.GET)
    public String getMyMedicineUsage(@PathVariable Long id, Model model) {
        model.addAttribute("medicine", medicineService.getMedicine(id));
        return "fragment/myMedicineUsage";
    }
}
