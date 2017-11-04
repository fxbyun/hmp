package com.qiaobei.hmp.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.EmrFileService;
import com.qiaobei.hmp.modules.service.EmrJClassAdviceDictService;
import com.qiaobei.hmp.modules.service.ExamLabFileService;
import com.qiaobei.hmp.modules.service.IaiLossDetailService;
import com.qiaobei.hmp.modules.webSocket.WebSocketHandlerImpl;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springside.modules.utils.Collections3;
import org.springside.modules.utils.Numbers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class DiagnosisController extends ConstantsController {

    @Resource
    IaiLossDetailService iaiLossDetailService;
    @Resource
    WebSocketHandlerImpl webSocketHandler;
    @Resource
    private PatientService patientService;
    @Resource
    private MedicinePrivateService medicinePrivateService;
    @Resource
    private EmrService emrService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private AdvertService advertService;
    @Resource
    private NoticeItemService noticeItemService;
    @Resource
    private MedicineCountService medicineCountService;
    @Resource
    private RegistrationService registrationService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private AppointPatientService appointPatientService;
    @Resource
    private EmrFileService emrFileService;
    @Resource
    private ExamLabFileService examLabFileService;
    @Resource
    private EmrJClassAdviceDictService emrJClassAdviceDictService;

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
        assert department != null;
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
        if (doctor != null) {
            SecuritySupport.getDoctor().setPrintType(doctor.getPrintType());
            SecuritySupport.getDoctor().setAutoSend(doctor.getAutoSend());
            SecuritySupport.getDoctor().setAutoSendDay(doctor.getAutoSendDay());
            SecuritySupport.getDoctor().setMobile(doctor.getMobile());
            SecuritySupport.getDoctor().setPrintModel(doctor.getPrintModel());
            SecuritySupport.getDoctor().setAutoSendActivateMsg(doctor.getAutoSendActivateMsg());

        }

        model.addAttribute("adlist", list);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
//        model.addAttribute("emrPatientList", emrService.findAllPatientByDoctor(SecuritySupport.getDoctor()));
        writeFile(list);
        return "diagnosis";
    }

    private void writeFile(List<Advert> list) {
        String dir = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/temp");
        if (list != null && !list.isEmpty()) {
            for (Advert aList : list) {
                DataFile file = aList.getFile();
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
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/diagnosis/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String get(@PathVariable("id") Long id, Model model,
                      @RequestParam(value = "callAdv", required = false, defaultValue = "false") String callAdv,
                      HttpServletRequest request) {
        Emr emr = emrService.getEmr(id);
        model.addAttribute("emr", emr);
        model.addAttribute("callAdv", callAdv);

        //拿出中医理疗
        //String HTML="";
        List<String> htmlList = Lists.newArrayList();
        List<EmrMedicine> therapyList = Lists.newArrayList();
        emr.getEmrMedicineList().stream().filter(emrMedicine -> emrMedicine.getMedicineType() == Medicine.Type.ChineseTherapy).forEach(emrMedicine -> {
            String HTML = medicinePrivateService.therapyHtml(medicinePrivateService.getMedPrivate(emrMedicine.getMedicinePrivateId()),
                    emrMedicine.getUseQty(),
                    emrMedicine.getUnit(),
                    THERAPY_UNITS.get(emrMedicine.getUnit()),
                    emrMedicine.getCopies().toString());
            htmlList.add(HTML);
            therapyList.add(emrMedicine);

        });
        model.addAttribute("HTML_JSON", JSONObject.toJSONString(htmlList));
        model.addAttribute("therapyList", therapyList);
        //其他主诉
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
        writeFile(list);
        if (t.isBefore(LocalDate.now().toDateTimeAtStartOfDay()))
            return "diagnosisView";
        Doctor doctor = doctorService.getDoctor(SecuritySupport.getDoctorId());
        SecuritySupport.getDoctor().setPrintType(doctor.getPrintType());
        SecuritySupport.getDoctor().setAutoSend(doctor.getAutoSend());
        SecuritySupport.getDoctor().setAutoSendDay(doctor.getAutoSendDay());
        SecuritySupport.getDoctor().setMobile(doctor.getMobile());
        SecuritySupport.getDoctor().setPrintModel(doctor.getPrintModel());
        SecuritySupport.getDoctor().setAutoSendActivateMsg(doctor.getAutoSendActivateMsg());
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
        assert department != null;
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
        writeFile(list);
        Doctor doctor = doctorService.getDoctor(SecuritySupport.getDoctorId());
        SecuritySupport.getDoctor().setPrintType(doctor.getPrintType());
        SecuritySupport.getDoctor().setAutoSend(doctor.getAutoSend());
        SecuritySupport.getDoctor().setAutoSendDay(doctor.getAutoSendDay());
        SecuritySupport.getDoctor().setMobile(doctor.getMobile());
        SecuritySupport.getDoctor().setPrintModel(doctor.getPrintModel());
        SecuritySupport.getDoctor().setAutoSendActivateMsg(doctor.getAutoSendActivateMsg());
        return "diagnosisEdit";
    }

    @RequestMapping(value = "/begeenSee/{patientUid}", method = RequestMethod.GET)
    public String newEmrVisit(@PathVariable("patientUid") String patientUid, @RequestParam(value = "id") Long id,
                              Model model, HttpServletRequest
                                      request) {

        List<Department> departments = departmentService.getAllDepartments();
        Emr newEmr = new Emr();
        Department department = Collections3.getFirst(departments);
        assert department != null;
        newEmr.setDepartmentId(department.getId());
        newEmr.setDepartmentName(department.getName());
        newEmr.getVitalSignList().addAll(VITAL_SIGN_LIST);


        registrationService.setAllDoNotCallByDoctor(SecuritySupport.getDoctor());
        //Registration registration = registrationService.getRegistration(patientUid, Registration.Status.Normal);
        Registration registration = registrationService.getRegistrationById(id);
        if (registration == null) {
            return "diagnosis";
        }
        model.addAttribute("emr", newEmr);
        model.addAttribute("patient", patientService.getPatientByUid(patientUid));
        registration.setCallStatus(Registration.CallStatus.CALL);
        //设置排队状态
        //registration.setQueueStatus(Registration.QueueStatus.Visited);
        registrationService.save(registration);

        model.addAttribute("departments", departments);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        model.addAttribute("witeListId", id);
        model.addAttribute("doctor", SecuritySupport.getDoctor());
        List<Advert> list = advertService.getAdvertByPositionAndStatus(Advert.Position.Home, StatusEntity.Status
                .Normal);
        model.addAttribute("adlist", list);
        writeFile(list);
        Doctor doctor = doctorService.getDoctor(SecuritySupport.getDoctorId());
        SecuritySupport.getDoctor().setPrintType(doctor.getPrintType());
        SecuritySupport.getDoctor().setAutoSend(doctor.getAutoSend());
        SecuritySupport.getDoctor().setAutoSendDay(doctor.getAutoSendDay());
        SecuritySupport.getDoctor().setMobile(doctor.getMobile());
        SecuritySupport.getDoctor().setPrintModel(doctor.getPrintModel());
        SecuritySupport.getDoctor().setAutoSendActivateMsg(doctor.getAutoSendActivateMsg());

        webSocketHandler.CallAdvingAndNusurByDoctor(doctor);
        return "diagnosisEdit";
    }

    /**
     * 演示自行绑定表单中的checkBox roleList到对象中.
     */
//    @RequiresPermissions("user:edit")
    @Transactional
    @RequestMapping(value = "/diagnosis/save", method = {RequestMethod.POST, RequestMethod.GET})
    public void saveDiagnosis(@Valid @ModelAttribute("emr") Emr emr,
                              @RequestParam(value = "mainSuits", required = false) List<String> mainSuits,
                              @RequestParam(value = "diagnosisResults", required = false) List<String> diagnosisResult,
                              @RequestParam(value = "symptomTagIds", required = false) List<Long> symptomTagIds,
                              @RequestParam(value = "diagnosisTagIds", required = false) List<Long> diagnosisTagIds,
                              @RequestParam(value = "suggestIds", required = false) List<Long> suggestIds,
                              @RequestParam(value = "medicinePrivateIds", required = false) List<Long> medicinePrivateIds,
                              @RequestParam(value = "unitPrices", required = false) List<Double> unitPrices,
                              @RequestParam(value = "medicineIds", required = false) List<String> medicineIds,
                              @RequestParam(value = "companyIds", required = false) List<Long> companyIds,
                              @RequestParam(value = "prices", required = false) List<Double> prices,
                              @RequestParam(value = "medicineQty", required = false) List<Double> medicineQty,
                              @RequestParam(value = "medicineRate", required = false) List<Double> medicineRate,
                              @RequestParam(value = "medicineUnit", required = false) List<Medicine.Unit> medicineUnit,
                              @RequestParam(value = "tjUnits", required = false) List<Medicine.Unit> tjUnits,
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
                              @RequestParam(value = "autoSendDay", defaultValue = "0") Long autoSendDay,
                              @RequestParam(value = "remarks", defaultValue = "") String remarks,
                              //以下是检查检验的参数
                              @RequestParam(value = "examOrLabInfo", required = false) List<String> examOrLabInfos,
                              @RequestParam(value = "adviceName", required = false) List<String> adviceNames,
                              @RequestParam(value = "examOrLabName", required = false) List<String> examOrLabNames,
                              @RequestParam(value = "examOrLabIds", required = false) List<Long> examOrLabIds,
                              @RequestParam(value = "examOrLabPrice", required = false) List<Double> examOrLabPrices,
                              @RequestParam(value = "tmpId", required = false) List<String> tmpId,
                              @RequestParam(value = "examOrLabtypeType", required = false) List<JClassAdviceDict.Advice_Type> examOrLabtypeTypes,

                              //以下是附加费用的参数
                              @RequestParam(value = "fuJiaNames", required = false) List<String> fuJiaNames,
                              @RequestParam(value = "fuJiaPrices", required = false) List<Double> fuJiaPrices,
                              @RequestParam(value = "fuJiaIds", required = false) List<Long> fuJiaIds,

                              //以下是中医理疗的参数
                              @RequestParam(value = "therapyId", required = false) List<Long> therapyIds,
                              @RequestParam(value = "useQty", required = false) List<String> useQtys,
                              @RequestParam(value = "useUnit", required = false) List<Medicine.Unit> useUnits,
                              @RequestParam(value = "therapyCopy", required = false) List<Double> therapyCopys,

                              //以下是药品的状态参数
                              @RequestParam(value = "status", required = false) List<StatusEntity.Status> statusList,
                              @RequestParam(value = "emrFileName", required = false) List<String> emrFileNameList,
                              @RequestParam(value = "emrFileType", required = false) List<EmrFile.EmrFileType> emrFileTypeList,
                              HttpServletResponse httpServletResponset

    ) {
        //从排队列表删除当前患者
        if (witeListId != null) {
            //修改预约信息，改为已就诊
            Registration registration = registrationService.getRegistrationById(witeListId);
            AppointPatient appointPatient = appointPatientService.findById(registration.getAppointPatientId());
            if (appointPatient != null) {
                appointPatient.setAppointStatus(AppointPatient.Status.Treatment);
                appointPatientService.saveAppointPatient(appointPatient);
            }
            if (registrationService.getRegistrationById(witeListId) != null) {
                registration.setQueueStatus(Registration.QueueStatus.Visited);
                registrationService.save(registration);
                //registrationService.delete(witeListId);
            }

//            webSocketHandler.CallAdvingAndNusurByDoctor(SecuritySupport.getDoctor());
        }
//        //默认 病历是 COMMMON 状态
//        if (emr.getType() == null) {
//            emr.setType(Emr.TYPE.COMMON);
//        }
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
        emr.setRemarks(remarks);
        if (Collections3.isNotEmpty(mainSuits)) {  //主诉ID
            emr.setMainSuit(StringUtils.join(mainSuits, Constants.TEXT_SEPARATOR));
        } else {
            emr.setMainSuit(null);
        }

        if (medicineIds == null) {
            emr.getEmrMedicineList().clear();
        }
        if (emr.getId() == null) {
            emr.setUpdateOn(new Date());
        }

        //保存检查检验
        emr.getEmrJClassAdviceDicts().clear();
        if (Collections3.isNotEmpty(examOrLabInfos)
                && Collections3.isNotEmpty(examOrLabIds)
                && Collections3.isNotEmpty(examOrLabPrices)
                && Collections3.isNotEmpty(examOrLabtypeTypes)) {
            Patient patient;
            if (emr.getId() != null) {
                patient = emr.getPatient();
            } else {
                patient = patientService.findByUid(emr.getPatientUid());
                if (patient == null) {
                    patient = patientService.findOne(emr.getPatient().getId());
                }
            }

            for (int i = 0; i < examOrLabInfos.size(); i++) {
                EmrJClassAdviceDict emrJClassAdviceDict = new EmrJClassAdviceDict();
                emrJClassAdviceDict.setInfo(examOrLabInfos.get(i));
                emrJClassAdviceDict.setType(examOrLabtypeTypes.get(i));
                emrJClassAdviceDict.setPrice(examOrLabPrices.get(i));
                emrJClassAdviceDict.setExp1(examOrLabIds.get(i));
                emrJClassAdviceDict.setExamLabName(examOrLabNames.get(i));
                emrJClassAdviceDict.setAdviceName(adviceNames.get(i));
                emrJClassAdviceDict.setCreateOn(new Date());
                emrJClassAdviceDict.setStatus(StatusEntity.Status.Normal);
                if (tmpId.size() >= (i + 1) && StringUtils.isNotEmpty(tmpId.get(i))) {
                    EmrFile emrFile = emrFileService.findByFileNameAndDoctorAndFileType(tmpId.get(i), doctor);
                    if (emrFile != null) {
                        ExamLabFile examLabFile = new ExamLabFile();
                        examLabFile.setFileData(emrFile.getContent());
                        examLabFile.setEmrJClassAdviceDict(emrJClassAdviceDict);
                        examLabFile.setCreateOn(new Date());
                        examLabFile.setFileName(tmpId.get(i) + ".png");
                        examLabFile.setTypes(ExamLabFile.Exam_Lab_File_Type.Png);
                        emrJClassAdviceDict.getExamLabFileList().add(examLabFile);
                        emrJClassAdviceDict.setStatus(StatusEntity.Status.Have_Exam_Or_Lab);
                        emrJClassAdviceDict.setTmpFileNameId(tmpId.get(i));
                        emrJClassAdviceDictService.save(emrJClassAdviceDict);
                        examLabFileService.save(examLabFile);
                        System.out.println(tmpId.get(i));
                    }

                }
                if (patient != null)
                    emrJClassAdviceDict.setPatient(patient);
                emrJClassAdviceDict.setEmr(emr);
                emr.getEmrJClassAdviceDicts().add(emrJClassAdviceDict);
            }


        }

        //保存附加费用
        emr.getEmrExtCostList().clear();
        if (Collections3.isNotEmpty(fuJiaNames)
                && Collections3.isNotEmpty(fuJiaPrices)
                && Collections3.isNotEmpty(fuJiaIds)) {

            for (int i = 0; i < fuJiaIds.size(); i++) {
                EmrExtCost emrExtCost = new EmrExtCost();
                emrExtCost.setEmr(emr);
                emrExtCost.setPrice(fuJiaPrices.get(i));
                emrExtCost.setClassName(fuJiaNames.get(i));
                emrExtCost.setDoctorCostId(fuJiaIds.get(i));
                emr.getEmrExtCostList().add(emrExtCost);
            }

        }

        emr.getEmrMedicineList().clear();
        //保存中医理疗
        if (CollectionUtils.isNotEmpty(therapyIds)) {
            for (int j = 0; j < therapyIds.size(); j++) {
                EmrMedicine itemThe = new EmrMedicine();
                itemThe.setUseQty(useQtys.get(j));
                itemThe.setUnit(useUnits.get(j));
                itemThe.setUseUnit(useUnits.get(j).name());
                itemThe.setMedicinePrivateId(therapyIds.get(j));
                itemThe.setCopies(therapyCopys.get(j));

                MedicinePrivate therapy = medicinePrivateService.getMedPrivate(therapyIds.get(j));
                itemThe.setEmr(emr);
                itemThe.setMedicine(therapy.getMedicine());
                itemThe.setMedicineName(therapy.getName());
                itemThe.setMedicineType(Medicine.Type.ChineseTherapy);
                itemThe.setRealUnit(therapy.getMedicine().getUnit());
                itemThe.setDoctorId(SecuritySupport.getDoctor().getId());
                itemThe.setDoctorName(SecuritySupport.getDoctor().getName());
                itemThe.setCreateOn(new Date());
                itemThe.setUnitPrice(therapy.getPrice());
                itemThe.setPrice(therapy.getPrice() * therapyCopys.get(j));
                itemThe.setCompany(therapy.getDefaultCompany());
                itemThe.setCompanyName(therapy.getDefaultCompanyName());

                emr.getEmrMedicineList().add(itemThe);
            }
        }

//

        //保存药品信息
        if (Collections3.isNotEmpty(medicineIds) //药品ID
                && Collections3.isNotEmpty(companyIds)//药品公司ID
                && Collections3.isNotEmpty(medicineQty)//药品总数量
                && Collections3.isNotEmpty(medicineUnit)) { //给药单位

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
                item.setPrice(prices.get(i));
                item.setQty(medicineQty.get(i));
                item.setMedicinePrivateId(medicinePrivateIds.get(i));
                item.setUnitPrice(unitPrices.get(i));
                item.setTjUnit(Optional.ofNullable(tjUnits.get(i)).orElse(null));
                item.setRate(medicineRate.get(i));
                item.setUnit(medicineUnit.get(i));
                item.setCopies(Optional.ofNullable(medicineCopies.get(i)).orElse(null));
                item.setUseMode(medicineUseModes.get(i));
                item.setHasUsage(medicineHasUsages.get(i));
                item.setGroupIndex(medicineGroupId.get(i));
                item.setMultiplexTag(multiplexTag);
                item.setStatus(statusList.get(i));
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
            for (Long suggestId : suggestIds) {
                EmrSuggest item = new EmrSuggest();
                item.setSuggestId(suggestId);
                emr.getEmrSuggestList().add(item);
            }
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


        //如果护士端 已经收费 那么 修改为未收费
//        if (emr.getId() != null && emr.getStatus() == StatusEntity.Status.CHARGE) {
//            emr.setStatus(StatusEntity.Status.Normal);
//        }
        emrService.saveEmr(emr);
        //高拍仪图片
//        emr.getEmrFileList().clear();
        if (Collections3.isNotEmpty(emrFileNameList) && Collections3.isNotEmpty(emrFileTypeList)) {
            emrFileService.delEmrFileByNotInNameList(emr, emrFileNameList);
            for (int i = 0; i < emrFileNameList.size(); i++) {
                EmrFile emrFile = emrFileService.findByFileNameAndDoctorAndFileType(emrFileNameList.get(i), doctor, emrFileTypeList.get(i));
                if (emrFile != null) {
                    emrFile.setEmr(emr);
                    emrFileService.save(emrFile);
                }
            }
        }
        //插入药品损耗表
        medicinePrivateService.insertLossDetil(emr);

        //修改 医生使用药品的频率
        changOrderMed(emr, medicineIds, doctor, oldEmrMedicineList, diagnosisResult);

        try {
            if (witeListId != null) {
                httpServletResponset.sendRedirect("/diagnosis/" + emr.getId() + "?callAdv=true");
            } else {
                httpServletResponset.sendRedirect("/diagnosis/" + emr.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "redirect:/diagnosis/" + emr.getId();
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

    private void changOrderMed(Emr emr, List<String> medicineIds, Doctor doctor, List<EmrMedicine>
            oldEmrMedicineList, List<String> diagnosisResult) {
        List<EmrMedicine> newEmrMedicineList = emr.getEmrMedicineList();
        String diagnosisTagFistName = "";

        if (diagnosisResult != null && newEmrMedicineList != null) {


            if (diagnosisResult.size() > 0) {
                diagnosisTagFistName = diagnosisResult.get(0);
            }

            if (medicineIds != null) {

                for (String medicineId : medicineIds) {
                    Medicine medicine = medicineService.getMedicine(Long.valueOf(medicineId.replaceAll
                            ("[s]+", "")));
                    MedicineCount medicineCount = medicineCountService.getMedicineCountByMedicineAndDoctorAndDiagnsis
                            (doctor, medicine,
                                    diagnosisTagFistName);
                    if (medicineCount != null) {
                        boolean isThisMedHaveInOldEmr = false;
                        for (EmrMedicine emrMedicine : oldEmrMedicineList) {

                            if ((emrMedicine.getMedicine().getId() + "").equals(medicineId.replaceAll("[s]+", ""))) {
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

    //TODO 查询消费明细 2016-10-8 14:44:41
    @RequestMapping("/fragment/chargeDetail")
    public String chargeDetail(Model model) {
        Doctor primaryDoctor;
        if (SecuritySupport.getDoctor().getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            primaryDoctor = doctorService.getPrimaryDoctor(SecuritySupport.getDoctor());
        } else {
            primaryDoctor = SecuritySupport.getDoctor();
        }
        model.addAttribute("loginDoctor", SecuritySupport.getDoctor());
        model.addAttribute("primaryDoctor", primaryDoctor);
        return "fragment/chargeDetail";
    }


    //TODO 修改药品单价 2016-10-8 17:15:50
    @RequestMapping("/fragment/editMedicinePrice")
    public String editMedicinePrice(
            @RequestParam(value = "medPrivateId") Long medPrivateId,
            @RequestParam(value = "unitPrice") double unitPrice,
            Model model
    ) {
        model.addAttribute("medPrivateId", medPrivateId);
        model.addAttribute("unitPrice", unitPrice);
        model.addAttribute("medicineUnits", MEDICINE_UNITS);
        MedicinePrivate medicinePrivate = medicinePrivateService.getMedPrivate(medPrivateId);
        if (medicinePrivate.getPrice() == null) {
            medicinePrivate.setPrice(0D);
        }
        model.addAttribute("med", medicinePrivate);

        return "fragment/editMedicinePrice";
    }

    @RequestMapping("/fragment/updateMedPrivateUnitPrice")
    @ResponseBody
    public Result updateMedPrivateUnitPrice(
            @RequestParam(value = "medPrivateId") Long medPrivateId,
            @RequestParam(value = "unitPrice") double unitPrice
    ) {
        try {
            MedicinePrivate medicinePrivate = medicinePrivateService.getMedPrivate(medPrivateId);
            medicinePrivate.setPrice(unitPrice);
            medicinePrivateService.update(medicinePrivate);
        } catch (Exception e) {
            return new Result().setSuccess(false).setMsg(e.getMessage());
        }
        return new Result().setSuccess(true);
    }

}
