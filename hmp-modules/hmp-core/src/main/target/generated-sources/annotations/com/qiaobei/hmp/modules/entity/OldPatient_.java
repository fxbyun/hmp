package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.OldPatient.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OldPatient.class)
public abstract class OldPatient_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<OldPatient, Date> birthday;
	public static volatile SingularAttribute<OldPatient, String> cfid;
	public static volatile SingularAttribute<OldPatient, Doctor> doctor;
	public static volatile SingularAttribute<OldPatient, String> address;
	public static volatile SingularAttribute<OldPatient, Gender> gender;
	public static volatile SingularAttribute<OldPatient, Date> createTime;
	public static volatile SingularAttribute<OldPatient, Patient> patient;
	public static volatile SingularAttribute<OldPatient, String> name;
	public static volatile SingularAttribute<OldPatient, String> zhenduan;
	public static volatile ListAttribute<OldPatient, OldPatientBingli> oldPatientBinglis;
	public static volatile SingularAttribute<OldPatient, Status> status;

}

