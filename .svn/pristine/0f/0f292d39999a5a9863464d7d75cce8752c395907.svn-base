package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Result;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import static com.qiaobei.hmp.web.ConstantsController.*;

/**
 * Created by Administrator on 2016/7/8.
 */
@Controller
@RequestMapping(value = "/learn")
public class LearnController {
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

    @Resource
    private PrescriptionService prescriptionService;
    @Resource
    private PrescriptionCategoryService prescriptionCategoryService;
    @Resource
    private PrescriptionLibService prescriptionLibService;
    @Resource
    private CompanyService companyService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;


    // TODO 手机登录页
    @RequestMapping(value = "/login")
    public String goToMobileLogin() {

        return "/learn/login";
    }

    // TODO 录入药方病症b
    @RequestMapping(value = "/index")
    public String goToIndex() {

        return "/learn/index";
    }


    // TODO 录入药方病症b
    @RequestMapping(value = "/prescription/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("rp") Prescription rp,
                       @RequestParam(value = "medicineIds", required = false) List<String> medicineIds,
                       @RequestParam(value = "companyIds", required = false) List<Long> companyIds,
                       @RequestParam(value = "medicineQty", required = false) List<Double> medicineQty,
                       @RequestParam(value = "medicineRate", required = false) List<Double> medicineRate,
                       @RequestParam(value = "medicineUnit", required = false) List<Medicine.Unit> medicineUnit,
                       @RequestParam(value = "medicineCopies", required = false) List<Double> medicineCopies,
                       @RequestParam(value = "medicineUseModes", required = false) List<String> medicineUseModes,
                       @RequestParam(value = "medicineHasUsages", required = false) List<Boolean> medicineHasUsages,
                       @RequestParam(value = "medicineUseTimes", required = false) List<String> medicineUseTimes,
                       @RequestParam(value = "medicineUseUsingTime", required = false) List<String>
                               medicineUseUsingTime,
                       @RequestParam(value = "medicineUseQty", required = false) List<String> medicineUseQty,
                       @RequestParam(value = "medicineUseUnit", required = false) List<String> medicineUseUnit,
                       @RequestParam(value = "medicineGroupId", required = false) List<String> medicineGroupId,
                       @RequestParam(value = "diagnosisResults", required = false) List<String>
                               diagnosisResult,
                       @RequestParam(value = "diagnosisTagIds", required = false) List<Long>
                               diagnosisTagIds,
                       RedirectAttributes redirectAttributes) {
        Doctor doctor = SecuritySupport.getDoctor();
        rp.setDoctor(doctor);
        rp.setStatus(StatusEntity.Status.Normal);

        if (Collections3.isNotEmpty(medicineIds)
                && Collections3.isNotEmpty(companyIds)
                && Collections3.isNotEmpty(medicineQty)
                && Collections3.isNotEmpty(medicineUnit)) {
            rp.getPrescriptionItemList().clear();
            for (int i = 0; i < medicineIds.size(); i++) {
                Long medId = 0L;
                String multiplexTag = "";
                String idOne = medicineIds.get(i);
                if (idOne.indexOf('s') > -1) {
                    String reg = "[s]+";
                    Pattern pattern = Pattern.compile(reg);
                    Matcher matcher = pattern.matcher(idOne);
                    if (matcher.find()) {
                        multiplexTag = matcher.group();
                        medId = Long.valueOf(idOne.replaceAll(reg, ""));
                    }
                } else medId = Long.valueOf(idOne);
                PrescriptionItem item = new PrescriptionItem();
                item.setMedicineId(medId);
                item.setCompanyId(companyIds.get(i));
                item.setQty(medicineQty.get(i));
                item.setRate(medicineRate.get(i));
                item.setUnit(medicineUnit.get(i));
                item.setCopies(medicineCopies.get(i));
                item.setUseMode(medicineUseModes.get(i));
                item.setHasUsage(medicineHasUsages.get(i));
                item.setMultiplexTag(multiplexTag);
                if (medicineGroupId != null)
                    item.setGroupIndex(medicineGroupId.get(i));
                if (medicineHasUsages.get(i)) {
                    if (Collections3.isNotEmpty(medicineUseTimes) && medicineUseTimes.size() > i)
                        item.setUseTimes(medicineUseTimes.get(i));
                    if (Collections3.isNotEmpty(medicineUseUsingTime) && medicineUseUsingTime.size() > i)
                        item.setUsingTime(medicineUseUsingTime.get(i));
                    if (Collections3.isNotEmpty(medicineUseQty) && medicineUseQty.size() > i)
                        item.setUseQty(medicineUseQty.get(i));
                    if (Collections3.isNotEmpty(medicineUseUnit) && medicineUseUnit.size() > i)
                        item.setUseUnit(medicineUseUnit.get(i));
                }
                rp.getPrescriptionItemList().add(item);
            }
        }
        prescriptionService.savePrescription(rp);
        redirectAttributes.addFlashAttribute("msg", "selfAdd");
        //return "/learn/index";
        //return "redirect:/rp/edit/" + rp.getId();
        return "redirect:/learn/myDetailPrescription/" + rp.getId();
    }


    // TODO  模板药方
    @RequestMapping(value = "/templatePrescription")
    public String templatePrescription(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                       @RequestParam(value = "categoryId", required = false) Long categoryId,
                                       @RequestParam(value = "medicineType", required = false) Medicine.Type
                                               medicineType,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "type", required = false, defaultValue = "mySelf")
                                               String type,
                                       Model model) {
        Pageable pageable = new PageRequest(page, size);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("medicineType", medicineType);
        model.addAttribute("name", name);

        if (!"mySelf".equals(type)) {
            //模板库
            model.addAttribute("categories", prescriptionCategoryService.listPrescriptionLibCategory());
            model.addAttribute("rpPage", prescriptionLibService.findPage(pageable, name, categoryId, medicineType));
            return "/learn/templatePrescription";
        }

        //个人药方
        model.addAttribute("rpPage", prescriptionService.pagePrescription(pageable, SecuritySupport.getDoctorId(),
                categoryId, medicineType, name));
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor
                (SecuritySupport
                        .getDoctorId()));
        return "/learn/myPrescription";
    }

    // TODO  我的药方
    @RequestMapping(value = "/myPrescription")
    public String myPrescription(Model model) {

        return "/learn/myPrescription";
    }


    @RequestMapping(value = "/medicine/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@Valid @ModelAttribute("medicine") Medicine medicine,
                       @RequestParam(value = "defaultCompanyId", required = false) Long defaultCompanyId,
                       @RequestParam(value = "companyList", required = false) List<Long> companyList,
                       RedirectAttributes redirectAttributes) {
        Result result = new Result();
        result.setSuccess(true);
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

        result.setMsg("药品信息已保存");
        //medicineService

        Medicine lodMedicine = medicineService.isThisMedHaveSameInSystem(medicine.getName());
        MedicinePrivate medicinePrivate = new MedicinePrivate();
        if (lodMedicine != null) {

            if (medicine.getId() != null) {
                medicineService.saveMedicine(medicine, SecuritySupport.getDoctor());
            } else {
                result.setSuccess(false);
                result.setMsg("保存失败,该药品已存在!");
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

        return result;
    }

    // TODO  修改药方
    @RequestMapping(value = "/editPrescription")
    public String editPrescription(Model model) {

        return "/learn/editPrescription";
    }

    // TODO  模板药方详情
    @RequestMapping(value = "/detailPrescription/{id}")
    public String detailPrescription(@PathVariable Long id, Model model) {
        PrescriptionLib ps = prescriptionLibService.getPrescriptionShare(id);
        Prescription p = new Prescription();
        p.setName(ps.getName());
        p.setMedicineType(ps.getMedicineType());
        p.setRemark(ps.getRemark());
        List<PrescriptionItem> pList = Lists.newArrayList();
        for (PrescriptionLibItem item : ps.getPrescriptionLibItemList()) {
            PrescriptionItem pItem = new PrescriptionItem();
            pItem.setCompanyId(item.getCompanyId());
            pItem.setCompanyName(item.getCompanyName());
            pItem.setMedicineId(item.getMedicineId());
            pItem.setMedicineType(item.getMedicineType());
            pItem.setMedicineName(item.getMedicineName());
            pItem.setQty(item.getQty());
            pItem.setUnit(item.getUnit());
            pItem.setRate(item.getRate());
            pItem.setCopies(item.getCopies());
            pItem.setUseMode(item.getUseMode());
            pItem.setHasUsage(item.getHasUsage());
            pList.add(pItem);
        }
        p.setPrescriptionItemList(pList);
        model.addAttribute("rp", p);
        model.addAttribute("libid", id);
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));

        return "/learn/detailPrescription";
    }

    //增加药方模板到我的药方
    @RequestMapping(value = "/detailPrescription/saveToMe", method = RequestMethod.POST)
    public String saveByLib(@Valid @ModelAttribute("rp") Prescription rp,
                            //@RequestParam(value = "libId") Long libId,
                            @RequestParam(value = "medicineIds", required = false) List<Long> medicineIds,
                            @RequestParam(value = "companyIds", required = false) List<Long> companyIds,
                            @RequestParam(value = "medicineQty", required = false) List<Double> medicineQty,
                            @RequestParam(value = "medicineRate", required = false) List<Double> medicineRate,
                            @RequestParam(value = "medicineUnit", required = false) List<Medicine.Unit> medicineUnit,
                            @RequestParam(value = "medicineCopies", required = false) List<Double> medicineCopies,
                            @RequestParam(value = "medicineUseModes", required = false) List<String> medicineUseModes,
                            @RequestParam(value = "medicineHasUsages", required = false) List<Boolean>
                                    medicineHasUsages,
                            RedirectAttributes redirectAttributes) {
        Doctor doctor = SecuritySupport.getDoctor();
        rp.setDoctor(doctor);
        rp.setStatus(StatusEntity.Status.Normal);
        if (Collections3.isNotEmpty(medicineIds)
                && Collections3.isNotEmpty(companyIds)
                && Collections3.isNotEmpty(medicineQty)
                && Collections3.isNotEmpty(medicineUnit)) {
            rp.getPrescriptionItemList().clear();
            for (int i = 0; i < medicineIds.size(); i++) {
                PrescriptionItem item = new PrescriptionItem();
                item.setMedicineId(medicineIds.get(i));
                item.setCompanyId(companyIds.get(i));
                item.setQty(medicineQty.get(i));
                item.setRate(medicineRate.get(i));
                item.setUnit(medicineUnit.get(i));
                item.setCopies(medicineCopies.get(i));
                item.setUseMode(medicineUseModes.get(i));
                item.setHasUsage(medicineHasUsages.get(i));
                rp.getPrescriptionItemList().add(item);
            }
        }
        prescriptionService.savePrescription(rp);
        redirectAttributes.addFlashAttribute("msg", "药方信息已保存");
        return "redirect:/learn/myDetailPrescription/" + rp.getId() + "?type=libAdd";
    }

    // TODO  我的药方详情
    @RequestMapping(value = "/myDetailPrescription/{id}")
    public String myDetailPrescription(Model model,
                                       @PathVariable(value = "id") Long id,
                                       @RequestParam(value = "type", required = false, defaultValue = "see")
                                               String type) {
        Prescription prescription = prescriptionService.getPrescription(id);
        model.addAttribute("rp", prescription);
        model.addAttribute("medicineType", prescription.getMedicineType());
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));
        model.addAttribute("msg", type);
        return "/learn/myDetailPrescription";
    }


    @RequestMapping(value = "/myDetailPrescription/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("rp") Prescription rp,
                       @RequestParam(value = "medicineIds", required = false) List<String> medicineIds,
                       @RequestParam(value = "companyIds", required = false) List<Long> companyIds,
                       @RequestParam(value = "medicineQty", required = false) List<Double> medicineQty,
                       @RequestParam(value = "medicineRate", required = false) List<Double> medicineRate,
                       @RequestParam(value = "medicineUnit", required = false) List<Medicine.Unit> medicineUnit,
                       @RequestParam(value = "medicineCopies", required = false) List<Double> medicineCopies,
                       @RequestParam(value = "medicineUseModes", required = false) List<String> medicineUseModes,
                       @RequestParam(value = "medicineHasUsages", required = false) List<Boolean> medicineHasUsages,
                       @RequestParam(value = "medicineUseTimes", required = false) List<String> medicineUseTimes,
                       @RequestParam(value = "medicineUseUsingTime", required = false) List<String>
                               medicineUseUsingTime,
                       @RequestParam(value = "medicineUseQty", required = false) List<String> medicineUseQty,
                       @RequestParam(value = "medicineUseUnit", required = false) List<String> medicineUseUnit,
                       @RequestParam(value = "medicineGroupId", required = false) List<String> medicineGroupId,
                       RedirectAttributes redirectAttributes) {
        Doctor doctor = SecuritySupport.getDoctor();
        rp.setDoctor(doctor);
        rp.setStatus(StatusEntity.Status.Normal);

        if (Collections3.isNotEmpty(medicineIds)
                && Collections3.isNotEmpty(companyIds)
                && Collections3.isNotEmpty(medicineQty)
                && Collections3.isNotEmpty(medicineUnit)) {
            rp.getPrescriptionItemList().clear();
            for (int i = 0; i < medicineIds.size(); i++) {
                Long medId = 0L;
                String multiplexTag = "";
                String idOne = medicineIds.get(i);
                if (idOne.indexOf('s') > -1) {
                    String reg = "[s]+";
                    Pattern pattern = Pattern.compile(reg);
                    Matcher matcher = pattern.matcher(idOne);
                    if (matcher.find()) {
                        multiplexTag = matcher.group();
                        medId = Long.valueOf(idOne.replaceAll(reg, ""));
                    }
                } else medId = Long.valueOf(idOne);
                PrescriptionItem item = new PrescriptionItem();
                item.setMedicineId(medId);
                item.setCompanyId(companyIds.get(i));
                item.setQty(medicineQty.get(i));
                item.setRate(medicineRate.get(i));
                item.setUnit(medicineUnit.get(i));
                item.setCopies(medicineCopies.get(i));
                item.setUseMode(medicineUseModes.get(i));
                item.setHasUsage(medicineHasUsages.get(i));
                item.setMultiplexTag(multiplexTag);
                if (medicineGroupId != null)
                    item.setGroupIndex(medicineGroupId.get(i));
                if (medicineHasUsages.get(i)) {
                    if (Collections3.isNotEmpty(medicineUseTimes) && medicineUseTimes.size() > i)
                        item.setUseTimes(medicineUseTimes.get(i));
                    if (Collections3.isNotEmpty(medicineUseUsingTime) && medicineUseUsingTime.size() > i)
                        item.setUsingTime(medicineUseUsingTime.get(i));
                    if (Collections3.isNotEmpty(medicineUseQty) && medicineUseQty.size() > i)
                        item.setUseQty(medicineUseQty.get(i));
                    if (Collections3.isNotEmpty(medicineUseUnit) && medicineUseUnit.size() > i)
                        item.setUseUnit(medicineUseUnit.get(i));
                }
                rp.getPrescriptionItemList().add(item);
            }
        }
        prescriptionService.savePrescription(rp);
        redirectAttributes.addFlashAttribute("msg", "药方信息已保存");
        //return "redirect:/rp/edit/" + rp.getId();
        return "redirect:/learn/myDetailPrescription/" + rp.getId() + "?type=edit";
    }


}
