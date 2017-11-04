package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Diagnosis.class)
public abstract class Diagnosis_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<Diagnosis, String> departmentName;
	public static volatile SingularAttribute<Diagnosis, String> patientName;
	public static volatile SingularAttribute<Diagnosis, String> doctorName;
	public static volatile SingularAttribute<Diagnosis, Long> doctorId;
	public static volatile SingularAttribute<Diagnosis, Long> departmentId;
	public static volatile SingularAttribute<Diagnosis, String> patientUid;
	public static volatile SingularAttribute<Diagnosis, String> name;
	public static volatile SingularAttribute<Diagnosis, Date> createOn;
	public static volatile SingularAttribute<Diagnosis, Emr> emr;

}

