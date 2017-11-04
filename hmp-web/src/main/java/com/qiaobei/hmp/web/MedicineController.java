package com.qiaobei.hmp.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.service.RetailMedicineService;
import com.qiaobei.hmp.modules.service.RetailService;
import com.qiaobei.hmp.modules.support.EntityTmpCloumsVal;
import com.qiaobei.hmp.redis.MedicineCountCache;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.utils.Collections3;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 11/4/15
 * Time 17:26
 */
@Controller
public class MedicineController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(MedicineController.class);
    @Resource
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;

    @Resource
    private DoctorService doctorService;
    @Resource
    private IaiLossDetailService iaiLossDetailService;

    @Resource
    private PatientService patientService;

    @Resource
    private RetailService retailService;

    @Resource
    private RetailMedicineService retailMedicineService;

    @Resource
    private PharmacistService pharmacistService;


    @Resource
    private MedicineCountService medicineCountService;

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
                             @RequestParam(value = "iaiType", required = false) String iaiType,
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
        if (iaiType != null && !"".equals(iaiType)) {
            model.addAttribute("iaiType", iaiType);
        }
        if (type == Medicine.Type.Chinese)
            medicine.setUseUnit(Medicine.Unit.g);
        model.addAttribute("medicine", medicine);
        return "fragment/medicineFormaNew";
    }

    @RequestMapping(value = "/medicine/edit/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model,
                             @RequestParam(value = "iaiType", required = false) String iaiType) {
        MedicinePrivate medicinePrivate = medicinePrivateService.getMedPrivate(id);
        //medicine.setUsed(medicineService.isUsedMedicineForDoctor(SecuritySupport.getDoctor(), medicine));
        model.addAttribute("medicine", medicinePrivate);
        model.addAttribute("iaiType", iaiType);
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
                       @RequestParam(value = "used", defaultValue = "false") boolean used) {
        try {
            Doctor doctor = SecuritySupport.getDoctor();
            Medicine medicine = new Medicine(id);
            if (used) {
                medicineService.addMedicineToDoctor(doctor, medicine);
            } else {
                medicineService.removeMedicineFromDoctor(doctor, medicine);
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
                       @RequestParam(value = "iaiType", required = false) String iaiType,
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
                redirectAttributes.addFlashAttribute("msg", "该药品已存在，已经为您自动增加!");
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
        if ("iaiType".equals(iaiType)) {
            return "redirect:/medicine/edit/" + medicinePrivate.getId() + "?iaiType=iaiType";
        }
        return "redirect:/medicine/edit/" + medicinePrivate.getId();
    }

    @RequestMapping(value = "/medicine/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("medicinePrivate") MedicinePrivate medicinePrivate,
                         @RequestParam(value = "defaultCompanyId", required = false) Long defaultCompanyId,
                         @RequestParam(value = "usageFlag", required = false) Long usageFlag,
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
        if (usageFlag == 0) {
            medicinePrivate.setUsageFlag(true);
        } else {
            medicinePrivate.setUsageFlag(false);
        }
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


    @RequestMapping(value = "/medicine/saveStandardQty")
    @ResponseBody
    public Result saveStandardQty(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "qty") String qty,
            @RequestParam(value = "unit", required = false) Medicine.Unit unit
    ) {
        try {
            MedicinePrivate medicinePrivate1 = medicinePrivateService.getMedPrivate(id);
            medicinePrivate1.setUseQty(qty);
            medicinePrivate1.setUnit(unit);
            medicinePrivateService.save(medicinePrivate1);
            return Result.ok("药品数量和单位已保存");
        } catch (Exception e) {
            logger.error("saveUsage error:{}", e.getMessage());
            e.printStackTrace();
            return Result.fail("保存药品数量和单位出错");
        }
    }


    @RequestMapping(value = "/medicine/saveUsage", method = RequestMethod.POST)
    @ResponseBody
    public Result saveUsage(@Valid @ModelAttribute("medicinePrivate") MedicinePrivate medicinePrivate,
                            @RequestParam(value = "tmpErrorId", required = false) String tmpErrorId) {
        if (medicinePrivate == null || medicinePrivate.getId() == null) {
            //当标准用量选择适用时，medicinePrivate为空
            logger.info("saveUsage errormedicinePriva为空:{}", tmpErrorId);
            System.out.println(tmpErrorId);
            return Result.fail("保存药品用量用法出错");
        }
        try {
            MedicinePrivate medicinePrivate1 = medicinePrivateService.getMedPrivate(medicinePrivate.getId());
            medicinePrivate1.setUseTimes(medicinePrivate.getUseTimes());
            medicinePrivate1.setUsingTime(medicinePrivate.getUsingTime());
            medicinePrivate1.setUseQty(medicinePrivate.getUseQty());
            medicinePrivate1.setUseUnit(medicinePrivate.getUseUnit());
            medicinePrivateService.save(medicinePrivate1);
            return Result.ok("药品用量用法已保存");
        } catch (Exception e) {
            logger.error("saveUsage error:{}", e.getMessage(), e);
            return Result.fail("保存药品用量用法出错");
        }
    }

    /**
     * 保存药品规格
     */
    @RequestMapping(value = "/medicine/saveStandard/{medPrivateId}", method = RequestMethod.GET)
    @ResponseBody
    public Result saveStandard(@PathVariable Long medPrivateId, @RequestParam(value = "standard") String standard) {
        try {
            if (null == medPrivateId) {
                logger.error("controller{saveStandard}:找不到该私有药品的id");
                return Result.fail("找不到该私有药品的id");
            }
            MedicinePrivate medicinePrivate1 = medicinePrivateService.getMedPrivate(medPrivateId);
            if (medicinePrivate1 != null) {
                if (standard != null && !standard.equals("") && !medicinePrivate1.getStandard().equals(standard)) {
                    medicinePrivate1.setStandard(standard);
                }
                medicinePrivateService.save(medicinePrivate1);
                return Result.ok("医生个人药品规格保存成功");
            }
            return Result.fail("保存私人医生药品规格失败");
        } catch (Exception e) {
            logger.error("saveStandard error{}", e.getMessage());
            return Result.fail("保存私人医生药品规格失败");
        }
    }


    @RequestMapping(value = "/medicine/saveRealQty")
    @ResponseBody
    public Result saveRealQty(@RequestParam(value = "realQty") String realQty,
                              @RequestParam(value = "realUnit") Medicine.Unit realUnit,
                              @RequestParam(value = "medPrivateId") Long medPrivateId) {

        MedicinePrivate medPrivate = medicinePrivateService.getMedPrivate(medPrivateId);
        if (StringUtils.isNotEmpty(realQty)) {
            medPrivate.setRealQty(realQty);
            medPrivate.setRealUnit(realUnit);
        }
        medicinePrivateService.save(medPrivate);
        return Result.ok("私人医生的日常开药用量保存成功!");
    }


    /**
     * 保存药品换算单位
     */
    @RequestMapping(value = "/medicine/saveRate/{medPrivateId}", method = RequestMethod.GET)
    @ResponseBody
    public Result saveRate(@PathVariable Long medPrivateId, @RequestParam(value = "rate") Double rate) {
        try {
            Medicine medicine2 = medicineService.getMedicine(medPrivateId);
            List<MedicinePrivate> medPrivateList = medicinePrivateService.findByDoctorAndMedicine(SecuritySupport.getDoctor(), medicine2);

            if (medPrivateList == null || medPrivateList.size() == 0) {
                Medicine medicine = medicineService.getMedicine(medPrivateId);

                if (medicine != null) {
                    MedicinePrivate medicinePrivate = new MedicinePrivate().medToMedPriveate(medicine);
                    if (rate != null) {
                        medicinePrivate.setDoctor(SecuritySupport.getDoctor());
                        medicinePrivate.setRate(rate);
                    }
                    medicinePrivateService.save(medicinePrivate);
                    return Result.ok("医生个人药品换算保存成功");
                }

            }
            if (medPrivateList != null && medPrivateList.size() > 0) {
                if (rate != null) {
                    medPrivateList.get(0).setRate(rate);
                }
                medicinePrivateService.save(medPrivateList.get(0));
                return Result.ok("医生个人药品换算保存成功");
            }
            return Result.fail("保存私人医生药品换算失败");
        } catch (Exception e) {
            logger.error("saveRate error{}", e.getMessage());
            return Result.fail("保存私人医生药品换算失败");
        }
    }

    //保存医生开药习惯
    @RequestMapping(value = "/medicine/saveDoctorHabit")
    @ResponseBody
    public Result saveDoctorHabit(@RequestBody JSONObject object) {
        System.out.println(object);
        //medicinePrivateService.saveDoctorHabit()
        MedicinePrivate medicinePrivate = medicinePrivateService.getMedPrivate(object.getLong("medicinePrivateId"));
        if (null != medicinePrivate) {
            //如果报错不影响开药try一下
            try {
                //保存最后一次的药品规格
                medicinePrivate.setStandard(object.getString("standard"));

                //保存换算率
                if (StringUtils.isNotEmpty(object.getString("rate"))) {
                    medicinePrivate.setRate(new Double(object.getString("rate")));
                }

                //保存治疗方式
                if (StringUtils.isNotEmpty(object.getString("useMode"))) {
                    medicinePrivate.setUseMode(object.getString("useMode"));
                }

                //保存标准用量是否适用
                if (null != object.getBoolean("hasUsage")) {
                    medicinePrivate.setUsageFlag(object.getBoolean("hasUsage"));
                }

                //保存药品分类
                if (StringUtils.isNotEmpty(object.getString("category"))) {
                    medicinePrivate.setCategory(object.getString("category"));
                }

                //保存药品数量单位的Qty
                if (StringUtils.isNotEmpty(object.getString("qty"))) {
                    medicinePrivate.setRealQty(object.getString("qty"));
                }
                medicinePrivateService.save(medicinePrivate);
                return Result.ok("医生开药习惯保存成功！");
            } catch (Exception e) {
                logger.debug("医生开药习惯保存失败：" + e.getMessage());
                return Result.fail();
            }
        } else {
            return Result.fail("根据id找不到该医生的私有药品！");
        }
    }



    @RequestMapping(value = "/fragment/medicine/updateRateForm", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateRateForm(@RequestParam Long medicineId, @RequestParam Medicine.Unit unit,
                                 Model model) {
        try {
            Doctor doctor = SecuritySupport.getDoctor();
            Medicine medicine = medicineService.getMedicine(medicineId);
            List<MedicinePrivate> medPrivateList = medicinePrivateService.findByDoctorAndMedicine(doctor, medicine);
            if (medPrivateList != null && medPrivateList.size() > 0) {
                Double rate = medPrivateList.get(0).getRate();
                if (rate != null) {
                    model.addAttribute("rate", rate);
                } else {
                    Pageable pageable = new PageRequest(0, 1);
                    List<MedicinePrivate> medRateList = medicinePrivateService.findRateByMoreUser
                            (medPrivateList.get(0).getMedicine().getId(), pageable);
                    if (medRateList.size() > 0) {
                        rate = medRateList.get(0).getRate();
                        model.addAttribute("rate", rate);
                    } else {
                        model.addAttribute("rate", "");
                    }
                }

            }
            if (medPrivateList != null && medPrivateList.size() > 0) {
                model.addAttribute("medicine", medPrivateList.get(0));
                model.addAttribute("unit", unit);
            } else {
                model.addAttribute("medicine", medicine);
                model.addAttribute("unit", unit);
            }
            model.addAttribute("medicineUnits", MEDICINE_UNITS);
        } catch (Exception e) {
            logger.error("saveRate error{}", e.getMessage());
        }
        return "fragment/conversionSelect";
    }

    @RequestMapping(value = "/fragment/medicine/select/{id}", method = RequestMethod.GET)
    public String select(@PathVariable("id") String id,
                         @RequestParam(value = "type", required = false, defaultValue = "edit") String type,
                         @RequestParam(value = "groupId", required = false, defaultValue = "10") String groupId,
                         @RequestParam(value = "txtMedicineQty", required = false) String txtMedicineQty,
                         @RequestParam(value = "selectMedUseModType", required = false) String selectMedUseModType,
                         @RequestParam(value = "specialInstructions", required = false) String specialInstructions,
                         @RequestParam(value = "standard", required = false) String standard,
                         @RequestParam(value = "lastSelectXwOrFenBao", required = false) String lastSelectXwOrFenBao,
                         @RequestParam(value = "isShowWindow", required = false) Boolean isShowWindow,
                         @RequestParam(value = "handleType", required = false, defaultValue = "Medicine_Add") String handleType
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
        MedicinePrivate medicinePrivate = null;
        Medicine medicine1 = medicineService.getMedicine(Long.valueOf(id));
        List<MedicinePrivate> medPrivateList = medicinePrivateService.findByDoctorAndMedicine(SecuritySupport.getDoctor(), medicine1);
        if (medPrivateList != null && medPrivateList.size() > 0) {
            medicinePrivate = medPrivateList.get(0);
            if (medicinePrivate.getUnit() == null) {
                medicinePrivate.setUnit(medicine1.getUnit());
                medicinePrivateService.save(medicinePrivate);
            }
        }
        if (null == medicinePrivate) {
            Medicine medicine = medicineService.getMedicine(Long.valueOf(id));
            medicinePrivate = new MedicinePrivate().medToMedPriveate(medicine);
            medicinePrivate.setHaveManager(MedicinePrivate.HaveManager.NO);
            medicinePrivate.setMedicine(medicine);
            medicinePrivate.setDoctor(SecuritySupport.getDoctor());
            isNewMedForPriv = true;
            medicinePrivateService.save(medicinePrivate);
        }

        if (txtMedicineQty != null && !"".equals(txtMedicineQty)) {
            medicinePrivate.setRealQty(txtMedicineQty);
        }
        //如果医生修改了药品的规格(这里不知道需不需要实时保存到医生私人库中)
        if (standard != null && !"".equals(standard)) {
            medicinePrivate.setStandard(standard);
        } else {
            //如果standard为空时，即该医生私人库中没有保存，这时候从其他医生私人库中找到相同药品保存最多的那个standard值
            Pageable pageable = new PageRequest(0, 1);
            List<MedicinePrivate> medRateList = medicinePrivateService.findStandardByMoreUser(medicinePrivate
                    .getMedicine().getId(), pageable);
            if (medRateList.size() > 0) {
                standard = medRateList.get(0).getStandard();
                model.addAttribute("standard", standard);
            } else {
                model.addAttribute("rate", standard);
            }
        }

        Doctor doctor = SecuritySupport.getDoctor();
        MedicinePrivate tmpMedicinePrivate = medicinePrivate;
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            Doctor parentDoctor = doctorService.getDoctor(doctor.getPrimaryDoctorId());
            List<MedicinePrivate> medicinePrivateList = medicinePrivateService.findByDoctorAndMedicine(parentDoctor, medicinePrivate.getMedicine());
            if (medicinePrivateList.size() > 0) {
                tmpMedicinePrivate = medicinePrivateList.get(0);
                tmpMedicinePrivate.setUseQty(medicinePrivate.getUseQty());
                //medicinePrivate = medicinePrivate.medPriveateToMedPriveate(tmpMedicinePrivate);

            }
        }
        if (tmpMedicinePrivate.getHaveManager() == MedicinePrivate.HaveManager.YES) {
            medicinePrivate.setHaveManager(MedicinePrivate.HaveManager.YES);
            List<EntityTmpCloumsVal> entityTmpCloumsValList = iaiLossDetailService.getIaiLossDetilStockByMedPaivate(tmpMedicinePrivate);

            Double kc = entityTmpCloumsValList.stream().mapToDouble(EntityTmpCloumsVal::getTotlenSize).sum();
            final Date[] date = {new Date()};
            final boolean[] isHave = {false};
            entityTmpCloumsValList.forEach(
                    entityTmpCloumsVal -> {
                        if (entityTmpCloumsVal.getTotlenSize() > 0 && entityTmpCloumsVal.getValidityDate().getTime() >= date[0].getTime()) {
                            model.addAttribute("medicine_yxrq", entityTmpCloumsVal.getValidityDate());
                            isHave[0] = true;
                        }
                    }
            );
            if (!isHave[0]) {
                if (entityTmpCloumsValList.size() > 0) {
                    model.addAttribute("medicine_yxrq", entityTmpCloumsValList.get(entityTmpCloumsValList.size() - 1).getValidityDate());
                }
                model.addAttribute("medicine_guoQi", true);
            }
            model.addAttribute("medicine_kc", kc);
        }


        if (medicinePrivate.getPrice() == null) {
            medicinePrivate.setPrice(0D);
        }

        //医生开药习惯保存
        //保存最近使用口服
        /*if (StringUtils.isNotEmpty(selectMedUseModType) && medicinePrivate.getUseMode() != selectMedUseModType) {
            medicinePrivate.setUseMode(selectMedUseModType);
            medicinePrivateService.save(medicinePrivate);
        }*/
        MedicineContainer mc = new MedicineContainer();
        if (null != txtMedicineQty) {
            mc.setQty(new Double(txtMedicineQty));
        }
        if (null != selectMedUseModType) {
            mc.setUseMode(selectMedUseModType);
        }


        //保存医生习惯
        medicinePrivateService.saveDoctorHabit(medicinePrivate, mc);


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
        //standard的参考值，来源于其他医生私人库中，使用次数最多的那个值。
        model.addAttribute("standard", standard);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("isShowWindow", isShowWindow);


        /*判断是否是药品添加还是修改*/
        model.addAttribute("handleType", handleType);

        return "fragment/medicineSelect";
        //return "";
    }


    //西药列表加载
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

        try {
            model.addAttribute("medicinePage",
                    medicineService.listDoctorMedTag(pageable, SecuritySupport.getDoctor(), Medicine.Type.Western,
                            name, diagonsisName));
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            model.addAttribute("medicinePage",
                    medicineService.listDoctorMedTag(pageable, SecuritySupport.getDoctor(), Medicine.Type.Western,
                            name, diagonsisName));
        }
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        return "fragment/westernMedicinePage";
    }

    @RequestMapping(value = "/fragment/medicines/Western", method = RequestMethod.POST)
    @ResponseBody
    public Page<Medicine> westernPost(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "diagonsisName", required = false) String diagonsisName,
                                      Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, 10, sort);
        return medicineService.listDoctorMedTag(pageable, SecuritySupport.getDoctor(), Medicine.Type.Western, name,
                diagonsisName);
    }

    @RequestMapping(value = "/fragment/otherMedicines/Western", method = RequestMethod.GET)
    public String weOther(@RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
        model.addAttribute("medicinePage",
                medicineService.pageOtherTag(pageable, SecuritySupport.getDoctor(), Medicine.Type.Western, name));
        return "fragment/weOtherMedicinePage";
    }

    @RequestMapping(value = "/fragment/medicines/Chinese", method = RequestMethod.GET)
    public String chinese(@RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "name", required = false) String name, Model model,
                          @RequestParam(value = "diagonsisName", required = false) String diagonsisName) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, 20);
        try {
            model.addAttribute("medicinePage",
                    medicineService.listDoctorMedTag(pageable, SecuritySupport.getDoctor(), Medicine.Type.Chinese,
                            name, diagonsisName));
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            model.addAttribute("medicinePage",
                    medicineService.listDoctorMedTag(pageable, SecuritySupport.getDoctor(), Medicine.Type.Chinese,
                            name, diagonsisName));
        }
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        return "fragment/chineseMedicinePage";
    }

    @RequestMapping(value = "/fragment/medicines/Chinese", method = RequestMethod.POST)
    @ResponseBody
    public Page<Medicine> chinesePost(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "name", required = false) String name, Model model,
                                      @RequestParam(value = "diagonsisName", required = false) String diagonsisName) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, 20);
        return medicineService.listDoctorMedTag(pageable, SecuritySupport.getDoctor(), Medicine.Type.Chinese, name,
                diagonsisName);
    }

    @RequestMapping(value = "/fragment/otherMedicines/Chinese", method = RequestMethod.GET)
    public String chOther(@RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "name", required = false) String name, Model model) {
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
        model.addAttribute("medicinePage",
                medicineService.pageOtherTag(pageable, SecuritySupport.getDoctor(), Medicine.Type.Chinese, name));
        return "fragment/chOtherMedicinePage";
    }

    @RequestMapping(value = "/fragment/medicines2/{type}", method = RequestMethod.GET)
    public String medicines(@PathVariable Medicine.Type type,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "name", required = false) String name,
                            Model model) {
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
        model.addAttribute("type", type);
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        model.addAttribute("medicinePage",
                medicineService.pageDoctorTag(pageable, SecuritySupport.getDoctor(), type, name));
        return "fragment/medicinePage";
    }

    @RequestMapping(value = "/fragment/otherMedicines2/{type}", method = RequestMethod.GET)
    public String otherMedicines(@PathVariable Medicine.Type type,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "name", required = false) String name,
                                 Model model) {
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
        model.addAttribute("type", type);
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        model.addAttribute("medicinePage",
                medicineService.pageOtherTag(pageable, SecuritySupport.getDoctor(), type, name));
        return "fragment/medicineOtherPage";
    }


    @RequestMapping(value = "/fragment/otherMedicines", method = RequestMethod.GET)
    public String queryOther(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                             @RequestParam(value = "name", required = false) String name,
                             Model model) {
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        model.addAttribute("medicinePage", medicineService.pageOtherTag(pageable, SecuritySupport.getDoctor(), name));
        return "fragment/medicineListSelect";
    }


    @RequestMapping(value = "/fragment/configMedicineTags/{type}", method = RequestMethod.GET)
    public String configMedicineTags(@PathVariable Medicine.Type type,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "name", required = false) String name,
                                     Model model) {
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
        model.addAttribute("type", type);
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        model.addAttribute("medicinePage",
                medicineService.pageDoctorTag(pageable, SecuritySupport.getDoctor(), type, name));
        return "fragment/configMedicineSelect";
    }

    @RequestMapping(value = "/fragment/configMedicineOtherTags/{type}", method = RequestMethod.GET)
    public String configMedicineOtherTags(@PathVariable Medicine.Type type,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "name", required = false) String name,
                                          Model model) {
        Pageable pageable = new PageRequest(page, Constants.TAG_PAGE_SIZE);
        model.addAttribute("type", type);
        if (StringUtils.isNotEmpty(name)) {
            model.addAttribute("name", name);
        }
        model.addAttribute("medicinePage",
                medicineService.pageOtherTag(pageable, SecuritySupport.getDoctor(), type, name));
        return "fragment/configMedicineOtherSelect";
    }

    @RequestMapping(value = "/medicine/updateUsageFlag/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUsage(@PathVariable Long id, boolean usageFlag) {
        try {
            MedicinePrivate medicinePrivate = medicinePrivateService.getMedPrivate(id);
            if (medicinePrivate != null) {
                medicinePrivate.setUsageFlag(usageFlag);
                medicinePrivateService.update(medicinePrivate);
            }
            return Result.ok();
        } catch (Exception e) {
            logger.error("updateUsageFlag error:{}", e.getMessage());
            return Result.fail("保存药品UsageFlag出错！");
        }
    }

    @RequestMapping(value = "/fragment/myMedicine/{medicineType}", method = RequestMethod.GET)
    public String myMedicine(@PathVariable Medicine.Type medicineType,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             Model model) {
        Pageable pageable = new PageRequest(page, Constants.DIATELS_PAGE_SIZE);
        List<String> categories;
        if (medicineType == Medicine.Type.Western) {
            categories = WESTERN_MEDICINE_CATE_LIST;
        } else {
            categories = CHINESE_MEDICINE_CATE_LIST;
        }
        model.addAttribute("categories", categories);
        model.addAttribute("medicinePage", medicineService.pageByDoctorAndCategory(pageable,
                SecuritySupport.getDoctor(), medicineType, categories.get(0)));
        model.addAttribute("medicineType", medicineType);
        return "fragment/myMedicineSelect";
    }

    @RequestMapping(value = "/fragment/myMedicine", method = RequestMethod.GET)
    public String getMyMedicine(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                @RequestParam(value = "category") String category,
                                @RequestParam(value = "medicineType") Medicine.Type medicineType,
                                Model model) {
        Pageable pageable = new PageRequest(page, Constants.DIATELS_PAGE_SIZE);
        Page<MedicinePrivate> mpPage = medicinePrivateService.getByTypeAndCategoryAndDoctor(pageable,
                SecuritySupport.getDoctor(), medicineType, category);
        Map<MedicinePrivate, Long> mpMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(mpPage.getContent())) {
            //得到具有排序的私有药品
            mpMap = medicineCountService.getDoctorOfCountSize(SecuritySupport.getDoctor(), mpPage.getContent());
            //根据排序Map转List
            List<MedicinePrivate> mpList = mpMap.entrySet().stream().map(mp -> mp.getKey()).collect(Collectors.toList());
            model.addAttribute("mpList", mpList);
            //System.out.println(mpMap);
        }
        model.addAttribute("medicinePage", mpMap);
        /*model.addAttribute("medicinePage", medicineService.pageByDoctorAndCategory(pageable,
                SecuritySupport.getDoctor(), medicineType, category));*/
        return "fragment/myMedicinePage";
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
    public String getMyMedicineUsage(@PathVariable Long id,  @RequestParam(value = "type", required = false, defaultValue = "edit") String type,
                                     @RequestParam(value = "groupId", required = false, defaultValue = "10") String groupId,
                                     @RequestParam(value = "txtMedicineQty", required = false) String txtMedicineQty,
                                     @RequestParam(value = "selectMedUseModType", required = false) String selectMedUseModType,
                                     @RequestParam(value = "specialInstructions", required = false) String specialInstructions,
                                     @RequestParam(value = "standard", required = false) String standard,
                                     @RequestParam(value = "lastSelectXwOrFenBao", required = false) String lastSelectXwOrFenBao,
                                     @RequestParam(value = "isShowWindow", required = false) Boolean isShowWindow,
                                     @RequestParam(value = "handleType", required = false, defaultValue = "Medicine_Add") String handleType
            , Model model) {

        MedicinePrivate medPrivate = medicinePrivateService.getMedPrivate(id);
        if (null != medPrivate) {
            model.addAttribute("medicine", medPrivate.getMedicine());
            model.addAttribute("medPrivate", medPrivate);
            model.addAttribute("medicineUnits", MEDICINE_UNITS);
            model.addAttribute("openType", type);
            model.addAttribute("groupId", groupId);
            model.addAttribute("txtMedicineQty", txtMedicineQty);
            model.addAttribute("selectMedUseModType", selectMedUseModType);
            model.addAttribute("lastSelectXwOrFenBao", lastSelectXwOrFenBao);
            model.addAttribute("specialInstructions", specialInstructions);
            model.addAttribute("handleType",handleType);
            //standard的参考值，来源于其他医生私人库中，使用次数最多的那个值。
            model.addAttribute("standard", standard);
            model.addAttribute("doctor", SecuritySupport.getDoctor());
            model.addAttribute("isShowWindow", isShowWindow);
        } else {
            MedicinePrivate medicinePrivate1 = new MedicinePrivate();
            medicinePrivate1 = medicinePrivate1.medToMedPriveate(medicineService.getMedicine(id));
            medicinePrivate1.setDoctor(SecuritySupport.getDoctor());
            medicinePrivateService.save(medicinePrivate1);
            model.addAttribute("medPrivate", medicinePrivate1);
        }
        /*没改成私有药品之前的代码
        model.addAttribute("medicine", medicineService.getMedicine(id));
        List<MedicinePrivate> medPrivateList = medicinePrivateService.findByDoctorAndMedicine(SecuritySupport.getDoctor(), medicineService.getMedicine(id));
        if (Collections3.isNotEmpty(medPrivateList)) {
            model.addAttribute("medPrivate", medPrivateList.get(0));
        } else {
            MedicinePrivate medicinePrivate1 = new MedicinePrivate();
            medicinePrivate1 = medicinePrivate1.medToMedPriveate(medicineService.getMedicine(id));
            medicinePrivate1.setDoctor(SecuritySupport.getDoctor());
            medicinePrivateService.save(medicinePrivate1);
            model.addAttribute("medPrivate", medicinePrivate1);
        }*/

        return "fragment/myMedicineUsage";
    }

    // TODO 购买药品记录
    /*@RequestMapping(value = "newPage/sunPage/medicBuyDetails")
    public String medicBuyDetails(@RequestParam(value = "patientId", required = false) Long patientId,
                                  @RequestParam(value = "startTime", required = false) Date startTime,
                                  @RequestParam(value = "endTime", required = false) Date endTime,
                                  Model model) {
        Doctor doctor = SecuritySupport.getDoctor();
        List<Retail> retailList = retailService.getByPatientStartAndEndTime(
                patientService.getPatientById(patientId), startTime, endTime, doctorService.getPrimaryDoctor(doctor));

        model.addAttribute("chineseMap", retailService.getChineseMedList(retailList));
        model.addAttribute("westernMap", retailService.getWesternMedList(retailList));
        model.addAttribute("TypeMap", pharmacistService.personTypeNameMap());
        model.addAttribute("retailList", retailList);
        model.addAttribute("patientId", patientId);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        return "newPage/sunPage/medicBuyDetails";
    }*/

    @Autowired
    private MedicineCountCache medicineCountCache;

    @RequestMapping(value = "/fragment/test", method = RequestMethod.GET)
    @ResponseBody
    public Result test() {
        MedicineCount count1 = new MedicineCount();
        count1.setId(12L);
        count1.setCountSize(3);
        count1.setDiagosisName("深大");
        //count.setDoctor(SecuritySupport.getDoctor());
        count1.setMedicine(medicineService.getMedicine(1L));


        MedicineCount count2 = new MedicineCount();
        count2.setId(12L);
        count2.setCountSize(3);
        count2.setDiagosisName("深大");
        //count.setDoctor(SecuritySupport.getDoctor());
        count2.setMedicine(medicineService.getMedicine(1L));

        List<MedicineCount> countList = Lists.newArrayList();

        countList.add(count1);
        countList.add(count2);


        List<MedicineCount> countListTest = medicineCountCache.getRedisTemplate().opsForList().range("countList",0,1);

        if(countListTest == null || countListTest.size()<=0){
            medicineCountCache.getRedisTemplate().opsForList().rightPushAll("countList",count1,count2);
        }

        //Object countTest = medicineCountCache.getValue(count,MedicineCountCache.MedicineCount);

        if(countListTest == null){
            //System.out.println(count.getId());
        }

        return Result.ok();
    }


}