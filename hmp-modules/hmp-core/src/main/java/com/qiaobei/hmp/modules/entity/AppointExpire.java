package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.support.AppointEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
@Entity
@Table(name = "appoint_expire")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppointExpire extends IdEntity {


    private Date createDate;                //预约列表产生时间
    private Date startTime;                 //预约开始时间
    private Date endTime;                   //预约结束时间
    private Long doctorId;                  //医生信息
    private AppointPatient.Status appointStatus;    //预约状态
    private Long patientId;                //病患信息
    private AppointPatient.SendMessage hasSendMessage;     //是否发送短信
    private Date appointDate;               //预约时间
    private Long mobileUserId;          //登录者信息
    private String message;                 //备注信息
    private Double money;                   //打赏金额
    private AppointReward.RewardStatus rewardStatus;      //预约订单状态
    private String orderId;                 //打赏订单id
    private Date payDate;                   //支付时间



    private String patientName;             //病患姓名
    private String mobileNum;               //手机号码

    public AppointExpire() {
    }

    public AppointExpire(AppointReward reward) {
        AppointPatient appPatient = reward.getAppointPatient();
        //为appExpire设值
        //使用reward中的patientId
        this.patientId=reward.getPatientId();
        this.patientName=reward.getPatientName();
        this.hasSendMessage=appPatient.getHasSendMessage();
        this.startTime=appPatient.getAppointList().getStartTime();
        this.endTime=appPatient.getAppointList().getEndTime();
        this.appointDate=appPatient.getDate();
        this.appointStatus=appPatient.getAppointStatus();
        this.createDate=appPatient.getAppointList().getDate();
        this.doctorId=reward.getDoctor().getId();
        this.message=reward.getMessage();
        this.mobileUserId=reward.getMobileUser().getId();
        this.mobileNum=reward.getMobile();
        this.money=reward.getMoney();
        this.orderId=reward.getOrderId();
        this.payDate=reward.getPayDate();
        this.rewardStatus=reward.getStatus();

    }





    public AppointExpire(Date createDate, Date startTime,
                         Date endTime, Long doctorId,
                         AppointPatient.Status appointStatus,
                         Long patientId, AppointPatient.SendMessage hasSendMessage,
                         Date appointDate, Long mobileUserId,
                         String message, Double money,
                         AppointReward.RewardStatus rewardStatus, String orderId,
                         Date payDate, String patientName, String mobileNum) {
        this.createDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctorId = doctorId;
        this.appointStatus = appointStatus;
        this.patientId = patientId;
        this.hasSendMessage = hasSendMessage;
        this.appointDate = appointDate;
        this.mobileUserId = mobileUserId;
        this.message = message;
        this.money = money;
        this.rewardStatus = rewardStatus;
        this.orderId = orderId;
        this.payDate = payDate;
        this.patientName = patientName;
        this.mobileNum = mobileNum;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    @Column(name = "appoint_status")
    @Enumerated(value = EnumType.ORDINAL)
    public AppointPatient.Status getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(AppointPatient.Status appointStatus) {
        this.appointStatus = appointStatus;
    }



    @Column(name = "has_send_message")
    @Enumerated(value = EnumType.ORDINAL)
    public AppointPatient.SendMessage getHasSendMessage() {
        return hasSendMessage;
    }

    public void setHasSendMessage(AppointPatient.SendMessage hasSendMessage) {
        this.hasSendMessage = hasSendMessage;
    }

    @Column(name = "appoint_date")
    public Date getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }


    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "money")
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Column(name = "reward_status")
    @Enumerated(value = EnumType.ORDINAL)
    public AppointReward.RewardStatus getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(AppointReward.RewardStatus rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Column(name = "pay_date")
    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Column(name = "doctor_id")
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "patient_id")
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Column(name = "mobile_user_id")
    public Long getMobileUserId() {
        return mobileUserId;
    }

    public void setMobileUserId(Long mobileUserId) {
        this.mobileUserId = mobileUserId;
    }

    @Column(name = "patient_name")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "mobile_num")
    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }


    public String StatusByEnum(){
        if(this.appointStatus== AppointPatient.Status.Undistributed){
            return "未预约";
        }else if(this.appointStatus== AppointPatient.Status.Distributed) {
            return "已预约";
        }else if(this.appointStatus== AppointPatient.Status.Canceled){
            return "已取消";
        }else if(this.appointStatus== AppointPatient.Status.Treatment){
            return "已就诊";
        }else if(this.appointStatus== AppointPatient.Status.Deleted){
            return "已删除";
        }else {
            return "未就诊";
        }
    }

    public String RewardByEnum(){
        if(this.rewardStatus== AppointReward.RewardStatus.NOT_PAID){
            return "已预约";
        }else if(this.rewardStatus== AppointReward.RewardStatus.SUCCESS) {
            return "已预约";
        }else if(this.rewardStatus== AppointReward.RewardStatus.FAIL){
            return "预约失败";
        }else if(this.rewardStatus== AppointReward.RewardStatus.MY_DELETE){
            return "已取消";
        }else if(this.rewardStatus== AppointReward.RewardStatus.DOC_DELETE){
            return "已删除";
        }else {
            return "已预约";
        }
    }

}
