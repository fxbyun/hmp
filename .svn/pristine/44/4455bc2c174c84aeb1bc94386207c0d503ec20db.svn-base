package com.qiaobei.hmp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.support.Constants;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.security.SecuritySupport;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Result;
import com.qiaobei.hmp.support.SMSUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/8/24
 * Time 13:20
 */
@Controller
public class AppointmentController extends BaseController {

    @Resource
    private AppointConfigService appointConfigService;

    @Resource
    private AppointWeekConfigService appointWeekConfigService;

    @Resource
    private AppointTimeConfigService appointTimeConfigService;

    @Resource
    private AppointListService appointListService;

    @Resource
    private AppointPatientService appointPatientService;

    @Resource
    private ErrorLogService errorLogService;
    @Resource
    private MsgMoneyService msgMoneyService;
    @Resource
    private MsgSendHistoryService msgSendHistoryService;
    @Resource
    private MsgSendHistoryDetailService msgSendHistoryDetailService;
    @Resource
    private MsgMeassageService msgMeassageServicel;

    @Resource
    private AppointRewardService appointRewardService;

    @Resource
    private RegistrationService registrationService;

    //测试用
    @Resource
    private PatientService patientService;

    @Resource
    private DoctorService doctorService;

    /**
     * 因为医生余额不足，存储没有发送的短信信息
     */
    private static boolean isInList(Doctor doctor, List<MsgMeassage> msgMeassageList) {
        if (null == msgMeassageList) {
            return false;
        }
        for (MsgMeassage msgMeassage : msgMeassageList) {
            if (msgMeassage.getDoctor().getId().equals(doctor.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 跳转到医生预约页面
     */
    @RequestMapping(value = "/appointment")
    public String appointment(Model model) {
        Doctor doctor = SecuritySupport.getDoctor();
        //得到医生的预约配置
        AppointConfig appointConfig = appointConfigService.findByDoctor(doctor);
        model.addAttribute("appointConfig", appointConfig);
        //得到具体的星期配置
        List<AppointWeekConfig> weekConfigList = appointWeekConfigService.findByDoctor(doctor);
        model.addAttribute("weekConfigList", weekConfigList);
        //得到医生勾选的星期
        ArrayList<String> weekList = Lists.newArrayList();
        //得到所有时间段
        HashedMap weekTime = new HashedMap();
        for (AppointWeekConfig week : weekConfigList) {
            //判断改天是否勾选了
            if (week.getOpenStatic() == AppointWeekConfig.OpenStatic.Open) {
                weekList.add(week.getWeekday().toString());
            }
            //得到该天的所有时间段集合（不分勾不勾选）
            List<AppointTimeConfig> timeList = appointTimeConfigService.findByAppointWeekConfig(week);
            if (timeList != null && timeList.size() != 0) {
                weekTime.put(week.getWeekday().toString(), timeList);
            }
        }
        model.addAttribute("weekList", weekList);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String weekTimeJson = mapper.writeValueAsString(weekTime);
            model.addAttribute("weekTimeJson", weekTimeJson);
        } catch (Exception e) {
            System.out.println("Json转化失败！");
        }
        model.addAttribute("doctor", doctor);
        return "appointment";
    }

    /**
     * 保存预约设置
     */
    @ResponseBody
    @RequestMapping(value = "/appoint/saveConfig")
    public Result saveConfig(@RequestParam(value = "openStatic") boolean openStatic, @RequestParam(value = "weekday") boolean[] weekday,
                             @RequestParam(value = "allDayTime") List<String> allDayTime,
                             @RequestParam(value = "peopleNum") int peopleNum) {

        //判断登录的医生是谁
        Doctor doctor = SecuritySupport.getDoctor();
        //判断该医生是否开启预约功能
        if (!openStatic) {
            return Result.fail("医生关闭了预约功能！");
        }
        //保存医生预约的信息
        int perMin = 15;
        AppointConfig appointConfig = appointConfigService.findByDoctor(doctor);
        if (appointConfig == null) {
            appointConfig = new AppointConfig();
        }
        appointConfig.setDoctor(doctor);
        appointConfig.setFlagChangeDate(new Date());
        appointConfig.setOpenStatic(AppointConfig.Static.Open);
        appointConfig.setPerMin(perMin);
        appointConfig.setPersonNum(peopleNum);
        appointConfigService.save(appointConfig);
        //保存每周工作那些天
        List<AppointWeekConfig> weekConfigList = appointWeekConfigService.findAllOrderByWeekday(doctor);

        for (int i = 0; i < weekday.length; i++) {
            AppointWeekConfig week;
            if (CollectionUtils.isEmpty(weekConfigList) || weekConfigList.get(i) == null) {
                week = new AppointWeekConfig();
            } else {
                week = weekConfigList.get(i);
            }
            //保存星期几
            AppointWeekConfig.Weekday weekArray[] = AppointWeekConfig.Weekday.values();
            System.out.println(weekArray[i]);
            week.setWeekday(weekArray[i]);

            //保存开启状态
            if (weekday[i]) {
                week.setOpenStatic(AppointWeekConfig.OpenStatic.Open);

            } else {
                week.setOpenStatic(AppointWeekConfig.OpenStatic.Close);
            }
            //保存医生
            week.setDoctor(doctor);
            //是否已经开始生产
            week.setProductStatic(AppointWeekConfig.ProductStatic.Not);
            appointWeekConfigService.save(week);

            //为了下面能够保存timeList设置，这里先清空该周几下的所有时间配置
            if (week.getOpenStatic().toString().equals("Open")) {
                //这个方法要测试一下
                appointTimeConfigService.delTimeListByWeekId(week.getId());
            }
        }

        //保存每天工作的时段
        ObjectMapper mapper = new ObjectMapper();
        //一个临时List，保存页面传过来的时间设置
        //List<AppointTimeConfig> tempTimeList = new ArrayList<>();
        for (String dayTimeJson : allDayTime) {
            try {
                //当设置只有一天一个时间段时，参数解析成List<String>时出错。
                if ("{YJZ}".equals(dayTimeJson)) {
                    break;
                }
                DayTime dayTime = mapper.readValue(dayTimeJson, DayTime.class);
                //根据序数得到这个枚举
                int weekdayIndex = Integer.parseInt(dayTime.getWeekday());
                AppointWeekConfig.Weekday weekdayEnum = AppointWeekConfig.Weekday.values()[weekdayIndex];
                //根据医生和周几找到这个时间段设置
                AppointWeekConfig weekConfig = appointWeekConfigService.findByDoctorAndWeekday(doctor, weekdayEnum);
                //根据传过来的id查找timeConfig
                AppointTimeConfig timeConfig;
                //没带id的都是新增加的时段
                /*if(dayTime.getId()==null){
                    //如果为空则是第一次保存，得保存weekday，不是第一次就不用了
                    timeConfig=new AppointTimeConfig();
                    timeConfig.setAppointWeekConfig(weekConfig);
                }else {
                    timeConfig =  appointTimeConfigService.findById(dayTime.getId());
                    //设置为null的原因是（原先是设计直接修改数据库中的数据信息，现在是直接删除原有的设置的）
                }*/
                timeConfig = new AppointTimeConfig();
                timeConfig.setAppointWeekConfig(weekConfig);
                if (weekConfig == null) {
                    return Result.fail("找不到weekday异常");
                }
                //保存开始结束时间段
                String timeStr = dayTime.getTime();
                Date start = dayTime.getStartTime(timeStr);
                Date end = dayTime.getEndTime(timeStr);
                timeConfig.setStartTime(start);
                timeConfig.setEndTime(end);
                //保存该时间段
                appointTimeConfigService.save(timeConfig);
            } catch (Exception e) {
                return Result.fail("时间数据Json格式错误！");
            }
        }

        return Result.ok("设置保存成功！");
    }

    /**
     * 关闭预约按钮
     */
    @ResponseBody
    @RequestMapping(value = "/appoint/closeAppoint")
    public Result closeAppoint(@RequestParam(value = "openStatic") String openStatic) {
        Doctor doctor = SecuritySupport.getDoctor();
        AppointConfig appointConfig = appointConfigService.findByDoctor(doctor);
        if (appointConfig == null) {
            //如果该医生进来后第一次没有保存过预约信息的话，点击关闭预约，新建一个预约信息并保存！
            appointConfig = new AppointConfig();
            appointConfig.setDoctor(doctor);
            appointConfig.setOpenStatic(AppointConfig.Static.Close);
            appointConfig.setFlagChangeDate(new Date());
            appointConfigService.save(appointConfig);
            //#FangXB 医生预约开启bug
            doctor.setAppointStatus(Doctor.DoctorAppointStatus.Close);
            doctorService.saveDoctor(doctor);


            return Result.ok("关闭预约！");
        } else {
            if (openStatic.equals("Close")) {
                appointConfig.setOpenStatic(AppointConfig.Static.Close);
                appointConfig.setFlagChangeDate(new Date());
                appointConfigService.save(appointConfig);

                doctor.setAppointStatus(Doctor.DoctorAppointStatus.Close);
                doctorService.saveDoctor(doctor);
            }
        }
        return Result.ok("关闭预约");
    }

    @ResponseBody
    @RequestMapping(value = "/appoint/openAppoint")
    public Result openAppoint(@RequestParam(value = "openStatic") String openStatic) {
        Doctor doctor = SecuritySupport.getDoctor();
        AppointConfig appointConfig = appointConfigService.findByDoctor(doctor);
        if (appointConfig == null) {
            //如果该医生进来后第一次没有保存过预约信息的话，点击关闭预约，新建一个预约信息并保存！
            appointConfig = new AppointConfig();
            appointConfig.setDoctor(doctor);
            appointConfig.setOpenStatic(AppointConfig.Static.Open);
            appointConfig.setFlagChangeDate(new Date());
            appointConfigService.save(appointConfig);
            doctor.setAppointStatus(Doctor.DoctorAppointStatus.Open);
            doctorService.saveDoctor(doctor);
            return Result.ok("开启预约！");
        } else {
            if (openStatic.equals("Open")) {
                appointConfig.setOpenStatic(AppointConfig.Static.Open);
                appointConfig.setFlagChangeDate(new Date());
                appointConfigService.save(appointConfig);

                doctor.setAppointStatus(Doctor.DoctorAppointStatus.Open);
                doctorService.saveDoctor(doctor);
            }
        }
        return Result.ok("开启预约");
    }

    /**
     * 获得三天的列表信息
     */
    @RequestMapping(value = "/fragment/appoint/appointThreeDay")
    public String appointThreeDay(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "day") String day, Model model) {

        Doctor doctor = SecuritySupport.getDoctor();

        if (StringUtils.isNotEmpty(day)) {
            model.addAttribute("day", day);
        }
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = new PageRequest(page, Constants.APPOINT_PAGE_SIZE);
        //根据"today"等字段查找预约列表
        Page<AppointList> appointPage = appointListService.findThreeDay(pageable, day, doctor);

        //遍历appointPage中的内容，根据ListId获得它的预约号
        Map<Long, List<AppointPatient>> appPatientMap = Maps.newHashMap();
        for (AppointList appList : appointPage.getContent()) {
            List<AppointPatient> appointPatientList = appointPatientService.findAppointPatientByListId(appList.getId());
            appPatientMap.put(appList.getId(), appointPatientList);
        }
        model.addAttribute("appointLists", appointPage.getContent());
        model.addAttribute("appointPage", appointPage);
        model.addAttribute("appPatientMap", appPatientMap);
        return "/fragment/appointList";
    }

    /**
     * 删除单个的预约号
     */
    @RequestMapping(value = "/fragment/appoint/delAppointPatient")
    @ResponseBody
    public Result delAppointPatient(@RequestParam(value = "id") Long id) {
        Doctor doctor = SecuritySupport.getDoctor();
        if (id == null) {
            return Result.fail("Id为空！删除失败");
        }


        //找到这个预约号，并修改他的状态
        AppointPatient appointPatient = appointPatientService.findById(id);
        if (appointPatient == null) {
            return Result.fail("数据库中没有该预约号！删除失败");
        }
        boolean sendMsg = false;
        //如果该状态是已删除，提醒已删除
        if (appointPatient.getAppointStatus() == AppointPatient.Status.Deleted) {
            return Result.fail("该预约号已被删除，不用重复删除！");
        }

        if (appointPatient.getAppointStatus() == AppointPatient.Status.Treatment) {
            return Result.fail("该预约号已就诊，不能删除！");
        }

        AppointList appointList = appointPatient.getAppointList();
        //如果该状态是已预约，那么发送短信
        if (appointPatient.getAppointStatus() == AppointPatient.Status.Distributed) {
            sendMsg = true;
            //
            if (appointList.getPeopleNum() > 0) {
                appointList.setPeopleNum(appointList.getPeopleNum() - 1);
            }

            //医生点击删除的话，那么可用的预约号也将减一
            if (appointList.getRemainder() > 0) {
                appointList.setRemainder(appointList.getRemainder() - 1);
            }
            appointListService.save(appointList);

        } else {
            //医生点击删除的话，那么可用的预约号也将减一
            if (appointList.getRemainder() > 0) {
                appointList.setRemainder(appointList.getRemainder() - 1);
            }
        }
        appointPatient.setAppointStatus(AppointPatient.Status.Deleted);
        appointPatientService.saveAppointPatient(appointPatient);


        //如果该状态是已预约的，发送删除短信提醒
        if (sendMsg) {
            String date = DateUtils.date2Str(appointList.getDate(), DateUtils.date_sdf_wz);
            String startTime = DateUtils.formatShortTime(appointList.getStartTime());
            String endTime = DateUtils.formatShortTime(appointList.getEndTime());
            //短信参数
            String dateTime = date + startTime + "～" + endTime;
            String doctorMobile = doctor.getMobile();
            String zhensuoName = doctor.getOutpatientService();
            //以逗号隔开
            String mobile = appointPatient.getPatient().getMobile();
            String ParamStr = dateTime + "," + doctorMobile + "," + zhensuoName;
            try {
                //测试号码
                SMSUtil.sendSMS(SMSUtil.INFORM_CODE, mobile, ParamStr);
            } catch (Exception e) {
                logger.error("预约号通知短信发送失败！" + e.getMessage());
            }
        }

        //改变打赏单上的状态
        AppointReward reward = appointRewardService.getByAppointPatient(appointPatient);
        if (reward != null) {
            reward.setStatus(AppointReward.RewardStatus.DOC_DELETE);
            appointRewardService.save(reward);
        }

        //从叫号排队列表中删除
        Registration registration = registrationService.getByAppointPatientId(appointPatient.getId());
        if (registration != null) {
            registrationService.delete(registration.getId());
        }


        return Result.ok("预约号删除成功！");
    }

    /**
     * 删除全部的预约号
     */
    @RequestMapping(value = "/fragment/appoint/delAllAppointPatient")
    @ResponseBody
    public Result delAllAppointPatient(@RequestParam(value = "listId") Long listId) {

        Doctor doctor = SecuritySupport.getDoctor();
        int treeNum = 0;
        if (listId == null) {
            return Result.fail("listId为空！删除失败");
        }

        //根据id找到这个列表，删除该列表下的所以预约号
        AppointList appointList = appointListService.findById(listId);
        if (appointList == null) {
            return Result.fail("数据库中不存在该列表");
        }

        List<AppointPatient> appPatientList = appointPatientService.findAppointPatientByListId(listId);

        for (AppointPatient appointPatient : appPatientList) {


            //如果该状态是已预约的，发送删除短信提醒
            if (appointPatient.getAppointStatus() == AppointPatient.Status.Distributed) {

                String date = DateUtils.date2Str(appointList.getDate(), DateUtils.date_sdf_wz);
                String startTime = DateUtils.formatShortTime(appointList.getStartTime());
                String endTime = DateUtils.formatShortTime(appointList.getEndTime());
                //短信参数
                String dateTime = date + startTime + "～" + endTime;
                String doctorMobile = doctor.getMobile();
                String zhensuoName = doctor.getOutpatientService();
                String ParamStr = dateTime + "," + doctorMobile + "," + zhensuoName;
                try {
                    SMSUtil.sendSMS(SMSUtil.INFORM_CODE, appointPatient.getPatient().getMobile(), ParamStr);
                } catch (Exception e) {
                    logger.error("预约号通知短信发送失败！" + e.getMessage());
                }

                //改变打赏单上的状态
                AppointReward reward = appointRewardService.getByAppointPatient(appointPatient);
                if (reward != null) {
                    reward.setStatus(AppointReward.RewardStatus.DOC_DELETE);
                    appointRewardService.save(reward);
                }

                //从叫号排队列表中删除
                Registration registration = registrationService.getByAppointPatientId(appointPatient.getId());
                if (registration != null) {
                    registrationService.delete(registration.getId());
                }

            }
            //如果为已就诊，也不能改变它的状态
            if (appointPatient.getAppointStatus() != AppointPatient.Status.Treatment) {
                appointPatient.setAppointStatus(AppointPatient.Status.Deleted);
            } else {
                //剩余预约人数都是已经就诊的
                treeNum++;
            }
            appointPatientService.saveAppointPatient(appointPatient);


        }
        appointList.setPeopleNum(treeNum);
        appointList.setRemainder(0);
        appointListService.save(appointList);
        return Result.ok("预约号删除成功！");
    }

//    /**
//     * 删除预约号，自动生成短信内容
//     */
//    public String pullMsg(AppointList appointList, Doctor doctor) {
//
//        String date = DateUtils.date2Str(appointList.getDate(), DateUtils.date_sdf_wz);
//        String startTime = DateUtils.formatShortTime(appointList.getStartTime());
//        String endTime = DateUtils.formatShortTime(appointList.getEndTime());
//        String doctorMobile = doctor.getMobile();
//        String zhensuoName = doctor.getOutpatientService();
//
//        return "【易佳诊】您于" + date +
//                startTime +
//                "～" +
//                endTime +
//                "的就诊预约，因医生有急事无法给您提供服务，已经取消，给您带来的不便敬请原谅，咨询电话" +
//                doctorMobile + "。" +
//                zhensuoName + "。退订回复TD";
//    }

    /**
     * 医生自主发送短信
     */
    private boolean sendMessage(AppointPatient appPatient, Doctor doctor, Patient patient, String contentMsg, MsgSendHistory.SendType sendType) {
        List<MsgMeassage> msgMeassageList = msgMeassageServicel.getAll();
        MsgMoney msgMoney = msgMeassageServicel.findByDoctor(doctor);

        double userMoney;
        int sendOneSize = (int) Math.ceil((double) contentMsg.length() /
                (contentMsg.length() <= 70 ? 70 : 67));
        userMoney = Constants.MSG_PRICE * 100 * sendOneSize / 100;


        String patientPhone = patient.getMobile();

        try {
            MsgSendHistory msgSendHistory = new MsgSendHistory();
            //判断钱是否足够
            if (msgMoney.getDeposit() >= userMoney) {
                msgMoney.setDeposit(msgMoney.getDeposit() - userMoney);
                msgSendHistory.setContext(contentMsg);
                msgSendHistory.setCreateDate(new Date());
                msgSendHistory.setDoctor(doctor);
                msgSendHistory.setMsgType(sendType);
                msgSendHistory.setSendSize(1);
                msgSendHistory.setTitle("删除预约号");
                msgSendHistory.setUseMoney(userMoney);
                msgSendHistory.setSuccessSize(1);
            } else {
                if (!isInList(doctor, msgMeassageList)) {
                    MsgMeassage msgMeassage = new MsgMeassage();
                    msgMeassage.setDoctor(doctor);
                    msgMeassage.setInfo(doctor.getName() + "医生,因为您的余额不足,导致您的删除预约号提醒功能短信业务被停止,如需继续使用,请先充值!");
                    msgMeassageServicel.save(msgMeassage);
                }
                System.err.println("医生余额不足,无法发送短信");
                throw new RuntimeException("医生余额不足,无法发送短信");
            }

            //发送短信,如果是自主发送则是使用营销模板，如果是
            SMSUtil.sendSMS(SMSUtil.CUSTOMIZE_ALONE_TEMLATE, patientPhone, contentMsg);


            //创建发送历史详情记录
            MsgSendHistoryDetail msgSendHistoryDetail = new MsgSendHistoryDetail();
            msgSendHistoryDetail.setMsgSendHistory(msgSendHistory);
            msgSendHistoryDetail.setPatient(patient);
            msgSendHistoryDetail.setSendStatus(MsgSendHistoryDetail.SendStatus.Success);


            //设置 当前患者已经 发送过短信
            appPatient.setHasSendMessage(AppointPatient.SendMessage.Has);
            //保存医生的金额表
            msgMoneyService.updateOrSave(msgMoney);
            //保存医生的发送历史表
            msgSendHistoryService.updateOrSave(msgSendHistory);
            //不抱错医生的发送历史详情表
            msgSendHistoryDetailService.updateOrSave(msgSendHistoryDetail);
            //保存预约号
            appointPatientService.saveAppointPatient(appPatient);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            //记录短信发送错误信息
            ErrorLog errorLog = new ErrorLog();
            errorLog.setDoctor(doctor);
            errorLog.setCreateTime(new Date());
            errorLog.setErrorInfo(e.getMessage());
            errorLog.setType("取消预约号短信错误");
            errorLog.setErrorUrl("取消预约号短信推送任务");
            errorLog.setErrorTitle("患者:" + patient.getName() + ",电话为:" + patientPhone + " 发送失败!");
            errorLogService.save(errorLog);
            System.err.println("患者:" + patient.getName() + " 发送失败!");
            return false;
        }

    }

    /**
     * 弹出短信编辑窗口
     */
    @RequestMapping(value = "/msPage/appoint/editMsg")
    public String editMsg(Model model, @RequestParam(value = "appPatientId") Long appPatientId) {
        AppointPatient appPatient = appointPatientService.findById(appPatientId);
        model.addAttribute("appointPatient", appPatient);
        return "msPage/editMsgAppoint";
    }


    /**
     * 发送编辑短信
     */
    @RequestMapping(value = "/appoint/sendMsgAppoint", method = RequestMethod.POST)
    @ResponseBody
    public Result sendMsgAppoint(@RequestParam(value = "contentMsg") String contentMsg, @RequestParam(value = "appPatientId") Long appPatientId) {

        Doctor doctor = SecuritySupport.getDoctor();

        if (contentMsg == null) {
            return Result.fail("信息内容为空！");
        }

        AppointPatient appointPatient = appointPatientService.findById(appPatientId);
        if (appointPatient == null) {
            return Result.fail("没有存在该预约号");
        }

        if (appointPatient.getAppointStatus() != AppointPatient.Status.Distributed) {
            return Result.fail("该预约号还没有被预约，不能发送短信！");
        }
        //测试用的病人
        Patient patient = patientService.getPatientById(appointPatient.getPatient().getId());

        if (patient == null) {
            return Result.fail("该预约号还没有被预定，无法发送短信！");
        }

        boolean flagMsg = sendMessage(appointPatient, doctor, patient, contentMsg, MsgSendHistory.SendType.AUTO);
        if (!flagMsg) {
            System.out.println("--------------短信发送失败！---------------------");
        }

        return Result.ok("短信发送成功！");
    }


    @RequestMapping(value = "/msPage/appSendMes")
    public String appSendMes() {

        return "msPage/appSendMes";
    }
}
