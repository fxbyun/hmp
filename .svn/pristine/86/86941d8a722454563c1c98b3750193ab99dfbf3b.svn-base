package com.qiaobei.hmp.web;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.service.AppointExpireService;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
@Controller
public class PersonalController extends BaseController {

    private final PatientService patientService;

    private final AppointPatientService appointPatientService;


    private final AppointRewardService appointRewardService;

    private final DoctorService doctorService;

    private final AppointListService appointListService;

    private final RegistrationService registrationService;

    private final AppointExpireService appointExpireService;

    @Autowired
    public PersonalController(DoctorService doctorService, AppointRewardService appointRewardService, AppointListService appointListService, RegistrationService registrationService, AppointExpireService appointExpireService, PatientService patientService, AppointPatientService appointPatientService) {
        this.doctorService = doctorService;
        this.appointRewardService = appointRewardService;
        this.appointListService = appointListService;
        this.registrationService = registrationService;
        this.appointExpireService = appointExpireService;
        this.patientService = patientService;
        this.appointPatientService = appointPatientService;
    }

    //TODO  个人主页 2016-8-19 10:33:54
    @RequestMapping(value = "personal/perHomepage")
    public String perHomepage(Model model) {
        MobileUser user = SecuritySupport.getMobileUser();
        if (user == null) {
            System.out.println("session失效或者该用户没有登录");
            return "login";
        }
        model.addAttribute("user", user);
        return "personal/perHomepage";
    }

    //TODO  我的挂号  2016-8-19 14:39:09
    @RequestMapping(value = "personal/myReward")
    public String myReward(Model model) {
        MobileUser currentUser = SecuritySupport.getMobileUser();

        assert currentUser != null;
        List<AppointReward> rewardList = appointRewardService.findByMobile(currentUser.getMobile());

        List<MyRewards> myRewardList = new ArrayList<>();
        for (AppointReward reward : rewardList) {
            String headUrl = doctorService.findDoctorHeaderUrl(reward.getDoctor().getId());
            if (headUrl == null) {
                headUrl = "doctor.jpg";
            }
            AppointExpire appointExpire = null;
            if (reward.getAppointPatient() == null) {
                appointExpire = appointExpireService.findByOrderId(reward.getOrderId());
            }


            //被封装的数据,传到前台
            MyRewards myReward = new MyRewards(reward, appointExpire, headUrl);
            myRewardList.add(myReward);
        }
        model.addAttribute("myRewardList", myRewardList);
        return "personal/myRegister";
    }

    //TODO  我的咨询  2016-8-19 14:39:09
    @RequestMapping(value = "personal/myConsulting")
    public String myConsulting() {

        return "personal/myConsulting";
    }

    @RequestMapping(value = "personal/savePatient", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("patient") Patient patient, RedirectAttributes redirectAttributes) {
//        if (patient.getStatus() == StatusEntity.Status.Unactivated) {
//            try {
//                SMSUtil.sendSMS(SMSUtil.ACTIVE_TEMPLATE, patient.getMobile(), SecuritySupport.getDoctor()
//                        .getOutpatientService() + "," + patient.getUid());
//            } catch (Exception e) {
//                logger.error("激活短信发送失败：" + e.getMessage());
//            }
//        }
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("msg", "患者信息成功保存");
        return "redirect:/personal/perPatientManage";
    }

    //TODO  就诊人管理  2016-8-19 16:21:29
    @RequestMapping(value = "personal/perPatientManage", method = RequestMethod.GET)
    public String perPatientManage(Model model) {
        //得到当前登录的用户id和手机号
        MobileUser currentUser = SecuritySupport.getMobileUser();
        if (currentUser == null) {
            return "login";
        }
        List<Patient> patientList = patientService.listPatientByMobile(currentUser.getMobile());
        model.addAttribute("patientList", patientList);
        return "personal/perPatientManage";
    }

    //TODO  关于我们  2016-8-19 17:02:33
    @RequestMapping(value = "personal/aboutUs")
    public String aboutUs() {

        return "personal/aboutUs";
    }

    @RequestMapping(value = "personal/delPatient")
    @ResponseBody
    public Result delPatient(@RequestParam(value = "patientId") Long patientId) {

        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            return Result.fail("该患者不存在！");
        }
        patient.setStatus(StatusEntity.Status.Removed);
        patientService.savePatient(patient);
        return Result.ok("该患者信息删除成功！");
    }

    @RequestMapping(value = "personal/delMyReward")
    @ResponseBody
    public Result delMyReward(@RequestParam(value = "rewardId") Long rewardId) {
        AppointReward appointReward = appointRewardService.getById(rewardId);
        if (appointReward == null) {
            return Result.fail("没有该预约号！");
        }
        if (appointReward.getStatus() == AppointReward.RewardStatus.SUCCESS || appointReward.getStatus() == AppointReward.RewardStatus.NOT_PAID) {
            //修改预约打赏为自己删除状态
            appointReward.setStatus(AppointReward.RewardStatus.MY_DELETE);
            appointRewardService.save(appointReward);

            //修改医生端的显示
            AppointPatient appointPatient = appointReward.getAppointPatient();
            appointPatient.setAppointStatus(AppointPatient.Status.Canceled);
            appointPatientService.saveAppointPatient(appointPatient);

            //预约列表人数设置
            AppointList appointList = appointPatient.getAppointList();
            if (appointList.getPeopleNum() > 0) {
                appointList.setPeopleNum(appointList.getPeopleNum() - 1);
            }
            if (appointList.getRemainder() < appointList.getConfigPeopleNum()) {
                appointList.setRemainder(appointList.getRemainder() + 1);
            }
            appointListService.save(appointList);

            //取消订单号后排队表中，删除该排队表中记录
            Registration registration = registrationService.getByAppointPatientId(appointPatient.getId());
            if (registration != null) {
                registrationService.delete(registration.getId());
            }

        } else {
            return Result.fail("该预约号已被删除！");
        }

        return Result.ok("预约已取消！");
    }

}













