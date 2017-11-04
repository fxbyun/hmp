package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Patient.class)
public abstract class Patient_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Patient, Date> birthday;
	public static volatile SingularAttribute<Patient, String> area;
	public static volatile SingularAttribute<Patient, Integer> cityNo;
	public static volatile SingularAttribute<Patient, String> address;
	public static volatile SingularAttribute<Patient, String> salt;
	public static volatile SingularAttribute<Patient, Gender> gender;
	public static volatile SingularAttribute<Patient, String> city;
	public static volatile SingularAttribute<Patient, String> mobile;
	public static volatile SingularAttribute<Patient, String> wxId;
	public static volatile SingularAttribute<Patient, String> helpCode;
	public static volatile ListAttribute<Patient, EmrJClassAdviceDict> emrJClassAdviceDicts;
	public static volatile SingularAttribute<Patient, String> uid;
	public static volatile SingularAttribute<Patient, String> password;
	public static volatile SingularAttribute<Patient, String> province;
	public static volatile SingularAttribute<Patient, Integer> areaNo;
	public static volatile ListAttribute<Patient, PatientTag> patientTagList;
	public static volatile SingularAttribute<Patient, Integer> provinceNo;
	public static volatile SingularAttribute<Patient, String> name;
	public static volatile SingularAttribute<Patient, Date> createOn;
	public static volatile SingularAttribute<Patient, String> sfId;
	public static volatile SingularAttribute<Patient, String> udid;
	public static volatile SingularAttribute<Patient, String> email;

}

