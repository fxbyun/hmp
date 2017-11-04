package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.webSocket.WebSocketHandlerImpl;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.CardService;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.service.RegistrationService;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegistrationController extends BaseController {
    @Resource
    WebSocketHandlerImpl webSocketHandler;
    //    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Resource
    private RegistrationService registrationService;
    @Resource
    private PatientService patientService;
    @Resource
    private CardService cardService;
    @Resource
    private DoctorService doctorService;

    @RequestMapping(value = "/fragment/regist", method = RequestMethod.GET)
    public String registration(Model model) {
        /*model.addAttribute("registration", registrationService.getRegistrationList(SecuritySupport.getDoctorId(),
                Registration.Status.Normal));*/

        model.addAttribute("registration", registrationService.getRegistrationList(SecuritySupport.getDoctorId(),
                Registration.Status.Normal, Registration.QueueStatus.NotVisit));

        return "fragment/registrationView";
    }

    @RequestMapping(value = "/fragment/registration/add", method = RequestMethod.GET)
    public String toAdd() {
        return "fragment/registration";
    }

    @RequestMapping(value = "/fragment/registration/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam(required = false) String udid,
                       @RequestParam(value = "mobile", required = false) String uid) {
        if (StringUtils.isNotEmpty(udid)) {
            Patient patientByUid = patientService.getPatientListByUdid(udid).get(0);
            List<Patient> patientList = patientService.getPatientListByUdid(udid);
            patientList = patientList.stream().filter(
                    patient -> StringUtils.isNotEmpty(
                            patient.getMobile())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(patientList)) {
                patientByUid = patientList.get(0);
            }
            if (patientByUid == null || patientByUid.getStatus() == StatusEntity.Status.Unactivated) {
                if (StringUtils.isEmpty(uid)) {
                    return Result.fail("该患者未绑卡,请先进行绑卡操作!");
                }
            }
        }

        Patient patient;
        Registration reg = new Registration();
        if (StringUtils.isNotEmpty(udid)) {
            patient = patientService.getPatientByUdid(udid);
            if (patient == null) {
                Card card = cardService.getCardByUdid(udid);
                if (card == null) {
                    return Result.fail("无效卡！");
                } else {
                    if (registrationService.getRegistration(card.getCardNo(), StatusEntity.Status.Normal, SecuritySupport.getDoctor(), Registration.QueueStatus.NotVisit) != null) {
                        return Result.fail("该患者已经挂号!");
                    }
                    reg.setPatientName(card.getCardNo());
                    reg.setPatientUid(card.getCardNo());
                }
            } else {
                if (registrationService.getRegistration(patient.getUid(), StatusEntity.Status.Normal, SecuritySupport.getDoctor(), Registration.QueueStatus.NotVisit) != null) {
                    return Result.fail("该患者已经挂号!");
                }
                reg.setPatientName(patient.getName());
                reg.setPatientUid(patient.getUid());
            }
        }
        if (StringUtils.isNotEmpty(uid)) {
            patient = patientService.getPatientByUid(uid);
            if (patient != null) {
                reg.setPatientName(patient.getName());
                reg.setPatientUid(patient.getUid());
                if (registrationService.getRegistration(patient.getUid(), StatusEntity.Status.Normal, SecuritySupport.getDoctor(), Registration.QueueStatus.NotVisit) != null) {
                    return Result.fail("该患者已经挂号!");
                }
            } else {
                return Result.fail("该手机未绑卡！");
//                reg.setPatientName(mobile);
//                reg.setPatientUid(mobile);
            }
        }
        Doctor doctor = SecuritySupport.getDoctor();
        reg.setDoctorId(doctor.getId());
        reg.setDoctorName(doctor.getName());
        reg.setCreateOn(new Date());
        reg.setStatus(Registration.Status.Normal);
        reg.setQueueStatus(Registration.QueueStatus.NotVisit);
        reg.setSequence(0);

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
        reg.setDoctorDeptName(SecuritySupport.getDoctor().getDeptName());

        //reg.setNoNumber(registrationService.getRegistrationToDayAndTypeEndNo(Registration.RegistrationTypeEnum.LOCALE));
        registrationService.save(reg);
        webSocketHandler.CallAdvingAndNusurByDoctor(SecuritySupport.getDoctor());
        return Result.ok();
    }

    @RequestMapping(value = "/fragment/registration/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteRegistration(@RequestParam Long id) {
        try {
            //registrationService.delete(id);
            Registration ret = registrationService.getRegistrationById(id);
            ret.setQueueStatus(Registration.QueueStatus.Delete);
            registrationService.save(ret);
            webSocketHandler.CallAdvingAndNusurByDoctor(SecuritySupport.getDoctor());
        } catch (ServiceException e) {
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping(value = "/fragment/callWebSocket", method = RequestMethod.POST)
    @ResponseBody
    public Result callWebSocket(@RequestParam(value = "witeListId", required = false) Long witeListId) {
        if (witeListId != null) {
            Registration registration = registrationService.getRegistrationById(witeListId);
            registration.setPatientName(" " + registration.getPatientName());
            registration.setCallStatus(Registration.CallStatus.CALL);
            registrationService.save(registration);
        }
        webSocketHandler.CallAdvingAndNusurByDoctor(SecuritySupport.getDoctor());
        return Result.ok();
    }

}
