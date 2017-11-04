package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.CardService;
import com.qiaobei.hmp.service.PatientService;
import com.qiaobei.hmp.service.RegistrationService;
import com.qiaobei.hmp.support.Result;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class RegistrationController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Resource
    private RegistrationService registrationService;
    @Resource
    private PatientService patientService;
    @Resource
    private CardService cardService;



    @RequestMapping(value = "/fragment/registration/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam(required = false) String udid,
                       @RequestParam(required = false) String mobile) {
        Patient patientByUid = patientService.getPatientByUdid(udid);
        if (patientByUid == null || patientByUid.getStatus() == StatusEntity.Status.Unactivated) {
            if (mobile == null || "".equals(mobile)) {
                return Result.fail("该患者未绑卡,请先进行绑卡操作!");
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
                    reg.setPatientName(card.getCardNo());
                    reg.setPatientUid(card.getCardNo());
                }
            } else {
                reg.setPatientName(patient.getName());
                reg.setPatientUid(patient.getUid());
            }
        }
        if (StringUtils.isNotEmpty(mobile)) {
            List<Patient> ps = patientService.listPatientByMobile(mobile);
            if (ps != null && !ps.isEmpty()) {
                patient = ps.get(0);
                reg.setPatientName(patient.getName());
                reg.setPatientUid(patient.getUid());
            } else {
                reg.setPatientName(mobile);
                reg.setPatientUid(mobile);
            }
        }
        Doctor doctor = SecuritySupport.getDoctor();
        reg.setDoctorId(doctor.getId());
        reg.setDoctorName(doctor.getName());
        reg.setCreateOn(new Date());
        reg.setStatus(Registration.Status.Normal);
        reg.setQueueStatus(Registration.QueueStatus.NotVisit);
        reg.setSequence(0);
        registrationService.save(reg);
        return Result.ok();
    }

    @RequestMapping(value = "/fragment/registration/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteRegistration(@RequestParam Long id) {
        try {
            registrationService.delete(id);
        } catch (ServiceException e) {
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }
}
