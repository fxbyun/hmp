package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Doctor.DoctorAppointStatus;
import com.qiaobei.hmp.modules.entity.Doctor.Doctor_Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Doctor.class)
public abstract class Doctor_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Doctor, String> businessLicense;
	public static volatile SingularAttribute<Doctor, String> deptName;
	public static volatile SingularAttribute<Doctor, String> specialty;
	public static volatile SingularAttribute<Doctor, Gender> gender;
	public static volatile SingularAttribute<Doctor, String> city;
	public static volatile SingularAttribute<Doctor, String> legalCard;
	public static volatile SingularAttribute<Doctor, String> certificate;
	public static volatile SingularAttribute<Doctor, String> businessAddr;
	public static volatile SingularAttribute<Doctor, Long> verifyById;
	public static volatile SingularAttribute<Doctor, DoctorAppointStatus> appointStatus;
	public static volatile SingularAttribute<Doctor, String> verifyBy;
	public static volatile SingularAttribute<Doctor, String> password;
	public static volatile SingularAttribute<Doctor, String> recommendMobile;
	public static volatile SingularAttribute<Doctor, String> province;
	public static volatile SingularAttribute<Doctor, Integer> areaNo;
	public static volatile SingularAttribute<Doctor, Integer> integral;
	public static volatile SingularAttribute<Doctor, String> autoSend;
	public static volatile SingularAttribute<Doctor, String> intro;
	public static volatile SingularAttribute<Doctor, Integer> provinceNo;
	public static volatile SingularAttribute<Doctor, String> legal;
	public static volatile SingularAttribute<Doctor, Date> verifyOn;
	public static volatile SingularAttribute<Doctor, String> tel;
	public static volatile SingularAttribute<Doctor, Long> autoSendDay;
	public static volatile SingularAttribute<Doctor, String> recommender;
	public static volatile SingularAttribute<Doctor, String> email;
	public static volatile SingularAttribute<Doctor, String> needAlonePrinTypeStrings;
	public static volatile SingularAttribute<Doctor, String> wealth;
	public static volatile SingularAttribute<Doctor, String> area;
	public static volatile SingularAttribute<Doctor, Integer> cityNo;
	public static volatile SingularAttribute<Doctor, Doctor_Type> doctorType;
	public static volatile SingularAttribute<Doctor, String> autoSendActivateMsg;
	public static volatile SingularAttribute<Doctor, String> salt;
	public static volatile SingularAttribute<Doctor, String> outpatientService;
	public static volatile SingularAttribute<Doctor, String> printType;
	public static volatile SingularAttribute<Doctor, String> printInfo;
	public static volatile SingularAttribute<Doctor, String> clinicInfo;
	public static volatile SingularAttribute<Doctor, String> mobile;
	public static volatile SingularAttribute<Doctor, String> wxId;
	public static volatile SingularAttribute<Doctor, Integer> evaluationCount;
	public static volatile SingularAttribute<Doctor, String> name;
	public static volatile SingularAttribute<Doctor, Integer> integration;
	public static volatile SingularAttribute<Doctor, Date> createOn;
	public static volatile SingularAttribute<Doctor, String> printModel;
	public static volatile SingularAttribute<Doctor, Long> primaryDoctorId;
	public static volatile SingularAttribute<Doctor, Integer> age;
	public static volatile SingularAttribute<Doctor, String> card;
	public static volatile SingularAttribute<Doctor, Integer> seniority;

}

