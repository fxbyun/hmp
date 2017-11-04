package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springside.modules.utils.Collections3;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class DiagnosisController extends ConstantsController {

    @Resource
    private PatientService patientService;
    @Resource
    private EmrService emrService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private TagService tagService;
    @Resource
    private AdvertService advertService;
    @Resource
    private NoticeItemService noticeItemService;
    @Resource
    private DataFileService dataFileService;
    @Resource
    private MedicineCountService medicineCountService;
    @Resource
    private DiagnosisService diagnosisService;
    @Resource
    private RegistrationService registrationService;
    @Resource
    private DoctorService doctorService;

    @ModelAttribute("westernMedicineCate")
    private List<String> westernMedicineCate(HttpServletRequest request) {
        return WESTERN_MEDICINE_CATE_LIST;
    }

    @ModelAttribute("chineseMedicineCate")
    private List<String> chineseMedicineCate(HttpServletRequest request) {
        return CHINESE_MEDICINE_CATE_LIST;
    }

    @ModelAttribute("vitalSignLabels")
    private EnumMap<VitalSign.Type, String> vitalSignLabelMap(HttpServletRequest request) {
        return VITAL_SIGN_LABELS;
    }

    @ModelAttribute("vitalSignUnits")
    private EnumMap<VitalSign.Type, String> vitalSignUnitMap(HttpServletRequest request) {
        return VITAL_SIGN_UNITS;
    }

    @ModelAttribute("genderMap")
    private EnumMap<Gender, String> genderMap(HttpServletRequest request) {
        return GENDER_MAP;
    }

    @RequestMapping(value = "/diagnosis", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        Long last = emrService.getLastEmrId(SecuritySupport.getDoctorId());
        if (Numbers.isNotNullOrZero(last)) {
            model.addAttribute("previous", last);
        }
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        Emr emr = new Emr();
        Department department = Collections3.getFirst(departments);
        emr.setDepartmentId(department.getId());
        emr.setDepartmentName(department.getName());
        emr.getVitalSignList().addAll(VITAL_SIGN_LIST);
        model.addAttribute("emr", emr);
        HttpSession session = request.getSession(true);
        if (session.getAttribute(Constants.CURRENT_USER) == null) {
            session.setAttribute(Constants.CURRENT_USER, SecuritySupport.getDoctor());
        }
        session.setAttribute("noReadCount", noticeItemService.noReadCount(SecuritySupport.getDoctorId(), NoticeItem
                .Status.Normal));
        List<Advert> list = advertService.getAdvertByPositionAndStatus(Advert.Position.Home, StatusEntity.Status
                .Normal);
        Doctor doctor = doctorService.getDoctor(SecuritySupport.getDoctorId());
        SecuritySupport.getDoctor().setPrintType(doctor.getPrintType());
        SecuritySupport.getDoctor().setAutoSend(doctor.getAutoSend());
        SecuritySupport.getDoctor().setAutoSendDay(doctor.getAutoSendDay());
        SecuritySupport.getDoctor().setMobile(doctor.getMobile());

        model.addAttribute("adlist", list);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        writeFile(list, request);
        return "diagnosis";
    }

    private void writeFile(List<Advert> list, HttpServletRequest request) {
        String dir = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/temp");
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                DataFile file = list.get(i).getFile();
                if (file == null) continue;
                byte[] content = file.getContent();
                String fileName = file.getFileName();
                try {
                    File f = new File(dir + "/" + fileName);
                    if (f.exists()) {
                        f.delete();
                    }
                    FileUtils.writeByteArrayToFile(f, content);
                } catch (IOException e) {
                }
            }
        }
    }

    @RequestMapping(value = "/diagnosis/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        Emr emr = emrService.getEmr(id);
        model.addAttribute("emr", emr);
        model.addAttribute("patient", emr.getPatient());
        Long doctorId = SecuritySupport.getDoctorId();
        Long prev = emrService.getPreviousEmrId(doctorId, id);
        if (Numbers.isNotNullOrZero(prev)) {
            model.addAttribute("previous", prev);
        }
        Long next = emrService.getNextEmrId(doctorId, id);
        if (Numbers.isNotNullOrZero(next)) {
            model.addAttribute("next", next);
        }

        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        DateTime t = new DateTime(emr.getCreateOn());
        List<Advert> list = advertService.getAdvertByPositionAndStatus(Advert.Position.Home, StatusEntity.Status
                .Normal);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        model.addAttribute("adlist", list);
        model.addAttribute("autoSend", emr.getAutoSend());
        model.addAttribute("autoSendDay", emr.getAutoSendDay());
        writeFile(list, request);
        if (t.isBefore(LocalDate.now().toDateTimeAtStartOfDay()))
            return "diagnosisView";

        return "diagnosisEdit";
    }

    @RequestMapping(value = "/diagnos/{id}", method = RequestMethod.GET)
    public String newEmr(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        Emr emr = emrService.getEmr(id);
        Long doctorId = SecuritySupport.getDoctorId();
        Long prev = emrService.getPreviousEmrId(doctorId, id);
        if (Numbers.isNotNullOrZero(prev)) {
            model.addAttribute("previous", prev);
        }
        Long next = emrService.getNextEmrId(doctorId, id);
        if (Numbers.isNotNullOrZero(next)) {
            model.addAttribute("next", next);
        }
        List<Department> departments = departmentService.getAllDepartments();
        Emr newEmr = new Emr();
        Department department = Collections3.getFirst(departments);
        newEmr.setDepartmentId(department.getId());
        newEmr.setDepartmentName(department.getName());
        newEmr.getVitalSignList().addAll(VITAL_SIGN_LIST);
        model.addAttribute("emr", newEmr);
        model.addAttribute("patient", emr.getPatient());
        model.addAttribute("departments", departments);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("autoSend", SecuritySupport.getDoctor().getAutoSend());
        model.addAttribute("autoSendDay", SecuritySupport.getDoctor().getAutoSendDay());
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        List<Advert> list = advertService.getAdvertByPositionAndStatus(Advert.Position.Home, StatusEntity.Status
                .Normal);
        model.addAttribute("adlist", list);
        writeFile(list, request);
        return "diagnosisEdit";
    }

    @RequestMapping(value = "/begeenSee/{patientUid}", method = RequestMethod.GET)
    public String newEmrVisit(@PathVariable("patientUid") String patientUid, @RequestParam(value = "id") Long id,
                              Model model, HttpServletRequest
                                      request) {

        List<Department> departments = departmentService.getAllDepartments();
        Emr newEmr = new Emr();
        Department department = Collections3.getFirst(departments);
        newEmr.setDepartmentId(department.getId());
        newEmr.setDepartmentName(department.getName());
        newEmr.getVitalSignList().addAll(VITAL_SIGN_LIST);
        model.addAttribute("emr", newEmr);
        model.addAttribute("patient", patientService.getPatientByUid(patientUid));
        model.addAttribute("departments", departments);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("witeListId", id);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        List<Advert> list = advertService.getAdvertByPositionAndStatus(Advert.Position.Home, StatusEntity.Status
                .Normal);
        model.addAttribute("adlist", list);
        writeFile(list, request);
        return "diagnosisEdit";
    }

    /**
     * 演示自行绑定表单中的checkBox roleList到对象中.
     */
//    @RequiresPermissions("user:edit")
    @RequestMapping(value = "/diagnosis/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String save(@Valid @ModelAttribute("emr") Emr emr,
                       @RequestParam(value = "mainSuits", required = false) List<String> mainSuits,
                       @RequestParam(value = "diagnosisResults", required = false) List<String> diagnosisResult,
                       @RequestParam(value = "symptomTagIds", required = false) List<Long> symptomTagIds,
                       @RequestParam(value = "diagnosisTagIds", required = false) List<Long> diagnosisTagIds,
                       @RequestParam(value = "suggestIds", required = false) List<Long> suggestIds,
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
                       @RequestParam(value = "comBackTimes", required = false) String comBackTimes,
                       @RequestParam(value = "comBackDays", required = false) String comBackDays,
                       @RequestParam(value = "witeListId", required = false) Long witeListId,
                       @RequestParam(value = "autoSend", required = false) String autoSend,
                       @RequestParam(value = "autoSendDay", defaultValue = "0") Long autoSendDay

    ) {
        //从排队列表删除当前患者
        if (witeListId != null) {
            registrationService.delete(witeListId);
        }
        //读取是否自动发送和 多久后发送
        if (null != autoSend && !"".equals(autoSend) && "是".equals(autoSend)) {
            emr.setHaveSend(Emr.HAVESEND.NO);
            emr.setAutoSend(autoSend);
            if (null != autoSendDay) {
                emr.setAutoSendDay(autoSendDay);
            } else {
                if (SecuritySupport.getDoctor().getAutoSendDay() == null) {
                    emr.setAutoSendDay(0L);
                } else {
                    emr.setAutoSendDay(SecuritySupport.getDoctor().getAutoSendDay());
                }

            }
        } else {
            emr.setAutoSend("否");
            emr.setAutoSendDay(0L);
        }

        List<EmrMedicine> oldEmrMedicineList = new ArrayList<>(emr.getEmrMedicineList());
        Doctor doctor = SecuritySupport.getDoctor();
        emr.setDoctor(doctor);
        emr.setSymptomTagIds(symptomTagIds);        //主诉ID
        emr.setDiagnosisTagIds(diagnosisTagIds);   //初步诊断ID
        emr.setBackTime(comBackTimes);
        emr.setBackDays(comBackDays);
        if (Collections3.isNotEmpty(mainSuits)) {  //主诉ID
            emr.setMainSuit(StringUtils.join(mainSuits, Constants.TEXT_SEPARATOR));
        } else {
            emr.setMainSuit(null);
        }
        if (Collections3.isNotEmpty(diagnosisResult)) {  //主诉名称
            emr.setDiagnosisResult(StringUtils.join(diagnosisResult, Constants.TEXT_SEPARATOR));
            emr.getDiagnosisList().clear();
            for (String result : diagnosisResult) {
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setName(result);
                emr.getDiagnosisList().add(diagnosis);
            }
        } else {
            emr.getDiagnosisList().clear();
            emr.setDiagnosisResult(null);
        }
        if (medicineIds == null) {
            emr.getEmrMedicineList().clear();
        }
        if (Collections3.isNotEmpty(medicineIds) //药品ID
                && Collections3.isNotEmpty(companyIds)//药品公司ID
                && Collections3.isNotEmpty(medicineQty)//药品总数量
                && Collections3.isNotEmpty(medicineUnit)) { //给药单位
            emr.getEmrMedicineList().clear();
            for (int i = 0; i < medicineIds.size(); i++) {
                EmrMedicine item = new EmrMedicine();

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
                } else {
                    medId = Long.valueOf(idOne);
                }
                item.setMedicine(new Medicine(medId));
                item.setCompany(new Company(companyIds.get(i)));
                item.setQty(medicineQty.get(i));
                item.setRate(medicineRate.get(i));
                item.setUnit(medicineUnit.get(i));
                item.setCopies(medicineCopies.get(i));
                item.setUseMode(medicineUseModes.get(i));
                item.setHasUsage(medicineHasUsages.get(i));
                item.setGroupIndex(medicineGroupId.get(i));
                item.setMultiplexTag(multiplexTag);
                if (medicineHasUsages.get(i)) {
                    if (Collections3.isNotEmpty(medicineUseTimes) && medicineUseTimes.size() > i) {
                        item.setUseTimes(medicineUseTimes.get(i));
                    }
                    if (Collections3.isNotEmpty(medicineUseUsingTime) && medicineUseUsingTime.size() > i) {
                        item.setUsingTime(medicineUseUsingTime.get(i));
                    }
                    if (Collections3.isNotEmpty(medicineUseQty) && medicineUseQty.size() > i) {
                        item.setUseQty(medicineUseQty.get(i));
                    }
                    if (Collections3.isNotEmpty(medicineUseUnit) && medicineUseUnit.size() > i) {
                        item.setUseUnit(medicineUseUnit.get(i));
                    }
                }
                //药品特殊说明
                if (Collections3.isNotEmpty(specialInstructions) && specialInstructions.size() > i) {
                    //String str = specialInstructions.get(i);
                    item.setSpecialInstructions(specialInstructions.get(i));
                }

                //药品规格
                if (Collections3.isNotEmpty(standard) && standard.size() > i) {
                    item.setStandard(standard.get(i));
                }
                emr.getEmrMedicineList().add(item);
            }
        }
        if (Collections3.isNotEmpty(suggestIds)) { //诊后建议
            emr.getEmrSuggestList().clear();
            for (int i = 0; i < suggestIds.size(); i++) {
                EmrSuggest item = new EmrSuggest();
                item.setSuggestId(suggestIds.get(i));
                emr.getEmrSuggestList().add(item);
            }
        }


        emrService.saveEmr(emr);
        //修改 医生使用药品的频率
        changOrderMed(emr, medicineIds, doctor, oldEmrMedicineList, diagnosisResult);


        return "redirect:/diagnosis/" + emr.getId();
    }

    /**
     * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
     * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("emr", emrService.getEmr(id));
//            System.out.println("createOn:" + emrService.getEmr(id).getCreateOn());
        }
    }

    /**
     * 不自动绑定对象中的roleList属性，另行处理。
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("createOn");
        binder.setDisallowedFields("mainSuit");
        binder.setDisallowedFields("diagnosisResult");
        binder.setDisallowedFields("diagnosisList");
        binder.setDisallowedFields("emrMedicineList");
        binder.setDisallowedFields("vitalSignList");
        binder.setDisallowedFields("evaluateList");
        binder.setDisallowedFields("tagIds");
    }

    /**
     * @param emr
     * @param medicineIds
     * @param doctor
     * @param oldEmrMedicineList
     */

    private void changOrderMed(Emr emr, List<String> medicineIds, Doctor doctor, List<EmrMedicine>
            oldEmrMedicineList, List<String> diagnosisResult) {
        List<EmrMedicine> newEmrMedicineList = emr.getEmrMedicineList();
        String diagnosisTagFistName = "";

        if (diagnosisResult != null && newEmrMedicineList != null) {


            if (diagnosisResult.size() > 0) {
                diagnosisTagFistName = diagnosisResult.get(0);
            }

            if (medicineIds != null) {

                for (int i = 0; i < medicineIds.size(); i++) {
                    Medicine medicine = medicineService.getMedicine(Long.valueOf(medicineIds.get(i).replaceAll
                            ("[s]+", "")));
                    MedicineCount medicineCount = medicineCountService.getMedicineCountByMedicineAndDoctorAndDiagnsis
                            (doctor, medicine,
                                    diagnosisTagFistName);
                    if (medicineCount != null) {
                        boolean isThisMedHaveInOldEmr = false;
                        for (EmrMedicine emrMedicine : oldEmrMedicineList) {

                            if (emrMedicine.getMedicine().getId().toString().equals(medicineIds.get(i).replaceAll("[s]+", ""))) {
                                isThisMedHaveInOldEmr = true;
                            }
                        }
                        //如果该药品存在于老病历的药品中 并且又存在于本次的药品保存ID 中
                        // 那么说明之前开药品的时候已经增加过使用次数了 就不增加次数
                        if (!isThisMedHaveInOldEmr) {
                            medicineCount.setCountSize(medicineCount.getCountSize() + 1);
                        }
                    } else {
                        medicineCount = new MedicineCount();
                        medicineCount.setCountSize(1);
                        medicineCount.setDoctor(doctor);
                        medicineCount.setMedicine(medicine);
                        medicineCount.setDiagosisName(diagnosisTagFistName);
                    }
                    medicineCountService.save(medicineCount);
                }
            }

            boolean isThisMedHaveInOldEmrAndThisTimeIsNotInEmr = true;

            for (EmrMedicine oldEmrMedicine : oldEmrMedicineList) {
                for (EmrMedicine newEmrMedicine : newEmrMedicineList) {
                    isThisMedHaveInOldEmrAndThisTimeIsNotInEmr = true;
                    if (newEmrMedicine.getMedicine().getId().equals(oldEmrMedicine.getMedicine().getId())) {
                        isThisMedHaveInOldEmrAndThisTimeIsNotInEmr = false;
                        break;
                    }
                }
                if (isThisMedHaveInOldEmrAndThisTimeIsNotInEmr) {
                    //MedicineCount medicineCount = medicineCountService.getMedicineByMedicine(oldEmrMedicine
                    // .getMedicine());
                    MedicineCount medicineCount = medicineCountService.getMedicineCountByMedicineAndDoctorAndDiagnsis
                            (doctor, oldEmrMedicine
                                    .getMedicine(), diagnosisTagFistName);
                    if (medicineCount != null) {
                        if (medicineCount.getCountSize() != 0) {
                            medicineCount.setCountSize(medicineCount.getCountSize() - 1);
                            medicineCountService.save(medicineCount);
                        }
                    }

                }
            }
        }
    }
}
