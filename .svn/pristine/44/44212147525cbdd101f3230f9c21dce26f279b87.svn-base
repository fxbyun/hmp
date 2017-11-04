package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.support.DateUtils;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class MyRewards {
    private Long rewardId;
    private Long doctorId;
    private String doctorName;
    private String headUrl;
    private String outServiceName;
    private String specialty;
    private String patientName;
    private String appDate;
    private String appStartTime;
    private String appEndTime;
    private String appStatus;
    private boolean isShowCancel = true;
    public MyRewards() {

    }

    public MyRewards(AppointReward reward,AppointExpire expire,String headUrl) {
        this.rewardId = reward.getId();
        this.doctorId = reward.getDoctor().getId();
        this.doctorName = reward.getDoctor().getName();
        this.headUrl = headUrl;
        this.outServiceName = reward.getDoctor().getOutpatientService();
        this.specialty = reward.getDoctor().getSpecialty();
        this.patientName = reward.getPatientName();
        //#doing
        AppointPatient appointPatient = reward.getAppointPatient();
        if(reward.getAppointPatient()==null){
            if(expire!=null){
                if ("已预约".equals(reward.RewardByEnum()) && DateUtils.dateDiff('d', new Date(), expire.getAppointDate()) < -1) {
                    this.isShowCancel = false;
                }
                this.appDate = DateUtils.date2Str(expire.getAppointDate(), DateUtils.date_sdf);
                this.appStartTime = DateUtils.formatShortTime(expire.getStartTime());
                this.appEndTime = DateUtils.formatShortTime(expire.getEndTime());
            }
        }else{
            if ("已预约".equals(reward.RewardByEnum()) && DateUtils.dateDiff('d', new Date(), appointPatient.getAppointList().getDate()) < -1) {
                this.isShowCancel = false;
            }
            this.appDate = DateUtils.date2Str(appointPatient.getAppointList().getDate(), DateUtils.date_sdf);
            this.appStartTime = DateUtils.formatShortTime(appointPatient.getAppointList().getStartTime());
            this.appEndTime = DateUtils.formatShortTime(appointPatient.getAppointList().getEndTime());
        }
        this.appStatus = reward.RewardByEnum();
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getOutServiceName() {
        return outServiceName;
    }

    public void setOutServiceName(String outServiceName) {
        this.outServiceName = outServiceName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppStartTime() {
        return appStartTime;
    }

    public void setAppStartTime(String appStartTime) {
        this.appStartTime = appStartTime;
    }

    public String getAppEndTime() {
        return appEndTime;
    }

    public void setAppEndTime(String appEndTime) {
        this.appEndTime = appEndTime;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }


    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public boolean getIsShowCancel() {
        return isShowCancel;
    }

    public void setIsShowCancel(boolean showCancel) {
        isShowCancel = showCancel;
    }
}
