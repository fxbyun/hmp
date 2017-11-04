package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
@Controller
public class ConsultationController {
    private static Logger logger = LoggerFactory.getLogger(ConsultationController.class);
    private static EnumMap<Gender, String> genderMap = new EnumMap<Gender, String>(Gender.class);

    static {
        genderMap.put(Gender.Male, "男");
        genderMap.put(Gender.Female, "女");
    }

    @Autowired
    private PatientService patientService;
    @Resource
    private NationService nationService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private EmrService emrService;
    @Resource
    private EvaluateService evaluateService;
    @Resource
    private AppointListService appointListService;
    @Resource
    private AppointConfigService appointConfigService;

    /**点击编辑患者信息
     * @param patientId
     * @param model
     * @return
     */
    @RequestMapping(value = "/consultation/conArchives",method = RequestMethod.GET)
    public String conArchives(@RequestParam(value = "patientId", required = false) Long patientId,
                              @RequestParam(value = "doctorId", required = false) Long doctorId,
                              @RequestParam(value = "appointListId", required = false) Long appointListId,
                              Model model) {
        //判断id是否为空
        String error;
        Patient patient;
        if(patientId==null){
            patient = new Patient();
            if (SecuritySupport.getMobileUser() == null) {
                return "login";
            }
            patient.setMobile(SecuritySupport.getMobileUser().getMobile());
        }else{
            patient = patientService.getPatientById(patientId);

        }
        if(patient==null){
            error="数据库中没有该患者的信息";
            model.addAttribute("error",error);
            return "error/500";
        }else {
            model.addAttribute("patient",patient);

            model.addAttribute("genderMap",genderMap);
            model.addAttribute("provinceList", nationService.listNation(0));
            model.addAttribute("cityList", nationService.listNation(patient.getProvinceNo()));
            model.addAttribute("areaList", nationService.listNation(patient.getCityNo()));
            if (doctorId != null) {
                model.addAttribute("doctorId", doctorId);
                model.addAttribute("appointListId", appointListId);
            } else {
                model.addAttribute("doctorId", null);
                model.addAttribute("appointListId", null);
            }
        }
        return "consultation/conArchives";
    }

    @ModelAttribute
    public void getPatient(@RequestParam(value = "uid", defaultValue = "") String uid, Model model) {
        if (StringUtils.isNotEmpty(uid)) {
            model.addAttribute("patient", patientService.getPatientByUid(uid));
        }
    }


    //TODO  选择就诊人 2016-8-18 11:07:28
    @RequestMapping(value = "/conPatient")
    public String conPatient() {

        return "consultation/conPatient";
    }

    //TODO  选择病例 2016-8-18 15:44:55
    @RequestMapping(value = "/conSelectCase")
    public String conSelectCase() {

        return "consultation/conSelectCase";
    }

    //TODO  咨询窗口 2016-8-18 16:35:20
    @RequestMapping(value = "consultation/conConsulting")
    public String conConsulting() {

        return "consultation/conConsulting";
    }

    //TODO  评价窗口 2016-8-18 17:41:56
    @RequestMapping(value = "consultation/conEvaluate")
    public String conEvaluate(@RequestParam("doctorId") Long doctorId,Model model) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        String headUrl = doctorService.findDoctorHeaderUrl(doctorId);
        //该医生的患者数量
        Long emrCount = emrService.getEmrCount(doctor.getId());
//        doctor.getVerifyBy();

        Calendar start=Calendar.getInstance();
        start.setTime(DateUtils.getDate());

        Calendar now=Calendar.getInstance();
        if (null == doctor.getVerifyOn()) {
            now.setTime(doctor.getCreateOn());
        } else {
            now.setTime(doctor.getVerifyOn());
        }

        int monNums =DateUtils.dateDiff('d',start,now)/30;
        Double averPerNums=doctorService.getAverage( Integer.parseInt(emrCount+""),(long) monNums);
        Double average=doctorService.getAverage(doctor.getIntegration(), emrCount);
        //该医生的平均分
        model.addAttribute("average", average);
        model.addAttribute("averPerNums", averPerNums);
        model.addAttribute("doctor",doctor);
        model.addAttribute("headUrl",headUrl);

        //拿到评价列表
        List<Evaluate> evaluateList = evaluateService.findEvaluateByELE(doctorId);
        model.addAttribute("evaluateList",evaluateList);
        return "consultation/conEvaluate";
    }

    /**手机端查看可预约列表
     * @param appDate
     * @return
     */
    @RequestMapping(value = "outpatient/fragment/consultation/getAppointList")
    public String getAppointList(@RequestParam(value = "appDate") String appDate,@RequestParam(value = "doctorId") Long doctorId,Model model){

        Doctor doctor = doctorService.getDoctor(doctorId);

        AppointConfig appointConfig = appointConfigService.findByDoctor(doctor);

        if(doctor==null){
            throw new RuntimeException("没有找到该医生!");
        }


        Date appointDate =  DateUtils.str2Date(appDate, DateUtils.date_sdf);
        List<AppointList> appointLists = appointListService.findByDate(appointDate,doctor);
        if(CollectionUtils.isNotEmpty(appointLists)){
            model.addAttribute("appointLists",appointLists);
            model.addAttribute("appointConfig",appointConfig);
            model.addAttribute("doctor",doctor);
        }
        return "/fragment/doctorAppointList";
    }



}
