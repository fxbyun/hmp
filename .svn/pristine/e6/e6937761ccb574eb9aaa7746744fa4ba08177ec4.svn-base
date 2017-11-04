package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.collections.CollectionUtils;
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
import java.text.DecimalFormat;
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
    private MedicinePrivateService medicinePrivateService;
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

    @ModelAttribute("doctor")
    private Doctor doctor(HttpServletRequest request) {
        return SecuritySupport.getDoctor();
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
    public List<PrescriptionItem> rpItems(@PathVariable Long id) {
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
            List<MedicinePrivate> medicinePrivateList = medicinePrivateService.findByDoctorAndMedicine(SecuritySupport.getDoctor(), new Medicine(item.getMedicineId()));
            DecimalFormat df = new DecimalFormat("######0.00");
            if (medicinePrivateList.size() > 0) {
                MedicinePrivate medicinePrivate = medicinePrivateList.get(0);
                if (SecuritySupport.getDoctor().getDoctorType() == Doctor.Doctor_Type.Sub_Doctor && (medicinePrivate.getPrice() == null || medicinePrivate.getPrice() == 0D)) {
                    List<MedicinePrivate> medicinePrivateListParentDoctor = medicinePrivateService.findByDoctorAndMedicine(new Doctor(SecuritySupport.getDoctor().getPrimaryDoctorId()), new Medicine(item.getMedicineId()));
                    if (medicinePrivateListParentDoctor.size() > 0) {
                        medicinePrivate = medicinePrivate.medPriveateToMedPriveate(medicinePrivateListParentDoctor.get(0));
                    }
                }
                item.setUnitPrice(medicinePrivate.getPrice());
                if (medicinePrivate.getRate() == null || medicinePrivate.getRate() == 0) {
                    medicinePrivate.setRate(1D);
                }
                if (medicinePrivate.getPrice() == null || medicinePrivate.getPrice() == 0) {
                    medicinePrivate.setPrice(0D);
                }
                Double qty;
                try {
                    qty = Double.valueOf(medicinePrivate.getUseQty());
                } catch (Exception e) {
                    qty = 1D;
                }


                try {
                    //item.setPrice(qty * (medicinePrivate.getPrice() / medicinePrivate.getRate()));
                    item.setPrice(qty * medicinePrivateService.getPriceAfterUnit(medicinePrivate));
                    item.setPrice(Double.valueOf(df.format(item.getPrice())));
                } catch (Exception e) {
                    item.setPrice(0D);
                }

                /*修改成私有医生当前的换算率*/
                if (null != medicinePrivate.getRate()) {
                    item.setRate(medicinePrivate.getRate());
                }


                item.setMedicinePrivateId(medicinePrivate.getId());
                item.setTjUnit(medicinePrivate.getUnit());

            } else {
                MedicinePrivate medicinePrivateNew = new MedicinePrivate().medToMedPriveate(medicineService.getMedicine(item.getMedicineId()));
                if (SecuritySupport.getDoctor().getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                    List<MedicinePrivate> medicinePrivateList1 = medicinePrivateService.findByDoctorAndMedicine(new Doctor(SecuritySupport.getDoctorId()), new Medicine(item.getMedicineId()));
                    if (medicinePrivateList1.size() > 0) {
                        medicinePrivateNew = new MedicinePrivate().medPriveateToMedPriveate(medicinePrivateList1.get(0));
                    }
                }
                medicinePrivateNew.setDoctor(SecuritySupport.getDoctor());
                item.setUnitPrice(medicinePrivateNew.getPrice());
                if (medicinePrivateNew.getRate() == null || medicinePrivateNew.getRate() == 0) {
                    medicinePrivateNew.setRate(1D);
                }
                if (medicinePrivateNew.getPrice() == null || medicinePrivateNew.getPrice() == 0) {
                    medicinePrivateNew.setPrice(0D);
                }
                Double qty;
                try {
                    qty = Double.valueOf(medicinePrivateNew.getUseQty());
                } catch (Exception e) {
                    qty = 1D;
                }


                item.setPrice(qty * (medicinePrivateNew.getPrice() / medicinePrivateNew.getRate()));
                try {
                    item.setPrice(Double.valueOf(df.format(item.getPrice())));
                    item.setPrice(medicinePrivateNew.getPrice());
                } catch (Exception e) {
                    item.setPrice(0D);
                }
                medicinePrivateService.save(medicinePrivateNew);
                item.setMedicinePrivateId(medicinePrivateNew.getId());
                item.setTjUnit(medicinePrivateNew.getUnit());

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
        model.addAttribute("doctor", SecuritySupport.getDoctor());
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
        Prescription prescription = prescriptionService.getPrescription(id);
//        final boolean[] needSave = {false};
        if (SecuritySupport.getDoctor().getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            prescription.getPrescriptionItemList().forEach(prescriptionItem -> {
                if (prescriptionItem.getPrice() == null || prescriptionItem.getPrice() == 0D) {
                    List<MedicinePrivate> medicinePrivateList = medicinePrivateService.findByDoctorAndMedicine(new Doctor(SecuritySupport.getDoctor().getPrimaryDoctorId()), new Medicine(prescriptionItem.getMedicineId()));
                    if (medicinePrivateList.size() > 0) {
                        MedicinePrivate medicinePrivatePatient = medicinePrivateList.get(0);
                        prescriptionItem.setPrice(medicinePrivatePatient.getPrice());
                        prescriptionItem.setUnitPrice(medicinePrivatePatient.getPrice());
                        prescriptionItem.setTjUnit(medicinePrivatePatient.getUseUnit());

//                        needSave[0] = true;
                        List<MedicinePrivate> medicinePrivateListMe = medicinePrivateService.findByDoctorAndMedicine(SecuritySupport.getDoctor(), new Medicine(prescriptionItem.getMedicineId()));
                        MedicinePrivate medicinePrivate12 = medicinePrivateListMe.size() > 0 ? medicinePrivate12 = medicinePrivateListMe.get(0) : new MedicinePrivate();
                        if (medicinePrivate12.getPrice() == null || medicinePrivate12.getPrice() == 0D) {
                            medicinePrivate12 = medicinePrivate12.medPriveateToMedPriveate(medicinePrivatePatient);
                            medicinePrivate12.setDoctor(SecuritySupport.getDoctor());
                            medicinePrivateService.save(medicinePrivate12);
                        }
                        prescriptionItem.setMedicinePrivateId(medicinePrivate12.getId());
                    }

                }
            });
//            if (needSave[0] || prescription.getPrescriptionItemList().size() > 0)
//                prescriptionService.savePrescription(prescription);
        }

        model.addAttribute("rp", prescription);
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
                       @RequestParam(value = "specialInstructions", required = false) List<String> specialInstructions,
                       @RequestParam(value = "standard", required = false) List<String> standard,
                       @RequestParam(value = "medicinePrivateIds", required = false) List<Long> medicinePrivateIds,
                       @RequestParam(value = "unitPrices", required = false) List<Double> unitPrices,
                       @RequestParam(value = "prices", required = false) List<Double> prices,
                       @RequestParam(value = "tjUnits", required = false) List<Medicine.Unit> tjUnits,
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
                if (CollectionUtils.isNotEmpty(medicineCopies)) {
                    item.setCopies(medicineCopies.get(i));
                }
                if (CollectionUtils.isNotEmpty(medicineUseModes)) {
                    item.setUseMode(medicineUseModes.get(i));
                }
                item.setHasUsage(medicineHasUsages.get(i));
                item.setMultiplexTag(multiplexTag);
                if (CollectionUtils.isNotEmpty(prices)) {
                    if (i < prices.size()) {
                        item.setPrice(prices.get(i));
                    }
                }

                if (CollectionUtils.isNotEmpty(tjUnits)) {
                    if (i < tjUnits.size()) {
                        item.setTjUnit(tjUnits.get(i));
                    }
                }
                if (CollectionUtils.isNotEmpty(unitPrices)) {
                    if (i < unitPrices.size()) {
                        item.setUnitPrice(unitPrices.get(i));
                    }
                }
                if (CollectionUtils.isNotEmpty(medicinePrivateIds)) {
                    if (i < medicinePrivateIds.size()) {
                        item.setMedicinePrivateId(medicinePrivateIds.get(i));
                    }
                }
                if (medicineGroupId != null && medicineGroupId.size() != 0)
                    if (i < medicineGroupId.size()) {
                        item.setGroupIndex(medicineGroupId.get(i));
                    }

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

                //药品特殊说明
                if (Collections3.isNotEmpty(specialInstructions) && specialInstructions.size() > i) {
                    item.setSpecialInstructions(specialInstructions.get(i));
                }

                //药品规格
                if (Collections3.isNotEmpty(standard) && standard.size() > i) {
                    item.setStandard(standard.get(i));
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
    public String selectDiagnosis() {
        return "fragment/diagnosisPage";
    }

    @RequestMapping(value = "/rp/find", method = RequestMethod.GET)
    @ResponseBody
    public Result find(@RequestParam String tag) {
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
