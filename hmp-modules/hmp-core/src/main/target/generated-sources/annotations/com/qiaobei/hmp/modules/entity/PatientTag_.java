package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PatientTag.class)
public abstract class PatientTag_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<PatientTag, String> doctorName;
	public static volatile SingularAttribute<PatientTag, Long> doctorId;
	public static volatile SingularAttribute<PatientTag, Patient> patient;
	public static volatile SingularAttribute<PatientTag, String> name;
	public static volatile SingularAttribute<PatientTag, Date> createOn;
	public static volatile SingularAttribute<PatientTag, Long> frequency;

}

