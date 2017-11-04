package com.qiaobei.hmp.schedule;

import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.service.*;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.DateFilter;
import com.qiaobei.hmp.support.DateUtils;
import com.qiaobei.hmp.support.SMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("autoSendMessagePreocess")
public class AutoSendMessagePreocess {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"); //设置时间格式
    private static Logger log = LoggerFactory.getLogger(AutoSendMessagePreocess.class);
    @Resource
    private EmrService emrService;
    @Resource
    private RegistrationService registrationService;
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

    private static String pullMsg(Emr emr, Doctor doctor) {
        return emr.getPatientName() + "，感谢您于" + sdf.format(emr.getCreateOn()) +
                "选择" +
                doctor.getOutpatientService() +
                "就诊，" +
                "不知您是否已康复，若有需要，可到门诊复查或咨询" +
                doctor.getMobile() + "。";
    }

    public void autoSendMessages() {
        List<Emr> emrList = emrService.getEmrListByNotSendIsNeddAutoSend();
        List<MsgMeassage> msgMeassageList = msgMeassageServicel.getAll();

        Date date2 = new Date();
        date2.setHours(23);
        date2.setMinutes(59);
        for (Emr emr : emrList) {
            boolean isNeddAutoSend = false;
            try {
                isNeddAutoSend = DateFilter.getSpecifiedDayAfter(emr.getCreateOn(), emr.getAutoSendDay()).getTime()
                        < date2.getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isNeddAutoSend) {
                //System.out.println("患者:" + emr.getPatientName() + "  已经发送!");
                //获取短信模板
                Doctor doctor = emr.getDoctor();
                MsgMoney msgMoney = msgMeassageServicel.findByDoctor(doctor);
                String sendStr;
                double userMoney = 0.09D;
                if (emr.getSendMsgInfo() != null && !"".equals(emr.getSendMsgInfo())) {
                    sendStr = emr.getSendMsgInfo();
                    int sendOneSize = (int) Math.ceil((double) sendStr.length() /
                            (sendStr.length() <= 70 ? 70 : 67));
                    userMoney = Constants.MSG_PRICE * 100 * sendOneSize / 100;
                } else {
                    sendStr = pullMsg(emr, doctor);
                }

                String patientMobile = emr.getPatient().getMobile();
                String patientName = emr.getPatient().getName();
                try {
                    MsgSendHistory msgSendHistory = new MsgSendHistory();
                    //判断钱是否足够
                    if (msgMoney.getDeposit() >= userMoney) {
                        msgMoney.setDeposit(msgMoney.getDeposit() - userMoney);
                        msgSendHistory.setContext(sendStr);
                        msgSendHistory.setCreateDate(new Date());
                        msgSendHistory.setDoctor(doctor);
                        msgSendHistory.setMsgType(MsgSendHistory.SendType.AUTO);
                        msgSendHistory.setSendSize(1);
                        msgSendHistory.setTitle("诊后自动回访");
                        msgSendHistory.setUseMoney(userMoney);
                        msgSendHistory.setSuccessSize(1);
                    } else {
                        if (!isInList(doctor, msgMeassageList)) {
                            MsgMeassage msgMeassage = new MsgMeassage();
                            msgMeassage.setDoctor(doctor);
                            msgMeassage.setInfo(doctor.getName() + "医生,因为您的余额不足,导致您的诊后回访短信业务被停止,如需继续使用,请先充值!");
                            msgMeassageServicel.save(msgMeassage);
                        }
                        System.err.println("医生余额不足,无法发送短信");
                        throw new RuntimeException("医生余额不足,无法发送短信");
                    }

                    //发送短信
                    SMSUtil.sendSMS(SMSUtil.CUSTOMIZE_ALONE_TEMLATE, patientMobile, sendStr);

                    //创建发送历史详情记录
                    MsgSendHistoryDetail msgSendHistoryDetail = new MsgSendHistoryDetail();
                    msgSendHistoryDetail.setMsgSendHistory(msgSendHistory);
                    msgSendHistoryDetail.setPatient(emr.getPatient());
                    msgSendHistoryDetail.setSendStatus(MsgSendHistoryDetail.SendStatus.Success);


                    //设置 当前患者已经 发送过短信
                    emr.setHaveSend(Emr.HAVESEND.YES);
                    //保存医生的金额表
                    msgMoneyService.updateOrSave(msgMoney);
                    //保存医生的发送历史表
                    msgSendHistoryService.updateOrSave(msgSendHistory);
                    //不抱错医生的发送历史详情表
                    msgSendHistoryDetailService.updateOrSave(msgSendHistoryDetail);
                    //保存Emr 病历
                    emrService.saveAndPullEmr(emr);
                } catch (Exception e) {
                    e.printStackTrace();
                    //记录短信发送错误信息
                    ErrorLog errorLog = new ErrorLog();
                    System.out.println("");
                    errorLog.setDoctor(doctor);
                    errorLog.setCreateTime(new Date());
                    errorLog.setErrorInfo(e.getMessage());
                    errorLog.setType("诊后短信错误");
                    errorLog.setErrorUrl("诊后短信推送任务");
                    errorLog.setErrorTitle("患者:" + patientName + ",电话为:" + patientMobile + " 发送失败!");
                    errorLogService.save(errorLog);
                    System.err.println("患者:" + emr.getPatientName() + " 发送失败!");
                }

            }
        }

    }

    public void autoDelVistiList() {
        System.out.println("");
        log.info("autoDelVistiList Task start:" + DateUtils.date2Str(DateUtils.time_sdf));
        registrationService.deleteAllRegistration();
        log.info("autoDelVistiList Task end");
        System.out.println("");
    }
}
