package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.service.PrescriptionCategoryService;
import com.qiaobei.hmp.service.PrescriptionLibService;
import com.qiaobei.hmp.service.PrescriptionService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.utils.Collections3;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.EnumMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PrescriptionController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(PrescriptionController.class);
    @Resource
    private PrescriptionService prescriptionService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private PrescriptionLibService prescriptionLibService;
    @Resource
    private PrescriptionCategoryService prescriptionCategoryService;

    @ModelAttribute("medicineTypes")
    private EnumMap<Medicine.Type, String> medicineTypes(HttpServletRequest request) {
        return MEDICINE_TYPES;
    }

    @ModelAttribute("medicineUnits")
    private EnumMap<Medicine.Unit, String> medicineUnits(HttpServletRequest request) {
        return MEDICINE_UNITS;
    }

    @RequestMapping(value = "/rp/select/{medicineType}", method = RequestMethod.GET)
    public String rpSelect(@PathVariable Medicine.Type medicineType, Model model) {
        Pageable pageable = new PageRequest(0, Constants.DEFAULT_PAGE_SIZE);
        List<PrescriptionCategory> categories = prescriptionCategoryService.listPrescriptionCategoryByDoctor
                (SecuritySupport.getDoctorId());

        model.addAttribute("categories", categories);
        if (Collections3.isNotEmpty(categories)) {
            model.addAttribute("rpPage", prescriptionService.pagePrescription(pageable,
                    SecuritySupport.getDoctorId(), Collections3.getFirst(categories).getId(), medicineType, null));
        }
        model.addAttribute("medicineType", medicineType);
        return "rpSelect";
    }

    @RequestMapping(value = "/fragment/rp/{id}", method = RequestMethod.GET)
    public String rpDetails(@PathVariable Long id, Model model) {
        model.addAttribute("rp", prescriptionService.getPrescription(id));
        return "/fragment/rpDetails";
    }

    @RequestMapping(value = "/rp/{id}/items", method = RequestMethod.GET)
    @ResponseBody
    public List<PrescriptionItem> rpItems(@PathVariable Long id, Model model) {
        Prescription rp = prescriptionService.getPrescription(id);
        List<PrescriptionItem> itemList = rp.getPrescriptionItemList();
        for (PrescriptionItem item : itemList) {
            if (item.getUnitLabel() == null) {
                item.setUnitLabel(MEDICINE_UNITS.get(item.getUnit()));
            }
            Medicine medicine = medicineService.getMedicine(item.getMedicineId());
            if (item.getUseTimes() == null) {
                item.setUseTimes(medicine.getUseTimes());
            }
            if (item.getUseQty() == null) {
                item.setUseQty(medicine.getUseQty());
            }
            if (item.getUseUnit() == null) {
                item.setUseUnit(MEDICINE_UNITS.get(medicine.getUseUnit()));
            }
            if (item.getUseMode() == null) {
                item.setUseMode(medicine.getUseMode());
            }
            if (item.getUsingTime() == null) {
                item.setUsingTime(medicine.getUsingTime());
            }

        }
        return itemList;
    }

    /**
     * 药方管理页
     */
    @RequestMapping(value = "/rp", method = RequestMethod.GET)
    public String rp(Model model) {
        Pageable pageable = new PageRequest(0, Constants.DEFAULT_PAGE_SIZE);
        model.addAttribute("rpPage", prescriptionService.pagePrescription(pageable, SecuritySupport.getDoctorId()));
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));
        return "rp";
    }

    @RequestMapping(value = "/rp", method = RequestMethod.POST)
    public String query(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                        @RequestParam(value = "categoryId", required = false) Long categoryId,
                        @RequestParam(value = "medicineType", required = false) Medicine.Type medicineType,
                        @RequestParam(value = "name", required = false) String name,
                        Model model) {
        Pageable pageable = new PageRequest(page, size);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("medicineType", medicineType);
        model.addAttribute("name", name);
        model.addAttribute("rpPage", prescriptionService.pagePrescription(pageable, SecuritySupport.getDoctorId(),
                categoryId, medicineType, name));
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));
        return "rp";
    }

    @RequestMapping(value = "/fragment/rp", method = RequestMethod.GET)
    public String queryFragment(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                @RequestParam(value = "categoryId", required = false) Long categoryId,
                                @RequestParam(value = "medicineType", required = false) Medicine.Type medicineType,
                                Model model) {
        Pageable pageable = new PageRequest(page, size);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("medicineType", medicineType);
        model.addAttribute("rpPage", prescriptionService.pagePrescription(pageable, SecuritySupport.getDoctorId(),
                categoryId, medicineType, null));
        return "/fragment/rpPage";
    }

    @ModelAttribute
    public void getPrescription(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("rp", prescriptionService.getPrescription(id));
        }
    }

    @RequestMapping(value = "/rp/add", method = RequestMethod.GET)
    public String newForm(Model model) {
        Prescription p = new Prescription();
        p.setMedicineType(Medicine.Type.Western);
        model.addAttribute("rp", p);
        model.addAttribute("categories",
                prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport.getDoctorId()));
        return "rpForm";
    }

    @RequestMapping(value = "/rp/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("rp", prescriptionService.getPrescription(id));
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionCategoryByDoctor(SecuritySupport
                .getDoctorId()));
        return "rpForm";
    }

    @RequestMapping(value = "/rp/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return Result.ok();
    }

    @RequestMapping(value = "/rp/save", method = RequestMethod.POST)
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
        return "redirect:/rp/edit/" + rp.getId();
    }

    /**
     * 共享药方管理页
     */
    @RequestMapping(value = "/rplib", method = RequestMethod.GET)
    public String rplib(Model model) {
        Pageable pageable = new PageRequest(0, Constants.DEFAULT_PAGE_SIZE);
        model.addAttribute("rplibPage", prescriptionLibService.findPage(pageable, null, null, null));
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionLibCategory());
        return "rplib";
    }

    @RequestMapping(value = "/rplib", method = RequestMethod.POST)
    public String queryLib(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                           @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                           @RequestParam(value = "categoryId", required = false) Long categoryId,
                           @RequestParam(value = "medicineType", required = false) Medicine.Type medicineType,
                           @RequestParam(value = "name", required = false) String name,
                           Model model) {
        Pageable pageable = new PageRequest(page, size);
        model.addAttribute("name", name);
        model.addAttribute("medicineType", medicineType);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", prescriptionCategoryService.listPrescriptionLibCategory());
        model.addAttribute("rplibPage", prescriptionLibService.findPage(pageable, name, categoryId, medicineType));
        return "rplib";
    }

    @RequestMapping(value = "/rp/toadd/{id}", method = RequestMethod.GET)
    public String toAdd(@PathVariable Long id, Model model) {
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
        return "rpLibForm";
    }

    @RequestMapping(value = "/rp/saveByLib", method = RequestMethod.POST)
    public String saveByLib(@Valid @ModelAttribute("rp") Prescription rp,
                            @RequestParam(value = "libId") Long libId,
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
        return "redirect:/rp/toadd/" + libId;
    }

    @RequestMapping(value = "/rp/select/diagnosis", method = RequestMethod.GET)
    public String selectDiagnosis(Model model) {
        return "fragment/diagnosisPage";
    }

    @RequestMapping(value = "/rp/find", method = RequestMethod.GET)
    @ResponseBody
    public Result find(@RequestParam String tag, Model model) {
        List<Prescription> rps = prescriptionService.listPrescription(SecuritySupport.getDoctorId(), tag);
        Result result = Result.ok();
        result.setData(rps);
        return result;
    }

    @RequestMapping(value = "/rp/list", method = RequestMethod.GET)
    @ResponseBody
    public Result doctorRp(@RequestParam Medicine.Type type,
                           @RequestParam Long cateId) {
        List<Prescription> rps = prescriptionService.listRP(SecuritySupport.getDoctorId(), type, cateId);
        Result result = Result.ok();
        result.setData(rps);
        return result;
    }

    @RequestMapping(value = "/rplib/list", method = RequestMethod.GET)
    @ResponseBody
    public Result libRp(@RequestParam Medicine.Type type,
                        @RequestParam Long cateId) {
        List<PrescriptionLib> rps = prescriptionLibService.listRPLib(type, cateId);
        Result result = Result.ok();
        result.setData(rps);
        return result;
    }

    @RequestMapping(value = "/rp/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteRp(@RequestParam String ids) {
        String tmpIds = ids.substring(0, ids.length() - 1);
        String[] arrayIds = tmpIds.split(",");
        for (String id : arrayIds) {
            prescriptionService.deletePrescription(Long.parseLong(id));
        }
        return Result.ok();
    }

    @RequestMapping(value = "/rplib/add", method = RequestMethod.GET)
    @ResponseBody
    public Result addRp(@RequestParam String ids, @RequestParam Long cateId) {
        String tmpIds = ids.substring(0, ids.length() - 1);
        Doctor d = SecuritySupport.getDoctor();
        if (d == null) {
            return Result.fail("请重新登陆！");
        }
        prescriptionService.batchAddPrescription(d, tmpIds, cateId);
        return Result.ok();
    }
}
