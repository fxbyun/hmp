package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DiagnosisTag.class)
public abstract class DiagnosisTag_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<DiagnosisTag, String> departmentName;
	public static volatile SingularAttribute<DiagnosisTag, String> doctorName;
	public static volatile SingularAttribute<DiagnosisTag, String> code;
	public static volatile SingularAttribute<DiagnosisTag, Long> doctorId;
	public static volatile SingularAttribute<DiagnosisTag, Long> departmentId;
	public static volatile SingularAttribute<DiagnosisTag, String> name;
	public static volatile SingularAttribute<DiagnosisTag, Date> createOn;
	public static volatile SingularAttribute<DiagnosisTag, String> helpCode;
	public static volatile SingularAttribute<DiagnosisTag, Long> frequency;

}

