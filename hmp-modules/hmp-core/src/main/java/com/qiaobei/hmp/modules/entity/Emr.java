package com.qiaobei.hmp.modules.entity;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.support.Constants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.*;


/**
 * 电子病历
 */
@Entity
@Table(name = "emr")
@DynamicInsert()
@DynamicUpdate()
public class Emr extends StatusEntity {

    private String no;
    private String patientUid;
    private String patientName;
    private String doctorName;
    private Long departmentId;
    private String departmentName;

    private Patient patient;
    private Doctor doctor;

    private String mainSuit; //主诉
    private String diagnosisResult;//诊断结果
    private String backDays;//复诊天数
    private String backTime;//上午还是下午  1 2

    //    private List<String> mainSuitList = Lists.newArrayList();
//    private List<String> diagnosisResultList = Lists.newArrayList();
    private List<Long> symptomTagIds = Lists.newArrayList();
    private List<Long> diagnosisTagIds = Lists.newArrayList();

    private Date createOn; //时间
    private Date updateOn; //修改时间
    private Boolean replied;
    private Integer westernQty = 0;
    private Integer chineseQty = 0;
    private List<EmrMedicine> emrMedicineList = Lists.newArrayList();
    private List<EmrSuggest> emrSuggestList = Lists.newArrayList();
    private List<VitalSign> vitalSignList = Lists.newArrayList();
    private List<Diagnosis> diagnosisList = Lists.newArrayList();
    private List<Evaluate> evaluateList = Lists.newArrayList();//患者咨询、评论
    private List<EmrJClassAdviceDict> emrJClassAdviceDicts = Lists.newArrayList();//检查检验
    private List<EmrExtCost> emrExtCostList = Lists.newArrayList();//附加费用
    private List<EmrFile> emrFileList = Lists.newArrayList();//图片
    private Double cost;//费用

    private String autoSend;
    private String sendMsgInfo;
    private String cardPwd;
    private Long autoSendDay;
    private HAVESEND haveSend;//是否已经发送
    private TYPE type;
    private String remarks;


    //新增加的收银员和退药人员
    private Long cashierId;             //收银员id
    private String cashierName;         //收银员姓名

    private Long drugRefundId;          //退药人id
    private String drugRefundName;      //退药人姓名

    private Double realCost;           //实收金额
    private Double beforeBackMonryRealCost; //退钱之前的实收金额

    private String backMedRemarks;    //退药备注


    public Emr() {
    }

    public Emr(Long id, Date createOn, Patient patient) {
        super(id);
        this.createOn = createOn;
        this.patient = patient;
    }

    public Emr(Long id) {
        super(id);
    }

    public static Map<String, List<EmrMedicine>> sortMapByKey(Map<String, List<EmrMedicine>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, List<EmrMedicine>> sortMap = new TreeMap<>(
                (o1, o2) -> {
                    if (o1 == null) {
                        return 0;
                    }
                    return o1.compareTo(o2);
                });
        sortMap.putAll(map);
        return sortMap;
    }


    @Column(name = "before_back_monry_real_cost")
    public Double getBeforeBackMonryRealCost() {
        return beforeBackMonryRealCost;
    }

    public void setBeforeBackMonryRealCost(Double beforeBackMonryRealCost) {
        this.beforeBackMonryRealCost = beforeBackMonryRealCost;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "emr")
    public List<EmrFile> getEmrFileList() {
        return emrFileList;
    }

    public void setEmrFileList(List<EmrFile> emrFileList) {
        this.emrFileList = emrFileList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "emr")
    public List<EmrExtCost> getEmrExtCostList() {
        return emrExtCostList;
    }

    public void setEmrExtCostList(List<EmrExtCost> emrExtCostList) {
        this.emrExtCostList = emrExtCostList;
    }

    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "card_pwd")
    public String getCardPwd() {
        return cardPwd;
    }

    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }

    @Column(name = "send_msg_info")
    public String getSendMsgInfo() {
        return sendMsgInfo;
    }

    public void setSendMsgInfo(String sendMsgInfo) {
        this.sendMsgInfo = sendMsgInfo;
    }

    @Column(name = "have_send")
    public HAVESEND getHaveSend() {
        return haveSend;
    }

    public void setHaveSend(HAVESEND haveSend) {
        this.haveSend = haveSend;
    }

    @Column(name = "auto_send", length = 100)
    public String getAutoSend() {
        return autoSend;
    }

    public void setAutoSend(String autoSend) {
        this.autoSend = autoSend;
    }

    @Column(name = "auto_send_day", length = 100)
    public Long getAutoSendDay() {
        return autoSendDay;
    }

    public void setAutoSendDay(Long autoSendDay) {
        this.autoSendDay = autoSendDay;
    }

    @Column(name = "type")
    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    @Column(name = "no", length = 20)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Column(name = "patient_uid", length = 20)
    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }

    @Column(name = "patient_name", length = 20)
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "department_id")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "department_name", length = 20)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Column(name = "doctor_name", length = 20)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_on")
    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "main_suit", length = 255)
    public String getMainSuit() {
        return mainSuit;
    }

    public void setMainSuit(String mainSuit) {
        this.mainSuit = mainSuit;
    }

    @Transient
    public List<String> getMainSuitList() {
        if (StringUtils.isNotEmpty(mainSuit))
            return Lists.newArrayList(StringUtils.split(mainSuit, Constants.TEXT_SEPARATOR));
        return Lists.newArrayList();
    }

    @Column(name = "diagnosis_result", length = 255)
    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    @Column(name = "back_days", length = 10)
    public String getBackDays() {
        return backDays;
    }

    public void setBackDays(String backDays) {
        this.backDays = backDays;
    }

    @Column(name = "back_time", length = 10)
    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        if (doctor != null) {
            this.doctorName = doctor.getName();
        }
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "emr")
    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(List<Diagnosis> diagnoses) {
        this.diagnosisList = diagnoses;
    }

    @Column(name = "replied", length = 1)
    public Boolean getReplied() {
        return replied;
    }

    public void setReplied(Boolean replied) {
        this.replied = replied;
    }

    @Column(name = "western_qty")
    public Integer getWesternQty() {
        return westernQty;
    }

    public void setWesternQty(Integer westernQty) {
        this.westernQty = westernQty;
    }

    @Column(name = "chinese_qty")
    public Integer getChineseQty() {
        return chineseQty;
    }

    public void setChineseQty(Integer chineseQty) {
        this.chineseQty = chineseQty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emr")
    public List<Evaluate> getEvaluateList() {
        return evaluateList;
    }

    public void setEvaluateList(List<Evaluate> evaluateList) {
        this.evaluateList = evaluateList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "emr")
    public List<EmrJClassAdviceDict> getEmrJClassAdviceDicts() {
        return emrJClassAdviceDicts;
    }

    public void setEmrJClassAdviceDicts(List<EmrJClassAdviceDict> emrJClassAdviceDicts) {
        this.emrJClassAdviceDicts = emrJClassAdviceDicts;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "emr")
    public List<EmrMedicine> getEmrMedicineList() {
        return emrMedicineList;
    }

    public void setEmrMedicineList(List<EmrMedicine> emrMedicineList) {
        this.emrMedicineList = emrMedicineList;
    }

    private Predicate<EmrMedicine> buildPredicateByMedicineType(final Medicine.Type type) {
        return input -> input.getMedicineType() == type;
    }

    private Predicate<EmrMedicine> buildPredicateByHasUsage() {
        return input -> {
            Boolean hasUsage = input.getHasUsage();
            return hasUsage != null && hasUsage.booleanValue();
        };
    }

    @Column(name = "cost")
    public Double getCost() {
        if (cost == null)
            return 0D;
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Transient
    public List<Long> getSymptomTagIds() {
        return symptomTagIds;
    }

    public void setSymptomTagIds(List<Long> symptomTagIds) {
        this.symptomTagIds = symptomTagIds;
    }

    @Transient
    public List<Long> getDiagnosisTagIds() {
        return diagnosisTagIds;
    }

    public void setDiagnosisTagIds(List<Long> diagnosisTagIds) {
        this.diagnosisTagIds = diagnosisTagIds;
    }

    @Transient
    public Map<String, List<EmrMedicine>> getMedicinesHasUsage() {
        Map<String, List<EmrMedicine>> map = Maps.newHashMap();
        Collection<EmrMedicine> medicines = Collections2.filter(emrMedicineList, buildPredicateByHasUsage());
        for (EmrMedicine emrMedicine : medicines) {
            String useMode = emrMedicine.getUseMode();
            List<EmrMedicine> list = map.get(useMode);
            if (list == null) list = Lists.newArrayList();
            list.add(emrMedicine);
            map.put(useMode, list);
        }
        return map;
    }

    @Transient
    public Map<String, List<String>> getDoctorAdviceList() {
        Map<String, List<String>> map = Maps.newHashMap();
        Collection<EmrMedicine> emrMedicines = Collections2.filter(emrMedicineList, buildPredicateByHasUsage());
        for (EmrMedicine emrMedicine : emrMedicines) {
            String useMode = emrMedicine.getUseMode();
            List<String> list = map.get(useMode);
            if (list == null) list = Lists.newArrayList();
            String advice = String.format("%s&nbsp;&nbsp;%s", emrMedicine.getMedicine().getUseTimes(), emrMedicine
                    .getMedicine().getUsingTime());
            if (!list.contains(advice)) list.add(advice);
            map.put(useMode, list);
        }
        return map;
    }

    @Transient
    private Map<String, List<EmrMedicine>> getMedicinesByUseMode(Medicine.Type type) {
        Map<String, List<EmrMedicine>> map = Maps.newHashMap();
        Collection<EmrMedicine> emrMedicines = Collections2.filter(emrMedicineList, buildPredicateByMedicineType(type));
        for (EmrMedicine emrMedicine : emrMedicines) {
            String useMode = emrMedicine.getUseMode();
            List<EmrMedicine> list = map.get(useMode);
            if (list == null) list = Lists.newArrayList();
            list.add(emrMedicine);
            Collections.sort(list, (o1, o2) -> {
                if (o1.getGroupIndex() == null) {
                    o1.setGroupIndex("");
                }
                if (o2.getGroupIndex() == null) {
                    o2.setGroupIndex("");
                }
                return o1.getGroupIndex().compareTo(o2.getGroupIndex());
            });
            map.put(useMode, list);
        }
        return map;
    }

    @Transient
    public Map<String, List<EmrMedicine>> getWesternMedicinesByUseMode() {
        return getMedicinesByUseMode(Medicine.Type.Western);
    }

    @Transient
    public Map<String, Map<String, List<EmrMedicine>>> getWesternMedicinesByUseModeAndGruopId() {

        return getMedByUseModeAndMedTypeAndGroupId(Medicine.Type.Western);
    }

    @Transient
    private Map<String, Map<String, List<EmrMedicine>>> getMedByUseModeAndMedTypeAndGroupId(Medicine.Type medType) {
        Map<String, Map<String, List<EmrMedicine>>> map2 = Maps.newHashMap();
        Collection<EmrMedicine> emrMedicines233 = Collections2.filter(emrMedicineList, buildPredicateByMedicineType
                (medType));
        for (EmrMedicine emrMedicine2 : emrMedicines233) {
            Map<String, List<EmrMedicine>> map = Maps.newHashMap();
            String useMode2 = emrMedicine2.getUseMode();

            if ("10".equals(emrMedicine2.getGroupIndex()) && !"研末".equals(emrMedicine2.getGroupIndex())) {
                emrMedicine2.setGroupIndex("0");
            }
            List<EmrMedicine> list2 = map.get(useMode2);
            if (list2 == null) list2 = Lists.newArrayList();
            list2.add(emrMedicine2);
            if (map2.size() > 0) {
                map = map2.get(useMode2);
                if (map != null && map.size() > 0) {
                    List<EmrMedicine> emrMedicineList = map.get(emrMedicine2.getGroupIndex());
                    if (emrMedicineList != null && emrMedicineList.size() > 0) {
                        emrMedicineList.add(emrMedicine2);
                        map.put(emrMedicine2.getGroupIndex(), emrMedicineList);
                    } else {
                        map = map2.get(useMode2);
                        map.put(emrMedicine2.getGroupIndex(), list2);
                    }
                } else {
                    map = Maps.newHashMap();
                    map.put(emrMedicine2.getGroupIndex(), list2);
                }
            } else {
                map = Maps.newHashMap();
                map.put(emrMedicine2.getGroupIndex(), list2);
            }
            if (map != null) {
                map = sortMapByKey(map);
            }

            map2.put(useMode2, map);
        }
        return map2;
    }

    @Transient
    public Map<String, Map<String, List<EmrMedicine>>> getChinaMedicinesByUseModeAndGruopId() {
        return getMedByUseModeAndMedTypeAndGroupId(Medicine.Type.Chinese);
    }

    @Transient
    public Map<String, List<EmrMedicine>> getChineseMedicinesByUseMode() {
        return getMedicinesByUseMode(Medicine.Type.Chinese);
    }

    @Transient
    public Collection<EmrMedicine> getWesternItems() {
        return Collections2.filter(emrMedicineList, buildPredicateByMedicineType(Medicine.Type.Western));
    }

    @Transient
    public Collection<EmrMedicine> getChineseItems() {
        return Collections2.filter(emrMedicineList, buildPredicateByMedicineType(Medicine.Type.Chinese));
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emr")
    public List<VitalSign> getVitalSignList() {
        return vitalSignList;
    }

    public void setVitalSignList(List<VitalSign> vitalSignList) {
        this.vitalSignList = vitalSignList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "emr")
    public List<EmrSuggest> getEmrSuggestList() {
        return emrSuggestList;
    }

    public void setEmrSuggestList(List<EmrSuggest> emrSuggestList) {
        this.emrSuggestList = emrSuggestList;
    }

    //新增加的收银员和退药人员
    @Column(name = "cashier_id")
    public Long getCashierId() {
        if (cashierId == null)
            return 0L;
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    @Column(name = "cashier_name")
    public String getCashierName() {
        if (cashierName == null)
            return "无收银";
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    @Column(name = "drug_refund_id")
    public Long getDrugRefundId() {
        return drugRefundId;
    }

    public void setDrugRefundId(Long drugRefundId) {
        this.drugRefundId = drugRefundId;
    }

    @Column(name = "drug_refund_name")
    public String getDrugRefundName() {
        return drugRefundName;
    }

    public void setDrugRefundName(String drugRefundName) {
        this.drugRefundName = drugRefundName;
    }

    @Transient
    public String getVitalSignText() {
        List<VitalSign> vs = this.getVitalSignList();
        StringBuffer sb = new StringBuffer("");
        for (VitalSign v : vs) {
            switch (v.getType()) {
                case Lbp:
                    if (v.getValue() != null) {
                        sb.append("血压" + v.getValue());
                    }
                    break;
                case Hbp:
                    if (v.getValue() != null) {
                        sb.append("/" + v.getValue() + "mmHg;");
                    }
                    break;
                case Hr:
                    if (v.getValue() != null) {
                        sb.append("心率" + v.getValue() + "次/分钟;");
                    }
                    break;
                case Glu:
                    if (v.getValue() != null) {
                        sb.append("血糖" + v.getValue() + "mg/ml;");
                    }
                    break;
                case Bt:
                    if (v.getValue() != null) {
                        sb.append("体温" + v.getValue() + "℃;");
                    }
                    break;
                case Br:
                    if (v.getValue() != null) {
                        sb.append("呼吸" + v.getValue() + "次/分钟");
                    }
                    break;
                case Ns:
                    if (v.getValue() != null) {
                        sb.append("尿酸" + v.getValue() + "umol/L");
                    }
                    break;
                default:

            }
        }
        return sb.toString();
    }

    @Transient
    public String getMainSuitText() {
        String suitTxt = "";
        if (this.getMainSuit() != null && !this.getMainSuit().equals("")) {
            suitTxt = this.getMainSuit().replaceAll(";", "");
        }
        return suitTxt;
    }

    @Column(name = "real_cost")
    public Double getRealCost() {
        if (realCost == null)
            return 0D;
        return realCost;
    }

    public void setRealCost(Double realCost) {
        this.realCost = realCost;
    }

    @Column(name = "back_med_remarks")
    public String getBackMedRemarks() {
        return backMedRemarks;
    }

    public void setBackMedRemarks(String backMedRemarks) {
        this.backMedRemarks = backMedRemarks;
    }


    public enum TYPE {
        COMMON, TMP
    }

    public enum HAVESEND {
        YES, NO
    }
}
