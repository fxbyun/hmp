package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Emr.HAVESEND;
import com.qiaobei.hmp.modules.entity.Emr.TYPE;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Emr.class)
public abstract class Emr_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Emr, String> no;
	public static volatile SingularAttribute<Emr, Boolean> replied;
	public static volatile SingularAttribute<Emr, Long> departmentId;
	public static volatile SingularAttribute<Emr, String> cardPwd;
	public static volatile SingularAttribute<Emr, TYPE> type;
	public static volatile ListAttribute<Emr, EmrMedicine> emrMedicineList;
	public static volatile ListAttribute<Emr, EmrJClassAdviceDict> emrJClassAdviceDicts;
	public static volatile SingularAttribute<Emr, Long> drugRefundId;
	public static volatile SingularAttribute<Emr, String> diagnosisResult;
	public static volatile ListAttribute<Emr, EmrSuggest> emrSuggestList;
	public static volatile SingularAttribute<Emr, Integer> chineseQty;
	public static volatile SingularAttribute<Emr, String> doctorName;
	public static volatile SingularAttribute<Emr, Patient> patient;
	public static volatile SingularAttribute<Emr, String> autoSend;
	public static volatile SingularAttribute<Emr, HAVESEND> haveSend;
	public static volatile SingularAttribute<Emr, String> mainSuit;
	public static volatile SingularAttribute<Emr, Long> cashierId;
	public static volatile ListAttribute<Emr, EmrFile> emrFileList;
	public static volatile SingularAttribute<Emr, Long> autoSendDay;
	public static volatile SingularAttribute<Emr, Date> updateOn;
	public static volatile ListAttribute<Emr, VitalSign> vitalSignList;
	public static volatile SingularAttribute<Emr, String> cashierName;
	public static volatile SingularAttribute<Emr, String> departmentName;
	public static volatile SingularAttribute<Emr, String> patientName;
	public static volatile SingularAttribute<Emr, String> drugRefundName;
	public static volatile ListAttribute<Emr, EmrExtCost> emrExtCostList;
	public static volatile SingularAttribute<Emr, Double> cost;
	public static volatile SingularAttribute<Emr, String> backMedRemarks;
	public static volatile SingularAttribute<Emr, String> patientUid;
	public static volatile ListAttribute<Emr, Evaluate> evaluateList;
	public static volatile SingularAttribute<Emr, Double> beforeBackMonryRealCost;
	public static volatile SingularAttribute<Emr, Doctor> doctor;
	public static volatile ListAttribute<Emr, Diagnosis> diagnosisList;
	public static volatile SingularAttribute<Emr, Integer> westernQty;
	public static volatile SingularAttribute<Emr, Double> realCost;
	public static volatile SingularAttribute<Emr, String> sendMsgInfo;
	public static volatile SingularAttribute<Emr, Date> createOn;
	public static volatile SingularAttribute<Emr, String> backDays;
	public static volatile SingularAttribute<Emr, String> remarks;
	public static volatile SingularAttribute<Emr, String> backTime;

}

