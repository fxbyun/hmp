package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.EmrExtCostService;
import com.qiaobei.hmp.modules.service.EmrJClassAdviceDictService;
import com.qiaobei.hmp.modules.service.EmrMedicineService;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.modules.support.DecimalCalculate;
import com.qiaobei.hmp.modules.support.FileUtilsServer;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.PharSecuritySupport;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.utils.Collections3;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdvertisingController extends ConstantsController {
    private static Logger logger = LoggerFactory.getLogger(AdvertisingController.class);
    @Resource
    private RegistrationService registrationService;
    @Resource
    private PatientService patientService;
    @Resource
    private EmrService emrService;
    @Resource
    private CardService cardService;
    @Resource
    private NationService nationService;
    @Resource
    private EmrJClassAdviceDictService emrJClassAdviceDictService;
    @Resource
    private EmrExtCostService emrExtCostService;
    //    @Resource
//    private ExamLabFileService examLabFileService;
    @Resource
    private EmrMedicineService emrMedicineService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private PharmacistService pharmacistService;

    @ModelAttribute("medicineUseModes")
    private List<String> medicineUseModes(HttpServletRequest request) {
        return MEDICINE_USE_MODE_LIST;
    }

    @ModelAttribute("loginUser")
    private Pharmacist loginUser(HttpServletRequest request) {
        return PharSecuritySupport.getPharmacist();
    }

    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }

    //TODO 首页 2016-10-10 14:49:11
    @RequestMapping("/adv/index")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return "/login";
        }
        if (PharSecuritySupport.getDoctor() == null) {
            return "/login";
        }
        model.addAttribute("doctor", PharSecuritySupport.getDoctor());
        return "/advertising/index";
    }

    /**
     * 轮询 获取排队 信息
     */
    @RequestMapping(value = "/advertising/getAdvings", method = RequestMethod.POST)
    @ResponseBody
    public Result getAdvingStatus() {
        if (PharSecuritySupport.getDoctor() == null)
            return Result.fail();
        List<Registration> registrationList = registrationService.getRegistrationList(PharSecuritySupport.getDoctor().getId(), Registration.Status.Normal, Registration.QueueStatus.NotVisit);
        Registration oneReg = registrationService.getCallNameByDoctor(PharSecuritySupport.getDoctor());
        if (StringUtils.isNotEmpty(oneReg.getPatientName())) {
            if (!registrationList.stream().map(Registration::getPatientName).collect(Collectors.toList()).contains(oneReg.getPatientName())) {
                oneReg.setPatientName("");
                oneReg.setDoctorDeptName("");
            }
        }
        return new Result().setSuccess(true).setData(registrationList.size()).setMsg(oneReg.getPatientName()).setDeptName(oneReg.getDoctorDeptName());
    }

    /**
     * 轮询 获取排队 信息列表
     */
    @RequestMapping(value = "/advertising/getAdvingListInfo", method = RequestMethod.POST)
    @ResponseBody
    public List<Registration> getAdvingListInfo() {
        if (PharSecuritySupport.getDoctor() == null)
            return Lists.newArrayList();
        return registrationService.getRegistrationList(PharSecuritySupport.getDoctor().getId(), Registration.Status.Normal, Registration.QueueStatus.NotVisit);
    }

    /**
     * 获取微信未签到 信息列表
     */
    @RequestMapping(value = "/advertising/getAdvingWxSinInListInfo", method = RequestMethod.GET)
    public String getAdvingWxSinInListInfo(@RequestParam("pageNow") int pageNow, Model model) {
        Doctor doctor = PharSecuritySupport.getDoctor();
        if (doctor == null) {
            return "/login";
        }
        model.addAttribute("list", registrationService.getRegistrationWxSinInList(doctor.getId(), Registration.Status.Normal, pageNow));
        return "/advertising/wxNotSingInList";
    }

    /**
     * 微信签到
     */
    @RequestMapping(value = "/advertising/setWxSingIn", method = RequestMethod.POST)
    @ResponseBody
    public Result setWxSingIn(@RequestParam("id") Long id) {
        try {
            registrationService.setWxSingInTrue(id);
        } catch (Exception e) {
            return new Result().setSuccess(false).setMsg(e.getMessage());
        }
        return new Result().setSuccess(true);
    }

    /**
     * 挂号页面
     */
    @RequestMapping("/advertising/registration")
    public String registration(@RequestParam(value = "doctorId", required = false) Long doctorId, @RequestParam(value = "msg", required = false, defaultValue = "") String msg, Model
            model) {
        if ("undefined".equals(msg)) {
            msg = "";
        }
        Doctor doctor = doctorService.getDoctor(doctorId);
        model.addAttribute("msg", msg);
        model.addAttribute("doctor", doctor);
        return "/advertising/registration";
    }


    /**
     * 获取和电话相关的患者的信息
     */
    @RequestMapping(value = "/patient/query", method = RequestMethod.GET)
    @ResponseBody
    public List<Patient> query(@RequestParam String key) {
        List<Patient> list = Lists.newArrayList();
        if (StringUtils.isNotEmpty(key))
            list = patientService.queryByMobile(key);
        return list;
    }

    /**
     * 进行挂号操作
     */
    @RequestMapping(value = "/advertising/registration/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam(required = false) String udid,
                       @RequestParam(required = false) String mobile,
                       @RequestParam(value = "doctorId") Long doctorId) {
        Patient patientByUid = patientService.getPatientByUdid(udid);
        if (patientByUid == null || patientByUid.getStatus() == StatusEntity.Status.Unactivated) {
            if (mobile == null || "".equals(mobile)) {
                if (udid != null) {
                    Card card = cardService.getCardByUdid(udid);
                    if (card == null) {
                        return Result.fail("无效卡号!");
                    } else {
                        return Result.fail("该患者未绑卡,请先进行绑卡操作!").setData(card);
                    }
                }

            }
        }

        Patient patient = null;
        Registration reg = new Registration();
        if (StringUtils.isNotEmpty(udid)) {
            patient = patientService.getPatientByUdid(udid);
            if (patient == null) {
                Card card = cardService.getCardByUdid(udid);
                if (card == null) {
                    return Result.fail("无效卡！");
                } else {
                    reg.setPatientName(card.getCardNo());
                    reg.setPatientUid(card.getCardNo());
                }
            } else {
                reg.setPatientName(patient.getName());
                reg.setPatientUid(patient.getUid());
            }
        }
        if (StringUtils.isNotEmpty(mobile)) {
            //List<Patient> ps = patientService.listPatientByMobile(mobile);
            patient = patientService.getPatientByUid(mobile);
            if (patient != null) {
                reg.setPatientName(patient.getName());
                reg.setPatientUid(patient.getUid());
            } else {
                Card card = cardService.getCardByNo(mobile);
                if (card == null) {
                    return Result.fail("无效卡号!");
                } else {
                    return Result.fail("该患者未绑卡,请先进行绑卡操作!").setData(card);
                }
            }
        }
        //Doctor doctor = PharSecuritySupport.getDoctor();
        Doctor doctor = doctorService.getDoctor(doctorId);
        //判断是否已经挂号
        //getRegistrationByPatientUidAndNameAndDocto
        List<Registration> registrationSa = registrationService.getByPatientAndDoctor(
                patient, doctor);
        if (registrationSa != null && registrationSa.size() > 0) {
            return Result.fail("您已经挂号了！如未显示，请刷新一下页面！");
        }

        reg.setDoctorId(doctor.getId());
        reg.setDoctorName(doctor.getName());
        reg.setCreateOn(new Date());
        reg.setStatus(Registration.Status.Normal);
        reg.setSequence(0);
        //设置排队状态
        reg.setQueueStatus(Registration.QueueStatus.NotVisit);
        //判断该医生是否是主治医生
        Doctor bossDoctor;
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            bossDoctor = doctorService.getDoctor(doctor.getPrimaryDoctorId());
        } else {
            bossDoctor = doctor;
        }

        List<Doctor> subDocList = doctorService.findSubDoctor(bossDoctor);
        List<Long> doctorIdList = Lists.newArrayList();
        subDocList.add(bossDoctor);
        subDocList.forEach(doctor1 -> doctorIdList.add(doctor1.getId()));

        //排队的顺序号码
        String queueNo = registrationService.getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum.LOCALE, doctorIdList);
        //reg.setNoNumber(registrationService.getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum.LOCALE));
        reg.setNoNumber(queueNo);
        reg.setDoctorDeptName(doctor.getDeptName());
        registrationService.save(reg);
        return Result.ok("挂号成功！");
    }


    /**
     * 绑卡操作保存
     */
    @RequestMapping(value = "/advertising/patient/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("patient") Patient patient,
                       @RequestParam(value = "doctorId") Long doctorId,
                       RedirectAttributes redirectAttributes) {
//        if (patient.getStatus() == StatusEntity.Status.Unactivated && !"否".equals(SecuritySupport.getDoctor()
//                .getAutoSendActivateMsg())) {
//            try {
//                SMSUtil.sendSMS(SMSUtil.ACTIVE_TEMPLATE, patient.getMobile(), SecuritySupport.getDoctor()
//                        .getOutpatientService() + "," + patient.getUid());
//            } catch (Exception e) {
//                logger.error("激活短信发送失败：" + e.getMessage());
//            }
//        }
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("msg", "信息成功保存");
        redirectAttributes.addFlashAttribute("udid", patient.getUdid());
        redirectAttributes.addFlashAttribute("doctorId", doctorId);
        return "redirect:/advertising/patientInfo?cardNo=" + patient.getUid() + "&doctorId=" + doctorId;
    }


    @RequestMapping("/advertising/patientInfo")
    public String patientInfo(@RequestParam("cardNo") String cardNo,
                              @RequestParam(value = "doctorId") Long doctorId,
                              Model model) {
        Card card = cardService.getCardByNo(cardNo);
        Patient patient = patientService.getPatientByUid(cardNo);
        if (card != null) {
            if (patient == null)
                patient = patientService.savePatient4Card(card);
            if (patient != null) {
                Doctor d = PharSecuritySupport.getDoctor();
                if (patient.getProvinceNo() == null && d != null) {
                    patient.setProvinceNo(d.getProvinceNo());
                    patient.setProvince(d.getProvince());
                    patient.setCityNo(d.getCityNo());
                    patient.setCity(d.getCity());
                    patient.setAreaNo(d.getAreaNo());
                    patient.setArea(d.getArea());
                }
                model.addAttribute("patient", patient);
                model.addAttribute("doctorId", doctorId);
                model.addAttribute("provinceList", nationService.listNation(0));
                model.addAttribute("cityList", nationService.listNation(patient.getProvinceNo()));
                model.addAttribute("areaList", nationService.listNation(patient.getCityNo()));
            }
        }
        return "/advertising/patientInfo";
    }


    //TODO 收费管理 2016-10-11 11:38:44
    @RequestMapping("/adv/chargeManage")
    public String chargeManage(Model model,
                               @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                               @RequestParam(value = "status", required = false) StatusEntity.Status status,
                               @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = Constants
                                       .PATTERN_DATE) Date startDate,
                               @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = Constants
                                       .PATTERN_DATE) Date endDate,
                               @ModelAttribute("cardPwd") String cardPwd,
                               @ModelAttribute("name") String name,
                               @ModelAttribute("phone") String phone

    ) {

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("genderMap", GENDER_MAP);
        Doctor doctor = PharSecuritySupport.getDoctor();
        if (PharSecuritySupport.getDoctor() == null)
            return "/login";
        Page<Emr> emrList = emrService.getEmrListByDoctor(doctor, status, new PageRequest(page, size, Sort.Direction.DESC, "updateOn", "id"), startDate, endDate, cardPwd, name, phone);
        //护士Map
        Map<Emr, Pharmacist> nurseMap = Maps.newHashMap();
        emrList.getContent().forEach(emr -> {
            if (null != emr.getCashierId()) {
                nurseMap.put(emr, pharmacistService.getPharmacistById(emr.getCashierId()));
            } else {
                nurseMap.put(emr, null);
            }
        });
        model.addAttribute("nurseMap", nurseMap);

        List<Doctor> searchDoctorList = Lists.newArrayList();
        //获取该诊所的所有医生
        Optional.ofNullable(PharSecuritySupport.getDoctor()).ifPresent(doc -> {
            if (doc.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                doc = doctorService.getDoctor(doc.getPrimaryDoctorId());
            }
            searchDoctorList.addAll(doctorService.findSubDoctor(doc));
            searchDoctorList.add(doc);
        });


        /*if (startDate == null) {
            startDate = DateUtils.getDayStartTime();
        }
        if (endDate == null) {
            endDate = DateUtils.getDayEndMaxTime();
        } else {
            endDate = DateUtils.getDayEndMaxTime(endDate);
        }*/

        List<Emr> oneDayEmrList = emrService.findAllByDoctorListAndTimeAndShouYinId(searchDoctorList, DateUtils.getDayStartTime(), DateUtils.getDayEndMaxTime(), null, null);
        //应收总额
        Double countCost = oneDayEmrList.stream().mapToDouble(Emr::getCost).sum();
        model.addAttribute("countCost", DecimalCalculate.roundDown(countCost, 2));
        //实收总额
        Double countRealCost = oneDayEmrList.stream().mapToDouble(Emr::getRealCost).sum();
        System.out.println(countRealCost);
        model.addAttribute("countRealCost", DecimalCalculate.roundDown(countRealCost, 2));

        //挂起金额
        Double HANG_UP = oneDayEmrList.stream().filter(
                emr -> emr.getStatus() == StatusEntity.Status.HANG_UP
        ).mapToDouble(Emr::getCost).sum();
        model.addAttribute("HANG_UP", DecimalCalculate.roundDown(HANG_UP, 2));


        model.addAttribute("emrListPage", emrList);
        if (status == null) {
            //待收费列表
            return "/advertising/chargeManage";
        } else if (status == StatusEntity.Status.CHARGE) {
            model.addAttribute("phar", PharSecuritySupport.getPharmacist());
            //已经收费列表
            return "/advertising/alreadyChargeList";
        } else if (status == StatusEntity.Status.HANG_UP) {
            //挂起列表
            return "/advertising/arrearsList";
        } else if (status == StatusEntity.Status.Back_Money_Success) {
            return "/advertising/refundList";
        }
        //收费列表
        return "/advertising/chargeManage";
    }

    //TODO 收费明细列表 2016-10-11 14:58:35+
    @RequestMapping("/adv/bombBox/chargeDetails")
    public String chargeDetails(Model model,
                                @RequestParam("id") Long id) {
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("emr", emrService.getEmr(id));
        Pharmacist pharmacist = PharSecuritySupport.getPharmacist();
        Doctor doctor = PharSecuritySupport.getDoctor();

        if (null != pharmacist && null != doctor) {

            if (doctor.getAllowNurseUpdatePrice() == null) {
                doctor.setAllowNurseUpdatePrice(false);
                doctorService.saveDoctor(doctor);
            }

            if (doctor.getAllowSubDoctorUpdatePrice() == null) {
                doctor.setAllowSubDoctorUpdatePrice(false);
                doctorService.saveDoctor(doctor);
            }

            if ((null == pharmacist.getIsChiefNurse() || !pharmacist.getIsChiefNurse().getName().equalsIgnoreCase("护士长"))
                    &&
                    !(doctor.getAllowNurseUpdatePrice() || null == doctor.getAllowNurseUpdatePrice())) {
                model.addAttribute("isAllowUpdatePrice", false);
            } else {
                model.addAttribute("isAllowUpdatePrice", true);
            }
        }

        return "/advertising/bombBox/chargeDetails";
    }


    //TODO  显示收费明细 2016-10-11 14:58:35
    @RequestMapping("/adv/bombBox/showInfo")
    public String showInfo(Model model,
                           @RequestParam(value = "id") Long id,
                           @RequestParam(value = "realCost") Double realCost) {
        Emr emr = emrService.getEmr(id);
        model.addAttribute("realCost", realCost);
        model.addAttribute("emr", emr);
        model.addAttribute("genderMap", GENDER_MAP);
        return "/advertising/bombBox/showInfo";
    }

    //TODO  修改状态 为 挂起或者收费
    @RequestMapping("/adv/bombBox/updateStatus")
    @ResponseBody
    public Result updateStatus(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "realCost", required = false) Double realCost,
            @RequestParam(value = "status") StatusEntity.Status status) {
        Emr emr = emrService.getEmr(id);
        emr.setRealCost(realCost);
        if (StatusEntity.Status.HANG_UP.equals(emr.getStatus())
                && StatusEntity.Status.CHARGE.equals(status)
                && DateUtils.getDayStartTime().getTime() > emr.getCreateOn().getTime()
                ) {
            //如果处方签为挂起状态,并且现在要进行收费的话
            //那么就该改为当前病历创建时间为当前时刻
            // 解决 已经挂起并且不是当天的病历单，重新点击收费时，收费金额应该计入点击收费当天的财务总额中 的问题
            emr.setCreateOn(new Date());
        }
        emr.setStatus(status);
        emr.setUpdateOn(new Date());

        //如果是发药、退药
        if (status == StatusEntity.Status.Have_Dispensing_Back) {
            emr.setDrugRefundId(PharSecuritySupport.getPharmacist().getId());
            emr.setDrugRefundName(PharSecuritySupport.getPharmacist().getName());
        }
        //设置收费退费人
        if (status == StatusEntity.Status.CHARGE || status == StatusEntity.Status.HANG_UP || status == StatusEntity.Status.Have_Dispensing) {
            emr.setCashierId(PharSecuritySupport.getPharmacist().getId());
            emr.setCashierName(PharSecuritySupport.getPharmacist().getName());
            emr.getEmrMedicineList().forEach(
                    emrMedicine -> {
                        if (status == StatusEntity.Status.HANG_UP && emrMedicine.getStatus() == StatusEntity.Status.Normal) {
                            emrMedicine.setStatus(status);
                            emrMedicineService.save(emrMedicine);
                        }
                        if (status == StatusEntity.Status.CHARGE || status == StatusEntity.Status.Have_Dispensing) {
                            emrMedicine.setStatus(status);
                            emrMedicineService.save(emrMedicine);
                        }
                    }
            );
            List<EmrJClassAdviceDict> jClassAdviceDictList = emr.getEmrJClassAdviceDicts();
            for (int i = 0; i < jClassAdviceDictList.size(); i++) {
                EmrJClassAdviceDict emrJClassAdviceDict = jClassAdviceDictList.get(i);
                emrJClassAdviceDict.setStatus(status);
                emrJClassAdviceDictService.save(emrJClassAdviceDict);
            }
//            jClassAdviceDictList.forEach(
//                    emrJClassAdviceDict -> {
//                        System.out.println(emrJClassAdviceDict.getAdviceName());
//
//                    }
//            );
            if (emr.getEmrExtCostList() != null) {
                emr.getEmrExtCostList().forEach(emrExtCost -> {
                    emrExtCost.setStatus(status);
                    emrExtCostService.save(emrExtCost);
                });
            }
        }

        emrService.updateEmr(emr);
        return Result.ok();
    }

    //TODO  护士修改单个药品总价
    @RequestMapping("/adv/bombBox/updatePrice")
    @ResponseBody
    public Result updatePrice(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "oldPrice", required = false) Double oldPrice
    ) {
        try {
            EmrMedicine emrMedicine = emrMedicineService.findById(id);
            emrMedicine.setPrice(price);
            emrMedicineService.save(emrMedicine);
            Emr emr = emrMedicine.getEmr();
            if (emr.getRealCost() != null && emr.getRealCost() != 0) {
                emr.setRealCost(emr.getRealCost() - oldPrice + price);
            } else {
                emr.setRealCost(emr.getCost() - oldPrice + price);
            }

            emrService.save(emr);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
        return Result.ok();
    }


    //TODO  退费信息 2016-10-11 14:58:35
    @RequestMapping("/adv/bombBox/alreadyInfo")
    public String alreadyInfo(
            @RequestParam(value = "emrId") Long emrId,
            Model model
    ) {
        model.addAttribute("emr", emrService.getEmr(emrId));

        return "/advertising/bombBox/alreadyInfo";
    }

    //TODO  退费信息 2016-10-11 14:58:35
    @RequestMapping("/adv/bombBox/backMoneyDetil")
    @ResponseBody
    public Result backMoneyDetil(
            @RequestParam(value = "emrId") Long emrId,
            @RequestParam(value = "backMoney") Double backMoney
    ) {
        Emr emr = emrService.getEmr(emrId);
        emr.setUpdateOn(new Date());
        emr.setBeforeBackMonryRealCost(emr.getRealCost());
        emr.setRealCost(emr.getRealCost() - backMoney);
        emr.setStatus(StatusEntity.Status.Back_Money_Success);
        emrService.save(emr);
        return Result.ok();
    }


    //TODO  欠费信息 2016-10-11 14:58:35
    @RequestMapping("/adv/bombBox/arrearsInfo")
    public String arrearsInfo() {

        return "/advertising/bombBox/arrearsInfo";
    }

    //TODO  药房管理 2016-10-12 14:29:30
    @RequestMapping("/adv/pharmacyManage")
    public String pharmacyManage(Model model,
                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                 @RequestParam(value = "status", required = false) StatusEntity.Status status,
                                 @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = Constants
                                         .PATTERN_DATE) Date startDate,
                                 @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = Constants
                                         .PATTERN_DATE) Date endDate,
                                 @ModelAttribute("cardPwd") String cardPwd,
                                 @ModelAttribute("name") String name,
                                 @ModelAttribute("phone") String phone) {
        if (status == null)
            status = StatusEntity.Status.CHARGE;
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("genderMap", GENDER_MAP);

        if (PharSecuritySupport.getDoctor() == null) {
            return "/login";
        }
        Page<Emr> emrList = emrService.getEmrListByDoctor(
                PharSecuritySupport.getDoctor(),
                status,
                new PageRequest(page, size, Sort.Direction.DESC, "updateOn", "id"),
                startDate, endDate, cardPwd, name, phone
        );
        model.addAttribute("emrListPage", emrList);
        if (status == StatusEntity.Status.CHARGE) {
            return "/advertising/pharmacyManage";
        } else if (status == StatusEntity.Status.Have_Dispensing) {
            return "/advertising/hasMedicineList";
        } else if (status == StatusEntity.Status.Have_Dispensing_Back) {
            return "/advertising/retiredMedicineList";
        }

        return "/advertising/pharmacyManage";
    }

    //TODO 药品清单 2016-10-12 14:44:59
    @RequestMapping("/adv/bombBox/pharmacyDetails")
    public String pharmacyDetails(Model model,
                                  @RequestParam("id") Long id) {

        model.addAttribute("emr", emrService.getEmr(id));
        model.addAttribute("genderMap", GENDER_MAP);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "/advertising/bombBox/pharmacyDetails";
    }

    @Transient
    @RequestMapping("/adv/hasMedicineList/backMed")
    @ResponseBody
    public Result backMed(
            @RequestParam(value = "ids", required = false) List<Long> longList,
            @RequestParam("id") Long id,
            @RequestParam("backMedRemarks") String backMedRemarks
    ) {
        Emr emr = emrService.getEmr(id);
        emr.setStatus(StatusEntity.Status.Have_Dispensing_Back);
        emr.setBackMedRemarks(backMedRemarks);

        //设置退药人
        emr.setDrugRefundName(PharSecuritySupport.getPharmacist().getName());
        emr.setDrugRefundId(PharSecuritySupport.getPharmacist().getId());


        //修改emrmed 表字段 status 为 Have_Dispensing_Back
        // 并且修改 iailossdatiel status 为 Have_Dispensing_Back
        emrService.backMedByEmrMedIds(longList);
//        emrService.updateEmr(emr
        return Result.ok();
    }

    //TODO  已发药列表 2016-10-12 14:29:30
    @RequestMapping("/adv/hasMedicineList")
    public String hasMedicineList() {

        return "/advertising/hasMedicineList";
    }

    //TODO 已发 药品清单 2016-10-12 14:44:59
    @RequestMapping("/adv/bombBox/hasMedicineDetails")
    public String hasMedicineDetails(Model model,
                                     @RequestParam("id") Long id) {
        model.addAttribute("emr", emrService.getEmr(id));
        model.addAttribute("genderMap", GENDER_MAP);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        return "/advertising/bombBox/hasMedicineDetails";
    }


    //TODO 已退药品详情 2016-10-12 14:44:59
    @RequestMapping("/adv/bombBox/retiredMedicineDetails")
    public String retiredMedicineDetails(
            @RequestParam(value = "emrId") Long emrId,
            Model model
    ) {
        model.addAttribute("emr", emrService.getEmr(emrId));
        model.addAttribute("genderMap", GENDER_MAP);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);

        return "/advertising/bombBox/retiredMedicineDetails";
    }

    //TODO 检查管理  2016-10-20 15:32:20
    @RequestMapping("/adv/inspectManage")
    public String inspectManage(Model model,
                                @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                @RequestParam(value = "status", required = false) StatusEntity.Status status,
                                @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                @ModelAttribute("cardPwd") String cardPwd,
                                @ModelAttribute("name") String name,
                                @ModelAttribute("phone") String phone
    ) {

        if (PharSecuritySupport.getDoctor() == null) {
            return "/login";
        }
        Page<EmrJClassAdviceDict> emrJClassAdviceDictPage =
                emrJClassAdviceDictService.findByDoctorAndStatusCardPwdNamePhoe(
                        PharSecuritySupport.getDoctor(),
                        status,
                        cardPwd,
                        name,
                        phone,
                        new PageRequest(page, size));
        model.addAttribute("emrJClassAdviceDictPage", emrJClassAdviceDictPage);
        model.addAttribute("status", status);
        if (status == StatusEntity.Status.Have_Exam_Or_Lab)
            return "/advertising/hasCompletedList";

        return "/advertising/inspectManage";
    }

    //TODO 检查结果  2016-10-20 15:32:20
    @RequestMapping("/adv/bombBox/inspectionResult")
    public String inspectionResult(
            @RequestParam("id") Long id,
            Model model
    ) {
        EmrJClassAdviceDict emrJClassAdviceDict = emrJClassAdviceDictService.findById(id);
        model.addAttribute("emrJClass", emrJClassAdviceDict);
        model.addAttribute("doctor", emrJClassAdviceDict.getEmr().getDoctor());
        return "/advertising/bombBox/inspectionResult";
    }

    @RequestMapping("/adv/bombBox/updateInspectionResult")
    public String updateInspectionResult(
            @RequestParam("id") Long id,
            @RequestParam(value = "resultInfo", required = false) String resultInfo,
            @RequestParam(value = "files", required = false) List<MultipartFile> fileList,
            Model model
    ) {
        EmrJClassAdviceDict emrJClassAdviceDict = emrJClassAdviceDictService.findById(id);
        emrJClassAdviceDict.setResultInfo(resultInfo);
        if (Collections3.isNotEmpty(fileList) && fileList.size() >= 1) {
            emrJClassAdviceDict.getExamLabFileList().clear();
            fileList.forEach(file -> {
                ExamLabFile examLabFile = new ExamLabFile();
                if (file.getOriginalFilename().indexOf("jpg") > 0) {
                    examLabFile.setFileName(System.currentTimeMillis() + ".jpg");
                    examLabFile.setTypes(ExamLabFile.Exam_Lab_File_Type.Jpg);
                } else if (file.getOriginalFilename().indexOf("png") > 0) {
                    examLabFile.setFileName(System.currentTimeMillis() + ".png");
                    examLabFile.setTypes(ExamLabFile.Exam_Lab_File_Type.Png);
                } else if (file.getOriginalFilename().indexOf("pdf") > 0) {
                    examLabFile.setFileName(System.currentTimeMillis() + ".pdf");
                    examLabFile.setTypes(ExamLabFile.Exam_Lab_File_Type.Pdf);
                }
                try {
                    examLabFile.setFileData(IOUtils.toByteArray(file.getInputStream()));
                    examLabFile.setEmrJClassAdviceDict(emrJClassAdviceDict);
//                    examLabFileService.save(examLabFile);
                    emrJClassAdviceDict.getExamLabFileList().add(examLabFile);
                    emrJClassAdviceDict.setStatus(StatusEntity.Status.Have_Exam_Or_Lab);
                    emrJClassAdviceDict.setUpdateOn(new Date());
                    emrJClassAdviceDictService.save(emrJClassAdviceDict);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("上传检查/检验文件报错");
                }
            });
        }

        Doctor doctor = PharSecuritySupport.getDoctor();

        if (doctor != null) {
            new FileUtilsServer().outPutExamLabFileToDisk(emrJClassAdviceDict.getExamLabFileList(), doctor);
        }

        model.addAttribute("emrJClass", emrJClassAdviceDict);
        model.addAttribute("doctor", doctor);
        model.addAttribute("msg", "OK");

        return "/advertising/bombBox/inspectionResult";
    }


    //TODO 已完成 检查结果  2016-10-20 15:32:20
    @RequestMapping("/adv/bombBox/hasCompletedResult")
    public String hasCompletedResult(
            @RequestParam("id") Long id,
            Model model
    ) {
        Doctor doctor = PharSecuritySupport.getDoctor();
        EmrJClassAdviceDict emrJClassAdviceDict = emrJClassAdviceDictService.findById(id);
        if (doctor != null) {
            new FileUtilsServer().outPutExamLabFileToDisk(emrJClassAdviceDict.getExamLabFileList(), doctor);
        }
        model.addAttribute("emrJClass", emrJClassAdviceDict);
        model.addAttribute("doctor", doctor);
        model.addAttribute("msg", "2");
        return "/advertising/bombBox/inspectionResult";
    }


    //TODO  选择医生 2016-11-10 16:48:38
    @RequestMapping("/adv/bombBox/changeDoctor")
    public String changeDoctor(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        Doctor doctor = PharSecuritySupport.getDoctor();
        if (doctor == null) {
            return "login";
        }
        System.out.println(doctor.getName());

        Page<Doctor> doctorPage = doctorService.findDocAndSubDoctorPage(doctor.getId(), new PageRequest(page, 3, Sort.Direction.ASC, "id"));
        Map<Long, String> docHeadUrl = Maps.newHashMap();

        doctorPage.getContent().forEach(doctor1 -> {
            //医生头像
            String headUlr = doctorService.findDoctorHeaderUrl(doctor.getId());
            docHeadUrl.put(doctor.getId(), headUlr);
        });
        System.out.println(doctorPage.getTotalPages());
        model.addAttribute("page", doctorPage);
        model.addAttribute("doctorList", doctorPage.getContent());
        model.addAttribute("docHeadUrl", docHeadUrl);

        return "/advertising/bombBox/changeDoctor";
    }

    //TODO  图片预览 2016-11-11 16:12:39
    @RequestMapping("/adv/bombBox/imageShow")
    public String imageShow() {

        return "/advertising/bombBox/imageShow";
    }

    //TODO  打印收据 2016-11-16 11:38:15
    @RequestMapping("/adv/print/printReceipt")
    public String printReceipt(Model model,
                               @RequestParam("id") Long id) {
        Emr emr = emrService.getEmr(id);
        model.addAttribute("emr", emr);
        Double westernPrice = emr.getEmrMedicineList().stream().filter(
                emrMedicine -> emrMedicine.getMedicineType() == Medicine.Type.Western
        ).mapToDouble(EmrMedicine::getPrice).sum();
        model.addAttribute("westernPrice", DecimalCalculate.roundDown(westernPrice, 2));

        Double chinesePrice = emr.getEmrMedicineList().stream().filter(
                emrMedicine -> emrMedicine.getMedicineType() == Medicine.Type.Chinese
        ).mapToDouble(EmrMedicine::getPrice).sum();
        model.addAttribute("chinesePrice", DecimalCalculate.roundDown(chinesePrice, 2));
        Double examPrice = emr.getEmrJClassAdviceDicts().stream().filter(
                emrJClassAdviceDict -> emrJClassAdviceDict.getType() == JClassAdviceDict.Advice_Type.JianYan
        ).mapToDouble(EmrJClassAdviceDict::getPrice).sum();
        model.addAttribute("examPrice", DecimalCalculate.roundDown(examPrice, 2));
        Double labPrice = emr.getEmrJClassAdviceDicts().stream().filter(
                emrJClassAdviceDict -> emrJClassAdviceDict.getType() == JClassAdviceDict.Advice_Type.JianCha
        ).mapToDouble(EmrJClassAdviceDict::getPrice).sum();
        model.addAttribute("labPrice", DecimalCalculate.roundDown(labPrice, 2));
        Double fuJiaPrice = emr.getEmrExtCostList().stream().mapToDouble(
                EmrExtCost::getPrice).sum();
        model.addAttribute("fuJiaPrice", DecimalCalculate.roundDown(fuJiaPrice, 2));
        model.addAttribute("nowDate", new Date());
        Double fePrice = westernPrice + chinesePrice + labPrice + examPrice + fuJiaPrice;
        if (fePrice < emr.getRealCost()) {
            fePrice = emr.getRealCost() - fePrice;
            if (westernPrice != 0D) {
                model.addAttribute("westernPrice", DecimalCalculate.roundDown(westernPrice + fePrice, 2));
            } else if (chinesePrice != 0D) {
                model.addAttribute("chinesePrice", DecimalCalculate.roundDown(chinesePrice + fePrice, 2));
            } else if (examPrice != 0D) {
                model.addAttribute("examPrice", DecimalCalculate.roundDown(examPrice + fePrice, 2));
            } else if (labPrice != 0D) {
                model.addAttribute("labPrice", DecimalCalculate.roundDown(labPrice + fePrice, 2));
            } else if (fuJiaPrice != 0D) {
                model.addAttribute("fuJiaPrice", DecimalCalculate.roundDown(fuJiaPrice + fePrice, 2));
            }

        }

        model.addAttribute("user", PharSecuritySupport.getPharmacist());
        return "/advertising/print/printReceipt";
    }


    /*------------------------------ 打印  ------------------------------ */

    //TODO  打印附加费用缴纳单 2016-11-28 12:02:09
    @RequestMapping("/adv/print/printChargesTable")
    public String printChargesTable() {

        return "/advertising/print/printChargesTable";
    }

    //TODO  打印就诊单 2016-11-28 12:02:09
    @RequestMapping("/adv/print/printDiagnosis")
    public String printDiagnosis() {

        return "/advertising/print/printDiagnosis";
    }

    //TODO  打印项目检查申请表 2016-11-28 12:02:09
    @RequestMapping("/adv/print/printItemsTable")
    public String printItemsTable() {

        return "/advertising/print/printItemsTable";
    }

    //TODO  打印中医理疗申请单 2016-11-28 12:02:09
    @RequestMapping("/adv/print/printPhyTable")
    public String printPhyTable() {

        return "/advertising/print/printPhyTable";
    }

    //TODO  打印处方笺 2016-11-28 14:48:11
    @RequestMapping("/adv/print/printRpA5")
    public String printRpA5() {

        return "/advertising/print/printRpA5";
    }

    //TODO  打印就诊单 2016-11-28 14:48:11
    @RequestMapping("/adv/print/printRpDiagnosisA5")
    public String printRpDiagnosisA5() {

        return "/advertising/print/printRpDiagnosisA5";
    }

    //TODO  零售开单 2016-12-8 11:26:04
    @RequestMapping("/adv/RetailBilling")
    public String RetailBilling() {

        return "/advertising/RetailBilling";
    }

    //TODO  零售列表 2016-12-8 11:26:06
    @RequestMapping("/adv/RetailList")
    public String RetailList() {

        return "/advertising/RetailList";
    }

    //TODO  零售订单详情 2016-12-8 12:04:21
    @RequestMapping("/adv/retailDetails")
    public String retailDetails() {

        return "/advertising/bombBox/retailDetails";
    }

    //TODO  选择药品 2016-12-12 10:04:37
    @RequestMapping("/adv/selectMedic")
    public String selectMedic() {

        return "/advertising/bombBox/selectMedic";
    }

    //TODO  开单关联患者 2016-12-12 11:49:33
    @RequestMapping("/adv/bombBox/registration")
    public String registration() {

        return "/advertising/bombBox/registration";
    }

    //TODO  费用明细 2016-12-12 15:01:21
    @RequestMapping("/adv/bombBox/retailChargeDetails")
    public String retailChargeDetails() {

        return "/advertising/bombBox/retailChargeDetails";
    }

    //TODO  拍摄照片 2016-12-19 16:31:11
    @RequestMapping("/adv/bombBox/takePhotos")
    public String takePhotos(Model model,
                             @RequestParam("id") Long id,
                             @RequestParam("doctorId") Long doctorId
    ) {
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("tmpId", UUID.randomUUID().toString().replace("-", ""));
        model.addAttribute("id", id);
        return "/advertising/bombBox/takePhotos";
    }


    //TODO  退费列表 2016-12-29 10:33:13
    @RequestMapping("/adv/refundList")
    public String refundList() {

        return "/advertising/refundList";
    }

    //TODO  退费清单 2016-12-29 10:57:14
    @RequestMapping("/adv/bombBox/refundDetails")
    public String refundDetails() {

        return "/advertising/bombBox/refundDetails";
    }


}
